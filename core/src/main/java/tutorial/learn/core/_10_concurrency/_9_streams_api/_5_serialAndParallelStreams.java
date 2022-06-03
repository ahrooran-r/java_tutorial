package tutorial.learn.core._10_concurrency._9_streams_api;

import java.util.stream.IntStream;

public class _5_serialAndParallelStreams {
    public static void main(String[] args) {

        int size = Integer.MAX_VALUE / 100;

        long t1 = System.currentTimeMillis();
        long output = IntStream
                .rangeClosed(2, size)
                .filter(_5_serialAndParallelStreams::isPrime)
                .count();
        long t2 = System.currentTimeMillis();

        System.out.printf("Output: %d, time taken: %d\n", output, t2 - t1);

        t1 = System.currentTimeMillis();
        output = IntStream
                .rangeClosed(2, size)
                .parallel()
                .filter(_5_serialAndParallelStreams::isPrime)
                .count();
        t2 = System.currentTimeMillis();

        System.out.printf("Output: %d, time taken: %d\n", output, t2 - t1);

    }

    public static boolean isPrime(int num) {

        // only positive numbers are prime numbers
        // 2 is smallest prime number
        // even numbers are not prime numbers
        if (num <= 1) return false;
        else if (num == 2) return true;
        else if (num % 2 == 0) return false;

        //we can check numbers in range [0, sqrt(num)]
        long maxDivisor = (long) Math.ceil(Math.sqrt(num));

        // incrementing by 2 because even numbers are not prime
        for (int i = 3; i <= maxDivisor; i += 2) {
            if (num % i == 0) return false;
        }

        return true;
    }
}
