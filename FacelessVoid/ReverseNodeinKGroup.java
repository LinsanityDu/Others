/*Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
         if (head==null||head.next==null||k<2) return head;

            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode tail = dummy, prev = dummy, temp;
            int count;
            while(true){
                count =k;
                while(count>0&&tail!=null){
                    count--;
                    tail=tail.next;
                } 
                if (tail==null) break;//Has reached the end


                head=prev.next;//for next cycle
            // prev-->temp-->...--->....--->tail-->....
            // Delete @temp and insert to the next position of @tail
            // prev-->...-->...-->tail-->head-->...
            // Assign @temp to the next node of @prev
            // prev-->temp-->...-->tail-->...-->...
            // Keep doing until @tail is the next node of @prev
                while(prev.next!=tail){
                    temp=prev.next;//Assign
                    prev.next=temp.next;//Delete

                    temp.next=tail.next;
                    tail.next=temp;//Insert

                }

                tail=head;
                prev=head;

            }
            return dummy.next;
    }
}


// NineChapter
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(k == 0 || k == 1) return head;
        ListNode cur = head;
        int length = 0;
        while (cur != null){
            cur = cur.next;
            length++;
        }
        int multi = length / k;
        if(multi == 0) return head;
        ListNode preTail = null, curHead = null, curTail = null;
        ListNode preNode = null, nextNode = null;
        cur = head;
        for(int j = 0; j < multi; j++) {
            preNode = null;
            for(int i = 0; i < k; i++) {
                if(cur != null) {
                    nextNode = cur.next;
                    cur.next = preNode;
                    preNode = cur;
                }
                if(i == 0) curTail = cur;
                if(i == (k - 1)) curHead = cur;
                cur = nextNode;
            }
            if(preTail == null) head = curHead;
            else preTail.next = curHead;
            preTail = curTail;
        }
        curTail.next = cur;
        return head;
    }
}


// Recursive
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode curr = head;
    int count = 0;
    while (curr != null && count != k) { // find the k+1 node
        curr = curr.next;
        count++;
    }
    if (count == k) { // if k+1 node is found
        curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
        // head - head-pointer to direct part, 
        // curr - head-pointer to reversed part;
        while (count-- > 0) { // reverse current k-group: 
            ListNode tmp = head.next; // tmp - next head in direct part
            head.next = curr; // preappending "direct" head to the reversed list 
            curr = head; // move head of reversed part to a new node
            head = tmp; // move "direct" head to the next node in direct part
        }
        head = curr;
    }
    return head;
}


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 0) return head;
        if (k == 0 || k == 1) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy; // current group dummy
        ListNode cur = head; // current group head
        
        while (cur != null) {
            ListNode p = pre.next;
            int group = k;
            while (p != null && group > 0) { // move p to next head as stop node
                group--;
                p = p.next;
            }
            if (group > 0) break; // end of list
            while (cur.next != p) { // note it's cur.next here
                ListNode nt = cur.next.next; // insert cur.next to head
                cur.next.next = pre.next;
                pre.next = cur.next;
                cur.next = nt; // unlink cur with cur next, link with next next
            }
            pre = cur; // move pre to current group's tail
            cur = cur.next; // move cur to next group's head
        }
        
        return dummy.next;
    }
}
