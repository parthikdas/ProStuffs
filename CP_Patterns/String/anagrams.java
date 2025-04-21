package CP_Patterns.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class anagrams {
    // 242. Valid Anagram
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        char str[] = new char[256];
        for(int i=0;i<s.length();i++) {
            str[s.charAt(i)]++;
            str[t.charAt(i)]--;
        }
        for(char ch: str) {
            if(ch != 0) return false;
        }
        return true;
    }
    // 49. Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            char ch[] = s.toCharArray();
            Arrays.sort(ch);
            String sorted = new String(ch);
            if (!map.containsKey(sorted)) { // if not exist create the list
                map.put(sorted, new ArrayList<>()); // make sorted as key
            }
            map.get(sorted).add(s);
        }
        return new ArrayList<>(map.values());
    }
    // 438. Find All Anagrams in a String
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length(), n = p.length();
        int[] a = new int[26]; // p freq
        int[] t = new int[26]; // freq of the window
        for(char ch : p.toCharArray()) a[ch - 'a']++; // insert the freq
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i<=m-n; i++) {
            if(i == 0) { // make the window
                for(int j = i; j<i+n;j++) t[s.charAt(j)-'a']++;
            } else { // move the window
                t[s.charAt(i-1)-'a']--; // remove the prev
                t[s.charAt(i+n-1)-'a']++; // add the next
            }
            int f = 0;
            for(int o = 0;o<26;o++) if(a[o] != t[o]) {f = 1;break;} // check if same or not
            if(f == 0) ans.add(i); // if same
        }
        return ans;
    }
    // 1347. Minimum Number of steps to make 2 strings anagram
    public int minSteps(String s, String t) {
        int a[] = new int[26];
        
        // Calculate the frequency difference between s and t
        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 'a']++;
            a[t.charAt(i) - 'a']--;
        }

        // Count the number of steps required to match the frequencies
        int count = 0;
        // We only care about the characters that have an extra count in s (positive differences)
        for (int i = 0; i < 26; i++) {
            if (a[i] > 0) {
                count += a[i]; // Add the excess character counts to the result
            }
        }
        return count;
    }
    // 2186. Minimum Number of steps to make 2 strings anagram II
    public int minSteps2(String s, String t) {
        int c =0;
        int[] a = new int[26]; // can use 2nd array as well, then it would be if(a[i]!=v[i]) c+=Math.abs(a[i] - v[i]);
        for(int i = 0 ; i < s.length();i++) a[s.charAt(i) - 'a']++;
        for(int i = 0 ; i < t.length();i++) a[t.charAt(i) - 'a']--;
        for(int i = 0;i<26;i++) c += Math.abs(a[i]);
        return c;
    }

    // 2273. Find Resultant Array After Removing Anagrams
    public List<String> removeAnagrams(String[] words) {
        String lastSeen = "";
        List<String> ans = new ArrayList<>();
        for(String s : words) {
            char ch[] = s.toCharArray();
            Arrays.sort(ch);
            String sorted = new String(ch);

            if(!sorted.equals(lastSeen)) {
                ans.add(s);
                lastSeen = sorted;
            }
        }
        return ans;
    }
    
    // 2514. Count anagrams - Hard
    public int countAnagrams(String s) {
        String[] words=s.split(" ");
        int l=0;
        for(String i:words){
            l=Math.max(l,i.length());
        }
        long[] dp=new long[l+1];
        dp[0]=1;
        for(int i=1; i<=l; i++) {
            dp[i]=(i*dp[i-1])%1000000007;
        }
        long ans=1;
        for(String i:words){
            long count=dp[i.length()];
            int[] freq=new int[26];
            for(int x=0;x<i.length();x++) {
                freq[i.charAt(x)-'a']++;
            }
            for(int f:freq) {
                if(f>1){
                    count = (count * modInverse(dp[f], 1000000007)) % 1000000007; // count is n! and the other part is modulo arithmetic version of fact of freq of distinct chars
                } 
            }
            ans= (ans*count % 1000000007);
        }
        return (int)ans;
    }
        private long modInverse(long a, int mod) {
        long res = 1;
        long base = a;
        int exp = mod - 2;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
