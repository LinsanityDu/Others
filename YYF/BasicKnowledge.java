1. DNS

DNS is an abbreviation of Domain Name System. It's the naming system for computer network services, and it converts domain names to actual IP addresses, just like a translator.

To understand more about its working principle, let's see the detailed DNS resolution process as follows.

1. After typing the domain name www.qq.com in the browser, the operating system will check if there are any mapping relationships in the hosts' files for this domain name. If so, then the domain name resolution is complete.

2. If no mapping relationships exist in the hosts' files, the operating system will check if any cache exists in the DNS. If so, then the domain name resolution is complete.

3. If no mapping relationships exist in both the host and DNS cache, the operating system finds the first DNS resolution server in your TCP/IP settings, which is likely your local DNS server. When the local DNS server receives the query, if the domain name that you want to query is contained within the local configuration of its regional resources, it returns the results to the client. This DNS resolution is authoritative.

4. If the local DNS server doesn't contain the domain name but a mapping relationship exists in the cache, the local DNS server gives back this result to the client. This DNS resolution is not authoritative.

5. If the local DNS server cannot resolve this domain name either by configuration of regional resources or cache, it will proceed to the next step, which depends on the local DNS server's settings. -If the local DNS server doesn't enable forwarding, it routes the request to the root DNS server, then returns the IP address of a top level DNS server which may know the domain name, .com in this case. If the first top level DNS server doesn't recognize the domain name, it again reroutes the request to the next top level DNS server until it reaches one that recognizes the domain name. Then the top level DNS server asks this next level DNS server for the IP address corresponding to www.qq.com. -If the local DNS server has forwarding enabled, it sends the request to an upper level DNS server. If the upper level DNS server also doesn't recognize the domain name, then the request keeps getting rerouted to higher levels until it finally reaches a DNS server which recognizes the domain name.

Whether or not the local DNS server enables forwarding, the IP address of the domain name always returns to the local DNS server, and the local DNS server sends it back to the client.



DNS Loadbalancer?

How DNS Load Balancing Works

DNS load balancing relies on the fact that most clients use the first IP address they receive for a domain. In most Linux distributions, DNS by default sends the list of IP addresses in a different order each time it responds to a new client, using the round-robin method. As a result, different clients direct their requests to different servers, effectively distributing the load across the server group.


2. 输入网址之后发生了什么

Every time you open your browsers, type some URLs and press enter, you will see beautiful web pages appear on your screen. But do you know what is happening behind these simple actions?

Normally, your browser is a client. After you type a URL, it takes the host part of the URL and sends it to a DNS server in order to get the IP address of the host. Then it connects to the IP address and asks to setup a TCP connection. The browser sends HTTP requests through the connection. The server handles them and replies with HTTP responses containing the content that make up the web page. Finally, the browser renders the body of the web page and disconnects from the server.

	查找浏览器缓存
    DNS解析、查找该域名对应的IP地址、重定向（301）、发出第二个GET请求
    进行HTTP协议会话
    客户端发送报头(请求报头)
    服务器回馈报头(响应报头)
    html文档开始下载
    文档树建立，根据标记请求所需指定MIME类型的文件
    文件显示
    [
    浏览器这边做的工作大致分为以下几步：

    加载：根据请求的URL进行域名解析，向服务器发起请求，接收文件（HTML、JS、CSS、图象等）。

    解析：对加载到的资源（HTML、JS、CSS等）进行语法解析，建议相应的内部数据结构（比如HTML的DOM树，JS的（对象）属性表，CSS的样式规则等等）
    }

3. HTTP

A web server, also known as an HTTP server, uses the HTTP protocol to communicate with clients. All web browsers can be considered clients.

We can divide the webs working principles into the following steps:

Client uses TCP/IP protocol to connect to server.
Client sends HTTP request packages to server.
Server returns HTTP response packages to client. If the requested resources include dynamic scripts, server calls script engine first.
Client disconnects from server, starts rendering HTML.
This is a simple work flow of HTTP affairs -notice that the server closes its connections after it sends data to the clients, then waits for the next request.

4. Get/Post 区别
两种 HTTP 请求方法：GET 和 POST
在客户机和服务器之间进行请求-响应时，两种最常被用到的方法是：GET 和 POST。
GET - 从指定的资源请求数据。
POST - 向指定的资源提交要被处理的数据

We can see that GET does not have a request body, unlike POST, which does.

