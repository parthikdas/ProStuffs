package CP_Patterns.slidingWindow;

import java.util.List;
// 3364. Minimum Positive Sum Subarray 
// Here we use differently left as we are genrating all the possiblities
public class minPosSumSubarray {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        int sum = 0;
        
        // Iterate over all possible subarrays
        for (int left = 0; left < n; left++) {
            sum = 0; // Reset sum for each new starting point (left)
            
            for (int right = left; right < n; right++) {
                sum += nums.get(right);  // Add current element to the sum
                
                // Check if the length of the subarray is within the valid range [l, r]
                int length = right - left + 1;
                if (length >= l && length <= r && sum > 0) {
                    minSum = Math.min(minSum, sum);  // Update the minimum sum
                }
            }
        }
        
        // If no valid subarray was found, return -1
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}
