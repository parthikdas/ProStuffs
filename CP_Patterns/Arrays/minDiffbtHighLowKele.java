package CP_Patterns.Arrays;

import java.util.Arrays;

// 1984. Minimum Difference Between Highest and Lowest of K Scores
public class minDiffbtHighLowKele {
    public int minimumDifference(int[] nums, int k) {
        // If k is 1, the minimum difference is always 0 since there is only one score.
        if (k == 1) {
            return 0;
        }
        
        // Sort the array to easily access the smallest differences
        Arrays.sort(nums);
        
        // Variable to track the minimum difference
        int minDiff = Integer.MAX_VALUE;
        
        // Sliding window approach: check each window of size k
        for (int i = 0; i <= nums.length - k; i++) {
            // Calculate the difference between the highest and lowest in the window
            int diff = nums[i + k - 1] - nums[i];
            minDiff = Math.min(minDiff, diff);
        }
        
        return minDiff;
    }
}
