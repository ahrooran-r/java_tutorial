package _10_concurrency._2_inter_thread_communication;

import lombok.SneakyThrows;

public class _2_Synchronization {

    public static int sum = 0;

    // only sum++ part needs to be synchronized
    // we can make sure this method is executed by single thread at same time
    public static synchronized void increment() {
        sum++;
    }

    /*
     * How `synchronized` keyword works?
     *
     *   Every object in java has an intrinsic lock
     *
     *   A thread that needs exclusive and consistent access to an object's fields
     *   has to acquire the `object's intrinsic lock` before accessing them, and then
     *   release the intrinsic lock when it's done with them
     *
     *   Because of the lock, no two threads can execute same synchronized method at same time
     *
     * */

    @SneakyThrows
    public static int process() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return sum;
    }

    public static void reset() {
        sum = 0;
    }

    public static void main(String[] args) {
        int iter = 0;
        while (true) {
            iter++;
            int result = process();
            if (result == 200) reset();
            else {
                System.out.printf("Sum is: %d, num. of iterations taken is: %d", sum, iter);
                return;
            }
        }

    }
}
