package CP_Patterns.DP;

import java.util.Arrays;
/*
 
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

 

Example 1:


Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200


Approach:
Using Memoziation Alvin Way,
    if u draw the tree structure and see we can de Min of left right and it will do it
    (2,2)
  down  /\ right
    (1,2)  (2,1)
(0,1) (1,1) (1,1)(2,0)

So if we return the min value and eventually we add up we get it
 */
public class minPathSum {
    public int countPaths(int m, int n, int[][]grid, int[][] dp) {
            if(m < 0 || n < 0) return Integer.MAX_VALUE;
            if(m == 0 && n == 0) return grid[m][n];
            if(dp[m][n] != -1) return dp[m][n];
            return dp[m][n] = grid[m][n] + Math.min(countPaths(m-1, n, grid, dp), countPaths(m, n-1, grid, dp));
    }
    public int minPathSum(int[][] grid) {
            int m = grid.length, n = grid[0].length; // Will do -1 later as if input is 0 it will not throw error
            int dp[][] = new int[m][n];
            for(int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
            return countPaths(m-1, n-1, grid, dp);
    }
}
