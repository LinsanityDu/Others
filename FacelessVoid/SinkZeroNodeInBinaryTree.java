Sink Zero in Binary Tree. Swap zero value of a node with non-zero value of one of its descendants 
so that no node with value zero could be parent of node with non-zero.

package traversal;

import java.util.LinkedList;

/**
 * Sink Zero in Binary Tree. Swap zero value of a node with non-zero value of one of its descendants
 * so that no node with value zero could be parent of node with non-zero.
 * 
 * [Difficulty] - Hard
 * [Source]     - facebook interview
 * 
 */
 Swap zero value of a node with non-zero value of one of its descendants so that no node with value zero could be parent of node with non-zero.
Solution: 
We use a postorder traversal of the tree where instead of sinking we rise the leafs, we keep the nodes that we are sure that have not a zero ancestor.

public class tr_trav_sink_zeros {
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public void sink(TreeNode root) {
        explore(root, new LinkedList<TreeNode>());
    }

    private void explore(TreeNode c, LinkedList<TreeNode> q) {
        if (c == null) {
            return;
        }

        explore(c.left, q);
        explore(c.right, q);

        if (c.val == 0 && !q.isEmpty()) {
            c.val = 1;
            q.poll().val = 0;
        } else if (c.val == 1) {
            q.add(c);
        }
    }
}