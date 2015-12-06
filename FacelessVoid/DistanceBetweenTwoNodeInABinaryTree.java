/*
The distance between two nodes can be obtained in terms of lowest common ancestor. Following is the formula.

Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2*Dist(root, lca) 
'n1' and 'n2' are the two given keys
'root' is root of given Binary Tree.
'lca' is lowest common ancestor of n1 and n2
Dist(n1, n2) is the distance between n1 and n2.*/
Distance(X, Y) = Distance(root, X) +Distance(root, Y) — 2*(Distance(root to LCA(X,Y)
where LCA(X,Y) = Low­est Com­mon Ances­tor of X,Y
In the above exam­ple if Distance(20,45) = 3
Distance(root, 20) = 2
Distance(root, 45) = 3
LCA(20, 45) = 10
Distance(root, 10) = 1
Distance(20,45) = 2+3 — 2*1 = 3


// Answer:
/*
find distance between two nodes in a binary tree
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
    
    public int distance(TreeNode root, TreeNode A, TreeNode B){
        if(root == null || A == null || B == null){
            throw new IllegalArgumentException();
        }
        TreeNode lca = LCA(root, A, B);
        return depth(root, A) + depth(root, B) - 2 * depth(root, lca);
    }
    
    private int depth(TreeNode root, TreeNode node){
        return depthHelper(root, node, 0);
    }
    
    private int depthHelper(TreeNode root, TreeNode node, int depth){
        if(root == null){
            return -1;
        }
        if(root == node){
            return depth;
        }
        int left = depthHelper(root.left, node, depth + 1);
        if(left != -1){
            return left;
        }
        return depthHelper(root.right, node, depth + 1);
        
    }
    
    public TreeNode LCA(TreeNode root, TreeNode A, TreeNode B) {
        if(root == null || A == null || B == null){
            return null;
        }
        return helper(root, A, B);
    }
    
    private TreeNode helper(TreeNode root, TreeNode A, TreeNode B){
        if(root == null){
            return null;
        }
        if(root == A || root == B){
            return root;
        }
        TreeNode left = helper(root.left, A, B);
        TreeNode right = helper(root.right, A, B);
        if(left != null && right != null){
            return root;
        }
        if(left != null){
            return left;
        }
        if(right != null){
            return right;
        }
        return null;
    }
    
    
    
    
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        
        node3.left = node6;
        node3.right = node7;
        node6.right = node8;
        
        Solution ins = new Solution();
        System.out.println(ins.distance(root, node4, node5));
        System.out.println(ins.distance(root, node4, node6));
        System.out.println(ins.distance(root, node3, node4));
        System.out.println(ins.distance(root, node2, node4));
        
        
    }
}




package PrintDistanceBwTwoNodes;


public class PrintDistance {
	public int findDistance(Node root, int n1, int n2){
		int x = Pathlength(root, n1)-1;
		int y = Pathlength(root, n2)-1;
		int lcaData = findLCA(root, n1, n2).data;
		int lcaDistance = Pathlength(root, lcaData)-1;
		return (x+y)- 2*lcaDistance;
	}
	public int Pathlength(Node root, int n1) {
		if (root != null) {
			int x=0;
			if ((root.data == n1) || (x=Pathlength(root.left, n1))>0|| (x=Pathlength(root.right, n1))>0) {
//				System.out.println(root.data);
				return x + 1;
			}
			return 0;
		}
		return 0;
	}
	public Node findLCA(Node root, int n1, int n2){
		if(root!=null){
			if(root.data==n1||root.data==n2){
				return root;
			}
			Node left = findLCA(root.left,n1,n2);
			Node right = findLCA(root.right,n1,n2);
			
			if(left!=null && right !=null){
				return root;
			}
			if(left!=null){
				return left;
			}
			if(right!=null){
				return right;
			}
		}
		return null;
	}
	public static void main (String[] args) throws java.lang.Exception
	{
		Node root = new Node(5);
		root.left = new Node(10);
		root.right = new Node(15);
		root.left.left = new Node(20);
		root.left.right = new Node(25);
		root.right.left = new Node(30);
		root.right.right = new Node(35);		
		root.left.right.right = new Node(45);	
		PrintDistance i  = new PrintDistance();	
		System.out.println("Distance between 45 and 20 is : " + i.findDistance(root, 45, 20));		
	}
}

class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}