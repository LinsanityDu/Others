ProducerConsumer.java
// Find it on Geeks for Geeks 
/*How to implement a stack which will support following operations in O(1) time complexity?
1) push() which adds an element to the top of stack.
2) pop() which removes an element from top of stack.
3) findMiddle() which will return middle element of the stack.
4) deleteMiddle() which will delete the middle element.
Push and pop are standard stack operations.*/

public class MidStack<E> {
    private class Entry<E> {
        E elem;
        Entry<E> prev;
        Entry<E> next;
        
        Entry(E elem) {
            this.elem = elem; prev = null; next = null;
        }
    }
    
    Entry<E> top;
    Entry<E> mid;
    int size;
    
    public MidStack() {
        top = null;
        mid = null;
        size = 0;
    }
    
    public void push(E elem) {
        Entry<E> entry = new Entry<E>(elem);
        if (top != null) {
            top.next = entry;
            entry.prev = top;
        }
        top = entry;
        size++;
        if (mid == null) mid = top;
        else mid = size()%2 == 1 ? mid.next : mid;
    }
    
    public E pop(E elem) {
        if (size() == 0)
            throw new EmptyStackException();
        E r = top.elem;
        top = top.prev;
        size--;
        mid = size()%2 == 0 ? mid.prev : mid;
        return r;
    }
    
    public E findMiddle() {
        if (size() == 0)
            throw new EmptyStackException();
        return mid.elem;
    }
    
    public void deleteMiddle() {
        if (size() == 0)
            throw new EmptyStackException();
        if (size() == 1) 
            mid = top = null;
        size--;
        mid.next.prev = mid.prev;
        if (mid.prev != null) mid.prev.next = mid.next;
    }
    
    public int size() {
        return size;
    }
    
    public static void main(String[] args) {
        MidStack<Integer> stack = new MidStack<Integer>();
        stack.push(1);
        System.out.println(stack.findMiddle());
        stack.push(2);
        stack.push(3);
        System.out.println(stack.findMiddle());
        stack.deleteMiddle();
        stack.push(4);
        stack.push(5);
        System.out.println(stack.findMiddle());
    }
}
