/*Consider a big party where a log register for guest’s entry and exit times is maintained. Find the time at which there are maximum guests in the party. Note that entries in register are not in any order.
*/

private static List<Integer> findMaxOverlap(int[] enter, int[] exit) {
    Arrays.sort(enter);
    Arrays.sort(exit);
    int maxCount = 0;
    int i = 0;
    int j = 0;
    int count = 0;
    List<Integer> res = new ArrayList<>();
    while (i < enter.length) {
      if (enter[i] <= exit[j]) {
        count++;
        if (count > maxCount) {
          maxCount = count;
          res.clear();
          res.add(enter[i]);
        } else if (count == maxCount) {
          res.add(enter[i]);
        }
        i++;
      } else {
        count--;
        j++;
      }
    }
    return res;
  }