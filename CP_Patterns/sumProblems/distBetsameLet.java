package CP_Patterns.sumProblems;

import java.util.HashMap;

public class distBetsameLet {
    public boolean checkDistances(String s, int[] distance) {
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                if (current == s.charAt(j)) {
                    int distBetween = j - i - 1;
                    if (distBetween != distance[current - 'a']) {
                        return false; // Return false if the distance does not match
                    }
                }
            }
        }
        return true; // Return true if all characters match the distances
    }
    public boolean checkDistances(String s, int[] distance) {
         HashMap<Character, Integer> seen = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (seen.containsKey(ch)) {
                if (distance[ch - 'a'] != i - seen.get(ch) - 1) {
                    return false; // Return false if the distance doesn't match
                }
            } else {
                seen.put(ch, i);
            }
        }
        return true; // Return true if all characters match the distances
    }
}
