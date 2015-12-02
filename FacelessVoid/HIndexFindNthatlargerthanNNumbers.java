/*Given一个unsorted array， find the greatest natural number N, such that, there exists at least N numbers in the array which are greater or equal to N
比如input是【5，5，5】的话，output是3

我先给的最简单的解法，sort array，然后从最大开始找起，就是O（nlogn），然后面试官问我有没有O(n)的解法。
我当时跟他说的是类似quick sort的方法，找一个pivot，然后看看需要把多少大于他的数移到右边。如果那个number正好等于pivot的值的话，循环终止。不然的话，如果pivot大于移到右边的个数的话，在pivot左边找，反之在右边找。
现在想一想这个方法应该是妥妥可以O(n)完成的（终止条件还需要再想想），因为每比较一次一半的数就不用考虑了，然并卵。。。他打断跟我说觉得这个解法太复杂，我们来想一想能不能把sort变成O（n）的。
他说假如我告诉你这个array所有integer的值都在【-2N，2N】之间呢？N=array_length。我说count sort，于是就O(n)了。然后他说好吧，你写。写了大概有三到五分钟。然后他说，那么现在我们去除这个假设怎么办？当时一看时间不到五分钟脑袋是懵的，没get到他整个在往哪里走。。。他就提示我，如果array里面数的值都比N大的话你的结果是不是总是N？是不是所有大于等于N的数不用区分了？额，等我反应过来他已经没耐心了。其实他意思就是count sort加点小小改动，因为所有小于0的数都不用跟0区分了，所有大于N的数也都不用跟N区分了*/


步骤如下：
1)建立一个新数组，长度为nums.length + 1
2)扫描nums，当前访问的数字为number
如果当前的数字小于等于零，则times(0)++
如果当前数字大于等于nums.length 则times（nums.length）++. From 1point 3acres bbs
否则times(number)++. Waral 鍗氬鏈夋洿澶氭枃绔�,
3)然后从times的尾部向前扫描，如果当前的index<=（times在index之后的总和加上times（index））那么就找到了这个数N
4）否则返回零


public int hIndex(int[] citations) {
    int len = citations.length;
    int[] count = new int[len + 1];

    for (int c: citations)
        if (c > len) 
            count[len]++;
        else 
            count[c]++;


    int total = 0;
    for (int i = len; i >= 0; i--) {
        total += count[i];
        if (total >= i)
            return i;
    }

    return 0;
}