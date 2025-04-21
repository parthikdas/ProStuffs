package CP_Patterns.BST;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {this.val = val;}
}
public class BackTracking {
    public static TreeNode insert(TreeNode root, int val) { // O(h), worst case - O(n)
        if(root == null) return new TreeNode(val);
        if(root.val > val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }
    private static void allPaths(TreeNode root, StringBuilder s, List<String> paths) {
        if(root == null) return; // empty
        s.append(root.val);
        if(root.left == null && root.right == null) { // leaf
            paths.add(s.toString());
        } else {
            allPaths(root.left, s, paths);
            allPaths(root.right, s, paths);
        }
        s.setLength(s.length()-1);
    }
    public static void main(String[] args) {
        TreeNode root = null;
        int a[] = {5, 3, 1, 4, 7, 6, 8};
        for(int i: a) root = insert(root, i);
        List<String> paths = new ArrayList<>();
        allPaths(root, new StringBuilder(""), paths);
        paths.forEach(System.out::println);
    }
    
}
