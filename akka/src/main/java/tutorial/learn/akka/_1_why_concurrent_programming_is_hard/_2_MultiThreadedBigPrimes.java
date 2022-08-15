package tutorial.learn.akka._1_why_concurrent_programming_is_hard;

import java.math.BigInteger;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Going to execute same program: {@link _1_SingleThreadedBigPrimes},
 * in parallel way
 */
public class _2_MultiThreadedBigPrimes {

    private static BigInteger integer = new BigInteger(1000, new Random());

    private static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {

        int THREADS = Runtime.getRuntime().availableProcessors();

        // About concurrent set: https://stackoverflow.com/a/38251748/10582056
        // This is not thread safe
        SortedSet<BigInteger> primes = new TreeSet<>();

        ExecutorService executors = Executors.newFixedThreadPool(THREADS);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 20; i++) {
            executors.execute(() -> {
                lock.lock();
                integer = integer.nextProbablePrime();
                primes.add(integer);
                lock.unlock();
            });
        }

        executors.shutdown();

        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + " ms");
    }
}
