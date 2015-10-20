/*Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

Hint:

Expected runtime complexity is in O(log n) and the input is sorted.*/


public class Solution {
    public int hIndex(int[] citations) {
        
    }
}




/*Just binary search, each time check citations[mid] case 1: citations[mid] == len-mid, then it means there are citations[mid] papers that have at least citations[mid] citations. case 2: citations[mid] > len-mid, then it means there are citations[mid] papers that have moret than citations[mid] citations, so we should continue searching in the left half case 3: citations[mid] < len-mid, we should continue searching in the right side After iteration, it is guaranteed that right+1 is the one we need to find (i.e. len-(right+1) papars have at least len-(righ+1) citations)*/

class Solution {
public:
    int hIndex(vector<int>& citations) {
        int left=0, len = citations.size(), right= len-1,  mid;
        while(left<=right)
        {
            mid=(left+right)>>1;
            if(citations[mid]== (len-mid)) return citations[mid];
            else if(citations[mid] > (len-mid)) right = mid - 1;
            else left = mid + 1;
        }
        return len - (right+1);
    }
};


O(lg n)

int hIndex(vector<int>& citations) {
   int left = 0;
   int right = citations.size() - 1;
   int n = citations.size();
   int last = 0;

   while (left <= right) {
      int mid = left + (right - left) / 2;
      if (citations[mid] == n - mid)
        return n - mid;

      if (n - mid < citations[mid]) {
        right = mid - 1;
        last = n - mid;
      }
      else
        left = mid + 1;
   }

   return last;
}
O(n)

int hIndex(vector<int>& citations) {
   int n = citations.size();
   for(int i = 0; i<citations.size(); i++) {
      if(n - i <= citations[i])
           return n - i;
   }

   return 0;
}