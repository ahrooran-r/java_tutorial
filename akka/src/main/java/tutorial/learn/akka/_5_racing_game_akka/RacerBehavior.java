package tutorial.learn.akka._5_racing_game_akka;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

public class RacerBehavior extends AbstractBehavior<CommonCommand> {

    private final int averageSpeedAdjustmentFactor;
    private final Random random;
    private int raceLength;
    private double currentSpeed = 0;
    private double currentPosition = 0;

    private RacerBehavior(ActorContext<CommonCommand> context) {

        super(context);

        random = new Random();
        averageSpeedAdjustmentFactor = random.nextInt(30) - 10;
    }

    public static Behavior<CommonCommand> create() {
        return Behaviors.setup(RacerBehavior::new);
    }


    @Override
    public Receive<CommonCommand> createReceive() {
        return notYetStarted();
    }

    public Receive<CommonCommand> notYetStarted() {
        return newReceiveBuilder()

                .onMessage(RaceLengthCommand.class, message -> {
                    this.raceLength = message.getRaceLength();

                    return running();
                })

                .build();
    }

    public Receive<CommonCommand> running() {
        return newReceiveBuilder()

                .onMessage(AskPosition.class, message -> {

                    if (currentPosition < raceLength) {

                        determineNextSpeed();
                        currentPosition += getDistanceMovedPerSecond();

                        if (currentPosition > raceLength) currentPosition = raceLength;

                        // send current position to monitor
                        message.getSender().tell(new MonitorBehavior.PositionCommand(this.getContext().getSelf(), currentPosition));

                        // return this => return Behaviors.same() -> both are same
                        // -> but latter is more explanatory
                        return Behaviors.same();

                    } else {

                        // send current time to sender
                        long completedTime = System.currentTimeMillis();
                        message.getSender().tell(new MonitorBehavior.ResultCommand(this.getContext().getSelf(), completedTime));

                        return completed_bestApproach();
                    }

                })

                .build();

    }

    public Receive<CommonCommand> completed_notTheBestApproach(long completedTime) {
        return newReceiveBuilder()

                .onMessage(AskPosition.class, message -> {
                    message.getSender().tell(new MonitorBehavior.ResultCommand(this.getContext().getSelf(), completedTime));

                    // This is not the best way to do this
                    // Because child actors stop themselves, the parent actor will not know about any of this
                    // So there will be `dead letters` from parent to child even after child stops.
                    // A better approach is to stop children from parent actors
                    return Behaviors.stopped();

                    // or we could ignore the receiving messages
                    // this actor will continue to receive messages -> but ignores them
                    // therefore no dead letter warnings will be there
                    // but this is a waste of resources

                    // return Behaviors.ignore();
                })

                .build();

    }

    /**
     * Instead of stopping child actor, it is going to send a completed message.
     * <p>
     * The parent actor will receive this and stops the child actor
     */
    public Receive<CommonCommand> completed_bestApproach() {
        return newReceiveBuilder()

                .onMessage(Command.class, message -> {

                    message.getSender().tell(new MonitorBehavior.CompletedCommand(this.getContext().getSelf()));

                    // When the parent initiates a stop command, it sends a signal to children to stop
                    // We can capture that signal ourselves and do any post-processing (clearing of resources etc.)
                    // Then close the child actor
                    return waitingToStop();

                })

                .build();

    }

    public Receive<CommonCommand> waitingToStop() {
        return newReceiveBuilder()

                .onAnyMessage(message -> Behaviors.same())

                // This signal is sent to child actors prior to stopping
                // This could be used to closing resources
                .onSignal(PostStop.class, signal -> {

                    // using akka logging
                    this.getContext().getLog().info("Prior to termination -> PostStop signal execution");

                    return Behaviors.same();

                })

                .build();
    }

    private double getMaxSpeed() {
        final double defaultAverageSpeed = 48.2;
        return defaultAverageSpeed * (1 + ((double) averageSpeedAdjustmentFactor / 100));
    }

    private double getDistanceMovedPerSecond() {
        return currentSpeed * 1000 / 3600;
    }

    private void determineNextSpeed() {
        if (currentPosition < (raceLength / 4.0)) {
            currentSpeed = currentSpeed + (((getMaxSpeed() - currentSpeed) / 10) * random.nextDouble());
        } else {
            currentSpeed = currentSpeed * (0.5 + random.nextDouble());
        }

        if (currentSpeed > getMaxSpeed())
            currentSpeed = getMaxSpeed();

        if (currentSpeed < 5)
            currentSpeed = 5;

        if (currentPosition > (raceLength / 2.0) && currentSpeed < getMaxSpeed() / 2) {
            currentSpeed = getMaxSpeed() / 2;
        }
    }

    @AllArgsConstructor
    @Getter
    public abstract static class Command implements CommonCommand {
        private ActorRef<CommonCommand> sender;
    }

    @Getter
    public static class AskPosition extends Command {

        public AskPosition(ActorRef<CommonCommand> sender) {
            super(sender);
        }
    }

    @Getter
    public static class RaceLengthCommand extends Command {
        private final int raceLength;

        public RaceLengthCommand(ActorRef<CommonCommand> sender, int raceLength) {
            super(sender);
            this.raceLength = raceLength;
        }
    }
}
