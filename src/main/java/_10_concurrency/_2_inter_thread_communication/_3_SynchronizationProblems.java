package _10_concurrency._2_inter_thread_communication;

public class _3_SynchronizationProblems {

    private static final int time = 1_000_000;

    /*
     * The problem in `synchronized` keyword:
     *   Every object has a single monitor lock
     *
     *   If we have 2 independent synchronized methods,
     *   then threads have to wait for each other to release lock
     *
     * */

    // https://stackoverflow.com/questions/3519664/difference-between-volatile-and-synchronized-in-java

    public static int sum1 = 0;
    public static int sum2 = 0;

    // first try without synchronized and then with synchronized

    public static synchronized void increment1() {
        sum1++;
    }

    // usually it is not good practise to use synchronized keyword WITH methods
    // rule of thumb: we synchronize blocks that are 100% necessary
    public static void increment2() {
        // class level locking
        synchronized (_3_SynchronizationProblems.class) {
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
