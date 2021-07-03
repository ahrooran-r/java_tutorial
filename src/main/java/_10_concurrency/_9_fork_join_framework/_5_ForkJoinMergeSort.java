package _10_concurrency._9_fork_join_framework;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class _5_ForkJoinMergeSort {

    private static final Random random = new Random(System.nanoTime());

    public static void main(String[] args) {

        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) array[i] = random.nextInt(100);
        System.out.println(Arrays.toString(array));

        ForkJoinPool pool = new ForkJoinPool();
        RecursiveAction mergeSort = new RecursiveActionMergeSort(array);
        pool.invoke(mergeSort);

        // now array is sorted
        System.out.println(Arrays.toString(array));

    }
}

class RecursiveActionMergeSort extends RecursiveAction {

    private static final int THRESHOLD = 10;
    private int[] array;

    public RecursiveActionMergeSort(int[] array) {
        this.array = array;
    }

    @Override
    protected void compute() {

        if (array.length >= THRESHOLD) mergeSort(array);

        else {

            int middle = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, middle);
            System.out.println(Arrays.toString(left));
            int[] right = Arrays.copyOfRange(array, middle, array.length);
            System.out.println(Arrays.toString(right));

            RecursiveAction leftAction = new RecursiveActionMergeSort(left);
            RecursiveAction rightAction = new RecursiveActionMergeSort(right);

            // it is a combination of fork() and join()
            invokeAll(leftAction, rightAction);

            // now both `left` and `right` are SORTED!!!
            // so again merge them
            merge(left, right, array);
        }
    }

    private void mergeSort(int[] array) {

        if (array.length <= 1) return;

        int middle = array.length / 2;

        int[] leftArray = Arrays.copyOfRange(array, 0, middle);
        int[] rightArray = Arrays.copyOfRange(array, middle, array.length);

        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }

    private void merge(int[] leftArray, int[] rightArray, int[] array) {

        int i = 0; // start index of left array
        int j = 0; // start index of right array
        int k = 0; // start index of result array which is the original array given as input

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) array[k++] = leftArray[i++];
            else array[k++] = rightArray[j++];
        }

        if (i < leftArray.length) {
            System.arraycopy(leftArray, i, array, k, leftArray.length - i);
            k += leftArray.length - i;
        }

        if (j < rightArray.length) System.arraycopy(rightArray, j, array, k, rightArray.length - j);
    }
}
