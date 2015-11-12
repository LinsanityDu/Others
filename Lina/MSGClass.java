
class Msg
{
 long key; 
 long value;
};
每个Msg都对应一个key。现在要设计一个class，实现如下功能
class Window
{
 Window(int nMilliseconds);
 addMsg(Msg m);
 Msg getMsg(long key);
 Double getAvg();
};
nMilliseconds给你了个time window，比如说如果是１０分钟，你只保存当前为止１０
分钟前的MSG。你需要能够添加信息，实现对于key的query（注意如果这个key对应的信
息是１０分钟前的，就返回NULL）， 和计算所有１０分钟内msg的平均。
我本来想用circular array，但不知道该怎么存这些msg。还有就是每个key和msg应该
用map来存吧，但如果用CIRCULAR ARRAY对于过时的信息还是得在MAP里一条一条删除。
我觉得好象也没有怎么省时间。所以我干脆用一个linkedlist来存每个MSG的timestamp
和msg，每次调用其他函数前先得update ，同时在list和map里删除过时的节点。对于
计算AVG，只需要记录tot和msg的个数，并update就可以了.不知道这题要不要考多线程
，反正我跟面试官说我一点也不会。
class MsgWrapper{
	Msg m;
	long timeStamp;
	MsgWrapper(long t, Msg msg){
   	m = msg;
   	timeStamp = t;
	}
}
class Window{
	private long getTimeStamp(){
   	return System.currentTimeMillis();
	}
	long windowSize;
	List<MsgWrapper> list;
	long tot;
	int numMsg;
	Map<Long, Msg> map;
	Window(int nMilliseconds){
   	list = new LinkedList<MsgWrapper>();
   	windowSize = (long)nMilliseconds;
   	tot = 0;
   	numMsg = 0;
   	map = new HashMap<Long, Msg>();
	}
  
	private long update(){
   	long timeStamp = getTimeStamp();
   	long lastTimeStamp = timeStamp - windowSize;
   	int ptr = 0;
   	while (ptr < list.size()){
       	if (list.get(0).timeStamp < lastTimeStamp){
           	Msg currMsg = list.get(0).m;
           	map.remove(currMsg.key);
           	tot -= currMsg.value;
           	--numMsg;
           	list.remove(0);
       	}else
           	break;
   	}
   	return timeStamp;
	}
  
	addMsg(Msg m){
   	update();
   	list.add(new MsgWrapper(currTImeStamp, m));
   	map.put(m.key, m);
   	tot += m.value;
   	++numMsg;
	}
  
	Msg getMsg(long key){
   	update();
   	return map.get(key);
	}
  
	double getAvg(){
   	update();
   	return ((double)tot)/numMsg;
	}
}
他最后也没说什么也没给提示。有没有朋友帮忙给出更efficient的解法呀？上次别人
贴这题的时候就有不少朋友说CIRCULAR BUFFER。到底该怎么用呢？非常感谢
