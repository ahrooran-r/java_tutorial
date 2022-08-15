package tutorial.learn.akka._2_first_actor;

import akka.actor.typed.ActorSystem;

public class Main {
    public static void main(String[] args) {

        // ActorSystem is indeed an actor with some extra features
        // Think of this as having a runnable method with a thread name in traditional Threads
        ActorSystem<String> actorSystem = ActorSystem.create(FirstSimpleBehavior.create(), "actor_1");

        actorSystem.tell("say hello");
        actorSystem.tell("who are you?");

        actorSystem.tell("create child");
        actorSystem.tell("blah blah blah");

    }
}
