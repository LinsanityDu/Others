/*Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6*/
This is like a preorder flatten.
// Easy to Understand
    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }

pre-order is simple because the root always is the head of flatten list. But if flatten the tree with in-order sequence, need extra parameter to track the head and tail of each flattened sun-tree.

// Inorder
For example, below binary tree.
or the in-order solution, I think you can mimic the preorder solution as well. Here is my java code：

    private static TreeNode last = null;
    public static void flatten(TreeNode root) {
            if (root == null) {
                      return;
            }
            if (root.left == null && root.right == null && last == null) {
                      last = root;
                      return;
            }
            flatten(root.left);
            if (last != null) {
                      last.left = null;
                     last.right = root;
           }
            last = root;
           flatten(root.right);
    }



// Diao Solution
private TreeNode prev = null;

public void flatten(TreeNode root) {
    if (root == null)
        return;
    flatten(root.right);
    flatten(root.left);
    root.right = prev; // prev = 
    root.left = null;
    prev = root;
}


// Nine Chapter
public class Solution {
    private TreeNode lastNode = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }
}





How about inorder flatten?
要求是把一颗binary search tree 输出成 都是只有右节点的树。 因为不好画图，我就用数组表示树，经常做leetcode的同学应该知道什么意思。比如 [2,1,3] 这个BST， output 是[1,null,2,null,3] 要求 in place。
// Inorder Flatten?
// Easy to Understand
    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode leftMostInRight = right; 
        while (leftMostInRight.left != null) {
          leftMostInRight = leftMostInRight.left;
        }
        root.left = null;

        flatten(left);
        flatten(right);
        while (left.right != null) left = left.right;
        left.right = root;
        root.right = leftMostInRight;
    }