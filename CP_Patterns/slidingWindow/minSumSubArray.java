package CP_Patterns.slidingWindow;
// 209. Minimum Size Subarray Sum
public class minSumSubArray {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, n = nums.length, sum = 0, minLength = Integer.MAX_VALUE;
        for(int right = 0; right < n; right++) {
            sum+=nums[right];
            while(sum>=target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }   
        }
        // If minLength was updated, return it; otherwise, return 0 (no valid subarray)
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