There are many methods you can use to communicate with servers in HTTP; GET, POST, PUT and DELETE are the 4 basic methods that we typically use. A URL represents a resource on a network, so these 4 methods define the query, change, add and delete operations that can act on these resources. GET and POST are very commonly used in HTTP. 

GET can append query parameters to the URL, using ? to separate the URL and parameters and & between the arguments, like EditPosts.aspx?name=test1&id=123456. POST puts data in the request body because the URL implements a length limitation via the browser. Thus, POST can submit much more data than GET. Also, when we submit user names and passwords, we don't want this kind of information to appear in the URL, so we use POST to keep them invisible.

GET 方法
请注意，查询字符串（名称/值对）是在 GET 请求的 URL 中发送的：
/test/demo_form.asp?name1=value1&name2=value2
有关 GET 请求的其他一些注释：
GET 请求可被缓存
GET 请求保留在浏览器历史记录中
GET 请求可被收藏为书签
GET 请求不应在处理敏感数据时使用
GET 请求有长度限制
GET 请求只应当用于取回数据
POST 方法
请注意，查询字符串（名称/值对）是在 POST 请求的 HTTP 消息主体中发送的：
POST /test/demo_form.asp HTTP/1.1
Host: w3schools.com
name1=value1&name2=value2
有关 POST 请求的其他一些注释：
POST 请求不会被缓存
POST 请求不会保留在浏览器历史记录中
POST 不能被收藏为书签
POST 请求对数据长度没有要求

GET idempotent: 所谓幂等是指多个请求返回相同的结果


5. TCP/UDP
Q. Can you explain the difference between UDP and TCP internet protocol (IP) traffic and its usage with an example?
A. Transmission Control Protocol (TCP) and User Datagram Protocol (UDP)is a transportation protocol that is one of the core protocols of the Internet protocol suite. Both TCP and UDP work at transport layer TCP/IP model and both have very different usage.

Difference between TCP and UDP
TCP	UDP
Reliability: TCP is connection-oriented protocol. When a file or message send it will get delivered unless connections fails. If connection lost, the server will request the lost part. There is no corruption while transferring a message.	

Reliability: UDP is connectionless protocol. When you a send a data or message, you don't know if it'll get there, it could get lost on the way. There may be corruption while transferring a message.

Ordered: If you send two messages along a connection, one after the other, you know the first message will get there first. You don't have to worry about data arriving in the wrong order.	

Ordered: If you send two messages out, you don't know what order they'll arrive in i.e. no ordered

Heavyweight: - when the low level parts of the TCP "stream" arrive in the wrong order, resend requests have to be sent, and all the out of sequence parts have to be put back together, so requires a bit of work to piece together.	
Lightweight: No ordering of messages, no tracking connections, etc. It's just fire and forget! This means it's a lot quicker, and the network card / OS have to do very little work to translate the data back from the packets.

Streaming: Data is read as a "stream," with nothing distinguishing where one packet ends and another begins. There may be multiple packets per read call.

Datagrams: Packets are sent individually and are guaranteed to be whole if they arrive. One packet per one read call.

Examples: World Wide Web (Apache TCP port 80), e-mail (SMTP TCP port 25 Postfix MTA), File Transfer Protocol (FTP port 21) and Secure Shell (OpenSSH port 22) etc.
Examples: Domain Name System (DNS UDP port 53), streaming media applications such as IPTV or movies, Voice over IP (VoIP), Trivial File Transfer Protocol (TFTP) and online multiplayer games etc

6. 网站反应慢
1. Reverse Proxy:
在计算机网络中，反向代理是代理服务器的一种。它根据客户端的请求，从后端的服务器上获取资源，然后再将这些资源返回给客户端。[1]与前向代理不同，前向代理作为一个媒介将互联网上获取的资源返回给相关联的客户端，而反向代理是在服务器端作为代理使用，而不是客户端。
2. reduce the size of webpage
1. Rewrite JS code
2. compress images
3. Lazy Initilization

3. More cacheable pages
Change	dynamic	webpage	into	static	webpage

网站反应慢
我有个网页，用户反映每次打开加载都很慢，说3个原因为什么？
（我也不是那么清楚。。。就想起什么说什么，说了bandwidth，说了database优化不够，数据量大很多join，还说了网页做的太次，全是image，可以用caching解决）

网站变慢，可能有什么因素？ load balancing有问题，图片太多，数据库操作
太频繁，服务器自身的硬件能力有问题etc. 

