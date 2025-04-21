package CP_Patterns.slidingWindow;
// 643. Maximum Average Subarray I
public class maxAvg {
    public double findMaxAverage(int[] nums, int k) {
        Double maxAvg = -Double.MAX_VALUE;
        int sum = 0;
        int left = 0, n = nums.length;
        for(int i = 0;i<n;i++) {
            sum+=nums[i];
            if(i - left + 1 == k) {
                maxAvg = Math.max(maxAvg, (double) sum/(i-left+1));
                sum -= nums[left];
                left++;
            }
        }
        return maxAvg;
    }
}
