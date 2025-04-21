package CP_Patterns.MinHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class topKFreqEle {
    // Basic idea is to make 2d array of elements and its freq sort on basis of freq and print 
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Build the frequency map using HashMap
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a Min-Heap to store the top k frequent elements
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = 
            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue()); // Acc order, give the comparator

        // Step 3: Add entries from the frequency map into the heap
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);

            // If the heap size exceeds k, remove the element with the lowest frequency, need to do it to maintain top elements
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 4: Extract the k most frequent elements
        List<Integer> resultList = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            resultList.add(minHeap.poll().getKey());
        }

        // Since we want the top k elements, we reverse the result list
        Collections.reverse(resultList);

        // Convert the result List to an array
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }

        return resultArray;
    }
}