说yelp有个页面访问量大，比较卡，怎么解决。如果不是数据库的问题，怎么解决， 加了cache还慢怎么解决
如果美国用户快法国用户慢怎么解决，如果在欧洲安了服务器还慢怎么解决。。。安了服务器怎么判断是美国还是法国的用户请求 


7. Cache
A web cache (or HTTP cache) is an information technology for the temporary storage (caching) of web documents, such as HTML pages and images, to reduce bandwidth usage, server load, and perceived lag. A web cache system stores copies of documents passing through it; subsequent requests may be satisfied from the cache if certain conditions are met.[1] A web cache system can refer either to an appliance, or to a computer program.

Cache Control
HTTP defines three basic mechanisms for controlling caches: freshness, validation, and invalidation.[5]

Freshness 
allows a response to be used without re-checking it on the origin server, and can be controlled by both the server and the client. For example, the Expires response header gives a date when the document becomes stale, and the Cache-Control: max-age directive tells the cache how many seconds the response is fresh for.

Validation 
can be used to check whether a cached response is still good after it becomes stale. For example, if the response has a Last-Modified header, a cache can make a conditional request using the If-Modified-Since header to see if it has changed. The ETag (entity tag) mechanism also allows for both strong and weak validation.

Invalidation 
is usually a side effect of another request that passes through the cache. For example, if a URL associated with a cached response subsequently gets a POST, PUT or DELETE request, the cached response will be invalidated.

8. XSS
Cross-site scripting (XSS) is a type of computer security vulnerability typically found in web applications. XSS enables attackers to inject client-side script into web pages viewed by other users. A cross-site scripting vulnerability may be used by attackers to bypass access controls such as the same-origin policy. Cross-site scripting carried out on websites accounted for roughly 84% of all security vulnerabilities documented by Symantec as of 2007.[1] Their effect may range from a petty nuisance to a significant security risk, depending on the sensitivity of the data handled by the vulnerable site and the nature of any security mitigation implemented by the site's owner.

9. Load balancer

In computing, load balancing distributes workloads across multiple computing resources, such as computers, a computer cluster, network links, central processing units or disk drives. Load balancing aims to optimize resource use, maximize throughput, minimize response time, and avoid overload of any single resource. Using multiple components with load balancing instead of a single component may increase reliability and availability through redundancy. Load balancing usually involves dedicated software or hardware, such as a multilayer switch or a Domain Name System server process.

Load balancing differs from channel bonding in that load balancing divides traffic between network interfaces on a network socket (OSI model layer 4) basis, while channel bonding implies a division of traffic between physical interfaces at a lower level, either per packet (OSI model Layer 3) or on a data link (OSI model Layer 2) basis with a protocol like shortest path bridging.

10. SQL Injection
SQL injection is a code injection technique, used to attack data-driven applications, in which malicious SQL statements are inserted into an entry field for execution (e.g. to dump the database contents to the attacker).[1] SQL injection must exploit a security vulnerability in an application's software, for example, when user input is either incorrectly filtered for string literal escape characters embedded in SQL statements or user input is not strongly typed and unexpectedly executed. SQL injection is mostly known as an attack vector for websites but can be used to attack any type of SQL database.


11. Database Index
数据库index什么原理，B树和hash table优缺点 ( 这里出糗了，他最后问hash table的缺点，... range... blabla没听懂，他直接pass了)，我说了b树查询速度相对慢是logN，没说出其他有营养的东西。嘴贱意淫说了hash table修改的时间复杂度高，貌似说错了 囧。 望大牛指点
B tree 的search time 是logN, 但是好處是range search的時候快。Hash table的edit, search time都是O(1), 缺點是range search的時候非常慢。

12. Database 查询速度慢

数据库就是多用 index和合并表单. 1point3acres.com/bbs
server 方面基本就是看是 某个location request 太多还是 针对某一部分的 request 太多然后调整 cache 和 server.

如果一个Database的查询速度很慢，如何改进。如果denormalization之后还是不给力，怎么办？
SQL里面如何优化query的速度，query里的aggregator函数的用法
数据库查询慢为啥，怎么解决。Index，只能想到这个。

Queries or updates that take longer than expected to execute can be caused by a variety of reasons. Slow-running queries can be caused by performance problems related to your network or the computer where SQL Server is running. Slow-running queries can also be caused by problems with your physical database design.
There are a number of common reasons for slow-running queries and updates:
Slow network communication.

