HashMap Key->元素，Value是该元素在array中的index

remove：通过hashtable找到元素的index，然后swap这个元素和array最末尾的元素，同时修改原本末尾元素在hashtable中的值


implement data structure insert, delete, random delete both in O(1)


special container add/remove/removeRandom at O(1): array + hashmap


public class DesignDataStructure {
	Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
	List<Integer> list = new ArrayList<Integer>();

	public void add(int num) {
		list.add(num);
		int index = list.size() - 1;
		if (map.containsKey(num)) {
			map.get(num).add(index);
		} else {
			Set<Integer> set = new HashSet<Integer>();
			set.add(index);
			map.put(num, set);
		}
	}

	public void delete(int num) {
		if (map.containsKey(num) == false) {
			throw new RuntimeException("this number is not the memory");
		}
		Iterator<Integer> it = map.get(num).iterator();
		int index = it.next();
		map.get(num).remove(index);
		int last = list.get(list.size() - 1);
		list.set(index, last);
		map.get(last).remove(list.size() - 1);
		map.get(last).add(index);
	}
	public void randomDelete() {
		Random rand = new Random();
		int index = rand.nextInt(list.size());
		int num = list.get(index);
		delete(num);
	}
}

The nextInt(int n) method is used to get a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive), drawn from this random number generator's sequence.



