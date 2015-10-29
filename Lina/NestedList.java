private int getSum(List<NestedInteger> input, int level) {  
    int sum = 0;  
    for(int i = 0 ; i < input.size(); i++) {  
        if(input.get(i).isInteger())   
            sum += level * input.get(i).getInteger();  
        else   
            sum += getSum(input.get(i).getList(), level + 1);  
    }  
    return sum;  
}  
  
public int depthSum (List<NestedInteger> input)  
{  
     //Implement this function  
    return getSum(input, 1);  
}  
  
/** 
 * This is the interface that allows for creating nested lists. You should not implement it, or speculate about its implementation 
 */  
public interface NestedInteger   
{  
    // Returns true if this NestedInteger holds a single integer, rather than a nested list  
    public boolean isInteger();  
  
    // Returns the single integer that this NestedInteger holds, if it holds a single integer  
    // Returns null if this NestedInteger holds a nested list  
    public Integer getInteger();  
  
    // Returns the nested list that this NestedInteger holds, if it holds a nested list  
    // Returns null if this NestedInteger holds a single integer  
    public List<NestedInteger> getList();  
}    



NestList 1: 求Sum
/**
* Given a nested list of integers, returns the sum of all integers in the list weighted by their depth. 
* For example, given the list {{1,1},2,{1,1}} the function should return 10 (four 1's at depth 2, one 2 at depth 1). 
* Given the list {1,{4,{6}}} the function should return 27 (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3)
*/

This is the nested integer structure.
public interface NestedInteger 
{ 
	// Returns true if this NestedInteger holds a single integer, rather than a nested list 
	public boolean isInteger(); 

	// Returns the single integer that this NestedInteger holds, if it holds a single integer 
	// Returns null if this NestedInteger holds a nested list 
	public Integer getInteger(); 

	// Returns the nested list that this NestedInteger holds, if it holds a nested list 
	// Returns null if this NestedInteger holds a single integer 
	public List<NestedInteger> getList(); 

}
public int getSum(List<NestedInteger> input) {
	return depthSum(input, 1);
}
public int depthSum (List<NestedInteger> input, int weight) 
{ 
	if (input == null) {
		return 0;
	}
	
	int sum = 0;
	for (NestedInteger elem : input)
	{
		if (elem.isInteger()) {
			sum += weight * elem.getInteger();
		} else {
			sum += depthSum(elem.getList(), weight+1);
		}
	}
	
	return sum;
}



Nest List2: 求Reverse sum
先算出高度，做法就跟楼主的一样了。
用queue的话，把每层的nestedinteger全放到arraylist里，然后这个arraylist放queue里。 
然后通过queue的size知道高度。。。


