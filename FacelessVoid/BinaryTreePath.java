/*Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]*/


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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if (root == null) return result;
        dfs(root, result, "");
        return result;
    }
    
    private void dfs(TreeNode root, List<String> result, String path) {
        if (root.left == null && root.right == null) {
            result.add(path + root.val);
        }
        if (root.left != null) {
            dfs(root.left, result, path + root.val + "->");
        }
        if (root.right != null) {
            dfs(root.right, result, path + root.val + "->");
        }
    }
}


// Iterative

public List<String> binaryTreePaths(TreeNode root) {
    Queue<TreeNode> nodes=new LinkedList<TreeNode>();
    Queue<String> strs=new LinkedList<String>();
    List<String> result=new ArrayList<String>();
    if(root!=null){
        nodes.offer(root);
        strs.offer(Integer.toString(root.val));
    }
    while(!nodes.isEmpty()){
        TreeNode node=nodes.poll();
        if(node.left==null&&node.right==null){
            result.add(strs.poll());
        }else{
            String newStr=strs.poll();
            if(node.left!=null){
                nodes.offer(node.left);
                strs.offer(newStr+"->"+node.left.val);
            }
            if(node.right!=null){
                nodes.offer(node.right);
                strs.offer(newStr+"->"+node.right.val);
            }
        }
    }
    return result;
}