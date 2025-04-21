package CP_Patterns.linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class intersectionof2 {

//   Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
 
    public class Solution {
    // basic idea is to store all the nodes of A with value as key and check if the value
    // matches with B then in the list whether the same node is present or not
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) { // O(n + m*k) , O(n)
            HashMap<Integer, List<ListNode>> map = new HashMap<>();
            while(headA!=null) {
                if(!map.containsKey(headA.val)) map.put(headA.val, new ArrayList<>());
                map.get(headA.val).add(headA);
                headA = headA.next;
            }
            while(headB!=null) {
                if(map.containsKey(headB.val)) {
                    List<ListNode> temp = map.get(headB.val);
                    for(ListNode t : temp) {
                        if(t == headB) return headB;
                    }
                }
                headB = headB.next;
            }
            return null;
        }

        // second method : O(n), O(n)
        public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
            HashSet<ListNode> set = new HashSet<>();
            while (headA != null) {
                set.add(headA);
                headA = headA.next;
            }

            while (headB != null) {
                if (set.contains(headB)) return headB;
                headB = headB.next;
            }
            return null;
        }   

        // third method : fastest O(n) O(1)
        public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) return null;
            
            ListNode a = headA, b = headB;
            
            while (a != b) {
                a = (a == null) ? headB : a.next;
                b = (b == null) ? headA : b.next;
            }
            
            return a;  // Either intersection node or null if no intersection
        }
    }
}

/*
 HashMap Approach – O(n + m*k), O(n)

We use a HashMap to store nodes of headA, using their values as keys. Then, we check each node in headB to see if it exists in the map and matches by reference.

🔹 Drawback? If many nodes have the same value, searching through the list increases complexity.


HashSet Approach – O(n + m), O(n)

We store all nodes of headA in a HashSet, then traverse headB to check if any node exists in the set.

🔹 More optimized than the HashMap approach as we store only nodes (not lists).


Two-Pointer Approach (Optimal) – O(n), O(1)

We use two pointers:
Pointer a starts at headA
Pointer b starts at headB
When a reaches the end, it switches to headB, and vice versa for b.
🔹 Why does this work? Both pointers traverse the same total length, ensuring they meet at the intersection (or null).
 */