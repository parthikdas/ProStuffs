package CP_Patterns.linkedlist;
import java.util.*;

class Node {
    int val;
    Node next;
    Node() {}
    Node(int val) { this.val = val; }
}
/*
    1 2 3 
    1 4 
    2 3 5 6 
 */
public class mergekSortedLists {
    // Logic: using merge sort logic
    public Node mergeList(Node[] lists) {
        int n = lists.length;
        if(n == 1) return lists[0];
        if(n == 0) return null;
        Node left = mergeList(Arrays.copyOfRange(lists, 0, n/2));
        Node right = mergeList(Arrays.copyOfRange(lists, n/2, n));
        return merge(left, right);
    }

    public Node merge(Node left, Node right) {
        Node head = new Node(-1), curr = head;

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
    public static Node mergeKlists2(List<Node> lists) { // O(n log k), O(log k)
        if(lists.size() == 0) return null;
        // return mergeList(lists);
        return null; // use above
    }
    // Logic: put all heads in the heap, put out the min add in ans if not null add next of that head in heap
    public static Node mergeKlists1(List<Node> lists) { // O(n log k), O(k) (heap)
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val)); //  or (a, b) -> a.val - b.val
        for (Node head : lists) if (head != null) pq.offer(head); // Push first node of each list
        Node dummy = new Node(0), tail = dummy;
        while (!pq.isEmpty()) {
            Node minNode = pq.poll();
            tail.next = new Node(minNode.val); // add to ans
            tail = tail.next;
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }
        return dummy.next;
    } 
    // Logic: as we have all sorted lists put the min head in ans
    public static Node mergeKlists(List<Node> lists) { // O(n × k), O(n) (only for result list)
        Node ans = new Node(0);
        Node temp = ans;
        while(true) {
            int min = Integer.MAX_VALUE, ind = -1;
            for(int i = 0; i < lists.size(); i++) {
                if(lists.get(i) == null) continue;
                if(lists.get(i).val < min) {
                    min = lists.get(i).val;
                    ind = i;
                }
            }
            if(ind == -1) break;
            temp.next = new Node(min); // add new node to ans
            temp = temp.next; // inc the pointer
            lists.set(ind, lists.get(ind).next); // inc the pointer of that list as the head is used already
        }
        return ans.next;
    }
    public static void printList(Node list) {
        Node temp = list;
        System.out.println();
        while(temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Node> list = new ArrayList<>();
        /*
         Taking input logic if we dont have array size
         */
        while(sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) break;

            String[] parts = line.split(" ");
            Node head = new Node(0);
            Node temp = head;
            for (String s : parts) {
                temp.next = new Node(Integer.parseInt(s));
                temp = temp.next;
            }
            list.add(head.next);
        }
        list.forEach(a -> printList(a));
        System.out.println();
        Node res = mergeKlists1(list);
        Node temp = res;
        while (temp!=null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }
}
