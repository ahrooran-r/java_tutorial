package tutorial.learn.akka._5_racing_game_akka;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class MonitorBehavior extends AbstractBehavior<CommonCommand> {


    private final long start = System.currentTimeMillis();

    private final HashMap<String, Double> currentPositions = new HashMap<>(10);
    private final HashMap<String, Long> results = new HashMap<>(10);

    private final HashMap<Integer, ActorRef<CommonCommand>> racers = new HashMap<>(10);

    private MonitorBehavior(ActorContext<CommonCommand> context) {
        super(context);
    }

    public static Behavior<CommonCommand> create() {
        return Behaviors.setup(MonitorBehavior::new);
    }

    private void displayRace(Map<String, Double> currentPositions) {
        for (int i = 0; i < 50; ++i) System.out.println();
        System.out.println("Race has been running for " + ((System.currentTimeMillis() - start) / 1000) + " seconds.");

        int displayLength = 160;

        System.out.println("    " + new String(new char[displayLength]).replace('\0', '='));

        currentPositions.keySet().stream().sorted().forEach(key -> {
            System.out.println(key + " : " + new String(new char[(int) (currentPositions.get(key).intValue() * displayLength / 100)]).replace('\0', '*'));
        });
    }

    @Override
    public Receive<CommonCommand> createReceive() {
        return newReceiveBuilder()

                .onMessage(InstructionCommand.class, message -> {

                    // this means the command comes from `Main`
                    if (message.getInstruction().equals("start")) {

                        // spawn 10 actors and set them to duty
                        for (int i = 0; i < 10; i++) {
                            ActorRef<CommonCommand> racer = this.getContext().spawn(RacerBehavior.create(), "racer_" + i);

                            // add racer actors to hashmap to access them later
                            racers.put(i, racer);

                            currentPositions.put(racer.path().toStringWithoutAddress(), 0D);

                            racer.tell(new RacerBehavior.RaceLengthCommand(this.getContext().getSelf(), 100));
                        }

                        // We can use this instead of Threads.sleep()
                        // What we're doing is to send a message to this actor from this actor (sounds weird!!!)
                        // once this actor receives that message, then this actor can do something with that message
                        // The FUN part is we can define how often to send this self message
                        // So instead of `return this`, we're returning something new
                        return Behaviors.withTimers(timer -> {
                            timer.startTimerAtFixedRate(new GetPosition(), Duration.ofSeconds(1));
                            return this;
                        });

                    }

                    // If message does not contain "start", then just do a normal return
                    return this;

                })

                .onMessage(GetPosition.class, message -> {

                    // whenever I send a GetPosition() message to myself,
                    // I get that reminder and send an AskPosition to all workers

                    if (results.keySet().size() < 10) {
                        // while not finished, ask for positions from child actors

                        // get child  actors from hashmap (hashmap lambda has 2 things in it -> bi-consumer)
                        racers.forEach((id, actor) -> actor.tell(new RacerBehavior.AskPosition(this.getContext().getSelf())));
                    }

                    // Now, this is not an optimum way but this is the best I could come up with right now
                    // Because the default child actor retrieval method returns a Void class

                    /*
                    this.getContext().getChildren().forEach(child -> {
                        child.tell("this is a void class, so I cannot send any message to children");
                    });
                    */

                    return this;
                })

                .onMessage(PositionCommand.class, message -> {

                    // just store the positions
                    currentPositions.put(message.getSender().path().toStringWithoutAddress(), null == message.getCurrentPosition() ? 0D : message.getCurrentPosition());

                    // display how much each racer completed until  final times are received
                    // that is until `results` set is complete
                    if (results.keySet().size() < 10) displayRace(currentPositions);

                    return this;
                })

                .onMessage(ResultCommand.class, message -> {

                    results.put(message.getSender().path().toStringWithoutAddress(), message.getCurrentTimeMillis());

                    if (results.keySet().size() >= 10) {
                        System.out.println("Results");
                        results.values().stream().sorted().forEach(it -> {
                            for (String key : results.keySet()) {
                                if (results.get(key).equals(it)) {
                                    System.out.println("Racer " + key + " finished in " + ((double) it - start) / 1000 + " seconds.");
                                }
                            }
                        });
                    }

                    return this;
                })

                .onMessage(CompletedCommand.class, message -> {

                    // stop the children
                    this.getContext().stop(message.getSender());

                    // In-fact, stopping parent actor will force all child actors to stop as well
                    // We don't need to explicitly stop child actors
                    // But doing so will conserve resources on child actors

                    // If all children are completed
                    if (results.keySet().size() >= 10) {

                        return Behaviors.withTimers(timers -> {

                            // cancel the timers
                            timers.cancelAll();

                            /*
                            stop the monitor itself (self stop)
                            This is not always a good ides
                            When an actor stops, it sends a TerminatedSignal to its parent
                            When we don't watch these actors [see learn.tutorial._9_akka_blockchain_example.ControllerBehavior#startNextWorker()],
                            parent actor simply ignores the signal and carries on
                            But when parent watches the child actors, this will THROW `DeathPact` exception !!!
                            * */
                            return Behaviors.stopped();
                        });
                    }

                    return Behaviors.same();
                })

                .build();
    }

    @Getter
    @AllArgsConstructor
    public static class InstructionCommand implements CommonCommand {
        private final String instruction;
    }

    @Getter
    public static class PositionCommand extends Command {
        private final Double currentPosition;

        public PositionCommand(ActorRef<CommonCommand> sender, double currentPosition) {
            super(sender);
            this.currentPosition = currentPosition;
        }
    }

    @Getter
    public static class ResultCommand extends Command {
        private final long currentTimeMillis;

        public ResultCommand(ActorRef<CommonCommand> sender, long currentTimeMillis) {
            super(sender);
            this.currentTimeMillis = currentTimeMillis;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Command implements CommonCommand {
        private ActorRef<CommonCommand> sender;
    }

    public static class CompletedCommand extends Command {

        public CompletedCommand(ActorRef<CommonCommand> sender) {
            super(sender);
        }
    }

    private static class GetPosition implements CommonCommand {

    }
}
