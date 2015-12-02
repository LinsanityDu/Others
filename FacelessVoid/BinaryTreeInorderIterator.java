public class InOrderBinaryTreeIteratorImpl implements InOrderBinaryTreeIterator {  
   Stack<TreeNode> stack = new Stack<TreeNode>();  
   
   /** Push node cur and all of its left children into stack */  
   private void pushLeftChildren(TreeNode cur) {  
     while (cur != null) {  
       stack.push(cur);  
       cur = cur.left;  
     }  
   }  
   
   /** Constructor */  
   public InOrderBinaryTreeIterator(TreeNode root) {  
     pushLeftChildren(root);  
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
   
     TreeNode res = stack.pop();  
     pushLeftChildren(res.right);  
   
     return res.val;  
   }  
   
   @Override  
   public void remove() {  
     throw new UnsupportedOperationException("remove() is not supported.");  
   }  
 }  