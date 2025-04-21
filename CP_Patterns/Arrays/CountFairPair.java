package CP_Patterns.Arrays;

import java.util.Arrays;
// 2563. Count the Number of Fair Pairs
public class CountFairPair {
    // To find lower<=nums[i] + nums[j]<=upper
    // Logic : Find all upper - Find all <=lower-1. If we do - them it will give ans
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return count(nums, upper) - count(nums, lower-1);
    }
    public long count(int[] a, int t) {
        long c = 0;
        int i = 0, j = a.length-1;
        while(j > i) {
            if(a[i] + a[j] <= t) { 
                // Imagine you’re at a valid pair (i, j) such that a[i] + a[j] <= t.
// Now because the array is sorted, the elements from i+1 to j (i.e., all values greater than a[i]) will also form valid pairs with a[i].
                c += (j - i); // all pairs from i to j
                i++; 
            }
            else j--;
        }
        return c;
    }
}
