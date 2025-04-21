package CP_Patterns.MinHeap;

import java.util.HashMap;

public class mostFreqEvenEle {
    // 2404. Leetcode
    public int mostFrequentEven(int[] nums) {
        // Step 1: Build the frequency map for only even numbers
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        
        // Count frequency of even numbers
        for (int num : nums) {
            if (num % 2 == 0) {  // Only consider even numbers
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }
        }

        // Step 2: Check for the most frequent even element
        int mostFrequentEven = -1;
        int maxFrequency = 0;

        for (int num : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(num);

            // If a new most frequent even number is found, or if the frequency is the same but the number is smaller
            if (frequency > maxFrequency || (frequency == maxFrequency && num < mostFrequentEven)) {
                mostFrequentEven = num;
                maxFrequency = frequency;
            }
        }

        // If no even number is found, return -1
        return mostFrequentEven;
    }
}
