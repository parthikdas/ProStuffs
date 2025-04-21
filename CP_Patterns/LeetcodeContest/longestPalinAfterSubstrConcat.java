package CP_Patterns.LeetcodeContest;

public class longestPalinAfterSubstrConcat {
    // 3503. Longest Palindrome After Substring Concatenation I
    public int longestPalindrome(String s, String t) {
        int maxLen = 0;
        
        // Generate all possible substrings from s
        for (int i = 0; i <= s.length(); i++) {
            for (int j = i; j <= s.length(); j++) {
                String subS = s.substring(i, j);
                
                // Generate all possible substrings from t
                for (int k = 0; k <= t.length(); k++) {
                    for (int l = k; l <= t.length(); l++) {
                        String subT = t.substring(k, l);
                        
                        String combined = subS + subT;
                        if (isPalindrome(combined)) {
                            maxLen = Math.max(maxLen, combined.length());
                        }
                    }
                }
            }
        }
        
        return maxLen;
    }
    
    private boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
/*
 Let n = s.length() and m = t.length().

Generating substrings of s:
Total substrings = O(n²)
Generating substrings of t for each subS:
Total substrings = O(m²)
Concatenation and palindrome check for each pair:
Let average length of subS + subT = O(n + m) (worst case)

Space Complexity
Each subS, subT, and combined string uses O(n + m) space temporarily.
 */