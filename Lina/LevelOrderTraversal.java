/* 

Tree: 
1 
/ \ 
3 5 
/ \ \ 
2 4 7 
/ \ \ 
9 6 8 

========== 
Expected Output: 
1 
35 
247 
968 
*/ 
class TreePrinter { 

	static class Node { 
		int value; 
		Node left; 
		Node right; 
		public Node(int value, Node left, Node right) { 
			this.value = value; 
			this.left = left; 
			this.right = right; 
		} 
	} 

	public void printTree(Node root) { 
		// implementation here
		if (root == null) return;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				if (node.left != null) {
					queue.offer(node.left);
				} else {
					queue.offer(node.right);
				}
				System.out.print(node.value + " ");
			}
			System.out.println("");
		}
		return; 
	}
}

print each level in a format with relative position preserved???
       eg.         1
                   /   \
                2      4
                 \        \
                 3        7
    output:
                   1
               2     4
                3     7

看到LinkedIn有考这道题目，就做了一下。 没什么特别的，在queue里面加了一个null来表示是在当前level的尽头

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if(root == null) {
            return ret;
        }
        //The Queue for bfs
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        q.add(null);//null means we are at the end of a level

        List<Integer> l = new LinkedList<Integer>();
        //这里开始traverse
        while(q.size() > 0) {
            TreeNode cur = q.poll();
            l.add(cur.val);
            if(cur.left != null) {
                q.add(cur.left);
            }
            if(cur.right != null) {
                q.add(cur.right);
            }
            if(q.peek() == null) {//meaning we have reached the end of a level
                ret.add(l);
                l = new LinkedList<Integer>();
                //表示是level的最后
                q.add(null);
                while(q.peek() == null && q.size() > 0) {
                    q.poll();
                }
            }
        }
        return ret;
    }
}
看到有人说还要考dfs的traverse， 尿了，赶紧乖乖奉上dfs的解法

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        //这里不用考虑edge case，直接在helper function里面考虑
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        _levelOrder(root, ret, 0);
        return ret;
    }
    public void _levelOrder(TreeNode cur, List<List<Integer>> l, int depth) {
        if(cur == null) {
            return;
        }
        if(l.size() >= (depth+1)) {
            List<Integer> temp = l.get(depth);
            temp.add(cur.val);
        } else {
            List<Integer> temp = new LinkedList<Integer>();
            temp.add(cur.val);
            l.add(temp);
        }
        //从左到右，可以保证按顺序。用depth记录当前的深度
        _levelOrder(cur.left, l, depth+1);
        _levelOrder(cur.right, l, depth+1);
    }
}
两个queue的解法，这里是print

public void printTree(TreeNode tmpRoot) {

        Queue<TreeNode> currentLevel = new LinkedList<TreeNode>();
        Queue<TreeNode> nextLevel = new LinkedList<TreeNode>();

        currentLevel.add(tmpRoot);

        while (!currentLevel.isEmpty()) {
            Iterator<TreeNode> iter = currentLevel.iterator();
            while (iter.hasNext()) {
                TreeNode currentNode = iter.next();
                if (currentNode.left != null) {
                    nextLevel.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    nextLevel.add(currentNode.right);
                }
                System.out.print(currentNode.value + " ");
            }
            System.out.println();
            currentLevel = nextLevel;
            nextLevel = new LinkedList<TreeNode>();

        }

    }