Inadequate memory in the server computer, or not enough memory available for SQL Server.
Lack of useful statistics
Lack of useful indexes.
Lack of useful indexed views.
Lack of useful data striping.
Lack of useful partitioning.


1) Bind Variables: When a SQL query is sent to the database engine for processing and sending the result, it is compiled by the database compiler to get the tokens of the query. This involves parsing, optimizing and identifying the query. After a number of steps, the SQL query is passed to the database engine for processing. In a small application with a user base of less than 500, it is usually the same query which is executed more often than others. The use of bind variables helps in storing the compiled query once and executing it with different data at different times. For using bind variables, one needs to use PreparedStatement objects in Java.

2) Query is not well formed: Usually the same SQL query can be written in multiple ways. There are ways by which a query can be optimized to give the best performance. The corresponding SQL construct should be chosen depending upon requirement. I have scenarios where people have used WHERE clause instead of GROUP BY and are complaining of poor response times. Similarly Sub queries and Joins complement each other.

3) Database structure is not well defined/normalized: This is probably known to everybody that the database tables should be properly normalized as this is part of every DBMS course at graduation level. If the tables are not properly designed and normalized, anomalies set in.

4) Proper caching is not in place: Many applications make use of temporary caches on the application server to store the reference data or frequently accessed data as memory is less of an issue than the time with new generation servers.

5) Number of rows in the table too large: If the table itself has too much of data then the queries will take time to execute. Partitioning a table into multiple tables is recommended in these situations. For example: If a table has employee records of 1000000 employees then it could be split into 5 small tables each having 200000 rows. The advantage is we know beforehand in which smaller table to look for a particular employee code as the division of large table can be done on the employee id column.

6) Connections are not being pooled: If connections are not pooled then the each time a new connection is requested for a request to database. Maintaining a connection pool is much better than creating and destroying the connection for executing every SQL query. Of course, there are frameworks like Hibernate which take care of creating the connection pools and also allow the customization of these pools

7) Connections not closed/returned to pool in case of exceptions: When an exception occurs while performing database operations, it ought to be caught. Usually catching the exception is not the issue because SQLException is a checked exception but closing the connection is something that most of the times is left out. If the connection is not released, the same connection cannot be used for any other purpose till the connection is timed out.

8) Stored procedures for complex computations on database: Stored procedures are a good way to perform database intensive operations. This is because they are already compiled and there is less network trips for getting the same results as compared to SQL queries.

Denormalization:
In computing, denormalization is the process of attempting to optimize the read performance of a database by adding redundant data or by grouping data.[1][2] In some cases, denormalization is a means of addressing performance or scalability in relational database software.

A normalized design will often store different but related pieces of information in separate logical tables (called relations). If these relations are stored physically as separate disk files, completing a database query that draws information from several relations (a join operation) can be slow. If many relations are joined, it may be prohibitively slow. There are two strategies for dealing with this. The preferred method is to keep the logical design normalized, but allow the database management system (DBMS) to store additional redundant information on disk to optimise query response. In this case it is the DBMS software's responsibility to ensure that any redundant copies are kept consistent. This method is often implemented in SQL as indexed views (Microsoft SQL Server) or materialised views (Oracle, PostgreSQL). A view represents information in a format convenient for querying, and the index ensures that queries against the view are optimised.


13. Mongo DB
著作权归作者所有。
商业转载请联系作者获得授权，非商业转载请注明出处。
作者：周思远
链接：http://www.zhihu.com/question/20059632/answer/14981332
来源：知乎

最后这个对比说明了，MongoDB希望在大多数应用中做为关系型数据库的替代品，提供这些优点：http://www.mongodb.org/display/DOCS/IntroductionDocument-
oriented，用文档来组织数据，不需要严格的结构。
High performance，高性能
High availability，高可用，比如复制 (Replica set)
Easy scalability，易扩展，比如Sharding
Rich query language，富查询


14. SQL VS NOSQL
No-SQL
• K/v pair
• Append
• Tolerant
• Performance
• Sequential record
SQL
Relationship
Edit
Correct
Transaction 
Combinational state

SQL 数据库:

在表中存储相关联的数据
在使用之前需要定义表的一个模式
鼓励标准化减少数据冗余
支持从多个表中检索相关数据表连接在一个单一的命令
实现数据完整性规则
提供事务使两个或两个以上的成功或失败的数据更改作为一个原子单元
可以扩展(有一些努力)
使用一个强声明性语言查询
提供足够的支持,专业技能和工具。

