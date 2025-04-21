package CP_Patterns.Arrays;

public class maxValueOfOrderedTriplet {
    // 2873. Maximum Value of an Ordered Triplet I
    // Approach 1: O(n^3), O(1)
    public long maximumTripletValue(int[] nums) {
        long maxTriplet = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int k = nums.length - 1; k > i; k--) {
                int j = i + 1;
                while (j < k) {
                    maxTriplet = Math.max(maxTriplet, (long)(nums[i] - nums[j]) * nums[k]);
                    j++;
                }
            }
        }
        return Math.max(0, maxTriplet);
    }

    // Approach 1: O(n), O(1)
    public long maximumTripletValue1(int[] nums) {
        long maxTriplet = 0, maxElement = 0, maxDiff = 0;
        for (int num : nums) {
            maxTriplet = Math.max(maxTriplet, maxDiff * num); // as we have the max diff check for the maxProduct
            maxDiff = Math.max(maxDiff, maxElement - num); // as we have the max ele, check the maxDiff
            maxElement = Math.max(maxElement, num); // Track the max ele, as we know it will be a part of the ans
        }
        return maxTriplet;
    }

    // 2874. Maximum Value of an Ordered Triplet II
    // same code as above
    // for understanding purpose another code:
    public long maximumTripletValue2(int[] nums) {
        int maxi = Integer.MIN_VALUE;
        int diff = 0;
        long res = 0;
        for (int i = 0; i < nums.length; i++) {
            maxi = Math.max(maxi, nums[i]);
            if (i >= 2)
                res = Math.max(res, (long)diff * nums[i]);
            if (i >= 1)
                diff = Math.max(diff, maxi - nums[i]);
        }
        return res;
    }
}
