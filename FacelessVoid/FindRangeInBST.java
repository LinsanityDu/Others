Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree. Print all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST. Print all the keys in increasing order.
For example, if k1 = 10 and k2 = 22, then your function should print 12, 20 and 22.

Thanks to bhasker for suggesting the following solution.
Algorithm:
1) If value of root’s key is greater than k1, then recursively call in left subtree.
2) If value of root’s key is in range, then print the root’s key.
3) If value of root’s key is smaller than k2, then recursively call in right subtree.

private void findNodesInBetween(TreeNode root, int start, int end) {
     if (root == null) {
      return;
     }
     if (start < root.val) {
      findNodesInBetween(root.left, start, end);
     }
     if (root.val >= start && root.val <= end) {
      System.out.println(root.val);
     }
     if (end > root.val) {
      findNodesInBetween(root.right, start, end);
     }
    }