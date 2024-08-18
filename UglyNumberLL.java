import java.util.ArrayList;
import java.util.List;
/*
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return the nth ugly number.

 

Example 1:

Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 

Constraints:

1 <= n <= 1690
 */
public class UglyNumberLL {
      public int nthUglyNumber(int n) {
        int[] primes = {2, 3, 5};  // Initialize the primes array
        int[] indices = {0, 0, 0}; // Initialize indices for multiples of 2, 3, 5
        List<Integer> uglyArr = new ArrayList<>();  // Initialize the ugly number array with 1
        uglyArr.add(1);

        for (int i = 1; i < n; ++i) {
            // Calculate the next possible ugly numbers
            int[] nextUglies = {
                uglyArr.get(indices[0]) * primes[0],
                uglyArr.get(indices[1]) * primes[1],
                uglyArr.get(indices[2]) * primes[2]
            };
            int minValue = Math.min(nextUglies[0], Math.min(nextUglies[1], nextUglies[2]));  // Find the smallest value
            uglyArr.add(minValue);  // Append the smallest value to uglyArr

            // Update indices for primes that generated the current min_value
            for (int j = 0; j < 3; ++j) {
                if (nextUglies[j] == minValue) {
                    indices[j]++;
                }
            }
        }

        return uglyArr.get(n - 1);
    }
    /*
     * class Ugly{
public int[] nums = new int[1690];
Ugly(){
    nums[0] = 1;
    int ugly,i2 = 0,i3=0,i5=0;

    for(int i=1;i<1690;i++){
        ugly = Math.min(Math.min(nums[i2]*2,nums[i3]*3),nums[i5]*5);
        nums[i] = ugly;

        if(ugly == nums[i2]*2) ++i2;
        if(ugly == nums[i3]*3) ++i3;
        if(ugly == nums[i5]*5) ++i5;
        
    }
}
}
class Solution {
    public static Ugly u = new Ugly();
    public int nthUglyNumber(int n) {
        return u.nums[n-1];
    }
}
     */
}
