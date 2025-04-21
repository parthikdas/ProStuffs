package CP_Patterns.bitmask;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class binaryLLtoint {
    // 1290. Convert Binary Number in a Linked List to Integer
    public int getDecimalValue(ListNode root) {
        int len = -1;
        ListNode head = root;
        while(head!=null) {
            len++;
            head = head.next;
        }
        int num = 0;
        head = root;
        while(head!=null) {
            if(head.val == 1) num += (int)Math.pow(2,len);
            len--;
            head = head.next;
        }
        return num;
    }
}
