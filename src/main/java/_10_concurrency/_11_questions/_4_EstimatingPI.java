package _10_concurrency._11_questions;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * PI = 4 * (1 - 1/3 + 1/5 - 1/7 + ... + (-1 ** n) / (2n + 1)
 * Find PI first using single threading
 * and then using multi threading
 */
public class _4_EstimatingPI {
    public static void main(String[] args) {

        long bound = (long) (Long.MAX_VALUE / Math.pow(10, 10));

        System.out.printf("Actual PI:   %.15f\n", Math.PI);

        long t1 = System.currentTimeMillis();
        System.out.printf("Serial PI:   %.15f\n", serialPI(bound));
        long t2 = System.currentTimeMillis();
        System.out.printf("Time taken for serial execution: %d\n\n", t2 - t1);

        System.out.printf("Parallel PI: %.15f\n", parallelPI(bound));
        long t3 = System.currentTimeMillis();
        System.out.printf("Time taken for parallel execution: %d\n\n", t3 - t2);
    }

    /**
     * This is all in one for serialPI method.
     * For parallelization, this can be broken down to two methods
     *
     * @param bound upper bound for n (exclusive)
     * @return PI with serial processing
     */
    private static double serialPI(long bound) {
        double factor = 1, sum = 0;
        for (long n = 0; n < bound; n++, factor = -factor) {
            sum += factor / (2 * n + 1);
        }
        return 4 * sum;
    }

    /**
     * This uses parallel processing with 2 threads where one thread will
     * compute even sum and other will compute odd sum
     */
    @SneakyThrows
    private static double parallelPI(long bound) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // for even n
        Future<Double> x = executor.submit(() -> findPartialSum(bound, 0));

        // for odd n
        Future<Double> y = executor.submit(() -> findPartialSum(bound, 1));

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(1);
        }

        return x.get() + y.get();
    }

    private static double findPartialSum(long bound, long start) {
        double sum = 0;
        for (long n = start; n < bound; n += 2) {
            sum += Math.pow(-1, n) / (2 * n + 1);
        }
        return 4 * sum;
    }
}
