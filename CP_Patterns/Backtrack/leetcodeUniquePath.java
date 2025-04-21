package CP_Patterns.Backtrack;

import java.util.Arrays;

// 62. Unique Paths
public class leetcodeUniquePath {
    private int ans(int i, int j, int m, int n, int[][] memo) {
        // If out of bounds, return 0 (no path)
        if (i >= m || j >= n) return 0;
        // If we reach the bottom-right corner, return 1 (found a path)
        if (i == m - 1 && j == n - 1) return 1;

        // Check if the result is already computed
        if (memo[i][j] != -1) return memo[i][j];

        // Move down and right
        // Store the result in the memoization table
        memo[i][j] = ans(i + 1, j, m, n, memo) + ans(i, j + 1, m, n, memo);

        // Return the total number of paths from this cell
        return memo[i][j];
    }

    public int uniquePaths(int m, int n) { // O(m+n) both
        // Create a memoization table initialized with -1
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1; // Mark all cells as unvisited
            }
        }
        // Start recursion from the top-left corner (0, 0)
        return ans(0, 0, m, n, memo);
    }
// ---------------------------------------------------------------
    // 63. Unique Paths II
    public int countPaths(int m, int n, int[][] obs, int[][] dp) {
        if(m < 0 || n < 0 || obs[m][n] == 1) return 0;
        if(m == 0 && n == 0) return 1;
        if(dp[m][n] != -1) return dp[m][n];  // Memoization
        return dp[m][n] = countPaths(m-1, n, obs, dp) + countPaths(m, n-1, obs, dp);
    }
    public int uniquePathsWithObstacles(int[][] obs) {
        int m = obs.length;
        int n = obs[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);  // Initialize with -1
        return countPaths(m-1,n-1,obs,dp);
    }
// ---------------------------------------------------------------
    // 64. Minimum Path Sum
    public int countPaths1(int m, int n, int[][]grid, int[][] dp) {
        if(m < 0 || n < 0) return Integer.MAX_VALUE;
        if(m == 0 && n == 0) return grid[m][n];
        if(dp[m][n] != -1) return dp[m][n];
        return dp[m][n] = grid[m][n] + Math.min(countPaths(m-1, n, grid, dp), countPaths(m, n-1, grid, dp));
    }
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int dp[][] = new int[m][n];
        for(int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        return countPaths(m-1, n-1, grid, dp);
    }
// ---------------------------------------------------------------
    // 2435. Paths in Matrix Whose Sum Is Divisible by K
    static int MOD = 1000000007;
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        
        // dp[i][j][mod] stores the number of ways to reach (i,j) with current sum % k == mod
        int[][][] dp = new int[m][n][k];  // trying to keep track of three distinct pieces of information - grid location, Current sum modulo k
        
        // Initialize the dp array with -1 for uncomputed states
        for (int[][] row : dp)
            for (int[] col : row)
                java.util.Arrays.fill(col, -1);
        
        return recur(grid, 0, 0, 0, k, dp);
    }

    // Helper function for recursive calls with memoization
    public int recur(int[][] grid, int i, int j, int mod, int k, int[][][] dp) {
        // If out of bounds, return 0 as no valid paths exist
        if (i >= grid.length || j >= grid[0].length) return 0;

        // Compute the new mod value after adding current cell value
        mod = (mod + grid[i][j]) % k;

        // If we're at the bottom-right corner, check if the mod is zero
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return mod == 0 ? 1 : 0;

        // Return the stored value from dp if already computed
        if (dp[i][j][mod] != -1) return dp[i][j][mod];

        // Move right and down and add the results
        int right = recur(grid, i, j + 1, mod, k, dp);
        int down = recur(grid, i + 1, j, mod, k, dp);

        // Store the result in dp and return it
        dp[i][j][mod] = (right + down) % MOD;
        
        return dp[i][j][mod];
    }
// ---------------------------------------------------------------
    // 980. Unique Paths III - HARD
    int res = 0, nonObs = 0;
    // -1 for obs, -2 for visited
    public void helper(int grid[][], int i, int j, int c) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == -1) return;
        if(grid[i][j] == 2) { // goal/base
            if(c == nonObs) res++; // Path were all the non obs are visited
            return;
        }
        int temp = grid[i][j]; // as we are using -1 for both obs and visited, store the original val to use later
        grid[i][j] = -1;
        helper(grid, i+1, j, c+1);
        helper(grid, i, j+1, c+1);
        helper(grid, i-1, j, c+1);
        helper(grid, i, j-1, c+1);
        grid[i][j] = temp;
    }
    public int uniquePathsIII(int[][] grid) {
        int a = 0, b = 0;
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {a = i; b = j;} // get the start index
                if(grid[i][j] != -1) // if(grid[i][j] == 0 || grid[i][j] == 1 || grid[i][j] == 2)
                    nonObs++;
                
            }
        }
        helper(grid, a, b, 1);  // start from 1 because starting cell is visited
        return res;
    }
// ---------------------------------------------------------------
    // 174. Dungeon Game - HARD
    public int calculateMinimumHP(int[][] dungeon) { // O(m*n), O(m*n)
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        for (int[] row : dp) Arrays.fill(row, -1);
        return dfs(dungeon, 0, 0, dp);
    }

    public int dfs(int[][] dungeon, int i, int j, int[][] dp) {
        int m = dungeon.length, n = dungeon[0].length;

        // If out of bounds, return large value since it's an invalid path
        if (i >= m || j >= n) return Integer.MAX_VALUE;
        
        if (dp[i][j] != -1) return dp[i][j];

        // If we're at the destination (bottom-right)
        if (i == m - 1 && j == n - 1) {
            // Minimum health needed to survive at this cell
            return Math.max(1, 1 - dungeon[i][j]);
        }

        // Recurse to right and down
        int right = dfs(dungeon, i, j + 1, dp);
        int down = dfs(dungeon, i + 1, j, dp);

        // Minimum health needed to proceed from this cell
        int need = Math.min(right, down) - dungeon[i][j];

        // Ensure at least 1 HP to stay alive
        return dp[i][j] = Math.max(1, need);
    }
}
