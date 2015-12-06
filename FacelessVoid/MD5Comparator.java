一个文件根目录, 底下有很多文件夹和文件, 如何找到相同的文件, output 应该是一个double vector(double arraylist in java),  (需要注意的是文件名也许不相同, 所以我们要有一个函数比较两个文件,这个我默认为已知了). 
比如 home/A/B/a.file, home/C/b.file, home/A/B/D/c.file, 假设a.file和b.file相同, c.file不同.就输出((a.file, b.file), (c.file)).

题目弄了好一会才看懂, 还好后来还蛮顺利.
follow up就是如果有快捷方式之类的链接文件怎么办(会形成环路), 然后就问怎么compare两个文件是否相同, 我说用MD5, 然后同意,然后问整个程序哪儿most expensive, 我说就是compare函数, 需要读取整个文件,才能call hash function, 他接着问如何优化,我不知道了,就瞎扯了好多,比如根据size,type,date先分一遍, 再run MD5. 最后大概他听不下去了,就说如果用multi-threading呢? 我说那就切块,distributed compare using one thread per chunk. 这大概就是他想听到的. 我前面还在和他扯,连trie都扯上了.

