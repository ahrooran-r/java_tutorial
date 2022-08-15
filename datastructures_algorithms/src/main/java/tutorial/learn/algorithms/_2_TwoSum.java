package tutorial.learn.algorithms;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/two-sum/">https://leetcode.com/problems/two-sum/</a>
 */
public class _2_TwoSum {
    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        _2_TwoSum question = new _2_TwoSum();

        int[] result = question.twoSum(nums, target);
        System.out.println(Arrays.toString(result));

    }

    /**
     * Works if array is sorted <- but array in question is not sorted
     */
    public int[] twoSumIfArrayIsSorted(int[] nums, int target) {

        int i = 0;
        int j = 1;
        int upper = nums.length - 1;

        while (true) {

            if (nums[i] + nums[j] == target) return new int[]{i, j};

            else if (nums[i] + nums[j] < target) {
                if (j < upper) j++;
                else {
                    i++;
                    j = i + 1;
                }

            } else if (nums[i] + nums[j] > target) {
                upper = j;
                i++;
                j++;
            }
        }
    }

    /**
     * Assuming there is always going to be a result, I'm returning `null`
     */
    public int[] twoSum(int[] nums, int target) {

        int i = 0;

        // check sum for consecutive elements <- because this may work with O(n)
        while (i < nums.length - 1) {
            if (nums[i] + nums[i + 1] == target) return new int[]{i, i + 1};
            i++;
        }

        // else go for brute force which takes O(n^2)
        for (int j = 0; j < nums.length; j++) {
            for (int k = 0; k < nums.length; k++) {
                if (j != k && nums[j] + nums[k] == target) return new int[]{j, k};
            }
        }

        return null;
    }
}
