这样也不行，这样minheap弹出当前最小值以后无法判断它是从哪个iterator出来的，然后就不知道该把哪个iterator的next放入minheap了。我把next值和对应的iterator放在一个class里了，不知道这样可以么
写comparator的时候如果直接用a.next()的话总感觉好像有点问题，就像stack.pop()了一样，我也不太熟悉iterator的操作，还得大神们来指导啊


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
        int next;
        Iterator<Integer> iter;
        public IntIter(Iterator<Integer> iter) {
            this.next = iter.next();
            this.iter = iter;
        }
    }

// Other
public static Iterable<Integer> mergeKSortedIterators(List<Iterator<Integer>> Iters){

        Queue<newIter> minHeap = new PriorityQueue<newIter>();
        List<Integer> result = new ArrayList<Integer>();
        for(Iterator<Integer> iter : Iters){
            if(iter.hasNext()){
                minHeap.add(new newIter(iter.next(), iter));
            }
        }

        while(!minHeap.isEmpty()){
            newIter newiter = minHeap.poll();
            result.add(newiter.getValue());
            if(newiter.hasNext()){
                minHeap.add(newiter);
            }
        }
        return result;
    }
    //这里存的是当前的value，因为直接compare哪个头最小的话，compare一次next()的值就没了，干脆直接用这个class来保存。
    //push的时候直接用里面的value来比较
    private static class newIter implements Comparable<newIter>{
        private Integer value;
        private Iterator<Integer> iter;
        public newIter(Integer val, Iterator<Integer> it){
            this.value = val;
            this.iter = it;
        }
        public Integer getValue(){
            return this.value;
        }
        public boolean hasNext(){
            if(iter.hasNext()){
                value = iter.next();
                return true;
            }
            return false;
        }

        public int compareTo(newIter a){
            return this.value - a.value;
        }
    }