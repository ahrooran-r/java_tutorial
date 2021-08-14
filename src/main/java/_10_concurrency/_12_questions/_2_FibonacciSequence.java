package _10_concurrency._12_questions;

import lombok.SneakyThrows;

public class _2_FibonacciSequence {
    public static void main(String[] args) {

        //for (int i = 1; i <= 10_000; i++) {
        //    System.out.println("i: "+ i + " fib: " + fib(i));
        //}

        int size = 100;

        //long t1 = System.currentTimeMillis();
        //System.out.printf("%dth Fib with fib() is %d\n", size, fib(size));
        //long t2 = System.currentTimeMillis();
        //System.out.println("Time: " + (t2 - t1) + "\n");

        long t3 = System.currentTimeMillis();
        System.out.printf("%dth Fib with fibParallel() is %d\n", size, fibParallel(size, 8));
        long t4 = System.currentTimeMillis();
        System.out.println("Time: " + (t4 - t3) + "\n");
    }

    /**
     * Simple recursive method for fibonacci
     */
    public static long fib(long n) {
        if (n <= 2) return 1;
        else return fib(n - 2) + fib(n - 1);
    }

    /**
     * Parallel recursive method
     */
    @SneakyThrows
    public static long fibParallel(long n, int numOfThreads) {

        if (numOfThreads <= 1 || n <= 45) return fib(n);

        // so I divide the task into two parts:
        // 1. find fib(n-1)
        // 2. find fib(n-2)

        // run first task in new thread
        final long[] out = new long[2];
        long x;
        Thread child = new Thread(() -> out[0] = fibParallel(n - 1, numOfThreads / 2));
        child.start();

        // at the same time run 2nd task in this thread
        out[1] = fibParallel(n - 2, numOfThreads - numOfThreads / 2);

        // then check if the child thread has finished,
        // can't really add value if it does not finish
        child.join();

        // now add the values
        return out[0] + out[1];
    }
}
