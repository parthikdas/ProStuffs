package CP_Patterns.DP.Knapsack01;
class Item {
    int value, weight;
    
    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}
public class knap0_1 {
    private static int recur(Item[] items, int capacity, int n) { // O(2^n), O(n) (due to recursion stack)
        // Base case
        if (n == 0 || capacity == 0) return 0;
        // If weight of item is more than capacity, skip it
        if (items[n - 1].weight > capacity) {
            return recur(items, capacity, n - 1);
        } else {
            // Max of: including the item vs skipping the item
            return Math.max(
                items[n - 1].value + recur(items, capacity - items[n - 1].weight, n - 1),
                recur(items, capacity, n - 1)
            );
        }
    }
    private static int memo(Item[] items, int capacity, int n, int[][] dp) { // O(n * W), O(n * W) (due to memoization table)
        // Base case
        if (n == 0 || capacity == 0) return 0;
        // Check if value has already been computed
        if (dp[capacity][n] != -1) return dp[capacity][n];
        // If the weight of the item is more than the remaining capacity, skip it
        if (items[n - 1].weight > capacity) {
            return dp[capacity][n] = recur(items, capacity, n - 1);
        } else {
            // Max of: including the item vs skipping the item
            return dp[capacity][n] = Math.max(
                items[n - 1].value + recur(items, capacity - items[n - 1].weight, n - 1),
                recur(items, capacity, n - 1)
            );
        }
    }
    public static void main(String[] args) {
        Item[] items = {
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };
        int capacity = 50;

        double maxValue = recur(items, capacity, items.length);
        System.out.println("Maximum value in knapsack = " + maxValue); // Output: 240.0

        int[][] dp = new int[capacity+1][items.length+1]; // can make [item.length+1][capacity+1] also
        // Fill dp table using memoization
        for (int i = 0; i <= capacity; i++) {
            for (int j = 0; j <= items.length; j++) {
                dp[i][j] = -1; // Mark all entries as uncalculated
            }
        }
        System.out.println("Using memo : " + memo(items, capacity, items.length, dp));
    }

}
