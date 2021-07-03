package _10_concurrency._8_parallel_algorithms;

import java.util.Random;

public class _3_ParallelThreadedMergeSort {

    private static final Random random = new Random(System.nanoTime());
    private static final int ARRAY_LENGTH = 100_000;
    private static final int NUM_OF_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

        int[] arrayForSerialMergeSort = new int[ARRAY_LENGTH];
        int[] arrayForParallelMergeSort = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) arrayForSerialMergeSort[i] = random.nextInt(10);

        // copy this into arrayForParallelMergeSort
        System.arraycopy(arrayForSerialMergeSort, 0, arrayForParallelMergeSort, 0, ARRAY_LENGTH);

        System.out.println("Both arrays are ready now");

        // do NORMAL merge sort and calculate the time:
        System.out.println("Started execution");

        long t1 = System.currentTimeMillis();
        mergeSort(arrayForSerialMergeSort, 0, ARRAY_LENGTH - 1);
        long t2 = System.currentTimeMillis();

        System.out.println("Time taken to sort array linearly: " + (t2 - t1));

        // do PARALLEL Merge sort and calculate the time
        t1 = System.currentTimeMillis();
        parallelMergeSort(arrayForParallelMergeSort, 0, ARRAY_LENGTH - 1, NUM_OF_THREADS);
        t2 = System.currentTimeMillis();

        System.out.println("Time taken to sort array parallely: " + (t2 - t1));
    }

    public static void parallelMergeSort(int[] array, int low, int high, int numOfThreads) {

        if (numOfThreads <= 1) mergeSort(array, low, high);

        else {
            int middle = (low + high) / 2;

            Thread leftSorter = new Thread(() -> parallelMergeSort(array, low, middle, numOfThreads / 2));
            Thread rightSorter = new Thread(() -> parallelMergeSort(array, middle + 1, high, numOfThreads / 2));

            leftSorter.start();
            rightSorter.start();

            try {
                leftSorter.join();
                rightSorter.join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            // merge comes after both left and right finish
            merge(array, low, middle, high);
        }
    }

    public static void mergeSort(int[] array, int low, int high) {
        if (low >= high) return;
        int middle = (low + high) / 2;
        mergeSort(array, low, middle);
        mergeSort(array, middle + 1, high);
        merge(array, low, middle, high);
    }

    private static void merge(int[] array, int low, int middle, int high) {

        int[] temp = new int[array.length];
        System.arraycopy(array, low, temp, low, high - low + 1);

        int i = low; // start index of left array
        int j = middle + 1; // start index of right array
        int k = low; // start index of result array which is the original array given as input

        while (i <= middle && j <= high) {
            if (temp[i] <= temp[j]) {
                array[k] = temp[i];
                i++;
            } else if (temp[i] > temp[j]) {
                array[k] = temp[j];
                j++;
            }
            k++;
        }

        // check whether there are any items left in left sub array
        while (i <= middle) {
            array[k] = temp[i];
            i++;
            k++;
        }
        //System.arraycopy(temp, i, array, k, middle - i + 1);
        //k += middle - i + 1;

        // check whether there are any items left in right sub array
        while (j <= high) {
            array[k] = temp[j];
            j++;
            k++;
        }
        //System.arraycopy(temp, j, array, k, high - j + 1);
        //k += high - j + 1;
    }
}
