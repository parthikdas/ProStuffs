package CP_Patterns.linkedlist;

public class oddEven {
    // 328. Odd Even Linked List
    public ListNode oddEvenList(ListNode head) { // O(n), O(1)
        // If the list is empty or has only one node, return as it is
        if (head == null || head.next == null) {
            return head;
        }

        // Initialize pointers for odd and even lists
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even; // Save the head of the even list to connect later

        // Traverse the list
        while (even != null && even.next != null) {
            odd.next = odd.next.next; // Link odd nodes
            even.next = even.next.next; // Link even nodes

            odd = odd.next; // Move odd pointer
            even = even.next; // Move even pointer
        }

        // Connect the last odd node to the first even node
        odd.next = evenHead;

        return head; // Return the head of the modified list
    }
}
