/*The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.*
/*/

/*又是比较难理解题意的一题...call multiple times，举个例子呀， 没例子咋理解。尝试了好几次才弄明白意思。给定read4，跟上一题一样，求可以call multiple times的read()。假如文件字符串是"abc"，我们调用read(1)，应该返回"a"，再调用read(2)，应该返回bc"。这里要注意的是，之前我们再第一次调用的时候，read4就已经读取了"abc"，所以这道题其实就是要在上一题的基础上处理这种情况。解决方法不难，我们可以用一个queue来存储多读取的部分，然后在下次调用read的时候，根据情况判断，先读queue里面上次读剩下的数据，再进行下面的读取。也可以把read4的buffer放在global，然后用一个int型的offset来记录上次读了read4的多少。由于这个global buffer不会超过4，所以space complexity算是O(1)的。 LeetCode有些题真的很难读懂题意，我笨怪我咯，你聪明你先上。

Time Complexity - O(n)， Space Complexity - O(1)。*/

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); 


/*I used buffer pointer (buffPtr) and buffer Counter (buffCnt) to store the data received in previous calls. In the while loop, if buffPtr reaches current buffCnt, it will be set as zero to be ready to read new data.*/

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
        private int buffPtr = 0;
        private int buffCnt = 0;
        private char[] buff = new char[4];
        public int read(char[] buf, int n) {
            int ptr = 0;
            while (ptr < n) {
                if (buffPtr == 0) {
                    buffCnt = read4(buff);
                }
                if (buffCnt == 0) break;
                while (ptr < n && buffPtr < buffCnt) {
                    buf[ptr++] = buff[buffPtr++];
                }
                if (buffPtr >= buffCnt) buffPtr = 0;
            }
            return ptr;
        }
}


// Discuss
/*1.copying data from the buffer;
2.copying data from the underlying stream;
3.maintain bufferPointer/bufferBytes in the both sections. No if(s).*/

public class Solution extends Reader4 {
    private char[] buffer = new char[4];
    private int bufferBytes = 0;
    private int bufferPointer = 0;

    public int read(char[] buf, int n) {
        int copy = Math.min(n, bufferBytes);
        System.arraycopy(buffer, bufferPointer, buf, 0, copy);
        bufferBytes -= copy;
        bufferPointer += copy;

        int total = copy;
        int readBytes = 4;
        while (total < n && readBytes == 4) {
            readBytes = read4(buffer);
            copy = Math.min(n-total, readBytes);
            bufferBytes = readBytes - copy;
            bufferPointer = copy;
            System.arraycopy(buffer, 0, buf, total, copy);
            total += copy;
        }

        return total;
    }
}







public class Solution extends Reader4 {

    private int offSet = 0;
    private int remaining = 0;
    private boolean isEndOfFile = false;
    private char[] buffer = new char[4];

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int readBytes = 0;
        while (readBytes < n && (remaining != 0 || !isEndOfFile)) {
            int readSize = 0;
            if (remaining != 0) {
                readSize = remaining;
            } else {
                offSet = 0;
                readSize = read4(buffer);
                if (readSize != 4) {
                    isEndOfFile = true;
                }
            }
            int length = Math.min(n - readBytes, readSize);
            for (int i= offSet; i<offSet + length; i++) {
                buf[readBytes++] = buffer[i];
            }
            remaining = readSize - length;
            if (remaining != 0) {
                offSet += length;
            }
        }
        return readBytes;
    }
}