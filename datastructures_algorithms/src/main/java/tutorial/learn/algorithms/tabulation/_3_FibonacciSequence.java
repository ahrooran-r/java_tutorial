package tutorial.learn.algorithms.tabulation;

import java.util.Arrays;

public class _3_FibonacciSequence {

    public static void main(String[] args) {

        var obj = new _3_FibonacciSequence();

        long t1 = System.currentTimeMillis();
        long result = obj.fib(50);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));

        // t1 = System.currentTimeMillis();
        // result = obj.fib(50);
        // System.out.println(result);
        // System.out.println("Time taken: " + (System.currentTimeMillis() - t1));
    }

    private long fib(int n) {

        // 1. create a table -> size = n+1 because we need to include 0 here
        long[] table = new long[n + 1];

        // 2. Fill the table with 0 -> because we are going to add, so 0 is a good starting point
        // If we are going to multiply then use 1 <- the filled value differs as per the problem
        Arrays.fill(table, 0);

        // 3. For fibonacci number -> i0 = 0, i1 = 1, i2 = 1, i3 = 2 ...
        // We need to fill table[1] with 1 -> rest can be filled by adding previous 2
        table[1] = 1;

        // fill rest of the table
        for (int i = 2; i < table.length; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }

        return table[n];
    }
}
