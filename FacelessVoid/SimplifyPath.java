/*Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"*/
The star means “zero or more of the previous character.” So the regex Bb* will match “B”, “Bb”, “Bbb”, “Bbbb”, and so on. (Note that you must have the capital B, or the match will fail; the B must match before b* can try to.)

The plus means “one or more of the previous character.” So the regex Bb+ will match “Bb”, “Bbb”, “Bbbb”, and so on, but will not match “B” by itself.

public class Solution {
    public String simplifyPath(String path) {
        String result = "/";
        String[] stubs = path.split("/+");
        ArrayList<String> paths = new ArrayList<String>();
        for (String s : stubs){
            if(s.equals("..")){
                if(paths.size() > 0){
                    paths.remove(paths.size() - 1);
                }
            }
            else if (!s.equals(".") && !s.equals("")){
                paths.add(s);
            }
        }
        for (String s : paths){
            result += s + "/";
        }
        if (result.length() > 1)
            result = result.substring(0, result.length() - 1);
        return result;
    }
}
// 
The main idea is to push to the stack every valid file name (not in {"",".",".."}), popping only if there's smth to pop and we met "..". I don't feel like the code below needs any additional comments.

public String simplifyPath(String path) {
    Deque<String> stack = new LinkedList<>();
    Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
    for (String dir : path.split("/")) {
        if (dir.equals("..") && !stack.isEmpty()) stack.pop();
        else if (!skip.contains(dir)) stack.push(dir);
    }
    String res = "";
    for (String dir : stack) res = "/" + dir + res;
    return res.isEmpty() ? "/" : res;
}

// Discussion
public String simplifyPath(String path) {
    StringBuilder sb = new StringBuilder("/");
    LinkedList<String> stack = new LinkedList<String>();
    for(String s: path.split("/")){
        if(s.equals("..")){
            if(!stack.isEmpty())
                stack.removeLast();
        }
        else if(!s.equals("") && !s.equals("."))
            stack.add(s);
    }
    for(String s: stack){
        sb.append(s+"/");
    }
    if(!stack.isEmpty()) sb.setLength(sb.length()-1);
    return sb.toString();
}