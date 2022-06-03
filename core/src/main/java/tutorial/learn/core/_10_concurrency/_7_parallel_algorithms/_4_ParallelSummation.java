package tutorial.learn.core._10_concurrency._7_parallel_algorithms;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

class SequentialSum {
    public static long sum(int[] numbers, int low, int high) {
        long total = 0;
        for (int i = low; i <= high; i++) total += numbers[i];
        return total;
    }
}

class ParallelSum {

    private static final AtomicLong finalSum = new AtomicLong(0);

    public static void parallelSum(int[] numbers, int low, int high, int threads) {

        if (threads <= 1) finalSum.addAndGet(SequentialSum.sum(numbers, low, high));
        else {
            int middle = (low + high) / 2;

            int accessibleThreads = threads / 2;
            Thread left = new Thread(() -> parallelSum(numbers, low, middle, accessibleThreads));

            // the reason for subtracting accessibleThreads is this:
            // Using both times threads / 2 may not use all possible threads as it rounds down
            // Instead I would calculate the first t1 = threads / 2 and t2 = threads - t1
            Thread right = new Thread(() -> parallelSum(numbers, middle + 1, high, threads - accessibleThreads));

            left.start();
            right.start();

            try {
                // although there are no code to be ran after starting the above threads,
                // I need to make this thread until both left and right complete
                // because only then I can make sure this method completely executed
                // otherwise, this method would return once two threads are started
                // then the main() method, would execute completely and we would not get total sum
                left.join();
                right.join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static long getOutput() {
        return finalSum.get();
    }
}

public class _4_ParallelSummation {

    private static final int NUM_OF_THREADS = Runtime.getRuntime().availableProcessors();
    private static final Random random = new Random();
    private static final int ARRAY_LENGTH = 500_000_000;

    public static void main(String[] args) throws InterruptedException {

        int[] numbers = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) numbers[i] = random.nextInt(10);


        long t1 = System.currentTimeMillis();
        long sum = SequentialSum.sum(numbers, 0, ARRAY_LENGTH - 1);
        long t2 = System.currentTimeMillis();
        System.out.printf("Sum is: %d, completed in %d ms\n", sum, t2 - t1);

        t1 = System.currentTimeMillis();

        // if I don't join the left and right threads,
        // this method would return once the sub threads are started,
        // and give inaccurate results!!!
        ParallelSum.parallelSum(numbers, 0, ARRAY_LENGTH - 1, NUM_OF_THREADS);
        t2 = System.currentTimeMillis();

        System.out.printf("Sum is: %d, completed in %d ms\n", ParallelSum.getOutput(), t2 - t1);
    }
}
