import java.util.ArrayList;
import java.util.List;

public class DifferentWayToAddParentheses241 {
    /*class Solution {
    List<Integer>[][] dp;
    public List<Integer> diffWaysToCompute(String ep) {
        dp = new ArrayList[ep.length()][ep.length()];
        return fun(ep, 0, ep.length()-1);
    }
    private List<Integer> fun(String a, int start, int end) {
        List<Integer> ret = new ArrayList<>();
        if (dp[start][end] != null)
        return dp[start][end];
        int x = operand(a, start, end);
        if (x != -1) {
            ret.add(x);
            dp[start][end] = ret;
            return ret;
        }
        for (int i = start; i <= end; i ++ ){ 
            if (!isOp(a.charAt(i)))
            continue;
            List<Integer> left = fun(a, start, i - 1);
            List<Integer> right = fun(a, i + 1, end);
            for (int j = 0 ;j < left.size(); j++) {
                for (int k = 0 ; k < right.size(); k++) {
                    if (a.charAt(i) == '*')
                    ret.add(left.get(j)*right.get(k));
                    if (a.charAt(i) == '+')
                    ret.add(left.get(j)+right.get(k));
                    if (a.charAt(i) == '-')
                    ret.add(left.get(j)-right.get(k));
                }
            }
        }
        dp[start][end] = ret;
        return ret;
    }
    private int operand(String a, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (isOp(a.charAt(i)))
            return -1;
        }
        int value = 0;
        for (int i = start; i <= end; i++)
        value = value * 10 + (a.charAt(i) - '0');
        return value;
    }
    private boolean isOp(char c) {
        return c == '*' || c == '-' || c == '+';
    }
}
Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.

The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.

 

Example 1:

Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0 
(2-(1-1)) = 2
Example 2:

Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
 

Constraints:

1 <= expression.length <= 20
expression consists of digits and the operator '+', '-', and '*'.
All the integer values in the input expression are in the range [0, 99].
The integer values in the input expression do not have a leading '-' or '+' denoting the sign.

*/
 public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < expression.length(); ++i) {
            char oper = expression.charAt(i);
            if (oper == '+' || oper == '-' || oper == '*') {
                List<Integer> s1 = diffWaysToCompute(expression.substring(0, i));
                List<Integer> s2 = diffWaysToCompute(expression.substring(i + 1));
                for (int a : s1) {
                    for (int b : s2) {
                        if (oper == '+') res.add(a + b);
                        else if (oper == '-') res.add(a - b);
                        else if (oper == '*') res.add(a * b);
                    }
                }
            }
        }
        if (res.isEmpty()) res.add(Integer.parseInt(expression));
        return res;
    }
}
