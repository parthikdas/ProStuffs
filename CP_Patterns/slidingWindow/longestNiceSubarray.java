package CP_Patterns.slidingWindow;

public class longestNiceSubarray {
    // 2401. Longest Nice Subarray
    public int longestNiceSubarray(int[] nums) {
        int left = 0, n = nums.length, maxLength = 1;
        int currentAND = nums[0]; // Bitwise AND of the current window

        for (int right = 1; right < n; right++) {
            // If the AND condition is violated (i.e., currentAND & nums[right] != 0)
            while ((currentAND & nums[right]) != 0) {
                // Shrink the window from the left
                currentAND ^= nums[left];
                left++;
            }
            // Add nums[right] to the AND of the current window
            currentAND |= nums[right];
            // Update the maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
