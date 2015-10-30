1. Task schedule: 给一个task list 比如： ABCDAABA， colddown time 是2， 问需要多长时间能完成这些任务。 每个task花费的时间是1， colddown time是指相同的任务必须等待冷却时间才能再一次执行， 要不然就得 空闲来等待。拿例子来说， 要完成这个任务需要的时间是11， ABCDA__AB_A，下划线是等待时

int taskScheduler(String s) {
	char cold1 = '*';
	char cold2 = '*';
	int result = 0;
	for (int i = 0; i < s.length(); i++) {
		if (cold2 == s.charAt(i)) {
			result += 3;
			cold1 = cold2 = '*';
		} else if (cold1 = s.charAt(i)) {
			result += 2;
			cold1 = cold2;
			cold2 = '*';
		} else {
			result++;
			cold1 = cold2;
			cold2 = s.charAt(i);
		}
	}
	return result;
}


我的思路是跟LZ差不多，我是用Java的, 用HashMap 记录每个task(no matter char or other object), value 是index， 每次到一个task, 去hashMap 查询，如果查得到，并且currentIndex - index + 1 < 2. 时间就要补差值 1 or 2. 如果其他的情况，都可以直接运行task，然后更新hashMap