NoSQL 数据库
将相关联的数据存储在类似 JSON 格式，名称-值
可以保存没有指定格式的数据
通常必须规范化，所以一个项目的信息包含在一个文档里
应该不需要连接（假设使用规范化的文档）
允许任何数据被保存在任何时候任何地方，不需要验证
保证更新一个文档 – 但不是多个文档
提供出色的性能和可伸缩性
使用 JSON 数据对象查询
是一个新的、令人兴奋的技术。




14. Sharding
Sharding 是把数据库Scale Out到多个物理节点上的一种有效的方式。Shard这个词的意思是“碎片”。如果将一个数据库当作一块大玻璃，将这块玻璃打碎，那么每一小块都称为数据 库的碎片（DatabaseShard）。将整个数据库打碎的过程就叫做sharding，可以翻译为分片。
形式上，Sharding可以简单定义为将大数据库分布到多个物理节点上的一个分区方案。每一个分区包含数据库的某一部 分，称为一个shard，分区方式可以是任意的，并不局限于传统的水平分区和垂直分区。一个shard可以包含多个表的内容甚至可以包含多个数据库实例中 的内容。 每个shard被放置在一个数据库服务器上。一个数据库服务器可以处理一个或多个shard的数据。系统中需要有服务器进行查询路由转发，负责将查询转发 到包含该查询所访问数据的shard或shards节点上去执行。


15. NoSQL 比较
NoSQL
NoSQL现在被理解为 Not Only SQL 的缩写，是对非关系型的数据库管理系统的统称（正因为此，人们通常理解 NoSQL 是 anti-RDBMS）。

NoSQL 与 RDBMS 存在许多不同点，
- 最重要的是NoSQL不使用SQL作为查询语言。
- NoSQL 不需要固定的表模式(table schema)，也经常会避免使用SQL的JOIN操作，一般有可水平扩展的特征。
- NoSQL产品会放宽一个或多个 ACID 属性（CAP定理）

CAP 理论
CAP理论是数据系统设计的基本理论，目前几乎所有的数据系统的设计都遵循了这个理论。CAP理论指出，分布式系统只能满足以下三项中的两项而不可能满足全部三项，
一致性（Consistency)（所有节点在同一时间具有相同的数据）

可用性（Availability）（保证每个请求不管成功或者失败都有响应）

分区容忍性（Partition tolerance）（系统中任意信息的丢失或失败不会影响系统的继续运作）

一致性有两种类型：
- strong consistency – ACID(Atomicity Consistency Isolation Durability)：对于关系型数据库，要求更新过的数据能被后续所有的访问都看到，这是强一致性。
- weak consistency – BASE(Basically Available Soft-state Eventual consistency )
-- Basically Available - system seems to work all the time (基本可用)
-- Soft State - it doesn't have to be consistent all the time （不要求所有时间都一致）
-- Eventually Consistent - becomes consistent at some later time （最终一致性）

对于分布式数据系统(scale out)，分区容忍性是基本要求，否则就失去了价值。因此只能在一致性和可用性上做取舍，如何处理这种取舍正是目前NoSQL数据库的核心焦点。几乎所有的情况都是牺牲一致性而换取高可用性。当然，牺牲一致性，只是不再要求关系数据库中的强一致性，而是只要系统能达到最终一致性即可。考虑到客户体验，这个最终一致的时间窗口，要尽可能的对用户透明，也就是需要保障“用户感知到的一致性”。通常是通过数据的多份异步复制来实现系统的高可用和数据的最终一致性的。


1. Key / Value Based NoSQL Database Management Systems

Key / Value data stores are highly performant, easy to work with and they usually scale well.
Popular Key / Value Based Databases
Some popular key / value based data stores are:
Redis:

In-memory K/V store with optional persistence.
Riak:

Highly distributed, replicated K/V store.
Memcached / MemcacheDB:

Distributed memory based K/V store.
When To Use
Some popular use cases for key / value based data stores are:
Caching:

Quickly storing data for - sometimes frequent - future use.
Queue-ing:

Some K/V stores (e.g. Redis) supports lists, sets, queues and more.
Distributing information / tasks:

They can be used to implement Pub/Sub.
Keeping live information:

