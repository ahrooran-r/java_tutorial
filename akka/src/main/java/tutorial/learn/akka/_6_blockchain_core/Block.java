package tutorial.learn.akka._6_blockchain_core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Block {

    private Transaction transaction;

    private int nonce;

    private String hash;

    @Setter(AccessLevel.PRIVATE)
    private String previousHash;

    public Block(Transaction transaction, String previousHash) {
        this.transaction = transaction;
        this.previousHash = previousHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return hash.equals(block.hash);
    }
}
