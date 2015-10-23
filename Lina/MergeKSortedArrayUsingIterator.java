public Iterable<Integer> mergeKSortedIterators(Iterator<Integer>[] iters){
        Queue<IntIter> minHeap = new PriorityQueue<IntIter>(iters.length, new iteratorComp());
        List<Integer> result = new ArrayList<Integer>();
        for(Iterator<Integer> iter : iters){
            if(iter.hasNext()){
                minHeap.add(new IntIter(iter));
            }
        }
        while(!minHeap.isEmpty()){
            IntIter curr = minHeap.poll();
            result.add(curr.next);
            if(curr.iter.hasNext()){
                minHeap.add(new IntIter(curr.iter));
            }
        } 
        return result;
    }
    
    public class iteratorComp implements Comparator<IntIter>{
        public int compare(IntIter a, IntIter b){
            return a.next - b.next;
        }
    }

    public class IntIter {
        int next;.
        Iterator<Integer> iter;
        public IntIter(Iterator<Integer> iter) {
            this.next = iter.next();
            this.iter = iter;
        }
    }