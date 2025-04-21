package CP_Patterns.Arrays;
// 2016. Maximum Difference Between Increasing Elements
public class maxDiffbtwIncELe {
    public int maximumDifference(int[] nums) {
        // Variable to track the minimum value encountered so far
        int minValue = Integer.MAX_VALUE;
        // Variable to track the maximum difference
        int maxDiff = -1;

        // Traverse through the array
        for (int num : nums) {
            // If the current number is greater than minValue, calculate the difference
            if (num > minValue) {
                maxDiff = Math.max(maxDiff, num - minValue);
            }
            // Update the minimum value encountered so far
            minValue = Math.min(minValue, num);
        }

        return maxDiff;
    }
}
