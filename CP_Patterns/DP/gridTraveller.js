// We have to travel the grid from top left to bottom right, We have to say the number of ways to do it
/*
    Movements:
        Right
        Down
        
    Base cases:
        (0x0) = 0 ways
        (8x0) = 0 ways
        (0x8) = 0 ways
        So if one of them is 0 then no ways to go as grid does not exist

        (1x1) = 1 way
*/

const gridTraveller = (m, n) => {
    if(m == 1 && n == 1) return 1;
    if(m == 0 || n == 0) return 0;
    return gridTraveller(m-1, n) + gridTraveller(m, n-1);
    /*
        Imagine going in a tree of recursion we going left means down and right is we going right so when we hit
        (1,1) we found the real base case and track till that path to see like - right, down, down
    */
}

const dpGridTraveller = (m, n, memo = {}) => {
    const key = m + ',' + n
    if(key in memo) return memo[key]
    if(m == 1 && n == 1) return 1;
    if(m == 0 || n == 0) return 0;
    memo[key] = gridTraveller(m-1, n, memo) + gridTraveller(m, n-1, memo)
    return memo[key];
}

console.log(dpGridTraveller(2,3))
console.log(dpGridTraveller(5,5))
console.log(dpGridTraveller(15,15))

// The time complexity for above code is O(2^(m+n))
/*
class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<vector<int>> dp(m, vector<int>(n, 1));
        for(int i = 1; i < m; i++)
            for(int j = 1; j < n; j++)
                dp[i][j] = dp[i-1][j] + dp[i][j-1];   // sum of unique paths ending at adjacent top and left cells
        return dp[m-1][n-1];         // return unique paths ending at cell (m-1, n-1)
    }
};
The above code is DP tabulation : Time - O(m*n)
*/
// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
Pattern 2

You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The testcases are generated so that the answer will be less than or equal to 2 * 109.
Example 1:


Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:


Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
 

Constraints:

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.

Code:
class Solution {
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
}


1d dp code:
class Solution {
    public int uniquePathsWithObstacles(int[][] obs) {
        int m = obs.length, n = obs[0].length;
        int[] dp = new int[n];

        dp[0] = (obs[0][0] == 0) ? 1 : 0;  // Base case

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obs[i][j] == 1) dp[j] = 0;  // Obstacle blocks path
                else if (j > 0) dp[j] += dp[j - 1];  // Add paths from left
            }
        }
        return dp[n - 1];
    }
}
*/