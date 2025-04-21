package CP_Patterns.String;
// 567. Permutation in String
public class PermutationString {
    public boolean checkInclusion(String s1, String s2) {
        int[] a = new int[26];
        int m = s1.length(), n = s2.length();
        for(int i = 0; i < m; i++) a[s1.charAt(i)-'a']++;
        int t[] = new int[26];
        for(int i = 0; i <= n-m; i++) {
            if(i==0) {
                for(int j = i; j < i+m; j++) t[s2.charAt(j)-'a']++;
            } else {
                t[s2.charAt(i-1)-'a']--;
                t[s2.charAt(i+m-1)-'a']++;
            }
            boolean flag = true;
            for(int j = 0; j < 26; j++) if(a[j] != t[j]) { flag = false; break; }
            if(flag) return true;
        }
        return false;
    }
}
/*
 We are using sliding window with hash table. First store all chars of s1 in table and create another hash table which will store the freq of the window.
Loop through s2:

The window is not created at first iteration, So create it
Now for the other iteration slide it i.e. increment the start of the window and add the new element so we dont have to create the window for every element. We are reusing :)
 */