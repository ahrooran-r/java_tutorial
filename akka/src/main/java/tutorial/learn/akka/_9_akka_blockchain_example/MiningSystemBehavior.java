package tutorial.learn.akka._9_akka_blockchain_example;

import akka.actor.typed.ActorRef;
import akka.actor.typed.SupervisorStrategy;
import akka.actor.typed.javadsl.*;

public class MiningSystemBehavior extends AbstractBehavior<Message> {

    private PoolRouter<Message> managerPoolRouter;

    // This is a pool of managers even though this is defined as a single actor-ref
    private ActorRef<Message> managers;

    private MiningSystemBehavior(ActorContext<Message> context) {
        super(context);

        // create a simple pool router
        // But what if one controller / manager in this pool crashes -> need crash mitigation
        // So best practise is to include crash resistance

        /*
        managerPoolRouter = Routers.pool(
                3,
                ControllerBehavior.create());
        */

        // The `automatic forwarding` works by round-robin method by default
        // 1st message to 1st manager, ... etc
        // There is another way which is random routing which we have to explictly set
        managerPoolRouter = Routers.pool(
                3,
                // including crash resistance
                Behaviors.supervise(ControllerBehavior.create()).onFailure(SupervisorStrategy.restart())
        ).withRoundRobinRouting();

        // now fill pool of managers
        managers = this.getContext().spawn(managerPoolRouter, "managerPool");
    }

    @Override
    public Receive<Message> createReceive() {
        return newReceiveBuilder()

                .onAnyMessage(message -> {

                    // because managers are managed in a pool,
                    // we just have to send this to a pool, and pool will automatically forward that to one of the managers

                    managers.tell(message);
                    return Behaviors.same();
                })

                .build();
    }
}
