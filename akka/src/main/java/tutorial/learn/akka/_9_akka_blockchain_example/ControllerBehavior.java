package tutorial.learn.akka._9_akka_blockchain_example;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.SupervisorStrategy;
import akka.actor.typed.Terminated;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import lombok.Getter;
import tutorial.learn.akka._3_big_prime_example.WorkerBehavior;
import tutorial.learn.akka._6_blockchain_core.Block;
import tutorial.learn.akka._6_blockchain_core.BlockChain;
import tutorial.learn.akka._6_blockchain_core.HashResult;

public class ControllerBehavior extends AbstractBehavior<Message> {

    private final BlockChain blockChain = new BlockChain();

    // private final List<ActorRef<Message>> miners = new ArrayList<>();

    private long startTime;

    private ActorRef<Message> originalSender;

    private boolean currentlyMining;

    private Block block;

    private int difficulty;

    private int currentNonce = 0;

    private ControllerBehavior(ActorContext<Message> context) {
        super(context);
    }

    public static Behavior<Message> create() {
        return Behaviors.setup(ControllerBehavior::new);
    }

    @Override
    public Receive<Message> createReceive() {
        return setup();
    }

    /**
     * Setup miners
     */
    public Receive<Message> setup() {
        return newReceiveBuilder()

                .onSignal(Terminated.class, signal -> {

                    // This is where we handle the `child.stopped()` signal
                    // This will work even if the child actor is crashed
                    //      -> by ignoring that child actor and starting another child actor
                    // This is called `Supervision Strategy`

                    startNextWorker();
                    return Behaviors.same();
                })

                .onMessage(Setup.class, message -> {

                    this.originalSender = message.getSender();
                    this.block = message.getBlock();
                    this.difficulty = message.getDifficulty();

                    // start counting since first setup message is arrived
                    startTime = System.currentTimeMillis();

                    // setup miners: for each block there can be many miners
                    for (int i = 0; i < 10; i++) {
                        startNextWorker();
                    }

                    return Behaviors.same();

                })

                .onMessage(HashResultMessage.class, message -> {

                    // first close all other children -> because we don't need them
                    this.getContext().getChildren().forEach(childActor -> this.getContext().stop(childActor));

                    originalSender.tell(message);
                    this.currentlyMining = false;
                    return Behaviors.same();
                })

                .build();
    }

    private void startNextWorker() {

        if (!currentlyMining) return;

        System.out.println("About to start working on nonces starting at " + currentNonce * 10_000);

        // We can do something when the child actor crashes instead of just leaving it in that state
        // We create Behavior object first -> not a reference to actor but the behavior itself
        Behavior<WorkerBehavior.Command> behavior
                = Behaviors.supervise(WorkerBehavior.create())
                .onFailure(SupervisorStrategy.resume());
        // NOTE: We're applying different strategy options on Worker Behavior `itself` -> NOT on the actor
        // So whenever any actor has this behavior, and it crashes, it will automatically re work according to the strategy specified

        // There some built in strategies:
        // 1. resume -> ignore the crash and continue listening for other messages
        // 2. restart -> let the actor die and have a new actor in its place. But new actor will have its own message queue (not the old queue)
        // 3. Restart with backoff

        // create a worker
        ActorRef<Message> newWorker = this.getContext().spawn(MinerBehavior.create(), "worker_" + currentNonce);

        // watch the worker -> if worker stops, controller will know
        this.getContext().watch(newWorker);

        // tell worker what to do
        newWorker.tell(new MinerBehavior.Mine(block, currentNonce * 10_000, difficulty, this.getContext().getSelf()));

        currentNonce++;
    }

    @Getter
    public static class HashResultMessage extends Message {
        private final HashResult hashResult;

        public HashResultMessage(ActorRef<Message> sender, HashResult hashResult) {
            super(sender);
            this.hashResult = hashResult;
        }
    }

    @Getter
    public static class Setup extends Message {
        private final Block block;
        private final int difficulty;

        public Setup(ActorRef<Message> sender, Block block, int difficulty) {
            super(sender);
            this.block = block;
            this.difficulty = difficulty;
        }
    }
}
