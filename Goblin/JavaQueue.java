用array[n]数组实现的至多含有n-1个元素的队列的方法.
队列具有属性head[Q],指向队列的头. 属性tail[Q]指向新元素要被插入的地方

head[Q] = tail[Q]时，队列空；
head[Q] = tail[Q]+1时，队列满；
初始head[Q] = tail[Q] = 1


public class Queue {
    private int[] array = new int[4];
    private int head = 1;
    private int tail = 1;
    //入队
    public void enqueue(int x){
        //处理上溢
        try{
            if(head != tail +1){
                array[tail++] = x;
                if(tail == array.length){
                    tail = 0;
                }
            }
            else{
                System.out.println("overflow");
                throw new Exception("queue overflow");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //出队
    public int dequeue(){
        int number=0;
        try{
            if(tail != head){
                number = array[head];
                head++;
            }
            else{
                throw new Exception("queue underflow");
            }

        }catch(Exception e){
            System.out.println("underflow");
            e.printStackTrace();
        }
        return number;
    }