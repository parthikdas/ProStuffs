package CP_Patterns.BST;
import java.util.*;
/*
    # BST patterns:
    - bfs, dfs , level order traversal, depth tracking dfs
    - Lca pattern 
    - path pattern (backtracking)
 */
// Note: Inorder of BST gives a sorted array
/*
        5
        /\
       3  7
      /\  /\
     1 4 6  8
 */
/*
    Insert
    Search
    98. validBST
    450. deleteNode bst
    Traversal:
        PreOrder, InOrder, PostOrder, LevelOrder, levelOrder_dfs, zigzagLevelOrder, DFS, BFS
        1161. Maximum Level Sum of a Binary Tree
        99. Recover Binary Search Tree
        783. Minimum Distance Between BST Nodes
        1038. Binary Search Tree to Greater Sum Tree, 538. Convert BST to Greater Tree <- same sol (reverse inorder)
    Height:
        height - Longest path from the node to a leaf
        110. Balanced Binary Tree
    Depth:
        depth -  Number of edges from the root to the node
        111. Minimum Depth of Binary Tree
        104. Maximum Depth of Binary Tree
    Leaf:
        Leaf Similar Trees - the leaves of 2 trees are same
        Print leaf
        Print leaf in alt order - 1st, nth, 2nd, (n-1)th, 3rd,........
        404. Sum of left leaves
        129. Sum Root to Leaf Numbers - amazon asked - root to all leafs path no sum
    Construction:
        1008. Construct Binary Search Tree from Preorder Traversal (DFS to BST)
        108. Convert Sorted Array to Binary Search Tree (inOrder to BST)
        1382. Balance a Binary Search Tree
    View:
        Top view - level order, dfs
        Left view - level order, dfs
        Right view - level order, dfs
    226. Invert Tree - swapping of left and right children
    101. Symmetric Tree - left side is mirror of right
    938. Range Sum of BST - sum of all nodes b/w a range
    222. Count Complete Tree Nodes - count nodes
    112. Path Sum - path to target sum
    230. kthSmallest bst
    2583. Kth Largest Sum in a Binary Tree
    2415. Reverse Odd Levels of Binary Tree
    783. Minimum Distance Between BST Nodes
    1305. All Elements in Two Binary Search Trees
    LCA:
        235. Lowest Common Ancestor of a Binary Search Tree <- 235, 236, 865, 1123 same logic
        236. Lowest Common Ancestor of a Binary Tree
        865. Smallest Subtree with all the Deepest Nodes, 1123. Lowest Common Ancestor of Deepest Leaves <- same solution (depth track dfs)
    BackTrack:
        257. Binary Tree Paths - 988 same logic same backtrack
        988. Smallest String Starting From Leaf - backtrack
    LCA + BackTrack:
        2096. Step-By-Step Directions From a Binary Tree Node to Another

    971. Flip Binary Tree To Match Preorder Traversal - backtrack
    951. Flip Equivalent Binary Trees - backtrack
    105. Construct Binary Tree from Preorder and Inorder Traversal - backtrack
    863. All Nodes Distance K in Binary Tree - backtrack
    1130. Minimum Cost Tree From Leaf Values - Dp
    124. Binary Tree Maximum Path Sum - hard - Dp
    2867. Count Valid Paths in a Tree - hard - Dp
    113. Path Sum II - Dp, backtrack
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {this.val = val;}
}
public class AllQue {
    public static TreeNode insert(TreeNode root, int val) { // O(h), worst case - O(n)
        if(root == null) return new TreeNode(val);
        if(root.val > val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }
    //---------------------------------------------------------------------------------------
    public static boolean search(TreeNode root, int val) {
        if(root == null) return false;
        if(root.val > val) return search(root.left, val);
        if(root.val < val) return search(root.right, val);
        return true;
    }
    //---------------------------------------------------------------------------------------
    public static boolean validBST(TreeNode root, int min, int max) {
        if(root == null) return true; // Base case: an empty tree is a valid BST
        if(root.val < min || root.val > max) return false; // if out of range
        return validBST(root.left, min, root.val) && validBST(root.right, root.val, max);
    }
    //---------------------------------------------------------------------------------------
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key ) {
            // Case 1: Node is a leaf
            if (root.left == null && root.right == null) {
                return null; // returning null will make the pointer as null of the parent node
            }
            // Case 2: Node has one child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // Case 3: Node has two children
            // Find the in-order successor (smallest in the right subtree)
            TreeNode successor = findMin(root.right);
            root.val = successor.val;  // Copy the successor's value
            root.right = deleteNode(root.right, successor.val);  // Delete the successor
        } else if(root.val > key ) root.left = deleteNode(root.left, key);
        else root.right = deleteNode(root.right, key);
        return root;
    }
    // Helper method to find the minimum node in a BST
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
    //---------------------------------------------------------------------------------------
    // TRAVERSAL
    //---------------------------------------------------------------------------------------
    public static void preOrder(TreeNode root) { // 5 3 1 4 7 6 8 - DFS - O(n)
        if(root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    //---------------------------------------------------------------------------------------
    public static void inOrder(TreeNode root) { // 1 3 4 5 6 7 8 - Sorted - O(n)
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }
    public static void inOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) { // Start from the root node
            while (root != null) { // Go to the leftmost node
                stack.push(root);
                root = root.left;
            }
            // Pop the top node from the stack (this is the node we visit)
            root = stack.pop();
            System.out.print(root.val + " ");
            // Move to the right subtree
            root = root.right;
        }

    }
    //---------------------------------------------------------------------------------------
    public static void postOrder(TreeNode root) { // 1 4 3 6 8 7 5 - O(n)
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }
    //---------------------------------------------------------------------------------------
    public static void levelOrder(TreeNode root) { // 5 3 7 1 4 6 8 - BFS - O(n)
        if(root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                System.out.print(cur.val + " ");
                if(cur.left != null) q.add(cur.left);
                if(cur.right != null) q.add(cur.right);
            }
        }
    }
    public static void levelOrder_dfs(TreeNode r, int l, List<List<Integer>> ll) {
        if(r == null) return;
        int size = ll.size();
        if (size < l + 1) ll.add(new ArrayList<>());
        ll.get(l).add(r.val);
        levelOrder_dfs(r.left, l+1, ll);
        levelOrder_dfs(r.right, l+1, ll);
    }
    //---------------------------------------------------------------------------------------
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) { // 5 7 3 1 4 6 8 - BFS - O(n)
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean flag = false;  // flag to alternate the level order
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                row.add(cur.val);
                // Add children in the appropriate order based on the flag
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
            if (flag) Collections.reverse(row); // If the flag is true, reverse the row (right to left)
            // Note: if u try to add the ele based on flag then the insertion of ele will not be correct we just need print in rev order not putting the ele in stack in rev so Collection.rev
            flag = !flag; // Toggle the flag after each level
            ans.add(row); // Add the current row to the result
        }

        return ans;
    }
    //---------------------------------------------------------------------------------------
    public static void DFS(TreeNode root) { // 5 3 1 4 7 6 8 - iterative version of DFS - O(n)
        if(root == null) return;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()) {
            TreeNode cur = s.pop();
            System.out.print(cur.val + " ");
            if(cur.right != null) s.add(cur.right);
            if(cur.left != null) s.add(cur.left);
        }
    }
    //---------------------------------------------------------------------------------------
    int maxLevel = 0;
    public void levelOrder_dfs(TreeNode r, int l, int[] ll) {
        if(r == null) return;
        ll[l] += r.val;
        maxLevel = Math.max(l, maxLevel);
        levelOrder_dfs(r.left, l+1, ll);
        levelOrder_dfs(r.right, l+1, ll);
    }
    public int maxLevelSum(TreeNode root) { // O(n), O(h unbalanced) or O(logn balanced)
        int ll[] = new int[10000]; // using array as we know limit or else used list
        levelOrder_dfs(root, 0, ll);
        int max = Integer.MIN_VALUE, ind = 0;
        for(int i = 0; i <= maxLevel; i++) {
            if(ll[i] > max) {
                max = ll[i];
                ind = i;
            }
        }
        return ind+1;
    }
    //---------------------------------------------------------------------------------------
    TreeNode first = null, second = null, prev1 = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        inorder2(root); // logic : find the 2 abnormalitites and swap it
        // Swap the two node values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    private void inorder2(TreeNode node) {
        if (node == null) return;
        inorder2(node.left);
        if (prev1.val > node.val) { // inorder gives sorted so no chance normally for this eg: 123 ok 132 notOk
            if (first == null) first = prev1; // first time found
            second = node; // always update the second one
        }
        prev1 = node;
        inorder2(node.right);
    }
    //---------------------------------------------------------------------------------------
    private int prev2 = -1; // to store the previous node value during in-order traversal
    private int minDiff1 = Integer.MAX_VALUE; // to store the minimum difference, ans
    public void inorder3(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev2 != -1) minDiff1 = Math.min(minDiff1, root.val - prev2);
        prev = root.val;
        inorder(root.right);
    }
    //---------------------------------------------------------------------------------------
    int sum = 0;
    private void reverseInorder(TreeNode node) {
        if (node == null) return;
        // Traverse right first (larger values)
        reverseInorder(node.right);
        // Update the current node
        sum += node.val;
        node.val = sum;
        // Traverse left (smaller values)
        reverseInorder(node.left);
    }
    //---------------------------------------------------------------------------------------
    // HEIGHT
    //---------------------------------------------------------------------------------------
    public static int height(TreeNode root) {
        if(root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    //---------------------------------------------------------------------------------------
    public int checkHeight(TreeNode root) { // without using variable
        if (root == null) return 0; // Base case: null node has height 0
        int leftHeight = checkHeight(root.left);
        int rightHeight = checkHeight(root.right);
        // If either subtree is unbalanced, propagate -1
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        // Return the height of the current node
        return 1 + Math.max(leftHeight, rightHeight);
    }
    public int Tree_height(TreeNode root){ // with using variable
        if(root == null) return 0; 
        int left_height = Tree_height(root.left);
        if(left_height == -1) return -1;
        int right_height = Tree_height(root.right);
        if(right_height == -1) return -1;
        int ans = 0;
        if(Math.abs(left_height - right_height)>1) ans = -1;
        else ans = 1 + Math.max(left_height,right_height);
        return ans;
    }
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }
    //---------------------------------------------------------------------------------------
    // DEPTH
    //---------------------------------------------------------------------------------------
    public static int depth(TreeNode root, int val, int c) {
        if(root == null) return 0;
        if(root.val == val) return c;
        if(root.val > val) return depth(root.left, val, c+1);
        else return depth(root.right, val, c+1);
    }
    //---------------------------------------------------------------------------------------
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right==null) return 1;
        int ans = Integer.MAX_VALUE;
        if(root.left !=null) ans = Math.min(ans, 1+minDepth(root.left));
        if(root.right !=null) ans = Math.min(ans, 1+minDepth(root.right));
        return ans;
    }
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;  // Corrected: return 0 for null nodes
        // If either left or right child is null, only consider the other subtree
        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);
        // For both children present, take the minimum depth
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
    //---------------------------------------------------------------------------------------
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;  // Base case: height of an empty tree is 0
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));  // Max height formula
    }
    public int maxDepth1(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right==null) return 1;
        int ans = Integer.MIN_VALUE;
        if(root.left !=null) ans = Math.max(ans, 1+maxDepth(root.left));
        if(root.right !=null) ans = Math.max(ans, 1+maxDepth(root.right));
        return ans;
    }
    //---------------------------------------------------------------------------------------
    // LEAF
    //---------------------------------------------------------------------------------------
    private void collectLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) return;        
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }
        collectLeaves(root.left, leaves);
        collectLeaves(root.right, leaves);
    }
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        collectLeaves(root1, leaves1);
        collectLeaves(root2, leaves2);
        return leaves1.equals(leaves2);
    }
    //---------------------------------------------------------------------------------------
    public static void printLeaf(TreeNode root) { // 1 4 6 8
        if(root == null) return;
        printLeaf(root.left);
        if(root.left == null && root.right == null) System.out.print(root.val + " ");
        printLeaf(root.right);
    }
    //---------------------------------------------------------------------------------------
    public static void printLeafinAltOrder(TreeNode root) { // 1 8 4 6
        if(root == null) return;
        // Approach 1 using extra space:
        List<Integer> l = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        s.add(root);
        while(!s.isEmpty()) {
            TreeNode cur = s.pop();
            if(cur.right!=null) s.add(cur.right);
            if(cur.left!=null) s.add(cur.left);
            if(cur.left == null && cur.right == null) l.add(cur.val);
        }
        for(int i = 0; i < l.size()/2;i++) System.out.print(l.get(i) + " " + l.get(l.size()-i-1) + " ");
    }
    //---------------------------------------------------------------------------------------
    public static int sumOfLeftLeaves(TreeNode r) { // using inorder u can use any
        if (r == null) return 0;
        int sum = 0;
        sum += sumOfLeftLeaves(r.left);
        if(r.left != null && r.left.left == null && r.left.right == null) {
            sum+=r.left.val;
        }
        sum += sumOfLeftLeaves(r.right);
        return sum;
    }
    //---------------------------------------------------------------------------------------
    public static int sumabc(TreeNode root,int currentSum){
        if (root==null) return 0;
        currentSum = currentSum * 10 + root.val;
        if (root.left==null && root.right==null) return currentSum;
        return sumabc(root.left,currentSum) + sumabc(root.right,currentSum);
    }
    //---------------------------------------------------------------------------------------
    // CONSTRUCTION
    //---------------------------------------------------------------------------------------
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        for(int n: preorder) root = insert(root, n);
        return root;
    }
    //---------------------------------------------------------------------------------------
    public TreeNode bstFromInorder(int a[], int l, int r) { // using binary search to get to the middle, as in inorder root is in middle always
        if(l > r) return null;
        int mid = l + (r-l) / 2;
        TreeNode node = new TreeNode(a[mid]);
        node.left = bstFromInorder(a,l,mid-1);
        node.right = bstFromInorder(a,mid+1,r);
        return node;
    }
    //---------------------------------------------------------------------------------------
    List<Integer> a = new ArrayList<>();
    public TreeNode bstFromInorder(int l, int r) { // using binary search to get to the middle, as in inorder root is in middle always
        if(l > r) return null;
        int mid = l + (r-l) / 2;
        TreeNode node = new TreeNode(a.get(mid));
        node.left = bstFromInorder(l,mid-1);
        node.right = bstFromInorder(mid+1,r);
        return node;
    }
    public void inOrder1(TreeNode root) { // 1 3 4 5 6 7 8 - Sorted - O(n)
        if(root == null) return;
        inOrder(root.left);
        a.add(root.val);
        inOrder(root.right);
    }
    public TreeNode balanceBST(TreeNode root) {
        inOrder(root);
        return bstFromInorder(0,a.size()-1);
    }
    //---------------------------------------------------------------------------------------
    // VIEW
    //---------------------------------------------------------------------------------------
    public static void topView_LevelOrder(TreeNode r){
        if(r == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(r);
        while (!q.isEmpty()) {
            int l = q.size();
            for(int i = 0; i < l; i++) {
                TreeNode cur = q.poll();
                System.out.print(cur.val + " ");
                if(i == 0 && cur.right != null) q.add(cur.right); // q takes opp
                if(i == l-1 && cur.left != null) q.add(cur.left);
            }
        }
    } 
    public static void topView_dfs(TreeNode r, int l){
        if(r == null) return;
        if(l == 0 || l == 1)  topView_dfs(r.left, 1);
        System.out.print(r.val + " ");
        if(l == 0 || l == 2)  topView_dfs(r.right, 2);
    }

    public static void leftView_LevelOrder(TreeNode r){
        if(r == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(r);
        while (!q.isEmpty()) {
            int l = q.size();
            for(int i = 0; i < l; i++) {
                TreeNode cur = q.poll();
                if(i == 0) System.out.print(cur.val + " ");
                if(cur.right != null) q.add(cur.right); // q takes opp
                if(cur.left != null) q.add(cur.left);
            }
        }
    }
    public static void leftView_dfs(TreeNode r, int level, int[] highestLevel){ // Even though highestLevel is updated, its value doesn’t persist in the recursive calls because primitives in Java are passed by value. so array
        if(r == null) return;
        if(level > highestLevel[0]) {
            highestLevel[0] = level;
            System.out.print(r.val + " ");
        }
        leftView_dfs(r.right, level+1, highestLevel);
        leftView_dfs(r.left, level+1, highestLevel);
    }

    public static void rightView_LevelOrder(TreeNode r){
        if(r == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(r);
        while (!q.isEmpty()) {
            int l = q.size();
            for(int i = 0; i < l; i++) {
                TreeNode cur = q.poll();
                if(i == l-1) System.out.print(cur.val + " ");
                if(cur.right != null) q.add(cur.right); // q takes opp
                if(cur.left != null) q.add(cur.left);
            }
        }
    }
    public static void rightView_dfs(TreeNode r, int level, int[] highestLevel){
        if(r == null) return;
        if(level > highestLevel[0]) {
            highestLevel[0] = level;
            System.out.print(r.val + " ");
        }
        rightView_dfs(r.left, level+1, highestLevel);
        rightView_dfs(r.right, level+1, highestLevel);
    }
    //---------------------------------------------------------------------------------------
    public static TreeNode invertTree(TreeNode root) { // 8 7 6 5 4 3 1
        if (root == null) {
            return null;
        }
        // Swap the left and right children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // Recursively invert the left and right subtrees
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    //---------------------------------------------------------------------------------------
    public static boolean symmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
        return true;
        // For two trees to be mirror images, the following
        // three conditions must be true
        // 1.) Their root node's key must be same
        // 2.) left subtree of left tree and right subtree of
        // right tree have to be mirror images
        // 3.) right subtree of left tree and left subtree of
        // right tree have to be mirror images
        if (root1 != null && root2 != null && root1.val == root2.val)
            return symmetric(root1.left, root2.right) && symmetric(root1.right, root2.left);
        // if none of above conditions is true then root1 and root2 are not mirror images
        return false;
    }
    //---------------------------------------------------------------------------------------
    public static int rangeSum(TreeNode r, int l, int h) { // using inorder traversal u can use any traversal,  O(n), O(h) can be O(n) if tree unbalanced
        if (r == null) return 0;  // If the node is null, return 0
        int sum = 0;
        sum += rangeSum(r.left, l, h); // Traverse left subtree and get the sum
        if (r.val >= l && r.val <= h) sum += r.val; // If the current node value is within the range [low, high], add it to the sum
        sum += rangeSum(r.right, l, h); // Traverse right subtree and get the sum
        return sum; // Return the accumulated sum
    }
    //---------------------------------------------------------------------------------------
    public int countNodes(TreeNode root) {
        if(root==null) return 0;
        if(root.left == null && root.right == null) return 1;
        return 1+countNodes(root.left) + countNodes(root.right);
    }
    //---------------------------------------------------------------------------------------
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        if(root.left == null && root.right == null) return root.val == targetSum; // Check only if its a leaf
        return hasPathSum(root.left,targetSum - root.val) || hasPathSum(root.right, targetSum-root.val);
    }
    //---------------------------------------------------------------------------------------
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
    // recur version
    int res = 0, count = 0;  // To track how many nodes we've processed so far
    public void inOrder(TreeNode root, int k) {
        if (root == null) return;
        inOrder(root.left, k);// Traverse the left subtree
        count++;
        if (count == k) { res = root.val; return; }
        inOrder(root.right, k);
    }
    // For kth largest swap left right lines
    //---------------------------------------------------------------------------------------
    public long kthLargestLevelSum(TreeNode root, int k) { // O(nlogk), O(n)
        PriorityQueue<Long> minHeap = new PriorityQueue<>(); // O(k) - space
        Queue<TreeNode> q = new LinkedList<>(); // O(n) - space
        q.add(root);
        while (!q.isEmpty()) {// bfs O(n)
            int levelSize = q.size();
            long sum = 0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = q.poll(); // poll O(logk)
                sum += current.val;
                if (current.left != null) q.add(current.left);
                if (current.right != null) q.add(current.right);
            }
            minHeap.offer(sum); // add O(logk)
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.size() < k ? -1 : minHeap.peek();
    }
    //---------------------------------------------------------------------------------------
    // Reverse odd levels 
    // Bfs iterative O(n), O(n)
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 1;
        while (!q.isEmpty()) {
            int row = q.size();
            TreeNode[] levelNodes = new TreeNode[row]; // as array is faster than list, worst case space is O(n/2)
            for (int i = 0; i < row; i++) {
                TreeNode cur = q.poll();
                levelNodes[i] = cur;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            if ((level & 1) == 0) { // if even level swap, O(k)
                int start = 0, end = row - 1;
                while (start < end) {
                    int temp = levelNodes[start].val;
                    levelNodes[start].val = levelNodes[end].val;
                    levelNodes[end].val = temp;
                    start++;
                    end--;
                }
            }
            level++;
        }
        return root;
    }
    // DFS recur O(n), O(h) -> height of tree, balanced tree-O(logn), unbalanced(O(n))
    public TreeNode reverseOddLevels1(TreeNode root) {
        dfs(root.left,root.right,1);
        return root;
    }
    void dfs(TreeNode lnode,TreeNode rnode,int lvl){
        if(lnode == null || rnode == null) 
            return;
        if((lvl & 1) == 1){
            int t = lnode.val;
            lnode.val = rnode.val;
            rnode.val = t;
        }
        dfs(lnode.left,rnode.right,lvl+1);
        dfs(lnode.right,rnode.left,lvl+1);
    }
    //---------------------------------------------------------------------------------------
    private int prev = -1; // to store the previous node value during in-order traversal
    private int minDiff = Integer.MAX_VALUE; // to store the minimum difference
    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != -1) {
            minDiff = Math.min(minDiff, root.val - prev);
        }
        prev = root.val;
        inorder(root.right);
    }
    //---------------------------------------------------------------------------------------
    public static void inOrder(TreeNode root, List<Integer> ans) {
        if(root == null) return;
        inOrder(root.left, ans);
        ans.add(root.val);
        inOrder(root.right, ans);
    }
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        inOrder(root1, ans);
        inOrder(root2, ans);
        Collections.sort(ans);
        return ans;
    }    
    //---------------------------------------------------------------------------------------
    // LCA
    //---------------------------------------------------------------------------------------
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null; // If the root is null, return null
        // If both p and q are smaller than root, LCA must be in the left subtree
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        // If both p and q are greater than root, LCA must be in the right subtree
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        // If p and q are on opposite sides, or one of them is the root, return the root
        return root;
    }
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) { //  in below format
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
    //---------------------------------------------------------------------------------------
    public TreeNode lowestCommonAncestorBT(TreeNode root, TreeNode p, TreeNode q) { // O(n), O(h) - recursion stack height
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestorBT(root.left, p, q);
        TreeNode right = lowestCommonAncestorBT(root.right, p, q);
        if(left == null) return right; // That means p and q are not found in the left subtree, so return right
        if(right == null) return left;
        return root; // It means p and q are split across left and right → So the current node (root) is their Lowest Common Ancestor. Imagine for 1st node i.e. root
    }
    //---------------------------------------------------------------------------------------
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    // Depth aware dfs
    private TreeNode dfs(TreeNode root, int level, int maxDepth) { // This logic is used in LCA problems, Tree Dp
        if (root == null) return null;
        if (level == maxDepth) return root;
        TreeNode left = dfs(root.left, level + 1, maxDepth);
        TreeNode right = dfs(root.right, level + 1, maxDepth);
        if(left == null) return right;
        if(right == null) return left;
        return root; // if both left right contains subtrees at lowest level this is the one
    }
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        int maxDepth = maxDepth2(root); // get the max height
        return dfs(root, 1, maxDepth);
    }
    //---------------------------------------------------------------------------------------
    // BACKTRACK
    //---------------------------------------------------------------------------------------
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> output = new ArrayList();
        buildBinaryTreePaths(root, new StringBuilder(), output);
        return output;
    }
    public void buildBinaryTreePaths(TreeNode root, StringBuilder sb, List<String> output) {
        if(root == null) return;
        int length = sb.length();
        sb.append(root.val);
        if(root.left == null && root.right == null) output.add(sb.toString()); // this is a leaf
        else {
            sb.append("->");
            buildBinaryTreePaths(root.left, sb, output);
            buildBinaryTreePaths(root.right, sb, output);
        }
        sb.setLength(length);  // Once you've finished exploring a child (or both children), you reset the StringBuilder back to its previous length to backtrack and avoid affecting the path for the next sibling node or the parent node.
    }
    //---------------------------------------------------------------------------------------
    public String smallestFromLeaf(TreeNode root) {
        StringBuilder smallest = new StringBuilder();
        dfs(root, new StringBuilder(), smallest);
        return smallest.toString();
    }
    /*
     example of beloe logic:
        Start at 'a' → path = "a"
        Go left to 'b' → path = "ab"
        'b' is a leaf → reverse: "ba", check if it's smallest
        Backtrack: remove 'b' → path = "a" ✅
        Go right to 'c' → path = "ac"
        'c' is a leaf → reverse: "ca", check
        Backtrack: remove 'c' → path = "a"

        without backtrack line : abac keep on adding
     */
    private void dfs(TreeNode node, StringBuilder path, StringBuilder smallest) { // O(N²), O(H) 
        if (node == null) return;
        // Append current node's character to the path
        path.append((char)('a' + node.val));
        // If it's a leaf node, compare and update smallest
        if (node.left == null && node.right == null) {
            String currentString = path.reverse().toString(); // Reverses the path to get leaf-to-root direction.
            if (smallest.length() == 0 || currentString.compareTo(smallest.toString()) < 0) { // Compares the reversed string with smallest
                smallest.setLength(0); // clearing the old value so next line set the new value
                smallest.append(currentString); // If it's smaller, replaces the current smallest with this new one.
            }
            path.reverse(); // backtrack by reversing again
        }
        // Recursively traverse left and right subtrees
        dfs(node.left, path, smallest);
        dfs(node.right, path, smallest);
        // Backtrack: remove the current node's character from the path
        path.setLength(path.length() - 1); // After traversing both children, removes the last character from path (backtracking).
    }
    //---------------------------------------------------------------------------------------
    // LCA + BackTrack
    //---------------------------------------------------------------------------------------
    public TreeNode lca(TreeNode r, int s, int d) { // O(n)
        if(r == null || r.val == s || r.val == d) return r;
        TreeNode left = lca(r.left,s,d);
        TreeNode right = lca(r.right,s,d);
        if(left == null) return right;
        if(right == null) return left;
        return r;
    }
    public boolean getPath(TreeNode root, int target, StringBuilder sb) { // worst O(n)
        if (root == null) return false;
        if (root.val == target) return true;

        sb.append('L');
        if (getPath(root.left, target, sb)) return true;
        sb.setLength(sb.length() - 1); // backtrack

        sb.append('R');
        if (getPath(root.right, target, sb)) return true;
        sb.setLength(sb.length() - 1); // backtrack

        return false;
    }
    public String getDirections(TreeNode root, int startValue, int destValue) { // O(n), space: O(n for unbalanced) or O(logn  for balanced)
        // Step 1: Find LCA
        TreeNode lca = lca(root, startValue, destValue);
        // Step 2: Get paths from LCA to both nodes
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();

        getPath(lca, startValue, pathToStart);
        getPath(lca, destValue, pathToDest);

        // Convert pathToStart to "U"
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < pathToStart.length(); i++) {
            result.append('U');
        }
        result.append(pathToDest);
        return result.toString();
    }
    public static void main(String[] args) {
        TreeNode root = null;
        int a[] = {5, 3, 1, 4, 7, 6, 8};
        for(int i: a) root = insert(root, i);
        System.out.println("PreOrder: ");
        preOrder(root);
        System.out.println("\nInOrder: ");
        inOrder(root);
        System.out.println("\nPostOrder: ");
        postOrder(root);
        System.out.println("\nLevelOrder: ");
        levelOrder(root);
        System.out.println("\nDFS iterative: ");
        DFS(root);
        System.out.println();
        System.out.println("Search 8: " + search(root, 8));
        System.out.println("height:" + height(root));
        System.out.println("Depth 8: " + depth(root, 8, 0));
        System.out.print("ValidBST: " + validBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("\nAll Leafs: ");printLeaf(root);
        System.out.println("\nAll Leafs in Alt order: ");printLeafinAltOrder(root);
        System.out.println("\nInverted Tree inorder: ");
        invertTree(root);
        inOrder(root);
        System.out.println("Tree is symmetric : " + symmetric(root.left, root.right));
        System.out.println("Range sum between 4 and 6 : "+ rangeSum(root, 4, 7));
        System.out.println("Sum of left leaves : " + sumOfLeftLeaves(root));
        System.out.println("Sum of all path to leaf : " + sumabc(root, 0));
        System.out.println("Top View Level Order : ");
        topView_LevelOrder(root);
        System.out.println();
        System.out.println("Top View DFS :");
        topView_dfs(root, 0);
        System.out.println();
        System.out.println("Left View Level Order : ");
        leftView_LevelOrder(root);
        System.out.println();
        System.out.println("Left View DFS : ");
        leftView_dfs(root,0, new int[]{-1});
        System.out.println();
        System.out.println("Right View Level Order : ");
        rightView_LevelOrder(root);
        System.out.println();
        System.out.println("Right View DFS : ");
        rightView_dfs(root,0, new int[]{-1});
    }
}
/*
 Categories divided by chatgpt of the above list:
 1. Basic Traversals and Search Algorithms
PreOrder, InOrder, PostOrder, LevelOrder – These are foundational for understanding tree traversal. Make sure you can implement them recursively and iteratively.
DFS, BFS – Crucial for graph/tree exploration. Ensure you know how to apply these on trees and understand the time complexities.
Search – A solid understanding of searching for elements in trees is fundamental (like binary search trees).
2. Tree Properties
Height – Longest path from a node to a leaf is useful for problems like Balance Tree or Diameter of Binary Tree.
Depth – Number of edges from the root to the node; understanding this is important for LCA (Lowest Common Ancestor), Path Sum problems, and so on.
3. Special Tree Problems
Valid BST (98), Symmetric Tree (101), Invert Tree (226), Leaf Similar Trees, Range Sum of BST (938) – All of these are common problems that test your understanding of tree properties, like BST properties and symmetric structures.
Construct BST from Traversals (1008, 105) – These help you understand how to reconstruct trees from given traversals.
Sum of Left Leaves (404), Path Sum (112), Count Complete Tree Nodes (222) – These deal with path calculations and tree structure manipulations, both of which are frequently tested.
4. Advanced Concepts
Lowest Common Ancestor (LCA) – A key problem for understanding tree relationships. It’s essential to grasp its concept thoroughly, as questions about LCA are often asked at FAANG interviews.
Kth Smallest, Kth Largest Sum in Binary Tree (230, 2583) – These are important for understanding how to navigate binary trees efficiently (often with DFS or BST-specific algorithms).
Binary Tree Maximum Path Sum (124) – This is a very hard and important problem for tackling deep dynamic programming on trees.
5. Leaf and Path Operations
Print leaf in alt order, Print leaf, Binary Tree Paths – These test your ability to manage nodes at different levels and across paths.
Smallest String Starting From Leaf (988) – A good problem for working with strings in tree structures.
6. Dynamic and Optimized Approaches
Recover Binary Search Tree (99) – A classic problem on detecting and recovering from mistakes in BST properties.
Balance Tree – Understanding tree balance, including rotations (AVL or Red-Black Trees), is important for optimization problems.
Flip Equivalent Binary Trees (951) – A problem testing tree symmetry and recursive tree transformations.
7. Other Miscellaneous Problems
Reverse Odd Levels of Binary Tree (2583), All Nodes Distance K in Binary Tree (863), Step-By-Step Directions From a Binary Tree Node to Another (2096) – These problems test your ability to traverse the tree in creative ways, such as using BFS/DFS for distances or transformations.
Path Sum II (113) – It involves path construction and dynamic recursion.
Areas to Strengthen:
Dynamic Programming on Trees – Problems like Binary Tree Maximum Path Sum (124), Count Valid Paths in a Tree (2867), and Minimum Cost Tree From Leaf Values (1130) require dynamic programming techniques.
Complex Tree Structures – Some problems like Flip Binary Tree To Match Preorder Traversal (971), Symmetric Tree (101), and Smallest String Starting From Leaf (988) require in-depth knowledge of manipulating tree structures.
 */