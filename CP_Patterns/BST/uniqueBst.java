package CP_Patterns.BST;

public class uniqueBst {
    // 96 leetcode
    /*
    Optimized O(n) Formula Using Catalan Number
Instead of using O(n²) DP, we can directly compute the n-th Catalan number using the formula:
    C(n) = (2n)! / (n+1)!n!

     * ✅ Time Complexity: O(n)
✅ Space Complexity: O(1)
     */
    public int numTrees(int n) {
        long catalan = 1;

        // Compute C(n) using formula: C(n) = (2n)! / ((n+1)! * n!)
        for (int i = 0; i < n; i++) {
            catalan = catalan * (2 * n - i) / (i + 1);
        }
        
        return (int) (catalan / (n + 1));
    }
}
