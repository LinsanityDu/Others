/*Sort a linked list in O(n log n) time using constant space complexity.*/

// My suck code
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode mid = findMiddle(head);

        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);

        return merge(left, right);
        
    }
    
    
    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null && l2 != null) return l2;
        if (l1 != null && l2 == null) return l1;

        ListNode dummy = new ListNode(0);
        ListNode start = dummy;
        while (l1 != null && l2 != null) {
        	if (l1.val <= l2.val) {
        		start.next = new ListNode(l1.val);
        		start = start.next;
        		l1 = l1.next;
        	} else {
        		start.next = new ListNode(l2.val);
        		start = start.next;
        		l2 = l2.next;
        	}
        }
        if (l1 != null) start.next = l1;
        if (l2 != null) start.next = l2;
        return dummy.next;
    }
    
    
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    } 
}


