package CP_Patterns.slidingWindow;
// 1456. Maximum Number of Vowels in a Substring of Given Length
public class maxVowelsSubstr {
    public boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }
    public int maxVowels(String s, int k) {
        int left = 0, right = 0, n = s.length(), c = 0, maxVowels = 0;
        while(right<n) {
            // If the character at the right pointer is a vowel, increment the count
            if (isVowel(s.charAt(right))) {
                c++;
            }

            // If window size exceeds k, shrink the window from the left
            if (right - left + 1 > k) {
                if (isVowel(s.charAt(left))) {
                    c--;
                }
                left++;
            }
            
            // Update the maximum vowel count in the window
            maxVowels = Math.max(maxVowels, c);

            // Expand the window by moving the right pointer
            right++;
        }
        return maxVowels;
    }
}
