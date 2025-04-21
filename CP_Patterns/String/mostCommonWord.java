package CP_Patterns.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// 819. Most Common Word
public class mostCommonWord {
    public String mostCommonWord(String p, String[] banned) { // O(n) , O(n)
        // Create a set of banned words for easy lookup (convert to lowercase)
        HashSet<String> set = new HashSet<>();
        for(String t : banned) set.add(t.toLowerCase());
        
        // HashMap to store frequency of each word
        HashMap<String, Integer> map = new HashMap<>();
        
        // Preprocess the string: split the string if a non alpha char comes
        // String[] words = p.toLowerCase().split("[^a-zA-Z]+");
        List<String> words = new ArrayList<>();
        int i = 0;
        while (i < p.length()) {
            // Skip non-alphabetic characters
            while (i < p.length() && !Character.isLetter(p.charAt(i))) i++;
            int j = i;
            while (j < p.length() && Character.isLetter(p.charAt(j))) j++;
            if (i < j) {
                words.add(p.substring(i, j).toLowerCase());
            }
            i = j; // Move i to the next character after the extracted word
        }

        // Count occurrences of each word but exclude banned words
        int maxCount = 0;
        String result = "";
        for (String word : words) {
            if (!set.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
                if(map.get(word) > maxCount) {
                    maxCount = map.get(word);
                    result = word;
                }
            }
        }

        // // Find the word with the maximum frequency
        // int maxCount = 0;
        // String result = "";
        // for (Map.Entry<String, Integer> entry : map.entrySet()) {
        //     if (entry.getValue() > maxCount) {
        //         maxCount = entry.getValue();
        //         result = entry.getKey();
        //     }
        // }
        
        return result;
    }
}
