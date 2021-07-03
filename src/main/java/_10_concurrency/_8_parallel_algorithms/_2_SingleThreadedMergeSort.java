package _10_concurrency._8_parallel_algorithms;

import java.util.Arrays;
import java.util.Random;

public class _2_SingleThreadedMergeSort {

    private static final Random random = new Random(System.nanoTime());

    public static void main(String[] args) {

        int[] array = new int[15];
        for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10);
        System.out.println(Arrays.toString(array));

        mergeSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

    }

    public static void mergeSort(int[] array, int low, int high) {
        if (low >= high) return;
        int middle = (low + high) / 2;
        mergeSort(array, low, middle);
        mergeSort(array, middle + 1, high);
        merge(array, low, middle, high);
    }

    public static void merge(int[] array, int low, int middle, int high) {

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
