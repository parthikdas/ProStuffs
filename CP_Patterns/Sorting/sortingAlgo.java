package CP_Patterns.Sorting;

public class sortingAlgo {
    // Selection Sort - find the min put in at cur ind
    public void optimizedSelectionSort(int[] a) { // O(n^2), O(1)
        int n = a.length;  // Assuming a.length = 8 as in your example
        for (int ind = 0; ind < n - 1; ind++) {
            int min = a[ind], index = ind;
            for (int i = ind + 1; i < n; i++) {
                if (a[i] < min) {
                    min = a[i];
                    index = i;
                }
            }
            if (index != ind) {
                // Swap only if we found a smaller element
                a[ind] = a[ind] ^ a[index];
                a[index] = a[ind] ^ a[index];
                a[ind] = a[ind] ^ a[index];
            }
        }
    }
    // Bubble Sort
    public void optimizedBubbleSort(int[] a) { // O(n^2), O(1)
        int n = a.length;  // Assuming a.length = 8 as in your example
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (a[j] > a[j+1]) {
                    a[j] = a[j] ^ a[j+1];
                    a[j+1] = a[j] ^ a[j+1];
                    a[j] = a[j] ^ a[j+1];
                }
            }
        }
    }
    // Insertion Sort - ensures the path left behind is sorted
    public void optimizedInsertionSort(int[] arr) { // O(n^2), O(1)
        // Mutates elements in arr by inserting out of place elements into appropriate
        // index repeatedly until arr is sorted
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            // Shift elements that are greater than key to one position ahead, so need of swapping
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            // Place the key at its correct position
            arr[j + 1] = key;
        }
    }
    // Insertion Sort on linked list, creating a new one
    class Solution {
        class ListNode {
            int val;
            ListNode next;
        }
        public ListNode insertionSortList(ListNode head) {
            if (head == null) return null;
            // Initialize sorted list with the first element
            ListNode sorted = null;
            ListNode current = head;
            // Traverse the original list
            while (current != null) {
                ListNode temp = current;
                current = current.next;
                temp.next = null;
                // Insert temp into the sorted list
                if (sorted == null || temp.val <= sorted.val) {
                    // Insert at the beginning
                    temp.next = sorted;
                    sorted = temp;
                } else {
                    // Find the correct position in the sorted list
                    ListNode trav = sorted;
                    while (trav.next != null && trav.next.val < temp.val) {
                        trav = trav.next;
                    }
                    // Insert after trav
                    temp.next = trav.next;
                    trav.next = temp;
                }
            }
    
            return sorted;
        }
    }
    // Heap Sort

}
