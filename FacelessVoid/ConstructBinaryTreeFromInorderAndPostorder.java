/*Given inorder and postorder traversal of a tree, construct the binary tree.*/
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null || postorder.length != inorder.length) {
            return null;
        }
        return helper(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode helper (int[] postorder, int a, int b, int[] inorder, int c, int d) {
        if (a > b) return null;
        int headVal = postorder[b];
        TreeNode root = new TreeNode(headVal);
        int p = c;
        while (p <= d) {
            if (inorder[p] == headVal) {
                break;
            }
            p++;
        }
        root.left = helper(postorder, a, b - d + p - 1, inorder, c, p - 1);
        root.right = helper(postorder, b - d + p, b - 1, inorder, p + 1, d);
        return root;
    }
}


//
public class Solution {
    private int findPosition(int[] arr, int start, int end, int key) {
        int i;
        for (i = start; i <= end; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    private TreeNode myBuildTree(int[] inorder, int instart, int inend,
            int[] postorder, int poststart, int postend) {
        if (instart > inend) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postend]);
        int position = findPosition(inorder, instart, inend, postorder[postend]);

        root.left = myBuildTree(inorder, instart, position - 1,
                postorder, poststart, poststart + position - instart - 1);
        root.right = myBuildTree(inorder, position + 1, inend,
                postorder, poststart + position - instart, postend - 1);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }
        return myBuildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
}