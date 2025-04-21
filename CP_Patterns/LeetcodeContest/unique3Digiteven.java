package CP_Patterns.LeetcodeContest;

import java.util.HashSet;
import java.util.Set;

/*

Companies

Hint
You are given an array of digits called digits. Your task is to determine the number of distinct three-digit even numbers that can be formed using these digits.

Note: Each copy of a digit can only be used once per number, and there may not be leading zeros.

 

Example 1:

Input: digits = [1,2,3,4]

Output: 12

Explanation: The 12 distinct 3-digit even numbers that can be formed are 124, 132, 134, 142, 214, 234, 312, 314, 324, 342, 412, and 432. Note that 222 cannot be formed because there is only 1 copy of the digit 2.

Example 2:

Input: digits = [0,2,2]

Output: 2

Explanation: The only 3-digit even numbers that can be formed are 202 and 220. Note that the digit 2 can be used twice because it appears twice in the array.

Example 3:

Input: digits = [6,6,6]

Output: 1

Explanation: Only 666 can be formed.

Example 4:

Input: digits = [1,3,5]

Output: 0

Explanation: No even 3-digit numbers can be formed.

 
 */
public class unique3Digiteven {
    public int totalNumbers(int[] a) { // O(n^2)
        Set<Integer> uniqueNumbers = new HashSet<>(); // as we need unique 
        int n = a.length;
        
        for (int k = 0; k < n; k++) { // Fix last digit (must be even)
            if (a[k] % 2 != 0) continue; // Skip odd numbers
            
            for (int i = 0; i < n; i++) { // First digit
                if (i == k || a[i] == 0) continue; // First digit cannot be 0 or already used
                
                for (int j = 0; j < n; j++) { // Second digit
                    if (j == i || j == k) continue; // Ensure distinct digits
                    
                    int num = a[i] * 100 + a[j] * 10 + a[k];
                    uniqueNumbers.add(num);
                }
            }
        }
        
        return uniqueNumbers.size(); // Return the count of unique numbers
    }
}
