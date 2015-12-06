Print a BST such that it looks like a tree (with new lines and indentation, the way we see it in algorithms books).
For example:

         4                     
   2           6         
1     3     5     7   
                    8
and:

   3         
1     4   
  2     5
FB的题，本质上就是level order遍历。这里不需要画斜线，只用空格表示还是比较简单的。
因为是二叉树，每一层最大节点数都是可以计算的，也就是每一个节点离previous节点的距离（空格数）也可以计算到。那么每层遍历的时候针对每个节点（包括空节点）输空格即可。
下面的代码适用于任意二叉树。

int max_height(TreeNode* node) {
    if(!node) return 0;
    return 1 + max(max_height(node->left), max_height(node->right));
}
void pretty_print_bst(TreeNode* root, int space_size = 3) {    
    deque<TreeNode*> q = { root };    
    
    int height = max_height(root);
    int cur_level_nodes = 1, next_level_nodes = 0, level = 1;
    int padding = space_size * (pow(2, height - level) - 1);
    cout << setw(padding / 2) << "";
    while (level <= height) {
        cout << setw(space_size);
        if (q.front()) {
            cout << q.front()->val;
            q.push_back(q.front()->left);
            q.push_back(q.front()->right);
        } else {
            cout << " ";
            q.push_back(nullptr);
            q.push_back(nullptr);
        }
        next_level_nodes += 2;
        cout << setw(padding) << "";
        q.pop_front();
        // go to next level
        if (--cur_level_nodes == 0) {
            cur_level_nodes = next_level_nodes;
            next_level_nodes = 0;
            ++level;
            padding = space_size * (pow(2, height - level) - 1);
            cout << endl << setw(padding / 2) << "";
        }
    }
}

// Java
public class Solution {
	LinkedList<TreeNode> queue = null;
	LinkedList<Integer> list = null;
	int maxDepth = 0;
	
	void levelOrder(TreeNode root) {
		queue.add(root);
		list.add(root.val);		
		int depth = 0;
		
		while (!queue.isEmpty()) {
			for (int i = 0; i < Math.pow(2, depth); i++) {
				TreeNode p = queue.removeFirst();
				if (p == null) {
					queue.add(null);
					list.add(0);
					queue.add(null);
					list.add(0);
				}
				else {
					queue.add(p.left);
					if (p.left == null) {
						list.add(0);
					}
					else {
						list.add(p.left.val);
					}
					queue.add(p.right);
					if (p.right == null) {
						list.add(0);
					}
					else {
						list.add(p.right.val);
					}
				}
			}
			depth++;
			if (depth == maxDepth) {
				break;
			}
		}
	}
	
	void drawBT(TreeNode root) {
		queue = new LinkedList<TreeNode>();
		list = new LinkedList<Integer>();

		getDepth();
		levelOrder(root);
		
		for (int i = 0; i < maxDepth + 1; i++) {
			int dis = (int)Math.pow(2, maxDepth - i);
			StringBuilder sdis = new StringBuilder();
			for (int k = 0; k < dis; k++) {
				sdis.append("  ");
			}
			for (int j = 0; j < Math.pow(2, i); j++) {
				if (j == 0) {
					System.out.print(sdis.toString());
				}
				else {
					System.out.print(sdis.toString());
					System.out.print(sdis.toString());
				}
				System.out.print(list.removeFirst());
			}
			
			System.out.println("");
			
		}
	}
}