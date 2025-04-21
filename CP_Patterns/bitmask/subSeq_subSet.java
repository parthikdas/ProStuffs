package CP_Patterns.bitmask;
import java.util.*;
/*
 A subarray is a contiguous portion of an array. This means the elements in the subarray are consecutive in the original array.
 A subset is any selection of elements from the original array, without needing to be contiguous.

 Subset:
 ✅ If n ≤ 20:
    Use Bitmasking
        Why? There are 2ⁿ subsets. Bitmasking runs in O(2ⁿ × n), which is fast enough for n ≤ 20.
        Usage: Subset sums, XORs, DP, etc.
        
 🚫 If n > 20:
        Avoid generating all subsets explicitly unless needed.
        Instead, use one of the following:
    1. Backtracking with Pruning
        Useful when you don’t need all subsets, just some with certain properties (e.g., target sum, k subsets).
        You stop early when the condition fails.
        Use Case: Leetcode 698 (Partition to K Equal Sum Subsets)
    2. Meet-in-the-Middle (For 20 < n ≤ 40)
        Split array into two halves.
        Generate all subset sums of both halves (2¹⁰ ~ 2²⁰ each).
        Combine smartly using binary search or hashmaps.
        Use Case: Subset sum equals target, max weight, etc.
        Example Problem: Leetcode 1755 - Closest Subsequence Sum
    3. Greedy / DP Approaches
        If problem only asks for "is possible" or "count", use DP with compression instead of full subsets.
        Ex: dp[mask] or dp[i][mask] to store optimal values.
        Avoid materializing all subsets.

 🧠 Summary Table
    Size of n	    Best Method	            Why
    n ≤ 20	        Bitmasking	            Fastest, clean
    20 < n ≤ 40	    Meet-in-the-middle	    Avoids full 2ⁿ
    n > 40	        DP/Greedy/Heuristics	Subsets are too big
*/
public class subSeq_subSet {
    // subarray
    // ([1]), ([1, 2]), ([1, 2, 3]), ([2]), ([2, 3]), ([3]) - Notice it does not contains 1,3 and its not together
    public static List<List<Integer>> getAllSubarrays(int[] arr) { // O(n^2)
        List<List<Integer>> subarrays = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            List<Integer> subarray = new ArrayList<>();
            for (int j = i; j < n; j++) {
                subarray.add(arr[j]);
                subarrays.add(new ArrayList<>(subarray)); // Adding every state of the list
            }
        }
        return subarrays;
    }

    // Subset - Here what we are using is powerset
    // Here order does not matter, (1,2) is same as (2,1)
        // The total number of subsets (or subsequences) of an array of size ( n ) is ( 2^n ).
        // The outer loop runs ( 2^n ) times (for each possible subset).
        // The inner loop runs ( n ) times to check each bit and construct the subset.
        // Therefore, the overall time complexity is: [ O(n*2^n) ] and space is O(n)
    public List<List<Integer>> generateSubsets(int[] arr) { // [[], [1], [2], [2, 1], [3], [3, 1], [3, 2], [3, 2, 1]] 
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;
        int totalSubsets = 1 << n; // 2^n
        for (int i = 0; i < totalSubsets; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // Check if jth bit in i is set
                if ((i & (1 << j)) != 0) {
                    subset.add(arr[j]);
                }
            }
            result.add(subset);
        }
        return result;
    }
    
    // Subsequence
    // Here order does matter, (1,2) is not same as (2,1)
    // [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    private static void generateSubsequences(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
        // Add the current subsequence to the result
        result.add(new ArrayList<>(current));
        // Iterate through the remaining elements
        for (int i = index; i < arr.length; i++) {
            // Include arr[i] in the current subsequence
            current.add(arr[i]);
            // Recur with the next index
            generateSubsequences(arr, i + 1, current, result);
            // Backtrack to explore the next possibility
            current.remove(current.size() - 1);
        }
    }

    // We can leverage the fact that the power set includes all possible subsets of a set, which inherently includes all subsequences as well.
    // So by using the subset thing we can get all subsequence

    public static void main(String[] args) {
        int[] arr = {2, 6, 2};
        List<List<Integer>> result = getAllSubarrays(arr);
        for (List<Integer> sub : result) {
            System.out.println(sub);
        }

        List<List<Integer>> res = new ArrayList<>();
        generateSubsequences(arr, 0, new ArrayList<>(), result);
    }
}
