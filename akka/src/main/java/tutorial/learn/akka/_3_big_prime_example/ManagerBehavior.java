package tutorial.learn.akka._3_big_prime_example;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.Duration;
import java.util.SortedSet;
import java.util.TreeSet;

public class ManagerBehavior extends AbstractBehavior<ManagerBehavior.Command> {

    // the original sender
    private ActorRef<SortedSet<BigInteger>> motherOfAll;

    private SortedSet<BigInteger> sortedSet = new TreeSet<>();

    // Stashing messages for later use
    private StashBuffer<Command> stashBuffer;

    private ManagerBehavior(ActorContext<Command> context, StashBuffer<Command> stashBuffer) {
        super(context);
        this.stashBuffer = stashBuffer;
    }

    public static Behavior<Command> create() {
        // This is a little different from normal create() method
        // We are returning the normal Behaviors.setup() within the withinStash() method
        return Behaviors.withStash(
                10,
                stash -> {
                    return Behaviors.setup(context -> {
                        return new ManagerBehavior(context, stash);
                    });
                }
        );
        /*
        now we can do
                stashBuffer.stash(message);
        and
            stashBuffer.unstashAll(someMessageHandler);

        This will stash messages when too much processing going on and then unstash it and send it to a message handler
        when needed

        This can be used when we have to send message to ourselves.
        * */

    }

    /**
     * see Interaction patterns
     * <p>
     * Ask pattern is a bit different from standard {@link ActorRef#tell(Object)}
     */
    private void askForAPrime(ActorRef<WorkerBehavior.Command> workerActor) {
        this.getContext().ask(
                Command.class,
                workerActor,
                Duration.ofSeconds(5),
                me -> {
                    // me -> the sender => me = `this.getContext().getSelf()`
                    return new WorkerBehavior.Command("start", me);
                },
                (response, throwable) -> {

                    if (response != null) {
                        return response;
                        // Since a proper return message is sent back to us,
                        // We just return it so the default mechanism can handle received message as usual
                    } else {

                        // problem is when message is lost in transit or timeout is exceeded
                        // now we need to do something about this
                        this.getContext().getLog().debug("This actor: {} failed to respond", workerActor.path());

                        // return a message to self actor saying worker not responded
                        // we don't need to do `actor.tell()` here -> JUST return the message
                        return new NoResponseFromWorker(workerActor);
                    }
                });
        /*
        1st param: expected result class
        2nd param: who to send this message? -> the receiving actor which is a worker in our case
        3rd param: timeout period
        4th param: the message we want to send -> but we have to wrap it in a lambda
                     this is a single param lambda with sending actor
        5th param: this is a 2 param lambda -> will execute whether we get a response or not
                    this is not the place where we process the return message -> that will happen in the usual place
                    this is where we decide whether to throw error or not
                    if the `response` (which is the returned message) is not null -> then we received a successful message
                    else something went wrong
        * */
    }

    @Override
    public Receive<Command> createReceive() {

        return newReceiveBuilder()

                .onMessage(InstructionCommand.class, command -> {

                    if (command.getMessage().equals("start")) {

                        // first extract the sender and save it
                        motherOfAll = command.getSender();

                        for (int i = 0; i < 20; i++) {

                            ActorRef<WorkerBehavior.Command> childActor =
                                    this.getContext().spawn(
                                            WorkerBehavior.create(),
                                            "worker_" + i
                                    );

                            // instead of using strings, I'm using the `Command` custom message object I created for this
                            // <--------------> This is the tell pattern <-------------->
                            // childActor.tell(new WorkerBehavior.Command("start", this.getContext().getSelf()));

                            // <--------------> Now using the ask pattern <-------------->
                            askForAPrime(childActor);
                        }
                    }

                    return Behaviors.same();

                })

                .onMessage(ResultCommand.class, command -> {
                    sortedSet.add(command.getResult());

                    if (sortedSet.size() >= 20) {

                        // send the primes to Main method
                        motherOfAll.tell(sortedSet);
                    }

                    return Behaviors.same();
                })

                .onMessage(NoResponseFromWorker.class, command -> {
                    this.getContext().getLog().debug("Trying again");

                    // again start from the beginning
                    askForAPrime(command.getWorker());

                    return Behaviors.same();
                })

                .build();
    }

    // If we take a look at manager, it needs to receive 2 types of messages
    // One is of `String` type -> "start" message from `Main` class
    // Other is the `reply` from worker class which is a `BigInteger`
    // Moreover it needs the `address` of worker actors too
    // So we need 2 different messages, but we can only specify one message class in any `Behavior` implementations
    // Solution: have an interface

    public interface Command extends Serializable {
    }

    @Getter
    @AllArgsConstructor
    public static class InstructionCommand implements Command {

        // The type inside doesn't matter to be a Command because the Main method is not really an actor based on behaviors
        // but rather the ActorGuardian (which is the original parent)
        private ActorRef<SortedSet<BigInteger>> sender;

        private String message;
    }

    // similar to WorkerBehavior the message is written as a subclass on receiving behavior
    // for example, message of type `ResultCommand` will be sent from `WorkerBehavior` but we're adding it here. Why ?
    // because `ManagerBehavior` is going to receive the message
    @Getter
    @AllArgsConstructor
    public static class ResultCommand implements Command {
        private BigInteger result;
    }

    @AllArgsConstructor
    @Getter
    private static class NoResponseFromWorker implements Command {
        private ActorRef<WorkerBehavior.Command> worker;
    }
}
