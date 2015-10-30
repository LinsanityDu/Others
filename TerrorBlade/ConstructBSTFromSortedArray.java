/*Given an array where elements are sorted in ascending order, convert it to a height balanced BST.*/

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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int start, int end) {
    	// Pay attention!!! start > end
    	if (start > end) return null;
    	int mid = start + (end - start) / 2;
    	TreeNode root = new TreeNode(nums[mid]);
    	root.left = helper(nums, start, mid - 1);
    	root.right = helper(nums, mid + 1, end);
    	return root;
    }
  
}


// Iterative
public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        Queue<MyNode> queue = new LinkedList<>();
        int left = 0;
        int right = nums.length - 1;
        int val = nums[left + (right - left) / 2];
        TreeNode root = new TreeNode(val);
        queue.offer(new MyNode(root, left, right));

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                MyNode cur = queue.poll();

                int mid = cur.lb + (cur.rb - cur.lb) / 2;

                if (mid != cur.lb) {
                    TreeNode leftChild = new TreeNode(nums[cur.lb + (mid - 1 - cur.lb) / 2]);
                    cur.node.left = leftChild;
                    queue.offer(new MyNode(leftChild, cur.lb, mid - 1));
                }

                if (mid != cur.rb) {
                    TreeNode rightChild = new TreeNode(nums[mid + 1 + (cur.rb - mid - 1) / 2]);
                    cur.node.right = rightChild;
                    queue.offer(new MyNode(rightChild, mid + 1, cur.rb));
                }
            }
        }

        return root;
    }

    private static class MyNode {
        TreeNode node;
        int lb;
        int index;
        int rb;

        public MyNode(TreeNode n, int theLeft, int theRight) {
            this.node = n;
            this.lb = theLeft;
            this.rb = theRight;
        }
    }
}