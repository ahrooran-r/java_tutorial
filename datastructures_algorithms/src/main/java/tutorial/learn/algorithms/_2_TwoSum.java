package tutorial.learn.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/two-sum/">https://leetcode.com/problems/two-sum/</a>
 */
public class _2_TwoSum {
    public static void main(String[] args) {

        int[] nums = {3, 2, 4};
        int target = 6;

        _2_TwoSum question = new _2_TwoSum();

        int[] result = question.twoSumWithSpace(nums, target);
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
                // sorted asc
                if (j < upper) j++;
                else {
                    i++;
                    j = i + 1;
                }

            } else if (nums[i] + nums[j] > target) {
                // sorted desc
                upper = j;
                i++;
                j++;
            }
        }
    }

    /**
     * uses map to store numbers. -> O(n) speed, O(n) space
     */
    public int[] twoSumWithSpace(int[] nums, int target) {
        Map<Integer, Integer> compliments = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int compliment = target - nums[i];
            if (compliment >= 0) compliments.put(compliment, i);

            Integer get = compliments.get(nums[i]);
            if (null != get) {
                int[] result = i <= get ? new int[]{i, get} : new int[]{get, i};
                return result;
            }
        }

        return null;
    }

    /**
     * Assuming there is always going to be a result, I'm returning `null`
     */
    public int[] twoSumWithoutSpace(int[] nums, int target) {

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
