package CP_Patterns.Backtrack;

import java.util.ArrayList;
import java.util.List;
// 93. Restore IP Addresses
// https://www.youtube.com/watch?v=A0E8YGCAfEE&list=PLpIkg8OmuX-KRHVXwqSixQC9UE6DsHnWa&index=12
public class restoreIPAddress {
    public boolean isValid(String s) {
        if (s.length() > 1 && s.startsWith("0")) return false;
        int n = Integer.parseInt(s);
        return n >= 0 && n <= 255;
    }
    public void solve(String s, int i, int parts, StringBuilder cur, List<String> ip) {
        if(parts == 4 && i == s.length()) {
            ip.add(cur.toString().substring(0, cur.length() - 1)); // remove last dot
            return;
        }
        int len0 = cur.length(); // save length for backtracking
        for (int len = 1; len <= 3 && i + len <= s.length(); len++) {
            String substr = s.substring(i, i + len);
            if (isValid(substr)) {
                cur.append(substr).append('.');
                solve(s, i + len, parts + 1, cur, ip);
                cur.setLength(len0); // backtrack
            }
        }
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> ip = new ArrayList<>();
        if(s.length() < 13) solve(s, 0, 0, new StringBuilder(""), ip);
        return ip;
    }
}

/*
 ✅ Time Complexity:
You can place 3 dots to divide the string into 4 parts.
For each segment, you can choose 1, 2, or 3 digits — so the total number of recursive paths is bounded.
The maximum length of the string is 12 (since IPs have at most 12 digits like 255255255255).
Thus, the number of valid recursive combinations is O(3³) = O(27) in the worst case, but:

Each recursive call does substring and append operations which take O(1)–O(n) (string length).
So overall, it's safe to say:
Time Complexity: O(1)
(It's constant because n is small and bounded by 12)
✅ Space Complexity:
You use recursion stack → O(4) max (for 4 parts).
Output list stores valid IPs → in worst case, it's known there can be dozens but still finite.
Space Complexity: O(1) auxiliary,
plus O(result) for storing answers.

 */
/*
 Using string not string builfder but slower
 class Solution {
    public boolean isValid(String s) {
        if (s.length() > 1 && s.startsWith("0")) return false;
        int n = Integer.parseInt(s);
        return n >= 0 && n <= 255;
    }
    public void solve(String s, int i, int parts, String cur, List<String> ip) {
        if(parts == 4 && i == s.length()) {
            ip.add(cur.substring(0, cur.length() - 1)); // remove last dot
            return;
        }
        if(i+1 <= s.length())
            solve(s, i+1, parts+1, cur + s.substring(i, i+1) + '.', ip);
        if(i+2 <= s.length() && isValid(s.substring(i, i+2)))
            solve(s, i+2, parts+1, cur + s.substring(i, i+2) + '.', ip);
        if(i+3 <= s.length() && isValid(s.substring(i, i+3)))
        solve(s, i+3, parts+1, cur + s.substring(i, i+3) + '.', ip);
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> ip = new ArrayList<>();
        if(s.length() < 13) solve(s, 0, 0, "", ip);
        return ip;
    }
}
 */