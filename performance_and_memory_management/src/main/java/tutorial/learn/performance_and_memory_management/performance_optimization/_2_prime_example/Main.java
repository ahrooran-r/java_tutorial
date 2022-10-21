package tutorial.learn.performance_and_memory_management.performance_optimization._2_prime_example;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        PrimeNumbers primeNumbers = new PrimeNumbers();
        Integer max = Integer.parseInt(args[0]);
        primeNumbers.generateNumbers(max);

        System.out.println("time = " + (System.currentTimeMillis() - start) + " ms");

        // 1131 ms
    }

}
