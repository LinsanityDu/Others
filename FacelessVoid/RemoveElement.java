int removeElement(int A[], int n, int elem) {
    int start = 0;
    int end = n-1;
    while(start <= end) {
        if(A[start] != elem) {
            start ++;
            continue;
        }
        if(A[end] == elem) {
            end --;
            continue;
        }
        A[start] = A[end];
        start ++;
        end --;
    }
    return end+1;
}