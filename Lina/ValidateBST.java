/*Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.*/

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
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);    
    }
 
    public boolean isValidBST(TreeNode p, double min, double max){
        if(p==null) 
            return true;
     
        if(p.val <= min || p.val >= max)
            return false;
     
        return isValidBST(p.left, min, p.val) && isValidBST(p.right, p.val, max);
    }
}



// Traverse
public class Solution {
    private int lastVal = Integer.MIN_VALUE;
    private boolean firstNode = true;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (!firstNode && lastVal >= root.val) {
            return false;
        }
        firstNode = false;
        lastVal = root.val;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }
}


// Iterate Traversal
public boolean isValidBST (TreeNode root){
           Stack<TreeNode> stack = new Stack<TreeNode> ();
           TreeNode cur = root ;
           TreeNode pre = null ;           
           while (!stack.isEmpty() || cur != null) {               
               if (cur != null) {
                   stack.push(cur);
                   cur = cur.left ;
               } else {                
                   TreeNode p = stack.pop() ;
                   if (pre != null && p.val <= pre.val) {                      
                       return false ;
                   }                   
                   pre = p ;                       
                   cur = p.right ;
               }
           }
           return true ; 
       }