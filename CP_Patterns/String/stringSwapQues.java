package CP_Patterns.String;

public class stringSwapQues {
    // 859 - Buddy String - swap 2 letter to make equal
    public boolean buddyStrings(String s, String g) {
        if(s.length() != g.length()) return false;
        // checking for equal strings
        if(s.equals(g)) {
            // Now we have 2 cases like : aa , ab -> we need atleast one diff, if all char same then false
            int t[] = new int[26];
            for(int i = 0; i<s.length();i++) {
                if(++t[s.charAt(i) - 'a'] == 2) return true; // ab
            }
            return false; // aa
        }
        // When they are not equal we need to see if diff char count is > 2
        int index = -1, diff = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != g.charAt(i)) {
                diff++;
                if(diff == 1) index = i; // first diff found
                else if(diff == 2) { // second diff found
                    if(s.charAt(index) != g.charAt(i) || s.charAt(i) != g.charAt(index)) return false; // to be swapped should be equal
                }
                else if(diff > 2) // not more than 2
                    return false;
            }
        }
        return diff == 2; // only 2 allowed
    }
    
    // 2531 -  Make Number of Distinct Characters Equal
    public boolean isItPossible(String word1, String word2) { // O(n+m) O(1)
        int arr1[] = new int[26];
        int arr2[] = new int[26];
        for (int i = 0; i < word1.length(); i++) arr1[word1.charAt(i) - 'a']++;
        for (int i = 0; i < word2.length(); i++) arr2[word2.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (arr1[i] != 0 && arr2[j] != 0) {
                    // swap
                    arr1[i]--;
                    arr2[j]--;

                    arr1[j]++;
                    arr2[i]++;

                    int c1 = 0;
                    int c2 = 0;
                    for (int k = 0; k < 26; k++) {
                        if (arr1[k] != 0) c1++;
                        if (arr2[k] != 0) c2++;
                    }
                    if (c1 == c2) return true;
                    // revert swap
                    arr1[i]++;
                    arr2[j]++;

                    arr1[j]--;
                    arr2[i]--;
                }
            }
        }
        return false;
    }

    // 1247


    // 1790 - Check if One String Swap Can Make Strings Equal
    public boolean areAlmostEqual(String s, String as) {
        // Check for length
        if(as.length()!=s.length()) return false;
        if(as.equals(s)) return true;
        // Check for same chars in both string should not be diff
        int[] a = new int[26];
        for(int i = 0;i<s.length();i++ ) {
            a[s.charAt(i)-'a']++;
            a[as.charAt(i)-'a']--;
        }
        for(int i = 0;i<26;i++) if(a[i]!=0) return false;
        // Check for indexes that are not same
        int c = 0;
        for(int i = 0;i<s.length();i++) if(s.charAt(i) != as.charAt(i)) c++;
        return c == 2; // should be 2 as we need to swap, more than that is false

        /*
         Faster logic: Storing the old ans
         if(as.equals(s)) return true;
            int c = 0;
            char ch1 = 'a', ch2 = 'b'; // storing the first diff chars
            for(int i = 0;i<s.length();i++) {
                if(s.charAt(i) != as.charAt(i)) {
                    c++;
                    if(c == 1) {
                        ch1 = s.charAt(i);
                        ch2 = as.charAt(i);
                    } else if(c == 2) { // check that swapping is same or not thatswhy checking with diff 
                        if(ch1!=as.charAt(i) || ch2!=s.charAt(i)) return false;
                    } else return false; // cant handle more than 2
                }
            }
         return c != 1; 
        */
    }


    // 777
    // 1156
    // 1963
    // 1202
    // 2366
    // 801
    // 2134
}
