/*There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.*/

public class Solution {
      public int canCompleteCircuit(int[] gas, int[] cost) {
          if (gas == null || cost == null || gas.length == 0 || cost.length == 0) {
              // Bug 0: should not return false;
              return -1;
          }
          
          int total = 0;
          int sum = 0;
         
         int startIndex = 0;
         
         int len = gas.length;
         for (int i = 0; i < len; i++) {
             int dif = gas[i] - cost[i];
             sum += dif;
             
             if (sum < 0) {
                 // Means that from 0 to this gas station, none of them can be the solution.
                 startIndex = i + 1; // Begin from the next station.
                 sum = 0; // reset the sum.
             }
             
             total += dif;
         }
         
         if (total < 0) {
             return -1;
         }
         
         return startIndex;
     }
 }
