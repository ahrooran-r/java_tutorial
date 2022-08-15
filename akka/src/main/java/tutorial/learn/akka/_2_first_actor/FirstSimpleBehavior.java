package tutorial.learn.akka._2_first_actor;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class FirstSimpleBehavior extends AbstractBehavior<String> {

    // problem with this constructor is that we don't have an `ActorContext` object first
    // But that context is available through a setup method akka offers
    // So we're going to make this constructor private and then have a static method
    private FirstSimpleBehavior(ActorContext<String> context) {
        super(context);
    }

    /**
     * Instead of creating new object like FirstSimpleBehavior behavior = new FirstSimpleBehavior(...),
     * I'm using a static method which does it for me.
     * <p>
     * The reason is as said above, Normally I don't have access to `context`
     */
    public static Behavior<String> create() {

        return Behaviors.setup(FirstSimpleBehavior::new);

        // Following is the actual code -> above is heavily simplified
        /*
        return Behaviors.setup(context -> {
           return new FirstSimpleBehavior(context);
        });
        */
    }

    /**
     * How an actor responds to a message.
     * Invoked when a message is received. Like onMessage() in JMS.
     */
    @Override
    public Receive<String> createReceive() {

        return newReceiveBuilder()

                // I can chain many of them as I like
                // But if the above method matches -> then bellow methods will not run
                .onMessageEquals("say hello", () -> {
                    System.out.println("Hello");
                    return this;
                })

                .onMessageEquals("who are you?", () -> {
                    // context() has everything
                    // so first call the respective context and from that can call the actor who's receiving the method
                    // path -> returns full path of actor
                    // name -> returns the name
                    System.out.println("My name is: " + this.getContext().getSelf().path());
                    return this;
                })

                // we want to create a new actor
                .onMessageEquals("create child", () -> {

                    // This is similar to:
                    // ActorSystem.create(...) -> same params: a `behavior` and a `name`
                    // Although these actors share same behavior class, they will never share the same object (a new one created for every actor)
                    // This makes these actors extremely thread safe (see _3_What is an actor.txt)
                    ActorRef<String> secondActor = this.getContext().spawn(FirstSimpleBehavior.create(), "actor_2");
                    // This is a reference to Actor hence the name `ActorRef`
                    // This is actually the most we work with
                    // `ActorSystem` has everything in `ActorRef` and more

                    secondActor.tell("who are you?");

                    return this;
                })

                .onAnyMessage(message -> {
                    System.out.println("Received message: " + message);
                    return this;
                })

                // builds a receiver with all parameters I have given above
                .build();
    }
}
