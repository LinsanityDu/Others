/*小哥说既然你有machine learning经验，那来你应该知道很多特征vector/matrix都是稀疏的，这题我见过啊！！！感谢地里！！先问我sparse vector 怎么表示比较好，然后让写代码求两个sparse vector dot product。再follow up， 如果一个vector 比另一个大很多怎么办，答对于小的vector里每一个（index， value），在大的里binary search。然后问了复杂度。
*/

是要保存两个arraylist 一个是存index，一个存value吗？为什么这个比较省空间呢？用hashmap 存index，value，比两个arraylist的性能差在哪里？
第一题我是用的arraylist来存的，比较省空间，然后能保持index有序性，hashtable存的话follow up就不能二分法了吧。
因为你只保存了非0元素呀，vector是稀疏的，这样就只需要保存很少。 hashmap存的话算dot product是没问题的，但Follow up里(index, value)表示后的向量一个很长，一个很短的话，就不能用二分查找了。我觉得是这样


http://www.cs.cmu.edu/~scandal/cacm/node9.html

如果都是sparse vectors,那思路就是把每个vector都表示成(index, non-zero value) pairs:
A =[0,2,0,2,0,0,3,0,0,4] ==> A={(1,2), (3,2), (6,3), (9,4)}
B=[0,0,0,0,5,0,2,0,0,8]  ==> B={(4,5), (6,2), (9,8)}

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


