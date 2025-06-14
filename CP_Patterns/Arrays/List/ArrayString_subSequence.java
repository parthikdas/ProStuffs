package CP_Patterns.Arrays.List;

import java.util.Arrays;

public class ArrayString_subSequence {
    // 392. Is subsequence
    public boolean isSubsequence(String s, String t) {
        int j=0;
        for(int i=0;i<t.length()&&j<s.length();i++) {
         if(t.charAt(i) == s.charAt(j)) j++;
        }
        return j == s.length();
    }
    // 1143 - Longest Common Subsequence
    public int lcsRecurWithMemo(String s1, String s2, int i, int j, int[][] dp) { // Normal recursion - O(2^n) but using memo O(m*n)
        if(i >= s1.length() || j >= s2.length()) return 0;
        if(dp[i][j] != -1) return dp[i][j]; // memoization check
        if(s1.charAt(i) == s2.charAt(j)) return dp[i][j] = 1 + lcsRecurWithMemo(s1, s2, i+1, j+1, dp);
        else return dp[i][j] = Math.max(lcsRecurWithMemo(s1, s2, i+1, j, dp), lcsRecurWithMemo(s1, s2, i, j+1, dp));
    }
    /*
     To understand the 2d dp
        abc
        0000
      b 0011
      c 0012
      Like this make 2d array 
    */
    public int longestCommonSubsequence2d(String s1, String s2) { // O(m*n), same
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) { // When we found same we did 1 + dp[i-1][j-1] so thats why next line
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) { // or this check which is max go to the max top or left
                i--;
            } else {
                j--;
            }
        }
        String res = lcs.reverse().toString(); // This is longestSubsequence
        return dp[m][n];
    }
    public int longestCommonSubsequence1d_1(String s1, String s2) { // O(m*n), O(n)
        int m = s1.length(), n = s2.length();
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= m; i++) {
            int[] cur = new int[n + 1]; // New row for the current iteration
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    cur[j] = dp[j - 1] + 1; // Diagonal match
                } else {
                    cur[j] = Math.max(dp[j], cur[j - 1]); // Max of top or left
                }
            }
            dp = cur; // Now safely update after the row is done
        }
        return dp[n];
    }
    public int longestCommonSubsequence1d_2(String s1, String s2) { // O(m*n), O(n)
        int m = s1.length(), n = s2.length();
        int[] dp = new int[n + 1]; // One-dimensional DP array
        for (int i = 1; i <= m; i++) {
            int prev = 0; // Stores the value of dp[i-1][j-1] for the next row
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // Store dp[j] for the next iteration
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[j] = prev + 1; // Match found, increment the LCS length
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]); // Max of left or top
                }
                prev = temp; // Update prev for the next iteration
            }
        }
        return dp[n]; // LCS length for the full strings
    }
    // 115. Distinct Subsequences
        // Recur with memo 2d
        // Logic if i runs out we lost, if j runs out we won, if both char same we have choice to choose it or not choose it, and if not same ovio we i++ not j++ as we need maintain the order of j
        public int distinctSubs1(String s, String t, int i, int j, int[][] dp) { // O(m*n), same
            // If we've matched all of t — success
            if (j == t.length()) return 1;
            // If we've run out of s but not finished t — fail
            if (i == s.length()) return 0;
            if(dp[i][j] != -1) return dp[i][j];
            int ans = 0;
            if(s.charAt(i) == t.charAt(j)) {
                ans += distinctSubs1(s,t,i+1,j+1,dp);
            }
            ans += distinctSubs1(s,t,i+1,j,dp);
            return dp[i][j] = ans;
        }
        public int distinctSubs2(String s, String t, int i, int j, int[][] dp) {
            // If we've matched all of t — success
            if (j == t.length()) return 1;
            // If we've run out of s but not finished t — fail
            if (i == s.length()) return 0;
            if(dp[i][j] != -1) return dp[i][j];
            if(s.charAt(i) == t.charAt(j)) {
                return dp[i][j] = distinctSubs2(s,t,i+1,j+1,dp) + distinctSubs2(s,t,i+1,j,dp);
            }
            return dp[i][j] = distinctSubs2(s,t,i+1,j,dp);
        }
        // 2d DP
        public int numDistinct2d(String s, String t) {
            int m = s.length(), n = t.length();
            int[][] dp = new int[m + 1][n + 1];
            // Base cases
            for (int i = 0; i <= m; i++) { // As j has become 0 all j exhasted like recursion so its 0 and there are values in i so in loop
                dp[i][0] = 1; // An empty t can be formed by deleting all of s
            }
            // Fill dp table
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; // same like recurion both same so i+1,j+1 and i+1,j
                    } else {
                        dp[i][j] = dp[i - 1][j]; // if not same i+1,j
                    }
                }
            }
    
            return dp[m][n];
        }
        // 1d dp
        public int numDistinct1d(String s, String t) {
            int m = s.length(), n = t.length();
            int[] dp = new int[n + 1];
            dp[0] = 1; // empty t can always be matched
            for (int i = 1; i <= m; i++) {
                for (int j = n; j >= 1; j--) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[j] = dp[j] + dp[j - 1]; // take + skip
                    }
                    // else, we do nothing: dp[j] already holds the value from previous s[i-1]
                }
            }
            return dp[n];
        }
    
    // 300. Longest Increasing Subsequence
        // Recursion with memo
        public int LISmemo(int[] nums, int cur, int prev, int[][] dp) { // O(n^2)
            if(cur >= nums.length) return 0;
            int take = 0;
            if(dp[cur][prev+1] != -1) return dp[cur][prev+1]; // we do co-ordinate change as prev was -1 and that cannot be a index
            if(prev == -1 || nums[cur] > nums[prev]) take = 1+LISmemo(nums, cur+1, cur, dp);
            int notTake = LISmemo(nums, cur+1, prev, dp);
            return dp[cur][prev+1] = Math.max(take, notTake);
        }
        public int lengthOfLIS(int[] nums) {
            int[][] dp = new int[nums.length][nums.length+1]; // we need 2d array as we have cur, prev and prev is -1 which is not possible for index so nums.length+1
            for(int n[] : dp) Arrays.fill(n, -1);
            return LISmemo(nums, 0, -1, dp);
        }
        // tabulation method
        public int lengthOfLIS2d(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n+1][n+1];
            for (int cur = n-1; cur >= 0; cur--) { //  When we want future ans we build from below
                for (int prev = cur-1; prev >= -1; prev--) {
                    int notTake = dp[cur+1][prev+1];
                    int take = 0;
                    if (prev == -1 || nums[cur] > nums[prev]) {
                        take = 1 + dp[cur+1][cur+1];
                    }
                    dp[cur][prev+1] = Math.max(take, notTake);
                }
            }
            return dp[0][0]; // Start with cur=0, prev=-1 shifted => dp[0][0]
        }
        // if u do this on paper see how easy it is, just check if possible simple brute force
        public int lengthOfLIS1d(int[] nums) { // O(n^2), O(n)
            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp, 1); // every element is an LIS of length 1 by itself
            int max = 0;
            for (int i = 1; i < n; i++) { // Iterate over each element starting from the second one
                for (int prev = 0; prev < i; prev++) { // Compare the current element with all previous elements
                    if (nums[i] > nums[prev]) {
                        dp[i] = Math.max(dp[i], dp[prev] + 1); // If nums[i] > nums[j], consider extending LIS at dp[j]
                    }
                }
            }
            for (int length : dp) max = Math.max(max, length); // The length of the longest increasing subsequence
            return max;
        }
        // If u want to print
        public static int lengthOfLIS1d_print(int[] nums) { 
            int n = nums.length;
            int[] dp = new int[n];
            int[] parent = new int[n];
            Arrays.fill(dp, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i; // initialize
            }
            int max = 1, lastIndex = 0;
            for (int i = 1; i < n; i++) {
                for (int prev = 0; prev < i; prev++) {
                    if (nums[i] > nums[prev] && dp[i] < dp[prev] + 1) {
                        dp[i] = dp[prev] + 1;
                        parent[i] = prev;
                    }
                }
                // Update the max and lastIndex inside the same loop
                if (dp[i] > max) {
                    max = dp[i];
                    lastIndex = i;
                }
            }
            // for (int i = 0; i < n; i++) {
            //     if (dp[i] > max) {
            //         max = dp[i];
            //         lastIndex = i;
            //     }
            // }
            // Printing parent array for understanding
            for (int kk : parent) System.out.println(kk);
            // Build the sequence
            StringBuilder seq = new StringBuilder();
            int current = lastIndex;
            while (parent[current] != current) {
                seq.append(nums[current]).append(" ");
                current = parent[current];
            }
            seq.append(nums[current]); // last starting element
            System.out.println("The Sequence is : " + seq.reverse().toString());
            return max;
        }

}
