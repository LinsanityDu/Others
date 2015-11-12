public interface FirstCommonAncestor {

    /**
     * Given two nodes of a tree,
     * method should return the deepest common ancestor of those nodes.. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
     *
     *          A 
     *         / \
     *        B   C
     *       / \
     *      D   E
     *         / \
     *        G   F
     *
     *  commonAncestor(D, F) = B
     *  commonAncestor(C, G) = A
     *  commonAncestor(E, B) = B
     */
    Node commonAncestor(Node one, Node two);
}

class Node {

    final Node parent;
    final Node left;
    final Node right;


    public Node(Node parent, Node left, Node right) {
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    bool isRoot() {

        return parent == null;
    }
}

// No Root with Parent Pointer
public static TreeNode LCA1(TreeNode n1,TreeNode n2){
    int depth1=getDepth(n1);
    int depth2=getDepth(n2);
    if(depth1<depth2){
        TreeNode t=n1;n1=n2;n2=t;
        int d=depth1;depth1=depth2;depth2=d;
    }
    for(int i=0;i<depth1-depth2;i++)
        n1=n1.parent;
        while(n1!=null&&n2!=null){
        if(n1==n2) return n1;
        n1=n1.parent;
        n2=n2.parent;
    }
    return null;
}
public static int getDepth(TreeNode n){
    int depth=0;
    while(n!=null){
        depth++;
        n=n.parent;
    }
    return depth;
}



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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }
}


// Pochman God

public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    return left == null ? right : right == null ? left : root;
}