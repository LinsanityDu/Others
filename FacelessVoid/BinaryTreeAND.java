这道题的意思是，每个树包含中间node和leaf node，其中leaf node可以是0或者1, internal node没有具体值，只是用于构成tree的结构。当两个tree求AND关系的时候，从root开始，如果都是internal node，那么结果的数也是internal node；如果其中一个是leaf node并且值为1，那么结果为另外一棵树的相同位置的subtree；如果其中一个节点是leaf并且值为0，那么结果对应的位置也为leaf并且值也为0.
.1point3acres缃�
另一个例子是：
         *                       *                             *                           *
       /   \                    /  \                          /  \                         / \
      *    *     and       *    *       =               *  *            =        *    0
    /  \   / \              / \    / \                    / \    / \                   / \
   1   0 0  1            1  0   *  0                 1 0   0  0                1   0
                                    /\
                                   1 0


    public TreeNode binaryAnd(TreeNode root1, TreeNode root2) {
     if (root1 == null || root2 == null) {
      return null;
     }
     TreeNode node = null;
     if (root1.val == 0) {
      node = root1;
     } else if (root1.val == 1) {
      node = root2;
     } else {
      node = root1;
     }
     node.left = binaryAnd(root1.left, root2.left);
     node.right = binaryAnd(root1.right, root2.right);
     
     if (node.left != null && node.right != null && node.left.val == 0 && node.right.val == 0) {
      node.val = 0;
      node.left = null;
      node.right = null;
     }
     return node;

    }