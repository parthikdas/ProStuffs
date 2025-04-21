package CP_Patterns.Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;
// 
public class mergekSortedLists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    // Approach 1 - Put all the ele in pq and extract all
    public ListNode mergeKLists(ListNode[] lists) { // O(nlogn), O(n)
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a-b);
        for(ListNode l : lists) {
            ListNode temp = l;
            while(temp!=null) {
                pq.offer(temp.val);
                temp = temp.next;
            }
        }
        ListNode ans = new ListNode(0);
        ListNode temp = ans;
        while (!pq.isEmpty()) {
            temp.next = new ListNode(pq.poll());
            temp = temp.next;
        }
        return ans.next;
    }

    
    // Approach 2 - better min heap - put all the heads in pq 
    public ListNode mergeKLists1(ListNode[] lists) { // O(nlogk), O(k) - better for large inputs
        // PriorityQueue to store the nodes in sorted order
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> a.val - b.val);  // Min-heap by node value
        // Add the head nodes of each list to the priority queue
        for (ListNode l : lists) {
            if (l != null) pq.offer(l); // put all heads
        }
        // Dummy node to simplify handling the head of the merged list
        ListNode head = new ListNode(0);
        ListNode curr = head;
        // Process the nodes in the priority queue
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();  // Get the node with the smallest value
            curr.next = node;  // Add it to the result list
            curr = curr.next;  // Move to the next node in the result list

            // If there is a next node in the list, add it to the priority queue
            if (node.next != null) pq.offer(node.next);
        }

        // Return the merged list starting from the node after the dummy
        return head.next;
    }


    // Approach 3 - Merge sort
    public ListNode mergeKLists2(ListNode[] lists) { // O(nlogk), O(logk) - not better or large inputs
        if(lists.length == 0) return null;
        return mergeList(lists);
    }

    public ListNode mergeList(ListNode[] lists) {
        int n = lists.length;

        if(n == 1) return lists[0];
        if(n == 0) return null;

        ListNode left = mergeList(Arrays.copyOfRange(lists, 0, n/2));
        ListNode right = mergeList(Arrays.copyOfRange(lists, n/2, n));

        return merge(left, right);
    }

    public ListNode merge(ListNode left, ListNode right) {
        ListNode head = new ListNode(-1), curr = head;

        while(left != null && right != null) {
            if(left.val < right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }

        while(left != null) {
            curr.next = left;
            left = left.next;
            curr = curr.next;
        }
        
        while(right != null) {
            curr.next = right;
            right = right.next;
            curr = curr.next;
        }

        return head.next;
    }
}

/*
 Approaches to Solve the Problem
1. Using Min-Heap (Priority Queue) – Efficient Approach
Data Structure Used:

Min-Heap (Priority Queue) to store the smallest element from each list.
Algorithm Used:

Heap-Based Merging (Greedy Approach).
Extract the smallest element from the heap and add its next node back to the heap.
Time Complexity:

Building the heap takes O(k) (inserting the first node from each list).
Extracting and inserting operations take O(logk).
Since there are N total elements (sum of all nodes in all lists), heap operations happen N times.
Total Complexity: O(Nlogk).
Space Complexity: O(k) for storing k elements in the heap.
✅ Best approach for large k values.

2. Using Divide and Conquer
Data Structure Used:

Recursion (for merging pairs of lists).
Algorithm Used:

Recursively divide the list into two halves and merge them using Merge Two Sorted Lists (like merge sort).
Time Complexity:

There are O(logk) levels of merging.
Each merge operation takes O(N) time (similar to merging two lists in merge sort).
Total Complexity: O(Nlogk).
Space Complexity: O(logk) for recursive stack calls.
✅ Best approach for in-place merging when heap is not preferred.

Final Comparison
Approach	   Data Structure Used	  Time Complexity	Space Complexity Best Use Case
Min-Heap       (Priority Queue)	      O(Nlogk)	        O(k)	         When k is large
Divide_Conquer Recursion (Merge Sort) O(Nlogk)	        O(logk)	         When heap is not feasible
🔹 Use Min-Heap for large k values
🔹 Use Divide & Conquer for in-place merging
 */