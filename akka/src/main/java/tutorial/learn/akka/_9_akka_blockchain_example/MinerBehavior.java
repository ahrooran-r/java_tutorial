package tutorial.learn.akka._9_akka_blockchain_example;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import lombok.Getter;
import tutorial.learn.akka._6_blockchain_core.Block;
import tutorial.learn.akka._6_blockchain_core.HashResult;
import tutorial.learn.akka._6_blockchain_core.utils.BlockChainUtils;

public class MinerBehavior extends AbstractBehavior<Message> {

    private MinerBehavior(ActorContext<Message> context) {
        super(context);
    }

    public static Behavior<Message> create() {
        return Behaviors.setup(MinerBehavior::new);
    }

    @Override
    public Receive<Message> createReceive() {
        return mine();
    }

    public Receive<Message> mine() {
        return newReceiveBuilder()

                .onMessage(Mine.class, message -> {

                    HashResult hashResult = BlockChainUtils.mineBlock(
                            message.getBlock(),
                            message.getDifficultyLevel(),
                            message.getStartNonce(),
                            message.getStartNonce() + 1000);

                    if (hashResult != null) {

                        hashResult.foundAHash(hashResult.getHash(), hashResult.getNonce());

                        // Encapsulate hash result in a message and send it to Controller
                        ControllerBehavior.HashResultMessage hashResultMessage
                                = new ControllerBehavior.HashResultMessage(
                                this.getContext().getSelf(),
                                hashResult
                        );

                        message.getSender().tell(hashResultMessage);

                        return Behaviors.same();

                    } else {

                        this.getContext().getLog().debug(LogMessages.FAILED_TO_FIND_HASH.getCorrespondingString());

                        // If we cannot find a hash, we're going to shut this actor down
                        // That will be picked up by the supervisor and that will create a new actor
                        return Behaviors.stopped();
                    }
                })

                .build();
    }

    @Getter
    public static class Mine extends Message {
        private final Block block;
        private final int startNonce;
        private final int difficultyLevel;

        public Mine(Block block, int startNonce, int difficultyLevel, ActorRef<Message> sender) {
            super(sender);
            this.block = block;
            this.startNonce = startNonce;
            this.difficultyLevel = difficultyLevel;
        }
    }
}
