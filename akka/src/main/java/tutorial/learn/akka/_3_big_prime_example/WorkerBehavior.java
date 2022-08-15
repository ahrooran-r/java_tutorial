package tutorial.learn.akka._3_big_prime_example;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

public class WorkerBehavior extends AbstractBehavior<WorkerBehavior.Command> {

    // private BigInteger result;

    // DO NOT have class level parameters (like above) if they are state local <- not good practise.
    // Instead, pass them around through different states
    // See below

    private WorkerBehavior(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<Command> create() {
        return Behaviors.setup(WorkerBehavior::new);
    }

    @Override
    public Receive<Command> createReceive() {

        // This is the default behavior method
        // Inside this we say to use our custom behaviour as a starting point

        return handleMessagesWhenWeYetDontHaveAPrimeNumber();

        // Look at explanation on this first: `handleMessagesWhenWeYetDontHaveAPrimeNumber()`
        // Now we have divided this into 3 states
        // 1. Default state
        // 2. No prime number state
        // 3. prime number exists state
        // This is a standard production way of programming different states
        // Here states -> different behavior methods
    }

    public Receive<Command> handleMessagesWhenWeYetDontHaveAPrimeNumber() {

        return newReceiveBuilder()

                .onAnyMessage(message -> {
                    // because this is a custom message object and not a string,
                    // we cannot use `.onMessageEquals()`

                    if (message.getMessage().equals("start")) {
                        BigInteger integer = new BigInteger(3000, new Random());
                        BigInteger result = integer.nextProbablePrime();

                        // worker returns result to manager
                        // it's all SAME method
                        message.getSender().tell(new ManagerBehavior.ResultCommand(result));

                        return handleMessagesWhenWeHaveAPrimeNumber(result);
                    }

                    return this;
                })

                /*
                    about `return this;`

                    whenever a message is received, we do some processing to it and then return a Behavior.
                    The concept is that `behaviors change over time`.
                    "the returned behavior is used to process next message"

                    Consider this situation: right now whenever a "start" message is received,
                    this method calculates a BigInteger and sends it to Manager actor

                    But what if we want to only calculate for a first time and then if Manager actor asks for a
                    second time, just return the already calculated answer

                    One way to do this is through an if...else statement by storing the value once calculated and
                    simply returning it whenever required again

                    Second way is by manipulating the `return this` statement. This is much easier.

                    Until now, we're returning the same behavior when the message is received.
                    In-fact,
                        `return this;`
                            is a shortcut for
                        `return Behaviors.same();`
                    We're saying the actor to carry on the exact same behavior at the end of having processed this message

                    Now we are going to simply tell the actor,
                    "Hey now that you have processed this message, you can use another behavior!"
                 * */


                .build();
    }

    public Receive<Command> handleMessagesWhenWeHaveAPrimeNumber(BigInteger result) {

        return newReceiveBuilder()

                .onAnyMessage(message -> {

                    if (message.getMessage().equals("start")) {
                        message.getSender().tell(new ManagerBehavior.ResultCommand(result));
                    }

                    // Once I switch behavior from `createReceive()` to `subsequentReceive()`,
                    // I don't have to change behavior anymore
                    // So I return this behavior again and again if Manager asks for answer
                    return this;
                    // here `return this;` means `return subsequentReceive();`
                })

                .build();
    }


    // In first example, we used String as a message type. But what if we want to send multiple forms of data in a single message
    // This is where custom messages' comes in: we create them ourselves
    // Any class can be used as a message if it is serializable
    // Hence we can create a message class referred to as `Command` in Akka terms
    // By convention, this class is usually created into the recipient behavior class -> in this case `WorkerBehavior`
    @Getter
    @AllArgsConstructor
    public static class Command implements Serializable {
        // we need to make this static so this is accessible to other classes in the public

        String message;

        // The sender (which is the actor that implements `ManagerBehavior`) sends ManagerBehavior.Command type messages
        // So here the type of sender is still `String`
        ActorRef<ManagerBehavior.Command> sender;

        // no need for setters

    }
}
