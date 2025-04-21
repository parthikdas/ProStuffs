package CP_Patterns.DP.Knapsack01;

public class trying {
    public static int recur(int[] w, int[] v, int capacity, int n) {
        // lets see the valid minimal input
        if(capacity == 0 || n == 0) return 0; // capacity to hold becomes 0 or the length is 0
        // If the current weight is bigger than capacity, we cant hold it
        if(w[n-1] > capacity) return recur(w, v, capacity, n-1); // skip the number, [n-1] as 0 based indexing, started with w.length not w.length-1
        // If the current weight smaller or equal to capacity
        else {
            // Check which will be more profitable taking the weight or not taking the weight
            return Math.max(v[n-1] + recur(w, v, capacity - w[n-1], n-1), recur(w, v, capacity, n-1));
        }
    }
    public static int recurWithMemo(int[] w, int[] v, int capacity, int n, int[][] dp) {
        if(capacity == 0 || n == 0) return 0;
        if(dp[capacity][n] != -1) return dp[capacity][n];
        if(w[n-1] > capacity) return dp[capacity][n] = recurWithMemo(w, v, capacity, n-1, dp);
        else {
            return dp[capacity][n] = Math.max(v[n-1] + recurWithMemo(w, v, capacity - w[n-1], n-1, dp), recurWithMemo(w, v, capacity, n-1, dp));
        }
    }
    public static int topDown(int[] w, int[] v, int capacity, int n) {
        int[][] DP = new int[n+1][capacity+1];
        // for(int i = 0; i <= capacity; i++) DP[i][0] = 0; // base
        // for(int i = 0; i <= w.length; i++) DP[0][i] = 0; // base
        // Base case is already zero-initialized
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= capacity; j++) {
                if(w[i-1] > j) DP[i][j] = DP[i-1][j];
                else DP[i][j] = Math.max(v[i-1] +  DP[i-1][j - w[i - 1]], DP[i-1][j]);
            }
        }
        return DP[n][capacity];
    }
    public static void main(String[] args) {
        int w[] = {1, 3, 2, 4, 5};
        int v[] = {2, 3, 4, 1, 6};
        int capacity = 8;

        // We need to get the max profit
        System.out.println("The max profit using recur : " + recur(w, v, capacity, w.length));

        // Recur with memo
        int[][] dp = new int[capacity+1][w.length+1];
        for(int i = 0; i <= capacity; i++) 
            for(int j = 0; j <= w.length; j++) dp[i][j] = -1;
        System.out.println("The max profit using recur and memo : " + recurWithMemo(w, v, capacity, w.length, dp));

        // Top down dp
        System.out.println("The max profit using topDown : " + topDown(w, v, capacity, w.length));

    }

}
