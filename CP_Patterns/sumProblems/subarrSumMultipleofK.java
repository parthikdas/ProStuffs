package CP_Patterns.sumProblems;

import java.util.HashMap;

public class subarrSumMultipleofK {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Handle case where entire subarray from start is valid
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int remainder = sum % k;

            if (remainder < 0) remainder += k; // Handle negative remainders

            if (map.containsKey(remainder)) {
                if (i - map.get(remainder) > 1) return true; // Ensure subarray length >= 2
            } else {
                map.put(remainder, i); // Store first occurrence of remainder
            }
        }
        return false;
    }
}
/*
 Understanding Negative Remainders in Modulo
In Java, the modulo (%) operator retains the sign of the dividend (numerator).
If the sum becomes negative at any point, sum % k will also be negative.
Example Where Negative Remainder Can Occur
Case: Negative Sum Scenario

int sum = -5;
int k = 3;
System.out.println(sum % k); // Output: -2
Expected remainder: 1 (since -5 ≡ 1 (mod 3))
Actual Java output: -2 (which is incorrect for our use case)
Fixing Negative Remainders
To ensure that all remainders stay positive, we do:

if (remainder < 0) remainder += k;
This shifts negative remainders into the correct modulo range [0, k-1].

Example Fix
int sum = -5;
int k = 3;
int remainder = sum % k;
if (remainder < 0) remainder += k;
System.out.println(remainder); // Output: 1 (corrected)
When Does sum Become Negative in the Subarray Sum Problem?
If we have negative numbers in nums[].
If the cumulative sum sum fluctuates below zero due to large negative numbers.
Some languages (like Python) always return positive mod values, but Java does not.
By normalizing the remainder, we ensure correctness in all cases.


Why Are We Storing the Remainder?
We store sum % k in a HashMap to efficiently detect if a subarray sum is a multiple of k.

Key Observation (Using Modulo)

If at two different indices j and i we have the same remainder:

prefixSum
[
i
]
%
k
=
prefixSum
[
j
]
%
k
prefixSum[i]%k=prefixSum[j]%k
Then the sum of elements between j+1 to i is a multiple of k:

prefixSum
[
i
]
−
prefixSum
[
j
]
=
n
×
k
prefixSum[i]−prefixSum[j]=n×k
This means there exists a valid subarray whose sum is a multiple of k.

Why i - map.get(remainder) > 1?
We need a subarray of at least length 2.
If the same remainder appears again at an index at least 2 steps apart, it ensures a valid subarray.
Example Walkthrough

Input: nums = [23, 2, 4, 6, 7], k = 6

Index:      0   1   2   3   4
Nums:      [23, 2,  4,  6,  7]
PrefixSum:  23  25  29  35  42
Mod k:      5   1   5   5   0
At i = 0, remainder 5 is stored at index 0.
At i = 2, remainder 5 is found again (i - 0 = 2, valid).
So, [2, 4] (from index 1 to 2) forms a valid subarray.
Key Idea
If we encounter the same remainder again, it means the sum between these two indices is a multiple of k.
We ensure that the subarray has at least length 2 (i - map.get(remainder) > 1).
Final Takeaway
Why store remainder? To detect repeated remainder values, indicating a valid subarray sum.
Why check i - map.get(remainder) > 1? To ensure the subarray has a length of at least 2.
This approach ensures we find the correct contiguous subarray efficiently in O(n) time! 🚀
 */