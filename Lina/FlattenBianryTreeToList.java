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


// Diao Solution
private TreeNode prev = null;

public void flatten(TreeNode root) {
    if (root == null)
        return;
    flatten(root.right);
    flatten(root.left);
    root.right = prev;
    root.left = null;
    prev = root;
}


// Segment fault
Solution - Recursion

To flatten a binary tree, according to the given example, is to recursively insert the left subtree to the right subtree and append the original right subtree to the end of the left subtree, i.e.
     root                        root
     /  \            ->            \
  left  right                      left
                                     \
                                    right
Since we need to append the original right-tree to the end of the left subtree, we let the recursion function return the last node after flatten.
 public TreeNode flatten(TreeNode root) {  
   if (root == null) return root;  
   TreeNode rtree = root.right;  
   if (root.left != null) {  
     root.right = root.left;  
     root.left = null;  
     root = flatten(root.right);  
   }  
   if (rtree != null) {  
     root.right = rtree;  
     root = flatten(root.right);  
   }  
   return root;  
 }  
Solution - Non-Recursion, With Stack

We can also solve the problem without recursion.

To do that, we can use a stack to store all right subtrees when we prune them temporarily, and append each of them back after we go through the corresponding left subtree.

Basically, given a non-empty tree,
if it has left subtree, store the right subtree (if not null) to stack, move the left subtree to right;
if not, append back a subtree from stack to the current node's right;
continue to the right node until finish.
 public void flatten(TreeNode root) {  
   TreeNode cur = root;  
   Stack<TreeNode> rtrees = new Stack<TreeNode>();  
   while (cur != null) {  
     while (cur.left != null) {  
       if (cur.right != null) rtrees.push(cur.right);  
       cur.right = cur.left;  
       cur.left = null;  
       cur = cur.right;  
     }  
     if (cur != null && cur.right == null && !rtrees.isEmpty()) {  
       cur.right = rtrees.pop();  
     }  
     cur = cur.right;  
   }  
 }  
Alternatively, we can append back a right subtree as early as possible (if the current left node has no right subtree).
 public void flatten(TreeNode root) {  
   TreeNode cur = root;  
   Stack<TreeNode> rtrees = new Stack<TreeNode>();  
   while (cur != null) {  
     if (cur.left != null) {  
       if (cur.right != null) rtrees.push(cur.right);  
       cur.right = cur.left;  
       cur.left = null;  
     }  
     if (cur.right == null && !rtrees.isEmpty()) {  
       cur.right = rtrees.pop();  
     }  
     cur = cur.right;  
   }  
 }  
This algorithm runs in time O(n) and uses O(n) extra spaces for the stack.

This would make the code looks simpler but it may introduce some unnecessary extra work. For instance,
       1           1           1
      / \           \           \
     2   4    ->     2    ->     2
    /               / \           \
   3               3   4           3
                                    \
                                     4
Notice that 4 has been cut down and append back twice.
Solution - Non-Recursion, No Stack

We can also solve the problem even without a stack:
Each time when we prune a right subtree, we use while-loop to find the right-most leaf of the current left subtree, and append the subtree there.
 public void flatten(TreeNode root) {  
   TreeNode cur = root;  
   while (cur != null) {  
     if (cur.left != null) {  
       if (cur.right != null) { // if we need to prune a right subtree
         TreeNode next = cur.left;  
         while (next.right != null) next = next.right;  
         next.right = cur.right;  
       }
       cur.right = cur.left;  
       cur.left = null;  
     }  
     cur = cur.right;  
   }  
 }  
We visit each node at most twice (one for flattening and maybe one for looking for rightmost leaf) and then for each node, cut the right tree and append it to its rightmost node. Overall, we access each node constant time. So the total running time is O(n) with O(1) space.

Similarly, since we append back right subtrees as early as possible (by finding the right-most leaf rather than the last pre-order leaf in a left subtree), it may introduce extra work as shown in previous section.