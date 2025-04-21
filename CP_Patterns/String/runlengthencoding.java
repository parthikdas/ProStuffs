package CP_Patterns.String;

import java.util.HashMap;

public class runlengthencoding {
    public static String usingLinear(String str) { // O(n) time, O(1) space
        StringBuilder ss = new StringBuilder();
        int c = 1; // count first char

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                c++;
            } else {
                ss.append(c).append(str.charAt(i - 1));
                c = 1; // reset counter for new char
            }
        }

        // Append the last group
        ss.append(c).append(str.charAt(str.length() - 1));
        return ss.toString();
    }
    public String usingMap(String str) { // O(n) - time, O(n) as map used
        String s = "aaabbbsbccc"; // 3a3b1s1b3c
        HashMap<Character, Integer> map = new HashMap<>();
       StringBuilder ss = new StringBuilder("");
       for(int i = 0;i<s.length();i++) {
        char ch = s.charAt(i);
        if(!map.containsKey(ch) && i>0) {
            ss.append(""+map.get(s.charAt(i-1)) + s.charAt(i-1));
            map.remove(s.charAt(i-1));
        }
        map.put(ch, map.getOrDefault(ch, 0)+1);
       }
        return ss.toString();
    }

    // 38. Count and say
    public String countAndSay(int n) { // as the string will grow exponentially-> O(2^n), same reason for space -> O(2^n)
        String s = "1";
        while(--n>0) {
            StringBuilder ss = new StringBuilder("");
            int a[] = new int[10];
            char last = ' ';
            for(char ch:s.toCharArray()) {
                if(last!=' ' && last!=ch) { // new char
                    ss.append(a[last - '0']).append(last);
                    a[last-'0'] = 0;
                }
                a[ch-'0']++;
                last = ch;
            }
            // Handle the final group
            ss.append(a[last - '0']).append(last);
            s = ss.toString();
        }
        return s;
    }
}
