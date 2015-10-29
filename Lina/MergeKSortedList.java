public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if(lists == null || lists.size() == 0) {
            return null;
        }
        PriorityQueue<ListNode> q = new PriorityQueue<ListNode> (lists.size(), new Comparator<ListNode>() {
            @Override
            public int compare(ListNode a, ListNode b) {
                return Integer.compare(a.val, b.val);
            }
        });

        ListNode head = new ListNode(0);//remove the hassel of some coner cases
        ListNode cur = head;
        //add the 1st node of each list to initialize it
        for(ListNode l : lists) {
            if(l != null) { //in case lists size is not 0 but only contains nulls
                q.add(l);
            }
        }
        while(!q.isEmpty()) {
            ListNode temp = q.poll();
            cur.next = temp;
            //keep adding the next node in the list
            if(temp.next != null){
                q.add(temp.next);
            }
            cur = cur.next;
        }
        return head.next;
    }
}