/*Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.*/

// solution1: BST -> Binary Search
public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k-1-count); // 1 is counted as current node
        }

        return root.val;
    }

public int countNodes(TreeNode n) {
    if (n == null) return 0;

    return 1 + countNodes(n.left) + countNodes(n.right);
}


//  DFS -> Recursive
	private static int number = 0;
    private static int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode n) {
        if (n.left != null) helper(n.left);
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }
        if (n.right != null) helper(n.right);
    }



// DFS -> Iterative
public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();

        while (root != null) {
            st.push(root);
            root = root.left;
        }

        while (k != 0) {
            TreeNode n = st.pop();
            k--;
            if (k == 0) return n.val;
            TreeNode right = n.right;
            while (right != null) {
                st.push(right);
                right = right.left;
            }
        }

        return -1; // never hit if k is valid
}

// Another solution
public static int ans = 0;
public int kthSmallest(TreeNode root, int k) {
    helper(root, k);
    return ans;
}

public int helper(TreeNode root, int k) {
    if (root == null) {
        return 0;
    }
    int leftCount = helper(root.left, k);
    int rightCount = helper(root.right, k - leftCount - 1);
    if (k == leftCount + 1) {
        ans = root.val;
    }
    return leftCount + rightCount + 1;
}


// Augment:
We can augment the BST to have each node in it store the number of elements in its left subtree. With this piece of information, it is simple to traverse the tree by repeatedly asking for the number of elements in the left subtree, to decide whether to do recurse into the left or right subtree.

