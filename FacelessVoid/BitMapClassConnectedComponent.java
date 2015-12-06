count number of island的变形~
给了一个Bitmap class，然后找connected component
需要记下每一个component size，最后return median

上来直接就是dfs了~
找到每个connected component的大小，然后sort，返回median
follow up问如果map很大怎么办~
dfs会导致stack上面有O(n)的function call
问能不能iteratively解决~
一下子没想出iterative怎么写~
直接改成了bfs和queue~
也可以解决stackoverflow的问题~

啊，我的理解是bfs每次处理一个index的时候只有符合条件才会放入queue里面~
public void bfs(Bitmap map, boolean[][] visited, int i, int j) {
    helper(map, visited, i, j);
    while (!queue.isEmpty()) {
        int index = queue.poll();
        i = index / map.width();
        j = index % map.width();
        helper(map, visited, i - 1, j);
        helper(map, visited, i + 1, j);
        helper(map, visited, i, j - 1);
        helper(map, visited, i, j + 1);
    }
}
public void helper(Bitmap map, boolean[][] visited, int i, int j) {
    if (i < 0 || i >= map.height() || j < 0 || j >= map.width() || !map.get(i, j) || visited[j]) {
        return;
    }
    count++;
    visited[j] = true;
    queue.add(i * map.width() + j);
}