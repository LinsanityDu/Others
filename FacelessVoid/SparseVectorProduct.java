// This Method
public int[][] multiply(int[][] A, int[][] B) {
    int m = A.length, n = A[0].length, nB = B[0].length;
    int[][] result = new int[m][nB];

    List[] indexA = new List[m];
    for(int i = 0; i < m; i++) {
        List<Integer> numsA = new ArrayList<>();
        for(int j = 0; j < n; j++) {
            if(A[i][j] != 0){
                numsA.add(j); 
                numsA.add(A[i][j]);
            }
        }
        indexA[i] = numsA;
    }

    for(int i = 0; i < m; i++) {
        List<Integer> numsA = indexA[i];
        for(int p = 0; p < numsA.size() - 1; p += 2) {
            int colA = numsA.get(p);
            int valA = numsA.get(p + 1);
            for(int j = 0; j < nB; j ++) {
                int valB = B[colA][j];
                result[i][j] += valA * valB;
            }
        }
    }

    return result;   
}


/*小哥说既然你有machine learning经验，那来你应该知道很多特征vector/matrix都是稀疏的，这题我见过啊！！！感谢地里！！先问我sparse vector 怎么表示比较好，然后让写代码求两个sparse vector dot product。再follow up， 如果一个vector 比另一个大很多怎么办，答对于小的vector里每一个（index， value），在大的里binary search。然后问了复杂度。
*/

一开始说连个hashmap，小哥说hashmap会浪费掉多余空间，我说那如果一个特别大的话就扫小一点的那个array，然后在特别大的array中用binary search，他说写代码。写完代码接着说，那如果差不多大，我说那就两个指针按照merge sort那么扫。然后我觉得基本都行了，他最后说那有没有O(Math.min(m, n))的方法。我鼓捣半天，最后说了个那就输入直接是一个tuple，第一个elem是位置（这个位置在两个array中必须都不是0），然后扫一遍就行了。其实我感觉他的意思是再用HashMap。不过他忘了之前和我说太浪费空间了。。。

/*于是第一个优化的想法是将向量变为这样的模式
向量A:{<x1',loc1'>,<x2',loc2'>...<xn',locn'>}
向量B:{<y1',loc1'>,<y2',loc2'>...<yn',locn'>},这里locx表示元素的位置信
息。
每个向量都不含零元素，或者接近于零的浮点数。显然向量数量远小于n，且向
量A，B的长度取决于各自的非零元，不妨设向量A长度为m，向量B长度为n.

那么计算A*B，可以采用在向量A中循环，在向量B中二分的方法，例如找到向量A
的首原素，<x1',loc1'>,将其位置loc1'在向量B中折半查找，直到找到，或者失
败。
这样计算代价为mlogn + min(m,n),前部为查找代价，后部为加法代价，加法代
价必然比min(m,n)还要小,最大情况下为min(m,n)-1。*/

是要保存两个arraylist 一个是存index，一个存value吗？为什么这个比较省空间呢？用hashmap 存index，value，比两个arraylist的性能差在哪里？
第一题我是用的arraylist来存的，比较省空间，然后能保持index有序性，hashtable存的话follow up就不能二分法了吧。
因为你只保存了非0元素呀，vector是稀疏的，这样就只需要保存很少。 hashmap存的话算dot product是没问题的，但Follow up里(index, value)表示后的向量一个很长，一个很短的话，就不能用二分查找了。我觉得是这样

int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;  
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
        
http://www.cs.cmu.edu/~scandal/cacm/node9.html

如果都是sparse vectors,那思路就是把每个vector都表示成(index, non-zero value) pairs:
A =[0,2,0,2,0,0,3,0,0,4] ==> A={(1,2), (3,2), (6,3), (9,4)}
B=[0,0,0,0,5,0,2,0,0,8]  ==> B={(4,5), (6,2), (9,8)}
/*类似于求intersection的方法
Algorithm Intersection(arr1[], arr2[]):
For Intersection of two arrays, print the element only if the element is present in both arrays.
1) Use two index variables i and j, initial values i = 0, j = 0
2) If arr1[i] is smaller than arr2[j] then increment i.
3) If arr1[i] is greater than arr2[j] then increment j.
4) If both are same then print any of them and increment both i and j.*/
/*
// Intersection
    public static void intersection(int[] array1, int[] array2) {
        int index1 = 0;
        int index2 = 0;
        while (index1 < array1.length && index2 < array2.length) {
            if (array1[index1] < array2[index2]) {
                index1++;
            } else if (array1[index1] > array2[index2]) {
                index2++;
            } else {
                System.out.println(array1[index1]);
                index1++;
                index2++;
            }
        }
        return;
    }*/


for each index i,  a = val of pair (i, v_in_A), b= val of pair (i, v_in_B)
dot_product(A,B) = sum_of ( a * b )  

A dot product B = 3*2 + 4*8 = 38. 


SparseVectorDotProduct
SparseMatrixProduct

// DotProduct
A: [0,0,0,5,0,0,2,5]
B: [1,0,0,2,0,4,0,0]
dot product is 10;
我开始想用Map， A: 3->5, 6->2, 7->5, 最后是用 Pair, (3,5),(6,2),(7,5) 好处是
index是sorted,所以两个List一起扫就出来出来了


稀疏向量的存储方法是存储一个列表，里面存非零元素的位置和值，也就是<index, value>，按index排序.

两个稀疏向量做点积的时候需要对index做归并，碰到有index相等的时候就把对应的两个value相乘，然后加到结果当中。归并完了就可以啦~

另外一个做法是用HashMap<index, value>，这样的话求点积就是遍历其中一个HashMap，在另外一个里面查找对应的index是否存在，这样也是可以的。



// Sparse

/*Sparse matrices, are matrices in which most elements are zero. To save space and running time it is critical to only store the nonzero elements. A standard representation of sparse matrices is to use an array with one element per row each of which contains a linked-list of the nonzero values in that row along with their column number. so For example MAT will be stored as ,

MAT = 
5  1  0  0
1  5  1  0
0  1  5  1
0  0  1  5
is stored in this way as
MAT = [[(0, 5), (1, 1)],
     [(0, 1), (1, 5), (2, 1)],
     [(1, 1), (2, 5), (3, 1)],
     [(2, 1), (3, 5)]]*/


A common operation on sparse matrices is to multiply them by a dense vector. In such an operation, the result is the dot-product of each sparse row of the matrix with the dense vector. The NESL code for taking the dot-product of a sparse row with a dense vector x is:

  sum({v * x[i] : (i,v) in row});
This code takes each index-value pair (i,v) in the sparse row, multiplies v with the i tex2html_wrap_inline1798 value of x, and sums the results. The work and depth is easily calculated using the performance rules. If n is the number of non-zeros in the row, then the depth of the computation is the depth of the sum, which is O(log n), and the work is the sum of the work across the elements, which is O(n).

function sparse_matrix_mult(A,x) =
  {sum({v * x[i] : (i,v) in row})
   : row in A};  

% An example matrix and vector %
A = [[(0, 2.0), (1, -1.0)],
     [(0, -1.0), (1, 2.0), (2, -1.0)],
     [(1, -1.0), (2, 2.0), (3, -1.0)],
     [(2, -1.0), (3, 2.0)]];
x = [1.0, 0.0, -1.0, 0.0];

% Try it out %
sparse_matrix_mult(A,x);


// Multiplication Naive
void multiply(int A[][N], int B[][N], int C[][N])
{
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            C[i][j] = 0;
            for (int k = 0; k < N; k++)
            {
                C[i][j] += A[i][k]*B[k][j];
            }
        }
    }
}


