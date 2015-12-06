/*Given a binary tree, print it vertically. The following example illustrates vertical order traversal.*/

          1
        /    \
       2      3
      / \    / \
     4   5  6   7
             \   \
              8   9 

The output of print this tree vertically will be:
4
2
1 5 6
3 8
7
9 
Time Complexity: Time complexity of above algorithm is O(w*n) where w is width of Binary Tree and n is number of nodes in Binary Tree. In worst case, the value of w can be O(n) (consider a complete tree for example) and time complexity can become O(n2).

Time Complexity of hashing based solution can be considered as O(n) under the assumption that we have good hashing function that allows insertion and retrieval operations in O(1) time. In the C++ implementation, map of STL is used. map in STL is typically implemented using a Self-Balancing Binary Search Tree where all operations take O(Logn) time.
第二题就是常见题型，用BFS遍历，首先以root为column 0，每往左走column-1，往右+1，存在TreeMap里，最后遍历一遍map就好。
这样column存好之后，从左到右遍历就只要遍历TreeMap就好，也没其他好处

public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
       //map's key is column, we assume the root column is zero, the left node will minus 1 ,and the right node will plus 1
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        Queue<TreeNode> queue = new LinkedList<>();
       //use a HashMap to store the TreeNode and the according cloumn value
        HashMap<TreeNode, Integer> weight = new HashMap<TreeNode, Integer>();
        queue.offer(root);
        weight.put(root, 0);
        int min = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int w = weight.get(node);
            if (!map.containsKey(w)) {
                map.put(w, new ArrayList<>());
            }
            map.get(w).add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                weight.put(node.left, w - 1);
            } 
            if (node.right != null) {
                queue.add(node.right);
                weight.put(node.right, w + 1);
            }
            //update min ,min means the minimum column value, which is the left most node
            min = Math.min(min, w);
        }
        while (map.containsKey(min)) {
            res.add(map.get(min++));
        }
        return res;
    }
}



1. Traverse the tree once and get the minimum and maximum horizontal distance with respect to root.

2. Iterate the tree and for each vertical line, fill in the values.

public List<List<Integer>> printVertically(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    // 1. find the range of left bound and right bound
    int[] range = new int[2];
    findRange(root, range, 0);

    // 2. calculate number of columns in the result
    int rootIndex = 0 - range[0];
    int columns = range[1] - range[0] + 1;
    for (int i = 0; i < columns; i++) {
        ans.add(new ArrayList<Integer>());
    }

    // 3. fill in vertically in a recursive manner
    fillNode(ans, root, rootIndex);

    return ans;
}

private void fillNode(List<List<Integer>> ans, TreeNode node, int index) {
    if (node == null) {
        return;
    }
    ans.get(index).add(node.val);
    fillNode(ans, node.left, index - 1);
    fillNode(ans, node.right, index + 1);
}

private void findRange(TreeNode node, int[] range, int position) {
    if (node == null) {
        return;
    }
    if (position < range[0]) {
        range[0] = position;
    }
    if (position > range[1]) {
        range[1] = position;
    }
    findRange(node.left, range, position - 1);
    findRange(node.right, range, position + 1);
}




我觉得可以用TreeMap，随时保证index按顺序排列，TreeMap<Integer, TreeMap<Integer, Integer>> map;

递归DFS，每次调用传入level以及横向的index，左孩子-1，右孩子+1
这样column存好之后，从左到右遍历就只要遍历TreeMap就好，也没其他好处

TreeMap<Integer, TreeMap<Integer, Integer>> map;

    void verticalPrint(TreeNode root) {
        if (root == null) return;
        map = new TreeMap<Integer, TreeMap<Integer, Integer>>();

. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴        populateMap(root, 1, 0);
        printMap(map);. more info on 1point3acres.com
    }

    void populateMap(TreeNode root, int level, int indent) {
        if (!map.containsKey(indent)) map.put(indent, new TreeMap<Integer, Integer>());
        map.get(indent).put(level, root.val);

        if (root.left != null) {
            populateMap(root.left, level+1, indent-1);
        }. more info on 1point3acres.com
        if (root.right != null) {
            populateMap(root.right, level+1, indent+1);
        } 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
    }

    void printMap(TreeMap<Integer, TreeMap<Integer, Integer>> map) {. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
        for (Integer indent : map.keySet()) {
            for (Integer level : map.get(indent).keySet()) {
                System.out.print(map.get(indent).get(level) + " ");. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
            }. visit 1point3acres.com for more.
            System.out.println();
        }
    }


// Using Map
1. Do the level order traversal
2. keep adding each node in hash-table with linked-list
3. At the end print hash table in order.


Node Value (Horizontal distance, Vertical Distance)
1 (0,0)
2 (1,1)
3 (1,1)
4 (-2,2)
5 (0,2)
6 (0,2)
7 (2,2)
8 (1,3)
9 (3.3)

Hash Table
-2 : 4
-1 : 2
0 : 1, 5, 6
1 :  3, 8
2 : 7
3 : 9

Time Complexity of hashing based solution can be considered as O(n) under the assumption that we have good hashing function that allows insertion and retrieval operations in O(1) time. In the C++ implementation, map of STL is used. map in STL is typically implemented using a Self-Balancing Binary Search Tree where all operations take O(Logn) time.
第二题就是常见题型，用BFS遍历，首先以root为column 0，每往左走column-1，往右+1，存在TreeMap里，最后遍历一遍map就好。
这样column存好之后，从左到右遍历就只要遍历TreeMap就好，也没其他好处


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PrintVerticalLevelNodes {

  public static void main(String[] args) {
    int[] array = {8, 3, 10, 1,6, 14, 4, 7, 13};
    
    BinarySearchTree bst = new BinarySearchTree();
    bst.ceateTree(array);
    printVerticalNodes(bst.getRoot());
  }
  
  public static void printVerticalNodes(BSTNode root) {
    if(root == null) {
      return;
    }
    Map<Integer, List<Integer>> levelMap = new TreeMap<Integer, List<Integer>>();
    makeVerticalLevelMap(root, levelMap, 0);
    printVerticalLevelMap(levelMap);
  }

  private static void makeVerticalLevelMap(BSTNode root,
      Map<Integer, List<Integer>> levelMap, int level) {
    if(root == null) {
      return;
    }
    List<Integer> levelList;
    if(levelMap.get(level) == null) {
      levelList = new ArrayList<Integer>();
      levelMap.put(level, levelList);
    } else {
      levelList = levelMap.get(level);
    }
    levelList.add(root.getData());
    
    if(root.getLeft() != null) {
      makeVerticalLevelMap(root.getLeft(), levelMap, level-1);
    } 
    if(root.getRight() != null) {
      makeVerticalLevelMap(root.getRight(), levelMap, level+1);
    }
  }  

  private static void printVerticalLevelMap(Map<Integer, List<Integer>> levelMap) {
    Set<Integer> keySet = levelMap.keySet();
    for(int key: keySet) {
      List<Integer> levelList = levelMap.get(key);
      for(int levelNode: levelList) {
        System.out.print(levelNode+" ");
      }
      System.out.println();
    }
  }

}