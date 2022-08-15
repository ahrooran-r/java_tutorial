package tutorial.learn.akka._8_multi_threaded_blockchain_example;

import lombok.AllArgsConstructor;
import tutorial.learn.akka._6_blockchain_core.Block;
import tutorial.learn.akka._6_blockchain_core.HashResult;
import tutorial.learn.akka._6_blockchain_core.utils.BlockChainUtils;

@AllArgsConstructor
public class BlockMiner implements Runnable {

    private Block block;
    private int firstNonce;
    private HashResult hashResult;
    private int difficultyLevel;

    @Override
    public void run() {
        HashResult hashResult = BlockChainUtils.mineBlock(block, difficultyLevel, firstNonce, firstNonce + 1000);
        if (hashResult != null) {
            this.hashResult.foundAHash(hashResult.getHash(), hashResult.getNonce());
        }
    }
}
