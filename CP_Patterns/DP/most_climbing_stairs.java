/*
 * 
 * Thinking Process for "Min Cost Climbing Stairs"
1️⃣ Understanding the Problem

We are given an array cost[], where cost[i] represents the cost of stepping on the i-th stair.
We can climb 1 step or 2 steps at a time.
We need to reach the top with the minimum total cost.
We can start from index 0 or 1.
🔹 Step 1: Thinking Recursively

Instead of solving the whole problem at once, break it down into smaller subproblems.

Recursive Relation
Let minCost(i) be the minimum cost to reach step i.

From step i, we can either jump one step (i+1) or two steps (i+2).
The total cost at i will be cost[i] + min(minCost(i+1), minCost(i+2)).
Base Cases
If we go beyond the last step, there's no cost (i.e., minCost(n) = 0 where n is the top).
If we are at the last step, we return its cost.
Recursive Formula
minCost(i)=cost[i]+min(minCost(i+1),minCost(i+2))
We can solve this recursively:

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        return Math.min(minCost(cost, 0), minCost(cost, 1));
    }
    
    private int minCost(int[] cost, int i) {
        if (i >= cost.length) return 0; // Base case: No cost beyond the last step
        return cost[i] + Math.min(minCost(cost, i+1), minCost(cost, i+2));
    }
}
🔹 Time Complexity: O(2^n), Exponential because of repeated calculations.
🔹 Space Complexity: O(n) (Recursive stack depth).

🔹 Step 2: Optimizing with Memoization (Top-Down DP)

Recursion has overlapping subproblems (e.g., minCost(2) is called multiple times).
Store results in a cache to avoid recomputation.
Memoized Code
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1]; // Cache for memoization
        Arrays.fill(dp, -1);
        return Math.min(minCost(cost, 0, dp), minCost(cost, 1, dp));
    }

    private int minCost(int[] cost, int i, int[] dp) {
        if (i >= cost.length) return 0;
        if (dp[i] != -1) return dp[i]; // Return cached result
        dp[i] = cost[i] + Math.min(minCost(cost, i + 1, dp), minCost(cost, i + 2, dp));
        return dp[i];
    }
}
🔹 Time Complexity: O(n), as each state is computed only once.
🔹 Space Complexity: O(n), due to recursion and cache.

🔹 Step 3: Bottom-Up DP (Iterative)

Instead of using recursion, we build the solution from the bottom up.

Bottom-Up Approach
The base cases are dp[n] = 0 and dp[n-1] = cost[n-1].
We compute dp[i] = cost[i] + min(dp[i+1], dp[i+2]) in reverse order.
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];

        dp[n] = 0;  // No cost beyond the last step
        dp[n - 1] = cost[n - 1]; // Base case

        for (int i = n - 2; i >= 0; i--) {
            dp[i] = cost[i] + Math.min(dp[i + 1], dp[i + 2]);
        }

        return Math.min(dp[0], dp[1]); // Start from either 0 or 1
    }
}
🔹 Time Complexity: O(n), single loop.
🔹 Space Complexity: O(n), for dp[] array.

🔹 Step 4: Space Optimization (Only Two Variables Needed)

We only need the last two values, so we can reduce space from O(n) to O(1).

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int first = cost[n - 1], second = 0; // Base cases

        for (int i = n - 2; i >= 0; i--) {
            int curr = cost[i] + Math.min(first, second);
            second = first;
            first = curr;
        }

        return Math.min(first, second);
    }
}
🔹 Time Complexity: O(n)
🔹 Space Complexity: O(1)
 */
package CP_Patterns.DP;

public class most_climbing_stairs { // its like fibonacci
    public int minCostClimbingStairs(int[] cost) {
        int a = cost[0], b = 0;
        for(int i=1;i<cost.length;i++) {
            int curr = cost[i] + Math.min(a,b);
            b = a;
            a = curr;
        }
        return Math.min(a,b);
    }
}
