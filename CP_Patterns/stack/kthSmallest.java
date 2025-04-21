package CP_Patterns.BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

public class kthSmallest {
    // 230 leetcode
    /*
     * ✅ Time Complexity: O(H + k) (H = height of BST)
✅ Space Complexity: O(H) (stack usage, where H ≈ log N for balanced trees)
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            k--;
            if (k == 0) return curr.val;
            curr = curr.right;
        }
        return -1; // Should not reach here if k is valid
    }


    /*
     * O(N) in the worst case (if the tree is skewed).
O(H + k) in a balanced BST (where H is tree height).
     */
    List<Integer> ans = new ArrayList<>();
    public void preOrder(TreeNode root) {
        if( root == null ) return;
        preOrder(root.left);
        ans.add(root.val);
        preOrder(root.right);
    }
    public int kthSmallest(TreeNode root, int k) {
        preOrder(root);
        return ans.get(k-1);
    }
}


// For kth largest instead of going left first go right first