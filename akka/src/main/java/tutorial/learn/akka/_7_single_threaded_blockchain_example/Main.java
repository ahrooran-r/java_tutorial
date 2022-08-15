package tutorial.learn.akka._7_single_threaded_blockchain_example;

import tutorial.learn.akka._6_blockchain_core.Block;
import tutorial.learn.akka._6_blockchain_core.BlockChain;
import tutorial.learn.akka._6_blockchain_core.BlockValidationException;
import tutorial.learn.akka._6_blockchain_core.HashResult;
import tutorial.learn.akka._6_blockchain_core.utils.BlockChainUtils;
import tutorial.learn.akka._6_blockchain_core.utils.BlocksData;

public class Main {

    public static void main(String[] args) throws BlockValidationException {

        int difficultyLevel = 5;

        Long start = System.currentTimeMillis();

        BlockChain blocks = new BlockChain();

        String lastHash = "0";

        for (int i = 0; i < 10; i++) {

            Block nextBlock = BlocksData.getNextBlock(i, lastHash);

            HashResult hashResult = BlockChainUtils.mineBlock(nextBlock, difficultyLevel, 0, 100000000);
            if (hashResult == null) {
                throw new RuntimeException("Didn't find a valid hash for block " + i);
            }

            nextBlock.setHash(hashResult.getHash());
            nextBlock.setNonce(hashResult.getNonce());
            blocks.addBlock(nextBlock);
            System.out.println("Block " + i + " hash : " + nextBlock.getHash());
            System.out.println("Block " + i + " nonce: " + nextBlock.getNonce());
            lastHash = nextBlock.getHash();
        }

        Long end = System.currentTimeMillis();
        blocks.printAndValidate();

        System.out.println("Time taken " + (end - start) + " ms.");
    }
}
