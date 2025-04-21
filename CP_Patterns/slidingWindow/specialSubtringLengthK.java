package CP_Patterns.slidingWindow;
/*
 You are given a string s and an integer k.

Determine if there exists a 
substring
 of length exactly k in s that satisfies the following conditions:

The substring consists of only one distinct character (e.g., "aaa" or "bbb").
If there is a character immediately before the substring, it must be different from the character in the substring.
If there is a character immediately after the substring, it must also be different from the character in the substring.
Return true if such a substring exists. Otherwise, return false.

 

Example 1:

Input: s = "aaabaaa", k = 3

Output: true

Explanation:

The substring s[4..6] == "aaa" satisfies the conditions.

It has a length of 3.
All characters are the same.
The character before "aaa" is 'b', which is different from 'a'.
There is no character after "aaa".
Example 2:

Input: s = "abc", k = 2

Output: false

Explanation:

There is no substring of length 2 that consists of one distinct character and satisfies the conditions.

 

Constraints:

1 <= k <= s.length <= 100
s consists of lowercase English letters only.
 */
public class specialSubtringLengthK {
    public boolean hasSpecialSubstring(String s, int k) {
        int n = s.length();
        for(int i = 0; i <= n-k; i++) { // Loop from start to end-k
            char ch = s.charAt(i); // Store it so we dont have to access it a lot of times
            int flag = 1;
            for(int j = i; j < i+k ; j++) { // Loop through the window to check for any distinct chars
                if(s.charAt(j) != ch) {
                    flag = 0;
                    break;
                }
            }
            if(flag == 1) { // if everything is okay
                if (i > 0 && s.charAt(i - 1) == ch) continue; // Left character should be different
                if (i + k < n && s.charAt(i + k) == ch) continue; // Right character should be different
                return true;
            }
        }
        return false;
    }
}
