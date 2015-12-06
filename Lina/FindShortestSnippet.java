Find shortest snippet，比如给一个Document是A,X,X,B,A,X,B，Query是A,B，要求返回shortestSnippet. 
第一问：如果Query有序（即A一定要在B前面），那么要返回A,X,B
Follow up：如果Query无序（即B在A前面也可以），那么要返回B,A
再Follow up：如果Document非常大，如何再优化？

 给一个string，比如UAXXBAUB，给一个pattern，比如AB，返回包含pattern的最短
substring，结果是AUB,考虑pattern是有序的。
就是Minimum Window Substring的有序版，主要考虑的是找到window后shrink到
底怎么做，有好几种情况。比如"UAXSSXSXAAUB", "XXA"，找到XSSXSXA之后shrink窗口
，得把leftBound前进到第2个X。


/*
         b  a  c  d  b  a  b  d. Waral 鍗氬鏈夋洿澶氭枃绔�,
      a -1  1  1  1  1  5  5  5
      b -1 -1 -1 -1  4  4  6  6
  */
//dp[j] is the index.1point3acres缃�
string findShortestSnippet(string s, string p){
        int m = p.length();
        int n = s.length();
        if(p=="")return "";
        int dp[m+1][n+1]; //dp: match b to a, the tail index.
        for(int i=0;i<m+1;i++)dp[0] = -1;
        for(int j=0;j<n+1;j++)dp[0][j] = 0;
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(dp[i-1][j]==-1)dp[j] = -1;
                else if(p[i-1]==s[j-1])dp[j] = j-1;
                else dp[j] = dp[j-1];
            }
        }

        int len = INT_MAX;
        int start = 0;
        int end = 0;
        for(int j=10;j<n+1;j++){. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
            if(dp[1][j]==-1)continue;
            if(dp[m][j]==-1)continue;
            if(dp[m][j]<dp[1][j])continue;. From 1point 3acres bbs
            if(dp[m][j]-dp[1][j] < len){
                start = dp[1][j];
                end = dp[m][j];. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
                len = end-start+1;.鏈枃鍘熷垱鑷�1point3acres璁哄潧
            }
        }
        return s.substr(start,len);

补充内容 (2015-10-20 13:37):. Waral 鍗氬鏈夋洿澶氭枃绔�,
注释写的不好。 可以自己用笔写一下dp的表格，就能理解了。dp[j]==-1: 表示s[0:j] 找不到 p[0:i].   