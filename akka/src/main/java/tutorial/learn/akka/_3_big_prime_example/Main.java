package tutorial.learn.akka._3_big_prime_example;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.AskPattern;

import java.math.BigInteger;
import java.time.Duration;
import java.util.SortedSet;
import java.util.concurrent.CompletionStage;

public class Main {
    public static void main(String[] args) {

        ActorSystem<ManagerBehavior.Command> manager = ActorSystem.create(ManagerBehavior.create(), "manager");

        // Instead of using context -> we're using AskPattern
        CompletionStage<SortedSet<BigInteger>> result = AskPattern.ask(
                manager,
                me -> new ManagerBehavior.InstructionCommand(me, "start"),
                Duration.ofSeconds(20),
                manager.scheduler()
        );

        /*
        1st param: ActorSystem
        2nd param: message wrapped in lambda
        3rd param: duration
        4th param: scheduler -> one already available with ActorSystem
        * */

        result.whenComplete((reply, failure) -> {
            if (reply != null) {

                // When finished: print the results altogether
                // reply is the sortedSet
                reply.forEach(System.out::println);

            } else {
                manager.log().debug("No response!");
            }
            manager.terminate();
        });
    }
}
