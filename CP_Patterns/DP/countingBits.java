/*
 Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.

 

Example 1:

Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
Example 2:

Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
 

Constraints:

0 <= n <= 105
 

Follow up:

It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?

-------Solution---------
✅ IF YOU LIKE THIS SOLUTION, PLEASE UPVOTE AT THE END ✅ :

Intuition :

To find the number of 1s in the binary representation of numbers from 0 to ( n ), we can use a pattern:

If ( i ) is even, the number of 1s in ( i ) is the same as the number of 1s in ( i/2 ) (right-shifting an even number halves it without adding a new 1).
If ( i ) is odd, the number of 1s in ( i ) is the number of 1s in ( i - 1 ) plus one additional 1 (as adding 1 to an even number makes it odd).
Approach :

Initialize the Array: Create an array ans of size ( n + 1 ) and set the base case ans[0] = 0.
Iterate from 1 to n:
For each ( i ):
If ( i ) is even, set ans[i] = ans[i / 2].
If ( i ) is odd, set ans[i] = ans[i - 1] + 1.
Return the array ans after completing the iteration.
Solving Steps :

Define an array ans with length ( n + 1 ).
Set ans[0] = 0.
Loop from 1 to ( n ):
For each ( i ), calculate ans[i] based on whether ( i ) is even or odd.
Return ans.
 */

package CP_Patterns.DP;

import java.util.Scanner;

public class countingBits {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
    public static void main(String[] args) {
    }
}

