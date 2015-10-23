/*Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.*/

//store the head and tail of the linked list
class NodePair{
    TreeNode head;
    TreeNode tail;
}
 
public static TreeNode bstToDll(TreeNode root){
    NodePair result = new NodePair();
    bstToDll(root, result);     
    return result.head;
}
     
public static void bstToDll(TreeNode root, NodePair result){
 
    if(root == null)
        return;
 
    bstToDll(root.left, result);   // go left
         
        root.left = result.tail;
    if(result.head == null){
        result.head = root;    
    }else{
        result.tail.right = root;
    }
     
 
    TreeNode right = root.right;
    result.tail = root;
    root.right = null;
 
    bstToDll(right, result);  //go right
 
}

// Another
public class TreeToList {
    public static class TreeNode { /* ... */ }

    private static class Context {
        public TreeNode head;
        public TreeNode prev;
    }

    // ...

    private static void convert(TreeNode root, Context context) {
        // use context.head and context.prev instead of head and prev, e.g.,
        if (root == null) {
            return;
        }
        convert(root.left, context);
        if (context.prev == null) {
            context.head = root;
        } else {
            root.left = context.prev;
            context.prev.right = root;
        }
        context.prev = root;
        convert(root.right, context);
    }

    public static void main(String[] args) {
        // ...
        Context context = new Context();
        convert(t1, context);
        print(context.head);
    }
}