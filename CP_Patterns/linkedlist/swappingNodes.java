package CP_Patterns.linkedlist;
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class swappingNodes {
    // 1721. Swapping Nodes in a Linked List
    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode secondTarget = dummy;
        
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        ListNode firstTarget = fast;
        while (fast != null) {
            fast = fast.next;
            secondTarget = secondTarget.next;
        }

        int temp = firstTarget.val;
        firstTarget.val = secondTarget.val;
        secondTarget.val = temp;

        return dummy.next;
    }
}
