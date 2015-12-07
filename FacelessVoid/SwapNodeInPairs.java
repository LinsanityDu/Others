/*Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// My Code
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        ListNode next = head.next;
        while (cur.next != null) {
            ListNode temp = next.next;
            prev.next = next;
            next.next = cur;
            cur.next = temp;
            prev = cur;
            cur = temp;
            if (cur == null) {
                break;
            } else {
                next = cur.next;
            }
        }
        return dummy.next;
    }
}


// Recursion extra space
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if ((head == null)||(head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
}

// Discussion

public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode point = new ListNode(0);
        point.next = head;
        head = point;
        while(point.next != null && point.next.next != null) {
            ListNode tmp = point.next.next.next;
            point.next.next.next = point.next;
            point.next = point.next.next;
            point.next.next.next = tmp;
            point = point.next.next;
        }
        return head.next;
    }
}