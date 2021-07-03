package _10_concurrency._2_inter_thread_communication;

/**
 * How can we solve problem in _3...
 * We create another dummy object as a lock
 */
public class _4_LockingWithCustomObjects {

    private static final int time = 1_000_000;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static int sum1 = 0;
    public static int sum2 = 0;

    // with this two threads can run at the same time
    // NOTE: same time != parallel execution -> time slicing is happening under the hood
    public static void increment1() {
        synchronized (lock1) {
            sum1++;
        }
    }

    public static void increment2() {
        synchronized (lock2) {
            sum2++;
        }
    }

    public static void process() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < time; i++) increment1();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < time; i++) increment2();
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public static void reset() {
        sum1 = 0;
        sum2 = 0;
    }

    public static void main(String[] args) {
        long t1 = System.nanoTime();
        process();
        long t2 = System.nanoTime();
        if (sum1 == time && sum2 == time) {
            System.out.printf("Sums are: %d, %d and time taken is: %d", sum1, sum2, t2 - t1);

            // you can see big difference in time taken to complete
            // synchronized implementation is much slower
        }
    }
}
