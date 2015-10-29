/*Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// If we have the parent pointer
1) If right subtree of node is not NULL, then succ lies in right subtree. Do following.
Go to right subtree and return the node with minimum key value in right subtree.
2) If right sbtree of node is NULL, then succ is one of the ancestors. Do following.
Travel up using the parent pointer until you see a node which is left child of it’s parent. The parent of such a node is the succ.


struct node * inOrderSuccessor(struct node *root, struct node *n)
{
  // step 1 of the above algorithm 
  if( n->right != NULL )
    return minValue(n->right);
 
  // step 2 of the above algorithm
  struct node *p = n->parent;
  while(p != NULL && n == p->right)
  {
     n = p;
     p = p->parent;
  }
  return p;
}



public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        if (p.right != null) {
            TreeNode next = p.right;
            while(next.left != null) {
                next = next.left;
            }
            return next;
        } else {
            TreeNode node = root;
            TreeNode next = null;
            while (true) {
                if (node.val > p.val) {
                    next = node;
                    node = node.left;
                } else if (node.val < p.val) {
                    node = node.right;
                } else {
                    break;
                }
            }
            return next;
        }
    }
}

// PochMan
public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (p.right != null) {
        p = p.right;
        while (p.left != null)
            p = p.left;
        return p;
    }
    TreeNode candidate = null;
    while (root != p)
        root = (p.val > root.val) ? root.right : (candidate = root).left;
    return candidate;
}



// Discusion Recusive
Successor

public TreeNode successor(TreeNode root, TreeNode p) {
  if (root == null)
    return null;

  if (root.val <= p.val) {
    return successor(root.right, p);
  } else {
    TreeNode left = successor(root.left, p);
    return (left != null) ? left : root;
  }
}
Predecessor

public TreeNode predecessor(TreeNode root, TreeNode p) {
  if (root == null)
    return null;

  if (root.val >= p.val) {
    return predecessor(root.left, p);
  } else {
    TreeNode right = predecessor(root.right, p);
    return (right != null) ? right : root;
  }
}