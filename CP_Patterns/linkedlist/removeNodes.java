package CP_Patterns.linkedlist;
// 2487. Remove Nodes From Linked List
public class removeNodes {
    public ListNode removeNodes(ListNode head) { // O(n), O(1)
        // Reverse the list to process from right to left
        head = reverseList(head);
        
        ListNode curr = head;
        int maxTillNow = -1;
        ListNode dummy = new ListNode(-1); // Dummy node to construct the new list
        ListNode newHead = dummy;

        // Traverse the reversed list and build the new list
        while (curr != null) {
            if (curr.val >= maxTillNow) {
                dummy.next = curr; // Keep the current node
                dummy = dummy.next; // Move the dummy pointer
                maxTillNow = curr.val; // Update the max value
            }
            curr = curr.next; // Move to the next node
        }
        
        // Set the next of the last node to null (end of the list)
        dummy.next = null;

        // Reverse the new list to restore the original order
        return reverseList(newHead.next);
    }

    // Helper function to reverse the linked list
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}
