package CP_Patterns.linkedlist;

import java.util.HashSet;

public class dltNodesFromArr {
    // 3217. Delete Nodes From Linked List Present in Array
    public ListNode modifiedList(int[] nums, ListNode head) {
        HashSet<Integer> set = new HashSet<>();
        for(int n : nums) set.add(n);
        while(set.contains(head.val)) head = head.next;
        ListNode temp = head;
        while(temp!=null && temp.next!=null) {
            if (set.contains(temp.next.val)) {
                // Skip the node that needs to be removed
                temp.next = temp.next.next;
            } else {
                // Move to the next node if the current node should not be removed
                temp = temp.next;
            }
        }
        if(temp!=null && set.contains(temp.val)) temp = null;
        return head;
    }
    /*
     O(n + m) where n is the number of nodes in the list and m is the length of the array nums. The time complexity for inserting into the HashSet is O(m), and traversing the list takes O(n) time.
    Space Complexity:
    O(m) where m is the length of the array nums because we're using a HashSet to store the values from nums.
     */


    // Faster not using set
    public ListNode modifiedList1(int[] nums, ListNode head) {
        // Find the maximum value in nums
        int max = -1;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        
        // Also find the maximum value in the linked list (if needed)
        ListNode temp = head;
        while (temp != null) {
            max = Math.max(temp.val, max);
            temp = temp.next;
        }

        // Create a frequency array large enough to cover all values up to the maximum value
        boolean[] freq = new boolean[max + 1];
        for (int num : nums) {
            freq[num] = true;
        }

        // Skip the nodes at the beginning if they need to be removed
        while(head != null && freq[head.val]) {
            head = head.next;
        }

        // Traverse the list and remove nodes whose values are in freq
         temp = head;
        while (temp != null && temp.next != null) {
            if (freq[temp.next.val]) {
                // Skip the node that needs to be removed
                temp.next = temp.next.next;
            } else {
                // Move to the next node if the current node should not be removed
                temp = temp.next;
            }
        }

        return head;
    }
    /*
     Time Complexity:
Finding the maximum value in the nums array: O(m) where m is the length of nums.
Traversing the linked list to find the maximum value: O(n) where n is the length of the linked list.
Creating the freq array and filling it: O(m).
Traversing the linked list to remove the nodes: O(n).
Overall, the time complexity is O(n + m).
Space Complexity:
The space complexity is O(max + 1) for the freq array, where max is the largest value in either the nums array or the linked list.
     */
}
