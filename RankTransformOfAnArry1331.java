import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RankTransformOfAnArry1331 {
   /*
   Given an array of integers arr, replace each element with its rank.

The rank represents how large the element is. The rank has the following rules:

Rank is an integer starting from 1.
The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
Rank should be as small as possible.
 

Example 1:

Input: arr = [40,10,20,30]
Output: [4,1,2,3]
Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
Example 2:

Input: arr = [100,100,100]
Output: [1,1,1]
Explanation: Same elements share the same rank.
Example 3:

Input: arr = [37,12,28,9,100,56,80,5,12]
Output: [5,3,4,2,8,6,7,1,3]
 

Constraints:

0 <= arr.length <= 105
-109 <= arr[i] <= 109
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] res = new int [arr.length];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int val: arr) {
            max = Math.max(val, max);
            min = Math.min(val, min);
        } 
    
        if (max - min > 200 && arr.length < 10) {
            int [] tmp = arr.clone();
            Arrays.sort(arr);
            int index = 1;
            boolean isRepeat = false;
            for (int i = 0; i < arr.length; i++) {
                int val = arr[i];
                isRepeat = false;
                for (int j = 0; j < tmp.length; j++) {
                    if (tmp[j] == val) {
                        if (isRepeat) {
                            i++;
                        }
                        res[j] = index;
                        isRepeat = true;
                    }
                }
                index++;
            }
            return res;

        } else {
            // [40,10,20,30]  nums[0 - 30] 
            // [100]
            int[] nums = new int [max - min + 1];
            for (int i = 0; i < arr.length; i++) {
                nums[arr[i] - min] = 1;
            }

            int rank = 1;
            for (int i = 0; i < max - min + 1; i++) {
                if (nums[i] == 1) {
                    nums[i] = rank;
                    rank++;
                }
            }

            //System.out.println(Arrays.toString(nums));
            for (int i = 0; i < arr.length; i++) {
                res[i] = nums[arr[i]-min];
            }

            return res;
        }
    }
}
*/ 
 public int[] arrayRankTransform(int[] arr) {
        Map<Integer, Integer> valueToRank = new HashMap<>();  // Map to store value-to-rank mapping
        int[] sortedUniqueNumbers = Arrays.stream(arr).distinct().sorted().toArray();  // Remove duplicates and sort
        
        // Assign ranks to sorted unique elements
        for (int i = 0; i < sortedUniqueNumbers.length; i++) {
            valueToRank.put(sortedUniqueNumbers[i], i + 1);
        }

        // Replace each element in the original array with its rank
        for (int i = 0; i < arr.length; i++) {
            arr[i] = valueToRank.get(arr[i]);
        }

        return arr;  // Return the updated array
    }
}
