就是说你要实现一个Iterator，每次调用next都返回nested array中的下一个，所谓下一个就是比如，
nested array 是 [[1,2], 3,[4, [5]]]
Call Iterator 的next 返回的序列应该是 1,2,3,4,5
思路就是用一个stack，首先放入最外层的Iterator，对于每个next，check 栈顶Iterator的next，如果是Iterator，入栈然后继续循环check 栈顶；如果是int，直接返回；如果next不存在，删除栈顶，继续循环check栈顶