先求depth：
private int depth(List<NestedInteger> input){
    int d = 0;
    
    for(NestedInteger nestedInt : input){
       
        if (!nestedInt.isInteger()) 
        	d = max(d, depth(nestInteger.getList());
               
    }
    return d + 1;
}

再求Weight sum

    int noWeiSum = 0;
    int height = 0;  
private int getSum(List<NestedInteger> input, int level) {  
    int weiSum = 0;
    for(int i = 0 ; i < input.size(); i++) {  
        if(input.get(i).isInteger())   
            weiSum += level * input.get(i).getInteger();
        	noWeiSum += input.get(i).getInteger();
        	height = max(height, level);
        else   
            weiSum += getSum(input.get(i).getList(), level + 1); 
    }  
    return sum;  
}  

Nest List3: Iterator
/*就是说你要实现一个Iterator，每次调用next都返回nested array中的下一个，所谓下一个就是比如，
nested array 是 [[1,2], 3,[4, [5]]]
Call Iterator 的next 返回的序列应该是 1,2,3,4,5
思路就是用一个stack，首先放入最外层的Iterator，对于每个next，check 栈顶Iterator的next，如果是Iterator，入栈然后继续循环check 栈顶；如果是int，直接返回；如果next不存在，删除栈顶，继续循环check栈顶*/



public class DeepIterator implements Iterator<Integer> {
	private Stack<Iterator> iteratorStack = new Stack<Iterator>();
	private Integer top = null;

	public DeepIterator(Iterable iterable){
		this.iteratorStack.push(iterable.iterator());
	}

	@Override
	public boolean hasNext() {
		if(this.top != null) return true;

		while(!this.iteratorStack.isEmpty()) {
			Iterator tmpIterator = this.iteratorStack.peek();

			if(tmpIterator.hasNext()){
				Object tmp = tmpIterator.next();
				if(tmp instanceof Integer){
					this.top = (Integer) tmp;
					return true;
				} else if(tmp instanceof Iterable){
					this.iteratorStack.push(((Iterable) tmp).iterator());
				} else {
					throw new RuntimeException("Unsupported data type. ");
				}
			} else {
				this.iteratorStack.pop();
			}
		}
		return false;
	}

	@Override
	public Integer next() throws NoSuchElementException {
		if(hasNext()){
			Integer tmp = this.top;
			this.top = null;
			return tmp;
		}

		throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("This is not supported right now.");
	}



// Anothre Deep Iterator
package collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Implement an iterator that provides a flattening iterator over a
 * Collection<?>.
 * 
 * i.e. The constructor takes in a Collection<?> object which may nest
 * either a Collection<?> object or an object of type T.
 * Also, it is expected that the nested Collection<?> objects will not
 * reference their container Collection<?> objects as this will lead
 * to a loop. But they can nest other Collection<?> objects. The class
 * then iterates through elements in the given Collection<?> object,
 * returning the values in the leaves of the nested structure one-by-one
 * upon each call to next().
 *
 * Example: This deep iterator can be used to iterate over a vector that
 *          either contains a vector of integers, or just plain integers,
 *          or both. Lets assume that the Collection<?> passed has the
 *          following structure:
 *
 *                           [int_a, vector_1, int_d, vector_2, int_h]
 *                                     /                 \
 *                                    /                   \
 *                               [int_b, int_c]        [int_e, vector_3]
 *                                                                /
 *                                                               /
 *                                                         [int_f, int_g]
 *
 *          The iterator returns the leaves in order viz.: int_a, int_b,
 *          int_c, int_d, int_e, int_f, int_g, int_h.
 * 
 * Methods expected to be implemented:
 * 
 * public class DeepIterator<T> implements Iterator<T> {
 * 		public DeepIterator(Collection<?> collection) {..}
 * 		public boolean hasNext() {...}
 * 		public T next() {...}
 * }
 */

public class DeepIterator<T> implements Iterator<T> {
	// A reference to the item which will be returned during
	// the next call to next().
	private T nextItem;
	private final Stack<Iterator<?>> stack = new Stack<Iterator<?>>();

	public DeepIterator(Collection<?> collection) {
		if (collection == null) {
			throw new NullPointerException("Can't iterate over a null collection.");
		}
		stack.push(collection.iterator());
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean hasNext() {
		if (nextItem != null) {
			return true;
		}

		while (!stack.isEmpty()) {
			Iterator<?> iter = stack.peek();
			if (iter.hasNext()) {
				Object item = iter.next();
				if (item instanceof Collection<?>) {
					stack.push(((Collection<?>) item).iterator());
				} else {
					nextItem = (T) item;
					return true;
				}
			} else {
				stack.pop();
			}
		}

		return false;
	}

	@Override
	public T next() {
		if (hasNext()) {
			T toReturn = nextItem;
			nextItem = null;
			return toReturn;
		}

		throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}



// Flatten List
import java.util.LinkedList;
import java.util.List;
 
 
public final class FlattenUtil {
 
	public static List<Object> flatten(List<?> list) {
		List<Object> retVal = new LinkedList<Object>();
		flatten(list, retVal);
		return retVal;
	}
 
	public static void flatten(List<?> fromTreeList, List<Object> toFlatList) {
		for (Object item : fromTreeList) {
			if (item instanceof List<?>) {
				flatten((List<?>) item, toFlatList);
			} else {
				toFlatList.add(item);
			}
		}
	}
}