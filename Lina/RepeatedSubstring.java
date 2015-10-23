/*Find all the repeating sub-string sequence of specified length in a large string sequence. The sequences returned i.e. the output must be sorted alphabetically. 

For e.g. 

Input String: "ABCACBABC" 
repeated sub-string length: 3 

Output: ABC 

Input String: "ABCABCA" 
repeated sub-string length: 2 

Output: AB, BC, CA*/

public Set<String> findRepeatedSubString(String s,int length){
        if(s==null||s.length()==0) return null;
        Set<String> checkSet = new HashSet<String>();
        Set<String> result = new TreeSet<String>();
        for(int i=0;i<s.length()-length+1;i++){
            if(!checkSet.contains(s.substring(i,i+length)))
                checkSet.add(s.substring(i,i+length));
            else
                result.add(s.substring(i,i+length));
        }
        if(result.size()==0) return null;
        return result;
}