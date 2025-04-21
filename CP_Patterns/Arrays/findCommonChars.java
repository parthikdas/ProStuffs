package CP_Patterns.Arrays;

import java.util.ArrayList;
import java.util.List;

public class findCommonChars {
    // 1002. Find Common Characters
    public List<String> commonChars(String[] words) {
        // Frequency array for the first word
        int[] oriFreq = new int[26];
        for (int i = 0; i < words[0].length(); i++) {
            oriFreq[words[0].charAt(i) - 'a']++;
        }
        // Process each subsequent word
        for (int j = 1; j < words.length; j++) {
            int[] temp = new int[26];
            for (int i = 0; i < words[j].length(); i++) {
                temp[words[j].charAt(i) - 'a']++;
            }
            // Update oriFreq to keep the minimum frequency across all words
            for (int i = 0; i < 26; i++) {
                if (oriFreq[i] > 0 && temp[i] > 0) {
                    oriFreq[i] = Math.min(oriFreq[i], temp[i]);
                } else oriFreq[i] = 0;
            }
        }
        // Collect the common characters based on the minimum frequencies
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            while (oriFreq[i] > 0) {
                ans.add("" + (char) (i + 'a'));  // Add the character multiple times
                oriFreq[i]--;
            }
        }
        return ans;
    }
}
/*
 Solution Overview
We need to find the common characters across all words in the given list. To solve this efficiently, we can use the following approach:

Initialize a frequency array for the first word. This will store the count of each character (from 'a' to 'z') in the word.
Process each subsequent word: For every word, we will update the frequency array by keeping the minimum frequency of each character across all words.
Collect the characters: After processing all words, the frequency array will contain the minimum occurrence of each character across all words. We then collect these characters in a list, each character appearing as many times as its minimum frequency.
Algorithm
Create a frequency array oriFreq for the first word. This will store the count of each character (from 'a' to 'z') in the first word.
For each subsequent word:
Create a temporary frequency array temp and count the occurrences of each character in the word.
Update the oriFreq array by keeping the minimum frequency of each character across the words processed so far.
Finally, collect the characters in the result list, based on their frequency in the oriFreq array.
Time Complexity
O(N * M): N is the number of words and M is the average length of each word. We loop through each word and its characters, updating the frequency array, so the time complexity is proportional to the number of words and the length of each word.
Space Complexity
O(1): The frequency array has a fixed size of 26 (for 'a' to 'z'). The space used is constant, regardless of the input size.
 



more same:
49. Group Anagrams
242. Valid Anagram
350. Intersection of Two Arrays II

*/
