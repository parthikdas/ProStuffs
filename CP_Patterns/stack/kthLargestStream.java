package CP_Patterns.BST;

import java.util.PriorityQueue;

public class kthLargestStream {
    // 703. leetcode
    private PriorityQueue<Integer> q;
    private int size;
    public KthLargest(int k, int[] nums) {   
        // Initialize the size variable to be k
        size = k;
        // Create a min-heap priority queue to store the k largest elements
        q = new PriorityQueue<>();
        
        // Add elements from the nums array to the priority queue
        for (int n : nums) {
            q.offer(n);  // Use the add method to maintain the size of the heap
            if(q.size() > size) q.poll();
        }
    }
    
    public int add(int val) {
        if(q.size() < size) q.offer(val); // If the size is not at threshold, insert
        else if(val > q.peek()) { // The value is smaller than the top no need of inserting
            q.offer(val);
            if(q.size() > size) q.poll(); // If the size is increased then remove 
        }
        return q.peek();
    }
}
