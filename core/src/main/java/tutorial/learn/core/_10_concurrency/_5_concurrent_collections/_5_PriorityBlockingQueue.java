package tutorial.learn.core._10_concurrency._5_concurrent_collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class _5_PriorityBlockingQueue {
    public static void main(String[] args) {

        final BlockingQueue<String> blockingQueue = new PriorityBlockingQueue<>(5);

        QueueWorker worker = new QueueWorker(blockingQueue);

        new Thread(worker::addToQueue).start();
        new Thread(worker::removeFromQueue).start();
    }
}

class QueueWorker {

    private final BlockingQueue<String> blockingQueue;

    public QueueWorker(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void addToQueue() {
        try {
            blockingQueue.put("A");
            blockingQueue.put("C");
            blockingQueue.put("B");
            Thread.sleep(1000);
            blockingQueue.put("D");
            Thread.sleep(1000);
            blockingQueue.put("E");
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void removeFromQueue() {
        try {
            Thread.sleep(5000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
