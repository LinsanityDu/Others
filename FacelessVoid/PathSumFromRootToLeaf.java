/*
题目1：求二叉树所有从根开始到叶子的所有路径和。
题目2：不能用递归，完成以上题目
*/
import java.util.*;
public class Solution{
    
    
    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    
    public int pathSum(TreeNode root){
        if(root == null){
            throw new IllegalArgumentException();
        }
        return helper(root, 0);
    }
    
    private int helper(TreeNode root, int prev){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return prev + root.val;
        }
        int left = helper(root.left, prev + root.val);
        int right = helper(root.right, prev + root.val);
        return left + right;
    }
    
    public int pathSum2(TreeNode root){
        if(root == null){
            throw new IllegalArgumentException();
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<Integer> sums = new Stack<Integer>();
        stack.push(root);
        sums.push(0);
        int res = 0;
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            int prev = sums.pop();
            if(node.left == null && node.right == null){
                res += node.val + prev;
            }
            if(node.right != null){
                stack.push(node.right);
                sums.push(node.val + prev);
            }
            if(node.left != null){
                stack.push(node.left);
                sums.push(node.val + prev);
            }
            
        }
        return res;
    }
    
    
    
    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        System.out.println(new Solution().pathSum2(root));
        
        
    }
    
    
    
    
    
    
}