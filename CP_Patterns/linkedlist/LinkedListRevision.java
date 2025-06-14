package CP_Patterns.linkedlist;
import java.util.PriorityQueue;

import Node;

class Node {
    int val;
    Node next;
    Node() {}
    Node(int val) {
        this.val = val;
    }
}
class LinkedListRevision {
    // Function to add at last
    public static Node insert1(Node root, int val) {
        if(root == null) return new Node(val);
        Node temp = root;
        while(temp.next != null) temp = temp.next;
        temp.next = new Node(val);
        return root;
    }
    // Function to add at first
    public static Node insert2(Node root, int val) {
        Node newNode = new Node(val);
        newNode.next = root;
        root = newNode;
        return root;
    }
    // Function to print
    public static void printList(Node root) {
        Node temp = root;
        while(temp != null) {
            System.out.print(temp.val + (temp.next!=null ? "->" : ""));
            temp = temp.next;
        }
    }
    /*
        Sort a Linked list:
            1. Create a new list and put the value at apt position by traversing - O(n^2), O(1) - insertion sort
            2. Use PQ then create list - 
                1. We can store it in ascending inside pq and poll and insert now this insert will take O(n^2) so O(n^2logn), O(n)
                2. We can store it in descending order and poll and use insert2 now this insert will take O(n) so O(nlogn), O(n) - better

                -- sort3 will be better than sort1 if u make afunction that inserts based on nodes not values so pq will also be using nodes now its more stable and a better soltuion
            3. Use merge sort - O(nlogn), O(1) -  Bestest solution
     */
    public static Node sort1(Node root) {
        Node newList = null;
        while(root != null) {
            Node temp = root;
            root = root.next;
            if(newList == null || temp.val < newList.val) {
                temp.next = newList;
                newList = temp;
            } else {
                Node tempNewList = newList;
                while (tempNewList.next != null && tempNewList.val < temp.val) tempNewList = tempNewList.next;
                if(tempNewList.next == null) tempNewList.next = temp;
                else {
                    temp.next = tempNewList.next;
                    tempNewList.next = temp;
                }
            }
        }
        return newList;
    }
    public static Node sort2(Node root) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a-b);
        while(root != null) {
            pq.offer(root.val);
            root = root.next;
        }
        while(!pq.isEmpty()) root = insert1(root, pq.poll());
        return root;
    }
    public static Node sort3(Node root) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        while(root != null) {
            pq.offer(root.val);
            root = root.next;
        }
        while(!pq.isEmpty()) root = insert2(root, pq.poll());
        return root;
    }
    public static Node sort4(Node root) {
        if(root == null || root.next == null) return root; // Base case : 0 or 1 elements
        // Step 1: Split the list into two halves
        Node middle = findMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;  // Break the list into two parts

        // Step 2: Recursively sort each half
        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);

        // Step 3: Merge the sorted halves
        return merge(left, right);
    }
    public static Node findMiddle(Node head) { // Split the list into two halves
        Node slow = head;
        Node fast = head;
        // Use the slow and fast pointer method to find the middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public static Node merge(Node left, Node right) { // Merge two sorted linked lists
        Node dummy = new Node(-1);  // Temporary node to avoid dealing with nulls
        Node current = dummy;
        // Merge the two lists by comparing their values
        while (left != null && right != null) {
            if (left.val <= right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
        // If there are remaining elements in either list, append them
        if (left != null) current.next = left;
        else current.next = right;
        return dummy.next;  // Return the merged sorted list
    }
    /*
        Reverse a list: Both O(n), O(1)
            1. Create another list
            2. Use Pointer
     */
    public static Node reverse1(Node root) {
        Node newList = null;
        while(root != null) {
            Node temp = root;
            root = root.next;
            temp.next = newList;
            newList = temp;
        }
        return newList;
    }
    public static Node reverse2(Node root) {
        Node prev = null, cur = root;
        while(cur != null) {
            Node next = cur.next; // Storing next as we are changing cur later
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
    public static void main(String[] args) {
        Node root1 = null;
        Node root2 = null;
        for(int i = 1; i < 5; i++) {
            root1 = insert1(root1, i);
            root2 = insert2(root2, i);
        }
        printList(root2);
        System.out.println();
        root2 = sort4(root2);
        printList(root2);
    }
}