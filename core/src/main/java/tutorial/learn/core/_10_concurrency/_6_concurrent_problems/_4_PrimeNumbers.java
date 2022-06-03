package tutorial.learn.core._10_concurrency._6_concurrent_problems;

import org.apache.commons.math3.primes.Primes;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _4_PrimeNumbers {
    public static void main(String[] args) {

        //final int RANGE = (int) Math.pow(10, 2);
        //System.out.println(printPrimes(RANGE));

        ExecutorService service = Executors.newFixedThreadPool(6);
        for (int i = 1; i <= 6; i++) {
            int finalI = i;
            service.execute(() -> printPrimesOld(finalI, 10, 6));
        }
    }

    public static List<Integer> printPrimes(int range) {
        return IntStream
                .rangeClosed(0, range)
                .parallel()
                .filter(Primes::isPrime)
                .boxed()
                // The boxed() method of the IntStream class returns a Stream
                // consisting of the elements of this stream, each boxed to an Integer.
                .collect(Collectors.toList());
    }

    public static void printPrimesOld(int threadId, int base, int power) {
        int amount = (int) Math.pow(base, power - 1);
        for (int i = threadId * amount; i < (threadId + 1) * amount; i++) {
            if (Primes.isPrime(i)) System.out.printf("ThreadID: %d, primeNum: %d\n", threadId, i);
        }
    }
}
