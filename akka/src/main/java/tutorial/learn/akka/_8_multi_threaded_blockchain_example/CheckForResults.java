package tutorial.learn.akka._8_multi_threaded_blockchain_example;

import lombok.AllArgsConstructor;
import tutorial.learn.akka._6_blockchain_core.HashResult;

@AllArgsConstructor
public class CheckForResults implements Runnable {

    private HashResult hashResult;

    @Override
    public void run() {
        while (!hashResult.isComplete()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
