package CP_Patterns.BST;

import javax.swing.tree.TreeNode;

public class minAbsDiff {
    // 530 leet code
    private int prev = -1; // to store the previous node value during in-order traversal
    private int minDiff = Integer.MAX_VALUE; // to store the minimum difference

    public void inorder(TreeNode root) {
        if (root == null) return;
        
        // Traverse left subtree
        inorder(root.left);

        // If prev is not -1, calculate the difference with the current node
        if (prev != -1) {
            minDiff = Math.min(minDiff, root.val - prev);
        }

        // Update prev to current node's value
        prev = root.val;

        // Traverse right subtree
        inorder(root.right);
    }
    public int getMinimumDifference(TreeNode root) {
               inorder(root); // Start in-order traversal
        return minDiff; // Return the minimum difference found
    }
}
