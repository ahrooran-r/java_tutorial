package _10_concurrency._5_concurrent_collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Keeps the elements in a queue until a certain delay has occurred
 * <p>
 * So an object can only taken from the queue when its delay is expired
 * <p>
 * -> the queue is sorted in a way that the object in the head has a
 * delay that has expired for the longest time
 */
public class _4_DelayQueue {
    public static void main(String[] args) {

        BlockingQueue<DelayedWorker> blockingQueue = new DelayQueue<>();

        try {
            blockingQueue.put(new DelayedWorker(1000, "first"));
            blockingQueue.put(new DelayedWorker(10000, "second"));
            blockingQueue.put(new DelayedWorker(3000, "third"));
            blockingQueue.put(new DelayedWorker(7000, "fourth"));
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        while (!blockingQueue.isEmpty()) {
            try {
                System.out.println(blockingQueue.take().getMessage());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}

class DelayedWorker implements Delayed {

    private long delayDuration;
    private String message;

    public DelayedWorker(long delayDuration, String message) {
        this.delayDuration = delayDuration + System.currentTimeMillis();
        this.message = message;
    }

    public long getDelayDuration() {
        return delayDuration;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delayDuration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * since this method sorts delayed objects, we have to tell
     * java on how to compare delayed objects
     */
    @Override
    public int compareTo(Delayed other) {
        return Long.compare(this.getDelayDuration(), ((DelayedWorker) other).getDelayDuration());
    }
}
