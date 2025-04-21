package CP_Patterns.linkedlist;

import java.util.Stack;

public class add2Nos {
    // Approach 1
    /*
     Time Complexity
        Converting linked list to StringBuilder: 
        O(N+M), where N and M are the lengths of l1 and l2.
        Adding two strings: O(max(N,M)) (iterating through both strings). 
        Converting string back to linked list: O(max(N,M)).
        Overall: O(N+M).

     Space Complexity
        StringBuilder storage: O(N+M).
        New linked list storage: O(max(N,M)).
        Total: O(N+M).
     */
    public StringBuilder addStrings(StringBuilder a, StringBuilder b) {
        int carry = 0, i = a.length() - 1, j = b.length() - 1;
        StringBuilder result = new StringBuilder();

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0'; // Convert char to int
            if (j >= 0) sum += b.charAt(j--) - '0';

            result.append(sum % 10);
            carry = sum / 10;
        }

        return result.reverse(); // Since we built it in reverse order
    }
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        StringBuilder a = new StringBuilder("");
        StringBuilder b = new StringBuilder("");
        ListNode temp = l1, temp2 = l2;
        while(temp!=null || temp2!=null ) {
            if(temp!=null) {
                a.append(temp.val);
                temp = temp.next;
            }
            if(temp2!=null) {
                b.append(temp2.val);
                temp2 = temp2.next;
            }
        }
        StringBuilder sum = addStrings(a, b);

        // Convert sum string to linked list
        ListNode ans = new ListNode(sum.charAt(0) - '0'); 
        temp = ans;
        for (int i = 1; i < sum.length(); i++) {
            temp.next = new ListNode(sum.charAt(i) - '0');
            temp = temp.next;
        }

        return ans;
    }

    // Approach 2
    /*
     Time Complexity
        Pushing into stacks: O(N+M)
        Popping and addition: O(max(N,M))
        Creating the linked list: O(max(N,M)).
        Overall: O(N+M).

     Space Complexity
        Stacks storage: O(N+M).
        New linked list storage: O(max(N,M)).
        Total: O(N+M).
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        // Push values from l1 and l2 into stacks
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode head = null;
        int carry = 0;

        // Process the stacks
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int sum = carry;
            if (!stack1.isEmpty()) sum += stack1.pop();
            if (!stack2.isEmpty()) sum += stack2.pop();

            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = head;  // Insert at front
            head = newNode;
        }

        return head;
    }

    // Approach 3
    /*
     Time Complexity
        Reversing both lists: O(N+M)
        Adding numbers: O(max(N,M))
        Reversing the resultt: O(max(N,M)).
        Overall: O(N+M).

     Space Complexity
        Reversed linked lists (modified in place): O(1).
        New linked list storage: O(max(N,M)).
        Total: O(max(N,M)).
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }

    public ListNode helper(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int digit1 = (l1 != null) ? l1.val : 0;
            int digit2 = (l2 != null) ? l2.val : 0;

            int sum = digit1 + digit2 + carry;
            int digit = sum % 10;
            carry = sum / 10;

            ListNode newNode = new ListNode(digit);
            tail.next = newNode;
            tail = tail.next;

            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }

        ListNode result = dummyHead.next;
        return result;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode ans = helper(l1, l2);
        return reverseList(ans);
    }

    // Insight:
    // If modifying the input is allowed, Approach 3 (Reversing List) is optimal due to O(1) extra space.
    // If modification is not allowed, Approach 2 (Using Stacks) is a good alternative.
}