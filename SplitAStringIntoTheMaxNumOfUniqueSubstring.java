import java.util.HashSet;

/**
 * SplitAStringIntoTheMaxNumOfUniqueSubstring
 * if(s.length() == 1)
            return 1;
        else if (s.length() == 2){
            if(s.charAt(0) == s.charAt(1))
                return 1;
            else 
                return 2;
        }

        Set<String> uniqueSet = new HashSet<>();
        int left = 0;
        int n = s.length();
        while( left < n){
            if(!uniqueSet.contains(s.substring(left,left+1))){
                uniqueSet.add(s.substring(left,left+1));
            } else {
                int start = left;
                int end = Math.min(left+2, n);
                String temp = null;
                while(true && end<=n){
                    temp = s.substring(start, end);
                    if(!uniqueSet.contains(temp)){
                        uniqueSet.add(temp);
                        left = end-1;
                        break;
                    } else{
                        end++;
                    }
                }
            }
            left++;
        }
        
        return uniqueSet.size();
    }

    
}
Given a string s, return the maximum number of unique substrings that the given string can be split into.

You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string. However, you must split the substrings such that all of them are unique.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "ababccc"
Output: 5
Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
Example 2:

Input: s = "aba"
Output: 2
Explanation: One way to split maximally is ['a', 'ba'].
Example 3:

Input: s = "aa"
Output: 1
Explanation: It is impossible to split the string any further.
 

Constraints:

1 <= s.length <= 16

s contains only lower case English letters.
 */
public class SplitAStringIntoTheMaxNumOfUniqueSubstring {

    public int maxUniqueSplit(String s) {
        return backtrack(0, s, new HashSet<>());
    }
    private int backtrack(int start, String s, HashSet<String> seen) {
        if (start == s.length()) {
            return 0; 
        }
        int maxSplits = 0;
        for (int end = start + 1; end <= s.length(); end++) {
            String substring = s.substring(start, end);
            if (!seen.contains(substring)) {
                seen.add(substring); 
                maxSplits = Math.max(maxSplits, 1 + backtrack(end, s, seen));
                seen.remove(substring);
            }
        }
        return maxSplits;
    } 
}