	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	public class Solution {
	    public ListNode reverseList(ListNode head) {
	        if (head == null) return head;
	        if (head.next == null) return head;
	        ListNode secondEle = head.next;
	        head.next = null;
	        ListNode revSecondEle = reverseList(secondEle);
	        secondEle.next = head;
	        return revSecondEle;
	    }
	}



	public class Solution {
	    public ListNode reverseList(ListNode head) {
	        ListNode result = null;
	        ListNode cur = head;
	        while (cur != null) {
	             ListNode temp = cur.next;
	             cur.next = result;
	             result = cur;
	             cur = temp;
	        }
	        return result;
	    }
	}

// Recursive
public ListNode reverseList(ListNode head) {
    return reverseListInt(head, null);
}

public ListNode reverseListInt(ListNode head, ListNode newHead) {
    if(head == null)
        return newHead;
    ListNode next = head.next;
    head.next = newHead;
    return reverseListInt(next, head);
}


// Iterative
public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode nextNode=head.next;
        ListNode newHead=reverseList(nextNode);
        nextNode.next=head;
        head.next=null;
        return newHead;
    }
}