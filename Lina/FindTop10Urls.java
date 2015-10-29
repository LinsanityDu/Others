/*Ans: 
we will just mimic the actions of map-reduce: 
1. pre-processing: let R be the number of servers in cluster, give each server unique id from 0,1,2,…,R-1 
2. (map) For each (string,id) - send the tuple to the server which has the id hash(string) % R. 
3. (reduce) Once step 2 is done (simple control communication), produce the (string,count) of the top 10 strings per server. Note that the tuples where those sent in step2 to this particular server. 
4. (map) Each server will send all his top 10 to 1 server (let it be server 0). It should be fine, there are only 10*R of those records. 
5. (reduce) Server 0 will yield the top 10 across the network.*/

