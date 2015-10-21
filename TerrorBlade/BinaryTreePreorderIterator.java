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