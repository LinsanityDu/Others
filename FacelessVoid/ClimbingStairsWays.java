stair climbing， print out all of possible solutions of the methods to climb a stars, you are allowed climb one or two steps for each time; what is time/space complexity? （use recursion）

package fib;
  
  import java.util.ArrayList;
  
  public class climbingstairs {
      
      public ArrayList<String> climb (int n) {
          if (n <= 0) return null;
          ArrayList<String> res = new ArrayList<String>();
         if (n == 1) { 
               res.add("1");
12             return res;
13         }
14         if (n == 2) {
15             res.add("2");
16             res.add("12");
17             return res;
18         }
19         ArrayList<String> former2 =  climb(n-2); 
20         for (String item : former2) {
21             res.add(item+Integer.toString(n));
22         }
23         ArrayList<String> former1 = climb(n-1);
24         for (String item : former1) {
25             res.add(item+Integer.toString(n));
26         }
27         return res;
28     }
29 
30     
31     public static void main(String[] args) {
32         climbingstairs obj = new climbingstairs();
33         ArrayList<String> res = obj.climb(6);
34         for (String item : res) {
35             System.out.println(item);
36         }
37     }
38 
39 }