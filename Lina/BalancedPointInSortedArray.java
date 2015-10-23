 find a balance point in an array, array不是sorted的，里面可能有负数。
解法也是从两边往中间搞，用两个数组left和right存原数组从左向右和从右向左的元素和。然后按index来比较left和right两个数组里的元素，有相等的就是balance point.

