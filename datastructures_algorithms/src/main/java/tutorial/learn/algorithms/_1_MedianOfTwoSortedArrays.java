package tutorial.learn.algorithms;

/**
 * <a href = "https://leetcode.com/problems/median-of-two-sorted-arrays/">https://leetcode.com/problems/median-of-two-sorted-arrays/</a>
 */
public class _1_MedianOfTwoSortedArrays {

    public static void main(String[] args) {

        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        _1_MedianOfTwoSortedArrays question = new _1_MedianOfTwoSortedArrays();

        double median = question.findMedianSortedArrays(nums1, nums2);

        System.out.println(median);

    }

    /**
     * Exact merge algorithm used in merge sort -> because the arrays are already sorted, this can be used here
     */
    private int[] merge(int[] nums1, int[] nums2) {

        int[] mergedArray = new int[nums1.length + nums2.length];

        // i -> pointer for nums1
        // j -> pointer for nums2
        // k -> pointer for result
        int i = 0, j = 0, k = 0;

        // first copy elements such that they go from minimum to maximum in both arrays
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) mergedArray[k++] = nums1[i++];
            else if (nums1[i] > nums2[j]) mergedArray[k++] = nums2[j++];
        }

        // copy remaining elements in nums1 if any
        System.arraycopy(nums1, i, mergedArray, k, nums1.length - i);

        // copy remaining elements in nums2 if any
        System.arraycopy(nums2, j, mergedArray, k, nums2.length - j);

        return mergedArray;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] mergedArray = merge(nums1, nums2);
        double median;

        int half = mergedArray.length / 2;

        // if mergedArray is even...
        if (mergedArray.length % 2 == 0) median = ((double) mergedArray[half] + (double) mergedArray[half - 1]) / 2;

            // if mergedArray is odd...
        else median = mergedArray[half];

        return median;
    }
}
