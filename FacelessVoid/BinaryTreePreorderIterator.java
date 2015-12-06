/*
binary tree preorder iterator
1, save all nodes in a arraylist o(n)
2, use stack o(logn)
*/
//preorder
import java.util.*;
public class BSTIterator{
    
    private Stack<TreeNode> stack;
    
    
    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        
        public TreeNode(int val){
            this.val = val;
        }
    }
    
    public BSTIterator(TreeNode root){
        if(root == null){
            throw new IllegalArgumentException();
        }
        stack = new Stack<TreeNode>();
        stack.push(root);
    }
    
    public boolean hasNext(){
        return !stack.isEmpty();
    }
    
    public int next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        TreeNode node = stack.pop();
        if(node.right != null){
            stack.push(node.right);
        }
        if(node.left != null){
            stack.push(node.left);
        }
        return node.val;
        
    }
    
    
    
    
    
    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(30);
        node2.left = node3;
        node2.right = node4;
        root.left = node1;
        root.right = node2;
        BSTIterator it = new BSTIterator(root);
        
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    
   
}



public class PreOrderBinaryTreeIteratorImpl implements PreOrderBinaryTreeIterator {  
   Stack<TreeNode> stack = new ArrayDeque<TreeNode>();  
   
   /** Constructor */  
   public PreOrderBinaryTreeIterator(TreeNode root) {  
     if (root != null) {  
       stack.push(root); // add to end of queue 
     }  
   }  
   
   /** {@inheritDoc} */  
   @Override  
   public boolean hasNext() {  
     return !stack.isEmpty();  
   }  
   
   /** {@inheritDoc} */  
   @Override  
   public Integer next() {  
     if (!hasNext()) {  
       throw new NoSuchElementException("All nodes have been visited!");  
     }  
   
     TreeNode res = stack.pop(); // retrieve and remove the head of queue 
     if (res.right != null) stack.push(res.right);  
     if (res.left != null) stack.push(res.left);  
   
     return res.val;  
   }  
   
   @Override  
   public void remove() {  
     throw new UnsupportedOperationException("remove() is not supported.");  
   }  
 }  