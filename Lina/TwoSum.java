public interface TwoSum {
    /**
     * Stores @param input in an internal data structure.
     */
    void store(int input);
    /**
     * Returns true if there is any pair of numbers in the internal data structure which
     * have sum @param val, and false otherwise.
     * For example, if the numbers 1, -2, 3, and 6 had been stored,
     * the method should return true for 4, -1, and 9, but false for 10, 5, and 0
     */
    boolean test(int val);
}

public class searchSum implements TwoSum{

    List<Integer> data = new ArrayList();
    void store(int input){
        data.add(intput);
    }

    boolean test(int val){
        int len = data.size();
       Set<Integer> mem = new HashSet();
        for(int i = 0; i < len; i++){
            if ( mem.contains(val - data.get(i)) return true;
           else mem.add(data.get(i));
        }
        return false;
    }
}


public class searchSumFaster implements TwoSum{

    Set<Integer> sum = new HashSet();
    List<Integer> data = new ArrayList();
    void store(int input){
        if (data.size() != 0){
            for(int i = 0; i < data.size(); i++){
                sum.add(data.get(i) + input);
            }
        }
        data.add(intput);
    }

    boolean test(int val){
        return sum.contains(val);
    }
}