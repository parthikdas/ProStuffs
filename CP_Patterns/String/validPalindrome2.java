package CP_Patterns.String;

public class validPalindrome2 {
    // 680. Valid Palindrome II
    private boolean isPalindrome(String s, int left, int right) {
        while (right > left) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length()-1;
        while(j > i) {
            if(s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                // Check by skipping character at i
                if (isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1)) {
                    return true;
                }
                return false; // If both fail, return false immediately
            }
        }
        return true;
    }
}
