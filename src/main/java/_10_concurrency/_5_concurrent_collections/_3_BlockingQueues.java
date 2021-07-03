package _10_concurrency._5_concurrent_collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Essentially a queue but concurrent access enabled
 * <p>
 * We can use producer consumer without synchronization enabled on blocking queue
 */
public class _3_BlockingQueues {
    public static void main(String[] args) {

        final Worker worker = new Worker();
        final ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(worker::producer);
        executor.execute(worker::consumer);

        executor.shutdown();
    }
}

class Worker {

    private final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5, true);

    public void producer() {
        int counter = 0;
        try {
            while (true) {
                blockingQueue.put(++counter);
                System.out.println("inserting " + counter + " into queue");
                Thread.sleep(1000);
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void consumer() {
        try {
            while (true) {
                int out = blockingQueue.take();
                System.out.println("taking element " + out + " from queue");
                Thread.sleep(1000);
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}