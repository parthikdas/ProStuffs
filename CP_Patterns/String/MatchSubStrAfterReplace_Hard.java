package CP_Patterns.String;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MatchSubStrAfterReplace_Hard {
    // 2d boolean array
    /*
        map[a][b] = true means 'a' can be replaced with 'b'
        Iterate every substring of s of length equal to sub
        Compare sub.charAt(j) with s.charAt(i+j):
        If equal: continue
        If not equal, check if replacement is allowed
        Return true if all characters match or are replaceable
     */
    public boolean matchReplacement(String s, String sub, char[][] mappings) { // O(n*m), O(1)
        boolean[][] map = new boolean[128][128]; // ASCII char mapping
        for (char[] m : mappings)
            map[m[0]][m[1]] = true;
        int n = s.length(), m = sub.length();
        for (int i = 0; i <= n - m; i++) {
            for (int j = 0; j <= m; j++) {
                if (j == m)
                    return true;
                char c1 = s.charAt(i + j), c2 = sub.charAt(j);
                if (c1 != c2 && !map[c2][c1])
                    break;
            }
        }
        return false;
    }

    // hashmap
    // Same logic but uses map to store valid replacements
    public boolean matchReplacemen1(String s, String sub, char[][] mappings) { // O(n*m), O(k) -> k = total mapping
        Map<Character, Set<Character>> map = new HashMap<>(); // Each char may have various replacement. So use Set
        
        // Populate the mapping dictionary
        for (char[] pair : mappings) {
            map.computeIfAbsent(pair[0], _ -> new HashSet<>()).add(pair[1]);
        }

        int len = sub.length();

        // Check all substrings of s with the same length as sub
        for (int i = 0; i <= s.length() - len; i++) {
            String candidate = s.substring(i, i + len);
            if (isMatch(candidate, sub, map)) return true;
        }

        return false;
    }

    private boolean isMatch(String candidate, String sub, Map<Character, Set<Character>> map) {
        for (int i = 0; i < sub.length(); i++) {
            char sc = sub.charAt(i);
            char tc = candidate.charAt(i);
            if (sc == tc) continue;
            if (!map.containsKey(sc) || !map.get(sc).contains(tc)) return false;
        }
        return true;
    }

    /*
     Working:
        Step-by-step:
        Check if "leet" (after applying allowed mappings) matches any substring of "fool3e7bar":

        Check substring "le3t":

        l == l ✅
        e == e ✅
        e != 3 → is map['e']['3'] == true? ✅
        t != 7 → is map['t']['7'] == true? ✅
        ✅ Found a match → return true
     */
}
