首先定义了suffix string 或者说suffix arrary
    如果有个数组是 int[] text = {10, 20, 30, 25}
   那么 suffix[0] = {10, 20, 30, 25}. from: 1point3acres.com/bbs 
           suffix[1] = {20, 30, 25}. 1point 3acres 璁哄潧
           suffix[2] = {30, 25}
           suffix[3] = {25}. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
如果对这些数组进行lexical order 的排序，我们可以得到
suffix[0] < suffix[1] < suffix[3] < suffix[2]
问题是：
    input: int[] text. Waral 鍗氬鏈夋洿澶氭枃绔�,
    output: int[] suffix_array_order.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
e.g.
input: int[] text = {10, 20, 30, 25}
output: int[] suffix_array_order = {0, 1, 3, 2}


第二题： input:  int[] text, int[] subText
              output: boolean isExist;
检查text数组中有没有一个subarray 是subText。要求时间小于O(N^2)， N == text.length;
这里假设我们有了第一题的 suffix_array_order.


public class Index {
    int[] suff;
    int idx;. more info on 1point3acres.com
    Index(int[] _suff, int _idx) {
        suff = _suff;
        idx = _idx; 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
    } 
}

//Sort.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
int[] suffixOrder(int[] text, int n) {. from: 1point3acres.com/bbs 
    int[] res = new int[n];
    Index[] indices = new Index[n];
    for (int i=0; i < n; i++) {
        int[] suffix = Arrays.copyOfRange(text, i, text.length);
        indices[i] = new Index(suffix, i);
    }
. 鍥磋鎴戜滑@1point 3 acres
    Arrays.sort(indices, new Comparator<Index>() {
        public int compare(Index i1, Index i2) {
            int p1 = 0;
            int p2 = 0;
            while (p1 < i1.suff.length && p2 < i2.suff.length) {
                if(i1.suff[p1] != i2.suff[p2]) {
                    return i1.suff[p1] - i2.suff[p2];-google 1point3acres
                } else {
                    p1 ++;.1point3acres缃�
                    p2 ++;
                }
            }
            if (p2 == i2.suff.length) {
                return 1;-google 1point3acres
            }
            return -1;
        }
    });

    for (int i = 0; i < n; i++) {. 1point 3acres 璁哄潧
        res[i] = indices[i].idx;. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
    }
    return res;
}

// Search. more info on 1point3acres.com
boolean search(int[] text, int n, int[] sub_text, int m) {
    int[] suffix_order = suffixOrder(text, n);. From 1point 3acres bbs
    Comparator<Index> textCompare = new Comparator<Index>(){
        @Override.鏈枃鍘熷垱鑷�1point3acres璁哄潧
        public int compare(Index i1, Index i2){
            while (a < text.length && b < sub_text.length) {. 1point3acres.com/bbs
                if (text[a] != sub_text[b]) {
                    return text[a] - sub_text[b];
                } else {
                    a ++;. Waral 鍗氬鏈夋洿澶氭枃绔�,
                    b ++;.鏈枃鍘熷垱鑷�1point3acres璁哄潧
                }. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
            }
            if(a == text.length) return -1;
            return 0;. From 1point 3acres bbs
        }. Waral 鍗氬鏈夋洿澶氭枃绔�,
    };
. visit 1point3acres.com for more.
    int lo = 0;
    int hi = n - 1;
    while (lo <= hi) {
    int mid = (lo + hi) / 2;. visit 1point3acres.com for more.
    int[] suffix = Arrays.copyOfRange(text, suffix_order[mid], text.length);
    int cmp = textCompare(suffix, sub_text);.鏈枃鍘熷垱鑷�1point3acres璁哄潧
    if (cmp == 0) {.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
        return true;. Waral 鍗氬鏈夋洿澶氭枃绔�,
    } else if(cmp < 0) {
        lo = mid + 1;
    } else {}
        hi = mid - 1;
    }
    return false;
}

int main(int argc, char const* argv[]) {
    int text[4] = {10, 20, 30, 25};. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
    int[] res = suffixOrder(text, 4);
    int sub_text[2] = {20, 30};
    boolean search_res = search(text, 4, sub_text, 2);
}




#include <iostream>.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
#include <vector>
using namespace std;

struct Index {
  int* p;
  int idx;.1point3acres缃�
  Index(int* _p, int _idx) : p(_p), idx(_idx) {}. From 1point 3acres bbs
};

vector<int> suffixOrder(int text[], int n) {
  vector<int> res(n);
  vector<Index> indices;. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
  for (int i; i < n; i++) indices.push_back(Index(text + i, i));. more info on 1point3acres.com
  function<bool(Index, Index)> compare = [&](const Index i1, const Index i2) {
    int* p1 = i1.p;. From 1point 3acres bbs
    int* p2 = i2.p;
    while (p1 != text + n && p2 != text + n) {
      if (*p1 != *p2)
        return *p1 < *p2;
      else {. Waral 鍗氬鏈夋洿澶氭枃绔�,
        p1++;.1point3acres缃�
        p2++;
      }
    }
    if (p2 == text + n) return false;. 1point3acres.com/bbs
    return true;
  };
  sort(indices.begin(), indices.end(), compare);. 鍥磋鎴戜滑@1point 3 acres
  for (int i = 0; i < n; i++) {
    res[i] = indices[i].idx;
  }
  return res;
}

