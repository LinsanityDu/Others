/*Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.*/


// My suck code
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
    public int closestValue(TreeNode root, double target) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int res = root.val;
        double dif = Math.abs(target - (double)root.val);
        TreeNode cur = root;
        while (cur != null) {
            if (target > (double)cur.val) {
                double newDif = Math.abs(target - (double)cur.val);
                if (newDif < dif) {
                    res = cur.val;
                    dif = newDif;
                }
                cur = cur.right;
            } else if (target < (double)cur.val) {
                double newDif = Math.abs(target - (double)cur.val);
                if (newDif < dif) {
                    res = cur.val;
                    dif = newDif;
                }
                cur = cur.left;
            } else {
                return cur.val;
            }
        }
        return res;
    }
}


// Pochman god recursive
public int closestValue(TreeNode root, double target) {
    int a = root.val;
    TreeNode kid = target < a ? root.left : root.right;
    if (kid == null) return a;
    int b = closestValue(kid, target);
    return Math.abs(a - target) < Math.abs(b - target) ? a : b;
}


// Iterative
public int closestValue(TreeNode root, double target) {
        int closestVal = root.val; 
        while(root != null){ 
            //update closestVal if the current value is closer to target
            closestVal = (Math.abs(target - root.val) < Math.abs(target - closestVal))? root.val : closestVal;
            if(closestVal == target){   //already find the best result
                return closestVal;
            }
            root = (root.val > target)? root.left: root.right;   //binary search
        }
        return closestVal;
  }