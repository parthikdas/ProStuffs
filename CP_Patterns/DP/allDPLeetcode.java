package CP_Patterns.DP;

import java.util.Arrays;

/*
    1. 70. Climbing Stairs
    121. Best Time to Buy and Sell Stock
    746. Min Cost Climbing Stairs
 */
public class allDPLeetcode {
    public int climbStairs(int n) { // O(n), O(n)
        // recur
        if(n == 0 || n == 1) return 1;
        // return climbStairs(n - 1) + climbStairs(n - 2);
        // top down
        int[] dp = new int[n+1];
        dp[0] = 1; // base
        dp[1] = 2; // base
        for(int i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
        return dp[n-1];
        // use 2 var for base case and one var inside to avoid and make it O(1) space
    }
    // ----------------------------------------------------------------------
        public int recur(int[] p, int i, int min, int profit) {
            if(i == p.length) return profit;
            if(p[i] < min) return recur(p, i+1, p[i], profit); // update min
            else return recur(p, i+1, min, Math.max(profit, p[i] - min)); // update profit
        }
        public int maxProfit(int[] p) {
            // return recur(p, 1, p[0], 0);
            // top bottom
            int maxProfit = -1, min = p[0]; // O(1)space as 1 var is used efficent dp
            for(int n: p) {
                min = Math.min(n, min);
                maxProfit = Math.max(n - min, maxProfit);
            }
            return maxProfit;
        }
    // ----------------------------------------------------------------------
    public int recur(int[] cost, int i, int[] dp) {
        if (i >= cost.length) return 0;
        if (dp[i] != -1) return dp[i];
        return dp[i] = cost[i] + Math.min(recur(cost, i + 1, dp), recur(cost, i + 2, dp));
    }
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        Arrays.fill(dp, -1);
        return Math.min(recur(cost, 0, dp), recur(cost, 1, dp));
        // top down
        // int a = cost[0], b = 0;
        // for(int i=1;i<cost.length;i++) {
        //     int curr = cost[i] + Math.min(a,b);
        //     b = a;
        //     a = curr;
        // }
    }
}
