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


