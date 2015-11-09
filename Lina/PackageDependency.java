/**
 * Created by Lin on 11/5/15.
 */
import java.util.*;

public class PackageDependency {
    public static List<String> depend(String str, Map<String, List<String>> map) {
        List<String> res = new ArrayList<String>();
        if (str == null || str.length() == 0 || !map.containsKey(str)) return res;
    //    Set<String> set = new HashSet<String>();
   //     Set<String> set2 = new HashSet<String>();

        Queue<String> queue = new LinkedList<String>();
        for (String s : map.get(str)) {
            queue.offer(s);
            res.add(s);
    //        set2.add(s);
        }
    //    set.add(str);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String temp = queue.poll();
//                if (set.contains(temp)) {
//                    System.out.println("No way!");
//                    return res;
//                }
                if (map.containsKey(temp)) {
                    for (String s : map.get(temp)) {
                      //  if (!set2.contains(s)) {
                            queue.offer(s);
                      //  }
                        if (!res.contains(s)) {
                            res.add(s);
                        }
                    }
               //     set.add(temp);
                }
            }
        }
        return res;

    }


    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list1 = new ArrayList<String>();
        list1.add("b");
        list1.add("c");
        map.put("a", list1);
        List<String> list2 = new ArrayList<String>();
        list2.add("c");
        list2.add("d");
        map.put("b", list2);
        List<String> list3 = new ArrayList<String>();
        list3.add("e");
        list3.add("d");
        map.put("c", list3);
        List<String> res = depend("a", map);
        for (String str : res) {
            System.out.print(str + " ");
        }
    }
}





/*import java.util.*;

public class PackageDependency {
	public static List<String> depend(String str, Map<String, List<String>> map) {
		List<String> res = new ArrayList<String>();
		if (str == null || str.length() == 0 || !map.containsKey(str)) return res; 
		Set<String> set = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		for (String s : map.get(str)) {
			queue.offer(s);
		}
		set.add(str);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String temp = queue.poll();
				if (set.contains(temp)) {
					System.out.println("No way!");
					return res;
				}
				if (map.containsKey(temp)) {
					for (String s : map.get(temp)) {
						queue.offer(s);
					}
					set.add(temp);
				}
				if (!res.contains(temp)) {
					res.add(temp);
				}
			}
		}
		return res;

	}


	public static void main(String[] args) {
		Map<String, List<String>> map = new HashMap<>();
		List<String> list1 = new ArrayList<>();
		list1.add("b");
		list1.add("c");
		List<String> list2 = new ArrayList<>();
		list2.add("c");
		list2.add("d");
		map.put("a", list1);
		map.put("b", list2);
		List<String> res = depend("a", map);
		for (String str : res) {
			System.out.print(str + " ");
		}
	}
}*/

/*public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[][] matrix = new int[numCourses][numCourses]; // i -> j
    int[] indegree = new int[numCourses];

    for (int i=0; i<prerequisites.length; i++) {
        int ready = prerequisites[i][0];
        int pre = prerequisites[i][1];
        if (matrix[pre][ready] == 0)
            indegree[ready]++; //duplicate case
        matrix[pre][ready] = 1;
    }

    int count = 0;
    Queue<Integer> queue = new LinkedList();
    for (int i=0; i<indegree.length; i++) {
        if (indegree[i] == 0) queue.offer(i);
    }
    while (!queue.isEmpty()) {
        int course = queue.poll();
        count++;
        for (int i=0; i<numCourses; i++) {
            if (matrix[course][i] != 0) {
                if (--indegree[i] == 0)
                    queue.offer(i);
            }
        }
    }
    return count == numCourses;
}*/
