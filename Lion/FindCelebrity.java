/*Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.*/

// My suck code
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

// My suck Code
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int[] inlevel = new int[n];
        int[] outlevel = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                if (knows(i, j)) {
                    outlevel[i]++;
                    inlevel[j]++;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            if (outlevel[k] == 0 && inlevel[k] == n - 1) {
                return k;
            }
        }
        return -1;
    }
}

// Two Pass Good Solution
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++){
            if(knows(candidate, i))
                candidate = i;
        }
        for(int i = 0; i < n; i++){
            if(i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
        }
        return candidate;
    }
}

// Using stacks
public int findCelebrity(int n) {
    // base case
    if (n <= 0) return -1;
    if (n == 1) return 0;

    Stack<Integer> stack = new Stack<>();

    // put all people to the stack
    for (int i = 0; i < n; i++) stack.push(i);

    int a = 0, b = 0;

    while (stack.size() > 1) {
        a = stack.pop(); b = stack.pop();

        if (knows(a, b)) 
            // a knows b, so a is not the celebrity, but b may be
            stack.push(b);
        else 
            // a doesn't know b, so b is not the celebrity, but a may be
            stack.push(a);
    }

    // double check the potential celebrity
    int c = stack.pop();

    for (int i = 0; i < n; i++)
        // c should not know anyone else
        if (i != c && (knows(c, i) || !knows(i, c)))
            return -1;

    return c;
}

