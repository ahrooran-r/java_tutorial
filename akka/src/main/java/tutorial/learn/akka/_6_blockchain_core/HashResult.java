package tutorial.learn.akka._6_blockchain_core;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class HashResult {

    private int nonce;

    private String hash;

    private boolean complete = false;

    public synchronized void foundAHash(String hash, int nonce) {
        this.hash = hash;
        this.nonce = nonce;
        this.complete = true;
    }

    @Override
    public String toString() {
        return "HashResult{" +
                "nonce=" + nonce +
                ", hash='" + hash + '\'' +
                '}';
    }
}
