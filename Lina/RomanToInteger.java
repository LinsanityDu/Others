/*Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.*/


public class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
	    map.put('M', 1000);
	    map.put('D', 500);
	    map.put('C', 100);
	    map.put('L', 50);
	    map.put('X', 10);
	    map.put('V', 5);
	    map.put('I', 1);
	    int index = 0;
	    int res = 0;
	    while (index < s.length() - 1) {
	        if (map.get(s.charAt(index)) >= map.get(s.charAt(index + 1))) {
	            res += map.get(s.charAt(index));
	            index++;
	        } else if (map.get(s.charAt(index)) < map.get(s.charAt(index + 1))) {
	            res += map.get(s.charAt(index + 1)) - map.get(s.charAt(index));
	            index += 2;
	        }
	    }
	    
	    if (index == s.length() - 1) {
	        res += map.get(s.charAt(index));
	    }
	    return res;
    }
}

// Discussion
public class Solution {
    public int romanToInt(String s) {
        //：Ⅰ（1）Ⅴ（5）Ⅹ（10）L（50）C（100）D（500）M（1000） 
        // rules:位于大数的后面时就作为加数；位于大数的前面就作为减数
        //eg：Ⅲ=3,Ⅳ=4,Ⅵ=6,ⅩⅨ=19,ⅩⅩ=20,ⅩLⅤ=45,MCMⅩⅩC=1980
        //"DCXXI"

        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int result = map.get(s.charAt(len -1));
        int pivot = result;
        for(int i = len -2; i>= 0;i--){
            int curr = map.get(s.charAt(i));
            if(curr >=  pivot){
                result += curr;
            }else{
                result -= curr;
            }
            pivot = curr;
        }
        return result;
    }
}