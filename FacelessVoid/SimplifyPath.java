/*Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"*/

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