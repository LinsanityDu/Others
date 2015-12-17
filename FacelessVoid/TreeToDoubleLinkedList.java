2, Tree to doubly linked list

                         4
                        / \
                       /   \.
                      10    7
                     /  \    \
                    /    \    \
                   3      2    1. 
   
  3 <-> 10 <-> 2 <-> 4 <-> 7 <-> 1

import java.io.*;
import java.util.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int v) {
      this.val = v;
    }
  }

  private TreeNode convert(TreeNode root) {
    TreeNode head = null;
    TreeNode pre = null;
    TreeNode cur = root;
    Stack<TreeNode> stk = new Stack<>();
    while (cur != null || !stk.isEmpty()) {
      if (cur != null) {
        stk.push(cur);
        cur = cur.left;
      } else {
        TreeNode node = stk.pop();
        if (head == null) {
          head = node;
        }
        if (pre != null) {
          pre.left = node;
        }
        cur = node.right;
      
        node.right = pre;
        pre = node;
      }
    }

    head.right = null;
    pre.left = null;
    return head;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    TreeNode n1 = new TreeNode(4);
    TreeNode n2 = new TreeNode(10);
    TreeNode n3 = new TreeNode(7);
    TreeNode n4 = new TreeNode(3);
    TreeNode n5 = new TreeNode(2);
    TreeNode n6 = new TreeNode(1);
    n1.left = n2;
    n1.right = n3;
    n2.left = n4;
    n2.right = n5;
    n3.right = n6;
    TreeNode head = s.convert(n1);
    TreeNode tail = null;
    while (head != null) {
      System.out.println(head.val);
      tail = head;
      head = head.left;
    }
    System.out.println("====");
    while (tail != null) {
      System.out.println(tail.val);
      tail = tail.right;
    }
  }
}



public class ConvertTreeToDoubleLinkedList {
	public TreeNode convertTreeToDoubleLinkedList(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode head = root;
		TreeNode tail = root;
		if (root.left != null) {
			TreeNode left = convertTreeToDoubleLinkedList(root.left);
			head = left;
			left.left.right = root;
			root.left = left.left;
		}
		if (root.right != null) {
			TreeNode right = convertTreeToDoubleLinkedList(root.right);
			tail = right.left;
			root.right = right;
			right.left = root;
		}
		head.left = tail;
		tail.right = head;
		return head;
	}
}