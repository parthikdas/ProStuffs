package CP_Patterns.Arrays;

public class productOfSelfArr {
    // 238. Product of Array Except Self
    /*
      It is called prefix sum: a way to store cumulative results (often sums, sometimes products) from the start of an array to a given index.
      Below we have 2 loops one goes from left to right another right to left

      Other types of this question:
        Range sum queries (e.g. "Find the sum of elements from index l to r many times")
        Counting or frequency problems (e.g. number of even numbers up to index i)
        2D prefix sums for matrix-related problems
        Product except self — where prefix and suffix products are used
        Difference arrays (inverse of prefix sums, used for efficient range updates)
     */
    public int[] productExceptSelf(int[] a) { // O(n), O(n) - DP
        int[] ans = new int[a.length];
        int n = a.length;
        int[] left = new int[n];
        left[0] = 1;
        for(int i = 1; i < n; i++) left[i] = left[i-1] * a[i-1];
        int[] right = new int[n];
        right[n-1] = 1;
        for(int i = n-2; i >= 0; i--) right[i] = right[i+1] * a[i+1];
        for(int i = 0; i < n; i++) ans[i] = left[i] * right[i];
        return ans;
    }

    // Optimised dp
    public int[] productExceptSelf1(int[] a) { // O(n), O(1)
        int[] ans = new int[a.length];
        int n = a.length;
        int[] left = new int[n];
        ans[0] = 1;
        for(int i = 1; i < n; i++) ans[i] = ans[i-1] * a[i-1];
        int right = 1;
        for(int i = n-1; i >= 0; i--) {
            ans[i] *= right;
            right *= a[i];
        }
        return ans;
    }
}
