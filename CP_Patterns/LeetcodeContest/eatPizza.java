package CP_Patterns.LeetcodeContest;

import java.util.Arrays;
/*
 You are given an integer array pizzas of size n, where pizzas[i] represents the weight of the ith pizza. Every day, you eat exactly 4 pizzas. Due to your incredible metabolism, when you eat pizzas of weights W, X, Y, and Z, where W <= X <= Y <= Z, you gain the weight of only 1 pizza!

On odd-numbered days (1-indexed), you gain a weight of Z.
On even-numbered days, you gain a weight of Y.
Find the maximum total weight you can gain by eating all pizzas optimally.

Note: It is guaranteed that n is a multiple of 4, and each pizza can be eaten only once.

 

Example 1:

Input: pizzas = [1,2,3,4,5,6,7,8]

Output: 14

Explanation:

On day 1, you eat pizzas at indices [1, 2, 4, 7] = [2, 3, 5, 8]. You gain a weight of 8.
On day 2, you eat pizzas at indices [0, 3, 5, 6] = [1, 4, 6, 7]. You gain a weight of 6.
The total weight gained after eating all the pizzas is 8 + 6 = 14.

Example 2:

Input: pizzas = [2,1,1,1,1,1,1,1]

Output: 3

Explanation:

On day 1, you eat pizzas at indices [4, 5, 6, 0] = [1, 1, 1, 2]. You gain a weight of 2.
On day 2, you eat pizzas at indices [1, 2, 3, 7] = [1, 1, 1, 1]. You gain a weight of 1.
The total weight gained after eating all the pizzas is 2 + 1 = 3.

 

Constraints:

4 <= n == pizzas.length <= 2 * 105
1 <= pizzas[i] <= 105
n is a multiple of 4.

Hints:

Hint 1
On odd-numbered days, it is optimal to pair the smallest three and the largest one.

Hint 2
On even-numbered days, it is optimal to pair the smallest two and the largest two.

Hint 3
There will be ceil((n / 4) / 2) odd-numbered days. Select pizzas for all odd-numbered days first.

Hint 4
Select the remaining pizzas for the even-numbered days.


    // Approach
    # Approach
We are given an array of integers and need to compute the maximum possible weight by selecting elements based on specific conditions.

Determine k:
k = n / 4, where n is the array size.
This means that out of the n elements, we will be selecting a total of k elements.
Handling Even and Odd k:
If k is odd, then we need to take k/2 + 1 elements from the last (largest) part of the sorted array.
If k is even, we take exactly k/2 elements from the last part.
The remaining elements are chosen in a gap of 2, moving leftward from the last selected element.

k = 12 / 4 = 3 (odd)
Determine the number of elements to pick
- a = (3/2) + 1 = 2  // since 3 is odd, take (k/2 + 1) from last
- b = k - a = 3 - 2 = 1  // remaining elements

Select Elements
- Pick the last a = 2 elements:
sum = 12 + 11 = 23
- Pick the remaining b = 1 elements, skipping every 2nd element:
sum += 9 (next largest after skipping 10)

Final sum = 23

I did evert test case till 24 and found if the n/4 is even then /2 is enough else /2 + 1 that many number of elements
to add from last.
After that gap of 2 skipping n/4 - a elements needs to be added

Eg: 1 2 3 16
    7 8 13 14
    4 5 6 15
    9 10 11 12
 */
public class eatPizza {
    public long maxWeight(int[] arr) { // First Solution
        Arrays.sort(arr);
        int n = arr.length;
        int k = n/4;
        long sum = 0;
        if((k&1) == 1) { //odd
            int a = (k/2) +1;
            int b = (n/4) - a;
            int i;
            for(i = n-1 ; i >= n-a; i--) sum += arr[i];
            for(i=n-a-2;b-->0;i-=2) sum += arr[i];

        } else { //even
            int a = (n/4)/2;
            int b = (n/4) - a;
            int i;
            for(i = n-1 ; i >= n-a; i--) sum += arr[i];
            for(i=n-a-2;b-->0;i-=2) sum += arr[i];
        }
        return sum;
    }
    public long maxWeight1(int[] arr) { // Same solution as above but optmisied
        Arrays.sort(arr);
        int n = arr.length;
        int k = n/4;
        long sum = 0;
        int a = (k/2) + (k&1);
        int b = k - a;
        for(int i=n-1;i>=n-a;i--) sum += arr[i];
        for(int i=n-a-2;b-->0;i-=2) sum += arr[i];
        return sum;
    }
}
