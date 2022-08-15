package tutorial.learn.akka._1_why_concurrent_programming_is_hard;

import java.math.BigInteger;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class _1_SingleThreadedBigPrimes {
    public static void main(String[] args) {

        SortedSet<BigInteger> primes = new TreeSet<>();

        long start = System.currentTimeMillis();

        // this creates a random number: 0 < randNum < 2**2000
        BigInteger integer = new BigInteger(3000, new Random());

        while (primes.size() < 20) {

            // now lets find a prime number bigger than this `integer`
            integer = integer.nextProbablePrime();

            primes.add(integer);
        }

        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + " ms");

    }
}
