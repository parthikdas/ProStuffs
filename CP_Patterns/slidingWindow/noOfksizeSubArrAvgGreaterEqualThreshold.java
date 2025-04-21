package CP_Patterns.slidingWindow;
// 1343. Leetcode
public class noOfksizeSubArrAvgGreaterEqualThreshold {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int n = arr.length;
        int count = 0;
        
        // Calculate the sum of the first window of size k
        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }
        
        // If the average of the first window is greater than or equal to the threshold, increment count
        if (currentSum / (double) k >= threshold) {
            count++;
        }
        
        // Use sliding window to calculate the sum of subsequent windows
        for (int i = k; i < n; i++) {
            currentSum += arr[i] - arr[i - k];  // Slide the window by removing the leftmost element and adding the next one
            
            // Check the new window's average
            if (currentSum / (double) k >= threshold) {
                count++;
            }
        }
        
        return count;
    }
}
