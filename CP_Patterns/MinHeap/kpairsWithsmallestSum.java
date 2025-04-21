package CP_Patterns.MinHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class kpairsWithsmallestSum {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // Min-heap to store the pairs and their sums
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

        // Add the first pair from nums1 and nums2
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[] {nums1[i], nums2[0], 0});
        }

        // Process the heap
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int num1 = current[0], num2 = current[1], idx2 = current[2];

            result.add(Arrays.asList(num1, num2));

            // If there is another element in nums2 for the current num1, add that pair to the heap
            if (idx2 + 1 < nums2.length) {
                minHeap.offer(new int[] {num1, nums2[idx2 + 1], idx2 + 1});
            }
        }

        return result;
    }
}
/*
 The priority queue (min-heap) stores elements in the form {num1, num2, idx2}:
num1: The current element from nums1 that we are pairing.
num2: The current element from nums2 that we are pairing with num1.
idx2: The index of num2 in nums2. This is used to track which element from nums2 we are working with.
Explanation:
idx2 + 1 < nums2.length:
idx2 is the index of the current element in nums2 that we are pairing with num1.
We check if there is another element in nums2 after the current num2 (which is nums2[idx2]).
Why idx2 + 1 < nums2.length?
The condition idx2 + 1 < nums2.length ensures that the next element in nums2 (i.e., nums2[idx2 + 1]) exists. If idx2 is the last index in nums2, nums2[idx2 + 1] would be out of bounds, and we should stop pairing.
minHeap.offer(new int[] {num1, nums2[idx2 + 1], idx2 + 1}):
If there is another element in nums2 to pair with num1, we add it to the heap. We create a new pair with num1 (the current element from nums1), nums2[idx2 + 1] (the next element in nums2), and the index idx2 + 1 (the index of the next element in nums2).
This ensures that we keep moving through nums2 for the same num1 element.
What Happens in the Whole Process?
Step 1: Initial Pair Insertion

Let's say we start with the pair (num1 = 1, num2 = 1) where:

num1 = nums1[0] = 1
num2 = nums2[0] = 1
idx2 = 0 (because we are pairing nums1[0] with nums2[0])
We add this pair to the min-heap:

minHeap.offer(new int[] {1, 1, 0}); // {num1, num2, idx2}
Step 2: Extract the Smallest Pair and Add Next Pair

Now, let's extract the smallest pair from the heap (which is (1, 1, 0)):

int[] current = minHeap.poll(); // Extracts {1, 1, 0}
int num1 = current[0];  // num1 = 1
int num2 = current[1];  // num2 = 1
int idx2 = current[2];  // idx2 = 0
Now, we want to pair num1 = 1 (from nums1) with the next element in nums2, which is nums2[1] = 2. Since idx2 + 1 < nums2.length is true (idx2 = 0, nums2.length = 3), we add the next pair to the heap:

minHeap.offer(new int[] {num1, nums2[idx2 + 1], idx2 + 1}); // {1, 2, 1}
Now the heap contains:

minHeap = [{1, 2, 1}]
Step 3: Repeat for the Next Pair

We continue this process: every time we extract the smallest pair, we move on to the next element in nums2 for the current num1 element and add that pair to the heap.

In this example:

After extracting (1, 1, 0), we added (1, 2, 1).
Then, we would extract (1, 2, 1) and add (1, 3, 2).
Why Do We Do This?
The goal is to systematically explore all possible pairs starting from the smallest sums first (because nums1 and nums2 are both sorted in non-decreasing order). By using the idx2 value to track our position in nums2 and adding the next pair for the same num1, we ensure that we're considering the next smallest valid pair at each step.

Example Walkthrough:
Given:

nums1 = [1, 1, 2]
nums2 = [1, 2, 3]
k = 2
Heap Initialization:

We add the first pair for each nums1[i]:

minHeap.offer(new int[] {1, 1, 0}); // nums1[0] with nums2[0]
minHeap.offer(new int[] {1, 1, 0}); // nums1[1] with nums2[0]
minHeap.offer(new int[] {2, 1, 0}); // nums1[2] with nums2[0]
Now minHeap = [{1, 1, 0}, {1, 1, 0}, {2, 1, 0}].

First Extraction:

We extract the smallest pair (1, 1, 0):

We add (1, 1) to the result.
We add the next pair (1, 2, 1) (from nums2[1]) to the heap.
minHeap = [{1, 1, 0}, {2, 1, 0}, {1, 2, 1}].

Second Extraction:

We extract the next smallest pair (1, 1, 0):

We add (1, 1) to the result.
We add the next pair (1, 2, 1) (from nums2[1]) to the heap.
At this point, minHeap = [{1, 2, 1}, {2, 1, 0}, {1, 2, 1}].

Conclusion:
The line if (idx2 + 1 < nums2.length) { minHeap.offer(new int[] {num1, nums2[idx2 + 1], idx2 + 1}); } checks if there's another element in nums2 to pair with the current num1, and if so, it adds the next pair to the heap.
This ensures that we are always considering the next smallest valid pair from nums2 for each element in nums1.

 */
