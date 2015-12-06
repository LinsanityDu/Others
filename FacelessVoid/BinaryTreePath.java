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


// Iterative BFS?

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


第二题是completed bst，打印所有path。follow up 是如果是有向图怎么修改代码，然后如果有cyclic怎么改。然后他说用boolean标记node有没有遇到过有什么pros and cons, 我只说了pros，不知道cons，他说多个线程同时读和写的时候可能会有问题。

class Soulution{
    public void printPath(TreeNode root, ArrayList<TreeNode> list){
        if(root == null || root.isVistied==true) return;
        if(root.child==null){
            list.add(root);
            print(list);
            list.remove(list.size()-1);
            return;
        }
        list.add(root);
        list.isVisited = true;
        for(TreeNode e : root.child){
            printPath(e, list);
        }
        //printPath(root.left, list); 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
        //printPath(root.right,list);.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
        list.remove(list.size()-1);
    }
    
    public void print(ArrayList<TreeNode> list){
        for(TreeNode e: list){
            System.out.print(e.val);
        }
        System.out.println();
    }
}



    A
   / \
  B   C
   \ /
    D
   / \
  E   F
   \ /
    G