Applications which need to keep a state cane use K/V stores easily.
Column Based NoSQL Database Management Systems
Column based data stores are extremely powerful and they can be reliably used to keep important data of very large sizes. Despite not being "flexible" in terms of what constitutes as data, they are highly functional and performant.
Popular Column Based Databases
Some popular column based data stores are:
Cassandra:

2. Column based data store based on BigTable and DynamoDB.
HBase:

Data store for Apache Hadoop based on ideas from BigTable.
When To Use
Some popular use cases for column based data stores are:
Keeping unstructured, non-volatile information:

If a large collection of attributes and values needs to be kept for long periods of time, column-based data stores come in extremely handy.
Scaling:

Column based data stores are highly scalable by nature. They can handle an awful amount of information.
Document Based NoSQL Database Management Systems
Document based data stores are excellent for keeping a lot of unrelated complex information that is highly variable in terms of structure from one another.
Popular Document Based Databases
Some popular document based data stores are:
Couchbase:

3. JSON-based, Memcached-compatible document-based data store.
CouchDB:

A ground-breaking document-based data store.
MongoDB:

An extremely popular and highly-functional database.
When To Use
Some popular use cases for document based data stores are:
Nested information:

Document-based data stores allow you to work with deeply nested, complex data structures.
JavaScript friendly:

One of the most critical functionalities of document-based data stores are the way they interface with applications: Using JS friendly JSON.

4. Graph Based NoSQL Database Management Systems
Graph based data stores offer a very unique functionality that is unmatched with any other DBMSs.
Popular Graph Based Databases
Some popular graph based data stores are:
OrientDB:

A very fast graph and document based hybrid NoSQL data store written in Java that comes with different operational modes.
Neo4J:

A schema-free, extremely popular and powerful Java graph based data store.

1. Key / Value Based
We will begin our NoSQL modeling journey with key / value based database management simply because they can be considered the most basic and backbone implementation of NoSQL.
These type of databases work by matching keys with values, similar to a dictionary. There is no structure nor relation. After connecting to the database server (e.g. Redis), an application can state a key (e.g.the_answer_to_life) and provide a matching value (e.g. 42) which can later be retrieved the same way by supplying the key.
Key / value DBMSs are usually used for quickly storing basic information, and sometimes not-so-basic ones after performing, for example, a CPU and memory intensive computation. They are extremely performant, efficient and usually easily scalable.
Note: When it comes to computers, a dictionary usually refers to a special sort of data object. They constitutes of arrays of collections with individual keys matching values.

2. Column Based
Column based NoSQL database management systems work by advancing the simple nature of key / value based ones.
Despite their complicated-to-understand image on the internet, these databases work very simply by creating collections of one or more key / value pairs that match a record.
Unlike the traditional defines schemas of relational databases, column-based NoSQL solutions do not require a pre-structured table to work with the data. Each record comes with one or more columns containing the information and each column of each record can be different.
Basically, column-based NoSQL databases are two dimensional arrays whereby each key (i.e. row / record) has one or more key / value pairs attached to it and these management systems allow very large and un-structured data to be kept and used (e.g. a record with tons of information).
These databases are commonly used when simple key / value pairs are not enough, and storing very large numbers of records with very large numbers of information is a must. DBMS implementing column-based, schema-less models can scale extremely well.

3. Document Based
Document based NoSQL database management systems can be considered the latest craze that managed to take a lot of people by storm. These DBMS work in a similar fashion to column-based ones; however, they allow much deeper nesting and complex structures to be achieved (e.g. a document, within a document, within a document).
Documents overcome the constraints of one or two level of key / value nesting of columnar databases. Basically, any complex and arbitrary structure can form a document, which can be stored using these management systems.
Despite their powerful nature, and the ability to query records by individual keys, document based management systems have their own issues and downfalls compared to others. For example, retrieving a value of a record means getting the whole lot of it and same goes for updates, all of which affect the performance.

4. Graph Based
Finally, the very interesting flavour of NoSQL database management systems is the graph based ones.
The graph based DBMS models represent the data in a completely different way than the previous three models. They use tree-like structures (i.e. graphs) with nodes and edges connecting each other through relations.
Similarly to mathematics, certain operations are much simpler to perform using these type of models thanks to their nature of linking and grouping related pieces of information (e.g. connected people).
These databases are commonly used by applications whereby clear boundaries for connections are necessary to establish. For example, when you register to a social network of any sort, your friends' connection to you and their friends' friends' relation to you are much easier to work with using graph-based database management systems.
