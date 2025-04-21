package CP_Patterns.MinHeap;

import java.util.PriorityQueue;

public class kthSmallestPrimeFactro {
    // 786 leetcode
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        
        // Min-Heap to store the fractions in the form of (value, i, j)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (arr[a[0]] * arr[b[1]] - arr[a[1]] * arr[b[0]]));
        
        // Initially push the first possible fractions (i=0, j>0)
        for (int j = 1; j < n; j++) {
            minHeap.offer(new int[] {0, j});
        }
        
        // Extract k smallest fractions
        for (int i = 0; i < k - 1; i++) {
            int[] top = minHeap.poll();
            int x = top[0];  // Numerator index
            int y = top[1];  // Denominator index
            
            // If we can move to the next fraction for this row (i.e., i increases)
            if (x + 1 < y) {
                minHeap.offer(new int[] {x + 1, y});
            }
        }
        
        // The k-th smallest fraction will be at the top of the heap
        int[] result = minHeap.poll();
        return new int[] {arr[result[0]], arr[result[1]]};
    }
}
/*
 Min-Heap: We use a min-heap to store the fractions, where each fraction is represented by a pair (arr[i], arr[j]), and the heap will always contain the smallest fractions from the list.
Initial Heap Insertion: We start by inserting the smallest possible fraction (arr[0] / arr[j] for all j > 0) into the min-heap.
Heap Operations: After inserting the initial fractions, we keep extracting the smallest element from the heap and then push the next fraction (arr[i+1], arr[j]) into the heap. This continues until we extract the kth smallest fraction.
Step-by-Step Complexity Analysis:
1. Inserting into the Heap:

For each row i, we insert the fraction arr[i] / arr[j] into the heap. Initially, for the first row i = 0, we insert the pairs (arr[0], arr[j]) for all j > 0 into the heap.
The number of initial elements to insert is O(n), where n is the length of the array arr.
Inserting an element into the heap takes O(log n) time. Thus, inserting n-1 elements takes O(n log n) time.

2. Extracting Elements from the Heap:

After inserting the initial elements into the heap, we start extracting the smallest element. Each extraction operation takes O(log n) time because the heap needs to reheapify after each extraction.
For each extraction, we then insert the next fraction (arr[i+1], arr[j]) (if there are more elements left in the sequence) into the heap.
The total number of extractions is k, because we're interested in finding the kth smallest fraction.

3. Total Time Complexity:

Initial heap insertions: O(n log n), because we insert n fractions initially.
Heap extractions: We perform k extractions, and each extraction takes O(log n) time.
Thus, the total time for extractions is O(k log n).
Final Time Complexity:

The overall time complexity is dominated by the sum of the heap insertions and extractions:
O(n log n) for initial insertions
O(k log n) for the extractions
Therefore, the total time complexity is:

O
(
n
log
⁡
n
+
k
log
⁡
n
)
O(nlogn+klogn)
Given that k can be at most n * (n - 1) / 2 (the total number of possible fractions), the worst case time complexity is O(n^2 log n).

Space Complexity:
The space complexity comes from storing the elements in the heap. At any point, the heap contains at most O(n) elements, so the space complexity is O(n).

Conclusion:
Time complexity: O(n log n + k log n)
Space complexity: O(n)
This is efficient, and especially when k is much smaller than n^2, the complexity will be dominated by O(n log n) from the initial heap insertions.
 */