/*Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  */

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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode next = null;
        TreeNode temp = null;

        while (curr != null) {
            next = curr.left;
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}


// Recursive
public TreeNode upsideDownBinaryTree(TreeNode root) {
  if (root == null || root.left == null && root.right == null)
    return root;

  TreeNode newRoot = upsideDownBinaryTree(root.left);

  root.left.left = root.right;
  root.left.right = root;

  root.left = null;
  root.right = null;

  return newRoot;
}



// New 3 Methods
这题有一个重要的限制就是，整个数的任何一个右孩子都不会再生枝节，基本就是一个梳子的形状。对于树类型的题目，首先可以想到一种递归的思路：把左子树继续颠倒，颠倒完后，原来的那个左孩子的左右孩子指针分别指向原来的根节点以及原来的右兄弟节点即可。


  public TreeNode UpsideDownBinaryTree(TreeNode root) {
    if (root == null)
      return null;
    TreeNode parent = root, left = root.left, right = root.right;
    if (left != null) {
      TreeNode ret = UpsideDownBinaryTree(left);
      left.left = right;
      left.right = parent;
      return ret;
    }
    return root;
  }


第二个思路是直接用迭代代替递归，做起来也不麻烦，并且效率会更高，因为省去了递归所用的栈空间。

  public TreeNode UpsideDownBinaryTree(TreeNode root) {
    TreeNode node = root, parent = null, right = null;
    while (node != null) {
      TreeNode left = node.left;
      node.left = right;
      right = node.right;
      node.right = parent;
      parent = node;
      node = left;
    }
    return parent;
  }


第三个思路比较特别，把后续遍历转换成层次遍历。注意由于Java不支持对TreeNode地址传引用，所以这里弄了一个全局变量。另外，类似于对链表的处理，这里我弄了一个dummy node简化对根节点的处理。

  private TreeNode out = null;
  public TreeNode UpsideDownBinaryTree(TreeNode root) { 
    TreeNode dummy = new TreeNode(0);
    dummy.left = new TreeNode(0);
    out = dummy;
    
    postorder(root);
    return dummy.right;
  }
    
  private void postorder(TreeNode root) {
    if (root == null)
      return;
    
    postorder(root.left);
    postorder(root.right);
    
    if (out.left == null) {
      out.left = root;
      out.left.left = null;
      out.left.right = null;
    } else if (out.right == null) {
      out.right = root;
      out.right.left = null;
      out.right.right = null;
    }
    
    if (out.left != null && out.right != null)
      out = out.right;
  }