bool search(int text[], int n, int sub_text[], int m) {
  vector<int> suffix_order = suffixOrder(text, n);
  function<int(int*, int*)> textCompare = [&](int* a, int* b) {
    while (a != text + n && b != sub_text + m) {
      if (*a != *b). 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
        return *a < *b ? -1 : 1;
      else {
        a++;
        b++;
      }
    }.1point3acres缃�
    if (a == text + n) return -1;
    return 0;. from: 1point3acres.com/bbs 
  };

  int lo = 0;
  int hi = n - 1;
  while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    int cmp = textCompare(text + suffix_order[mid], sub_text);
    if (cmp == 0)
      return true;
    else if (cmp < 0)
      lo = mid + 1;
    else
      hi = mid - 1;.1point3acres缃�
  }
  return false;
}
.鏈枃鍘熷垱鑷�1point3acres璁哄潧
int main(int argc, char const* argv[]) {. From 1point 3acres bbs
  int text[4] = {10, 20, 30, 25};
  vector<int> res = suffixOrder(text, 4);
  for (int i = 0; i < res.size(); i++) cout << res[i] << " ";
  cout << endl;
  int sub_text[2] = {20, 30};
  bool search_res = search(text, 4, sub_text, 2);
  cout << search_res << endl;
}



We strongly recommend to read following post on suffix trees as a pre-requisite for this post.

Pattern Searching | Set 8 (Suffix Tree Introduction)

A suffix array is a sorted array of all suffixes of a given string. The definition is similar to Suffix Tree which is compressed trie of all suffixes of the given text. Any suffix tree based algorithm can be replaced with an algorithm that uses a suffix array enhanced with additional information and solves the same problem in the same time complexity (Source Wiki).
A suffix array can be constructed from Suffix tree by doing a DFS traversal of the suffix tree. In fact Suffix array and suffix tree both can be constructed from each other in linear time.
Advantages of suffix arrays over suffix trees include improved space requirements, simpler linear time construction algorithms (e.g., compared to Ukkonen’s algorithm) and improved cache locality (Source: Wiki)

Example:

Let the given string be "banana".

0 banana                          5 a
1 anana     Sort the Suffixes     3 ana
2 nana      ---------------->     1 anana  
3 ana        alphabetically       0 banana  
4 na                              4 na   
5 a                               2 nana

So the suffix array for "banana" is {5, 3, 1, 0, 4, 2}
Naive method to build Suffix Array
A simple method to construct suffix array is to make an array of all suffixes and then sort the array. Following is implementation of simple method.

// Naive algorithm for building suffix array of a given text
#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;
 
// Structure to store information of a suffix
struct suffix
{
    int index;
    char *suff;
};
 
// A comparison function used by sort() to compare two suffixes
int cmp(struct suffix a, struct suffix b)
{
    return strcmp(a.suff, b.suff) < 0? 1 : 0;
}
 
// This is the main function that takes a string 'txt' of size n as an
// argument, builds and return the suffix array for the given string
int *buildSuffixArray(char *txt, int n)
{
    // A structure to store suffixes and their indexes
    struct suffix suffixes[n];
 
    // Store suffixes and their indexes in an array of structures.
    // The structure is needed to sort the suffixes alphabatically
    // and maintain their old indexes while sorting
    for (int i = 0; i < n; i++)
    {
        suffixes[i].index = i;
        suffixes[i].suff = (txt+i);
    }
 
    // Sort the suffixes using the comparison function
    // defined above.
    sort(suffixes, suffixes+n, cmp);
 
    // Store indexes of all sorted suffixes in the suffix array
    int *suffixArr = new int[n];
    for (int i = 0; i < n; i++)
        suffixArr[i] = suffixes[i].index;
 
    // Return the suffix array
    return  suffixArr;
}
 
// A utility function to print an array of given size
void printArr(int arr[], int n)
{
    for(int i = 0; i < n; i++)
        cout << arr[i] << " ";
    cout << endl;
}
 
// Driver program to test above functions
int main()
{
    char txt[] = "banana";
    int n = strlen(txt);
    int *suffixArr = buildSuffixArray(txt,  n);
    cout << "Following is suffix array for " << txt << endl;
    printArr(suffixArr, n);
    return 0;
}
Run on IDE
Output:

Following is suffix array for banana
5 3 1 0 4 2
The time complexity of above method to build suffix array is O(n2Logn) if we consider a O(nLogn) algorithm used for sorting. The sorting step itself takes O(n2Logn) time as every comparison is a comparison of two strings and the comparison takes O(n) time.
There are many efficient algorithms to build suffix array. We will soon be covering them as separate posts.