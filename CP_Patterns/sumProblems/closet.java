package CP_Patterns.sumProblems;

import java.util.Arrays;

public class closet {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // Sort the array first
        int n = nums.length;
        int ans = nums[0] + nums[1] + nums[2]; // Initialize with a valid sum
        int minDiff = Integer.MAX_VALUE; // Initialize minimum difference with the largest possible value
        // Loop through the array, with the outer loop fixed at each index
        for (int i = 0; i < n - 2; i++) {
            // Skip duplicate values for 'i'
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1, k = n - 1;
            // Use two pointers to find the closest sum for the fixed element nums[i]
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(sum - target);
                // If the difference is smaller, update the answer
                if (diff < minDiff) {
                    minDiff = diff;
                    ans = sum;
                }
                // Move the pointers to find a closer sum
                if (sum < target) {
                    j++; // Increase the sum by moving the left pointer right
                } else if (sum > target) {
                    k--; // Decrease the sum by moving the right pointer left
                } else {
                    // If we find an exact match, return immediately
                    return sum;
                }
            }
        }
        return ans; // Return the sum closest to the target
    }
}
