package tutorial.learn.algorithms;

import java.util.Arrays;

/**
 * Problem: Rotate an array of n elements to the right by k steps. For example, with n
 * = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * How many ways do you know to solve this problem?
 */
public class _11_RotateArray {

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 7};
        // int[] array = {1, 2, 3};
        int k = 3;

        var obj = new _11_RotateArray();
        obj.bubbleRotate(array, k);
        System.out.println(Arrays.toString(array));


    }

    /**
     * Out of place switch
     * <p>
     * create a new array and then copy elements to the new array. Then change the original array
     * <p>
     * space complexity: O(n)
     * <p>
     * time complexity: O(n)
     */
    private void rotate_1(int[] nums, int k) {
        k = k % nums.length;
        int[] out = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            out[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(out, 0, nums, 0, nums.length);
    }

    /**
     * Instead of creating a new array of same size as original array, just create empty array of size k.
     * <p>
     * Then copy bleeding elements into the new array.
     * <p>
     * Then copy everything back to the original array.
     * <p>
     * space complexity: O(k) < O(n) mostly k <<< n
     * <p>
     * time complexity: O(n)
     */
    private void rotate_2(int[] nums, int k) {
        k = k % nums.length;
        int[] out = new int[k];
        // running in reverse
        for (int i = nums.length - 1; i >= 0; i--) {
            if ((i + k) > nums.length - 1) out[(i + k) % nums.length] = nums[i];
            else nums[i + k] = nums[i];
        }
        System.arraycopy(out, 0, nums, 0, out.length);
    }

    /**
     * Use same approach as bubble sort to do in-place movement.
     * <p>
     * For every increment in k, I have to bring last element to the first place
     * <p>
     * space complexity: O(1)
     * <p>
     * time complexity: O(n*k)
     */
    private void bubbleRotate(int[] nums, int k) {
        k = k % nums.length;
        for (int i = 0; i < k; i++) {
            for (int j = nums.length - 1; j > 0; j--) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
            }
        }
    }
}
