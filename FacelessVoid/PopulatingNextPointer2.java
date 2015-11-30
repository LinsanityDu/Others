/*Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL*/

// O(1) Solution
public class Solution {

    public void connect(TreeLinkNode root) {
        TreeLinkNode head=root;//The left most node in the lower level
        TreeLinkNode prev=null;//The previous node in the lower level
        TreeLinkNode curr=null;//The current node in the upper level
        while (head!=null){
            curr=head;
            prev=null;
            head=null;
            while (curr!=null){
                if (curr.left!=null){
                    if (prev!=null)
                        prev.next=curr.left;
                    else 
                        head=curr.left;
                    prev=curr.left;
                }
                if (curr.right!=null){
                    if (prev!=null)
                        prev.next=curr.right;
                    else 
                        head=curr.right;
                    prev=curr.right;
                }
                curr=curr.next;
            }
        }
    }
}

// O(1) BFS
public void connect(TreeLinkNode root) {
    TreeLinkNode queue = root;
    while (queue != null) {
        TreeLinkNode level = new TreeLinkNode(0);
        TreeLinkNode current = level;
        while (queue != null) {
            if (queue.left != null) {
                current.next = queue.left;
                current = current.next;
            }
            if (queue.right != null) {
                current.next = queue.right;
                current = current.next;
            }
            queue = queue.next;
        }
        queue = level.next;
    }
}


public void connect(TreeLinkNode root) {
    if(root == null)
        return;
    TreeLinkNode dummy = new TreeLinkNode(0);
    dummy.next = root;
    while(dummy.next != null){
        TreeLinkNode cur = dummy.next, pre = dummy;
        dummy.next = null;
        while(cur != null){
            if(cur.left != null)
                pre = pre.next = cur.left;
            if(cur.right != null)
                pre = pre.next = cur.right;
            cur = cur.next;
        }
    }
}





/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null || root.left == null || root.right == null) {
		    return;
        }
        Queue<TreeLinkNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	TreeLinkNode prev = queue.poll();
        	for (int i = 0; i < size; i++) {
            	if (prev.left != null) queue.offer(prev.left);
            	if (prev.right != null) queue.offer(prev.right);
            	if (i == size - 1) {
            	    prev.next = null;
            	} else {
            	    TreeLinkNode cur = queue.poll();
            	    prev.next = cur;
            	    prev = cur;
            	}

            }
    }
}
}