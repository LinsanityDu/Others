NIM GAME?

* In the 100 game two players take turns adding, to a running 
total, any integer from 1..10. The player who first causes the running 
total to reach or exceed 100 wins. 
What if we change the game so that players cannot re-use integers? 
For example, if two players might take turns drawing from a common pool of numbers 
of 1..15 without replacement until they reach a total >= 100. This problem is 
to write a program that determines which player would win with ideal play. 

Write a procedure, "Boolean canIWin(int maxChoosableInteger, int desiredTotal)", 
which returns true if the first player to move can force a win with optimal play. 

Your priority should be programmer efficiency; dont focus on minimizing 
either space or time complexity. 
*/ 

Boolean canIWin(int maxChoosableInteger, int desiredTotal) { 
// Implementation here. Write yours 

}

先手胜的情况是
n 轮(先手): 当前的数组中存在一个数使得 Sum > target.
n-1轮(后手): 当前的数组中选任何一个数 X 都不能使得 Sum > target. 但是当后手选完之后存在一个数可使得 Sum > target. 

代码也不长
public class PickUpNumbers {
    public boolean canIWin(int[] numberPool, int target) {
        boolean isEmpty = true;
        for (int data : numberPool)
            if (data > 0) isEmpty = false; 
        if (isEmpty) return false;
        else {
            if (target <= 0) return false;
            for (int data : numberPool)
                if (data > 0 && data >= target) return true;
            boolean canIWinFlag = false;
            for (int i = 0; i < numberPool.length && numberPool[i] > 0; ++i) {
                int data = numberPool[i];
                numberPool[i] = -1;
                canIWinFlag = canIWinFlag || !canIWin(numberPool, target - data); // other's turn
                numberPool[i] = data;
            }
            return canIWinFlag;
        }
    }
    public static void main(String[] args) {
        int[] numberPool = {1, 2, 3};
        System.out.println(new PickUpNumbers().canIWin(numberPool, 5));-google 1point3acres
        System.out.println(new PickUpNumbers().canIWin(numberPool, 4));
    }
}




又被问了个面经外的题，目测是杯具了，实在是没梳理出逻辑boolean canIWin(int maxNum, int target)，从1,2...maxNum的数组里两个玩家轮流选数，第一个达到sum>=target的玩家获胜，问如何判断先选的玩家能获胜。. 鍥磋鎴戜滑@1point 3 acres
能想到的就是先求总和sum, 如果sum < target 无解, false
                                           如果sum == target, 根据数组的长度判断，奇数个则true偶数个false 
然后是sum>target, 双方的目标是要至少保证选完数x后target-x > 数组里留下的最大数，然后递归，根据回合数的奇偶判断是true还是false。然后就这个逻辑不知道该怎么实现了。。。. 
为啥就不能碰个面经里的题呢>.<

private static boolean helper(ArrayList<Integer> numList, int target) {
                /* empty list, return false */.1point3acres缃�
                if(numList.size() == 0) {.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
                        return false;
                }
                boolean opRes = true;            //results of opponent
                for(int i = 0; i < numList.size(); i++) {
                        int num = numList.get(i); 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
                        if(num >= target) {
                                /* pick this number, I will win */
                                return true;
                        }
                        numList.remove(i);       //I pick this one
                        if(!helper(numList, target - num)) {
                                /* my opponent cannot win */
                                opRes = false;. From 1point 3acres bbs
                        }.1point3acres缃�
                        numList.add(i, num);    //I don't pick this one since my opponent will always win, add it back. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
                }
                return !opRes;             //if among these numbers picking one of them will let my opponent lose, then I will win
        }


Given an array of positive integers and two players. In each turn, one player picks up one number and if the sum of all the picked up numbers is greater than a target number, the player wins. Write a program canIWin() to print the result.
Answer:
enum Result {Win, Lose, Draw}
public class PickUpNumbers {
        public static Result canIWin(int[] numberPool, int target) {
                if (target <= 0) return Result.Lose;
                boolean isEmpty = true;
                for (int data : numberPool)
                        if (data > 0) isEmpty = false;
                if (isEmpty) return Result.Draw;
                else {
                        for (int data : numberPool)
                                if (data >= target) return Result.Win;
                        Result drawFlag = Result.Draw, rivalWinFlag = Result.Win;
                        for (int i = 0; i < numberPool.length; ++i) {
                                if (numberPool < 0) continue;
                                int data = numberPool;
                                numberPool = -1;
                                Result rivalResult = canIWin(numberPool, target - data); // rival's turn
                                if (rivalResult != Result.Win) rivalWinFlag = rivalResult;
                                if (rivalResult != Result.Draw) drawFlag = rivalResult;
                                numberPool = data;
                        }
                        if (drawFlag == Result.Draw) return Result.Draw;
                        if (rivalWinFlag == Result.Win) return Result.Lose; // whatever number i choose, rival wins
                        return Result.Win;
                }
        }
}
Test Code:
public static void main(String[] args) {
        int[] numberPool1 = {1, 2, 3};
        System.out.println(PickUpNumbers.canIWin(numberPool1, 5));
        System.out.println(PickUpNumbers.canIWin(numberPool1, 4));
        System.out.println(PickUpNumbers.canIWin(numberPool1, 8));
        int[] numberPool2 = {1, 2, 3, 4, 5};
        System.out.println(PickUpNumbers.canIWin(numberPool2, 11));
        int[] numberPool3 = {1, 2, 3, 4, 5, 6};
        System.out.println(PickUpNumbers.canIWin(numberPool3, 17));
}
Result:
Win
Lose
Draw
Lose

Win