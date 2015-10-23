

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:
public boolean isSysmetric(TreeNode r1, TreeNode r2) {  
    if(r1 == null && r2 == null) return true;  
    if(r1 == null || r2 == null) return false;  
    if(r1.val != r2.val) return false;  
      
    Queue<TreeNode> q1 = new LinkedList<TreeNode>();  
    Queue<TreeNode> q2 = new LinkedList<TreeNode>();  
    q1.offer(r1);   
    q2.offer(r2);  
      
    while(!q1.isEmpty() && !q2.isEmpty()) {  
        TreeNode cur1 = q1.poll();  
        TreeNode cur2 = q2.poll();  
        if(cur1.val != cur2.val) return false;  
          
        if(cur1.left == null && cur2.right != null) return false;  
        else if(cur1.left != null && cur2.right == null) return false;  
        else if(cur1.left != null && cur2.right != null) {  
            q1.offer(cur1.left);  
            q2.offer(cur2.right);  
        }  
  
        if(cur1.right == null && cur2.left != null) return false;  
        else if(cur1.right != null && cur2.left == null) return false;  
        else if(cur1.right != null && cur2.left != null) {  
            q1.offer(cur1.right);  
            q2.offer(cur2.left);  
        }  
    }  
    if( !q1.isEmpty() || !q2.isEmpty()) return false;  
      
    return true;  
}  



Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so fuck off.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.right == null && root.left == null)) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return root;
    }
}



public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left  = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}