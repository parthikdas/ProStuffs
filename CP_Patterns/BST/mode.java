package CP_Patterns.BST;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class mode {
    // 501 leet code
    int max = 0; // Start from 0 to handle edge cases more naturally
    int curCount = 0; // Reset current count for each node
    int cur = Integer.MIN_VALUE; // Initially set to a value outside possible node values
    List<Integer> list = new ArrayList<>();
    
    public void in(TreeNode r) {
        if (r == null) return;
        
        in(r.left);  // Visit left subtree
        
        // Handle the current node
        if (r.val == cur) {  // If the value is the same as the previous one
            curCount++;  // Increment count for the current value
        } else {  // If the current value is different from the previous one
            cur = r.val;  // Update current value
            curCount = 1;  // Reset count for the new value
        }
        
        // Update the list of modes based on the current count
        if (curCount > max) {  // If current count exceeds max found so far
            max = curCount;
            list.clear();  // Clear previous modes
            list.add(r.val);  // Add the new mode
        } else if (curCount == max) {  // If the current count equals the max count
            list.add(r.val);  // Add the current value to the list of modes
        }

        in(r.right);  // Visit right subtree
    }
    
    public int[] findMode(TreeNode root) {
        in(root);  // Perform in-order traversal
        
        // Convert the result list to an array
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        
        return ans;
    }
}

/*
 * Traversing the tree in inorder fashion gives sorted array. So all the common elements will be together. We could use HashMap to do it but its gonna make it slow as making frequency table then finding max freq then extracting the values bruh its boring....

Approach

We will perform all our actions inside Inorder method to reduce time, so we will keep track of current ongoing element(cur) and the count of that element (curCount) and the max count found so far. Plus point of this is we can insert those elements here itself rather than doing it laterr..Then at last according to list make the array and return.... Happy Codingggg

Complexity

Time complexity: O(n)
Space complexity:O(n)

 */