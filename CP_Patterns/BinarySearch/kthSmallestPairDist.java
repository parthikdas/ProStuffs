package CP_Patterns.BinarySearch;

import java.util.Arrays;

public class kthSmallestPairDist {
    // 719 
    int countPairs(int[] nums, int mid) {
            int count = 0;
            int j = 0;
            
            // Two pointer approach to count pairs with distance <= mid
            for (int i = 0; i < nums.length; i++) {
                while (j < nums.length && nums[j] - nums[i] <= mid) {
                    j++;
                }
                count += (j - i - 1);
            }
            return count;
        }
    public int smallestDistancePair(int[] nums, int k) {
       Arrays.sort(nums);  // First, sort the array
        
        // Helper function to count how many pairs have distance <= mid
        
        // Binary search for the k-th smallest distance
        int left = 0, right = nums[nums.length - 1] - nums[0];
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (countPairs(nums, mid) < k) {
                left = mid + 1;  // Need a larger distance
            } else {
                right = mid;  // We have enough pairs with this distance or less
            }
        }
        
        return left;
    }
}
