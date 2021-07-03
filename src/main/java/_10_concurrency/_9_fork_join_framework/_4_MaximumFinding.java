package _10_concurrency._9_fork_join_framework;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class _4_MaximumFinding {
    public static void main(String[] args) {

        final int size = 5000;
        final Random random = new Random();

        // generating array
        int[] array = new int[size];
        for (int i = 0; i < size; i++) array[i] = random.nextInt(10000 * size);
        System.out.println("Array generated!\n");

        // Find max num using normal method
        int maxNum = Arrays.stream(array).max().getAsInt();
        System.out.printf("Maximum number using normal way: %d\n\n", maxNum);

        ForkJoinPool pool = new ForkJoinPool(4);
        RecursiveTask<Integer> task = new MaxFinderTask(array, 0, size - 1);

        maxNum = pool.invoke(task);
        System.out.printf("\nMaximum number using RecursiveTask: %d", maxNum);
    }
}

class MaxFinderTask extends RecursiveTask<Integer> {

    private final int[] givenArray;
    private final int low;
    private final int high;

    public MaxFinderTask(int[] givenArray, int low, int high) {
        this.givenArray = givenArray;
        this.low = low;
        this.high = high;
    }

    @Override
    protected Integer compute() {
        int numOfElements = high - low + 1;

        if (numOfElements > 1000) {

            int middle = (low + high) / 2;
            System.out.printf("Since num. of elements = %d, " +
                            "dividing load into two: [%d, %d] and [%d, %d]\n",
                    numOfElements, low, middle, middle + 1, high);

            RecursiveTask<Integer> left = new MaxFinderTask(givenArray, low, middle);
            RecursiveTask<Integer> right = new MaxFinderTask(givenArray, middle + 1, high);

            left.fork();
            right.fork();

            int max = Integer.MIN_VALUE;
            max = Integer.max(max, left.join());
            max = Integer.max(max, right.join());

            return max;

        } else {
            return findMax();
        }
    }

    private int findMax() {
        int max = Integer.MIN_VALUE;
        for (int i = low; i < high + 1; i++) max = Integer.max(max, givenArray[i]);
        return max;
    }
}
