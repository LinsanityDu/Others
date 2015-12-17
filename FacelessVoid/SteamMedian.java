让我找streaming median

private void getMedian(int nums[]) {
  PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
  PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
   public int compare(Integer i1, Integer i2) {
    return i2.intValue() - i1.intValue();
   }
  });
  for (int i = 0; i < nums.length; i++) {
   int n = nums[i];
   if (maxHeap.size() == 0 || n <= maxHeap.peek()) {
    maxHeap.add(n);
   } else {
    minHeap.add(n);
   }
   if (maxHeap.size() - minHeap.size() >= 2) {
    int val = maxHeap.poll();
    minHeap.add(val);
   }
   if (minHeap.size() - maxHeap.size() >= 2) {
    maxHeap.add(minHeap.poll());
   }
   if (maxHeap.size() == minHeap.size()) {
    System.out.println((maxHeap.peek() + minHeap.peek()) / 2);
   } else if (maxHeap.size() > minHeap.size()) {
    System.out.println(maxHeap.peek());
   } else{
    System.out.println(minHeap.peek());
   }
  }
 }