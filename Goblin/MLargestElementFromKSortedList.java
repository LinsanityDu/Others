/*first question: select M largest elements from N sorted linked lists. from: 1point3acres.com/bbs 
Every element has to be checked, either add them to the priority queue, or ignore if it's smaller than the smallest element in the queue. . more info on 1point3acres.com
Run time complexity: worst case: O(log(M) * number of total elements); best case: O (log(M) + number of total elements)*/

 public static List<Integer> selectMLargest(List<Queue<Integer>> input, int m) {
        List<Integer> result = new ArrayList<Integer>();
        Queue<Integer> queue = new PriorityQueue<Integer>(m);
        while (true) {. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
            boolean found = false;
            for (int i = 0; i < input.size(); i++) {. from: 1point3acres.com/bbs 
                if (input.get(i).isEmpty()) continue;
. more info on 1point3acres.com                if (queue.size() < m) {
                    found = true;. more info on 1point3acres.com
                    queue.offer(input.get(i).poll());.鏈枃鍘熷垱鑷�1point3acres璁哄潧
                }
                else if (queue.peek() < input.get(i).peek()) {
                    found = true;
                    queue.poll();
                    queue.offer(input.get(i).poll());
                } 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
            }
            if (!found) break;
        }. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
        while (!queue.isEmpty()) result.add(queue.poll());
        return result;. Waral 鍗氬鏈夋洿澶氭枃绔�,
    }