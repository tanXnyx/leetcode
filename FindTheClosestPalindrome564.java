public class FindTheClosestPalindrome564 {
    /*
     * Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.

The closest is defined as the absolute difference minimized between two integers.

 

Example 1:

Input: n = "123"
Output: "121"
Example 2:

Input: n = "1"
Output: "0"
Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
 

Constraints:

1 <= n.length <= 18
n consists of only digits.
n does not have leading zeros.
n is representing an integer in the range [1, 1018 - 1].
     */
    public String nearestPalindromic(String n) {
        
        int len=n.length();

        if(len==1){
            int val = Integer.parseInt(n)-1;
            return Integer.toString(val);
        }

        long[] candidates = new long[5];

        Long num = Long.parseLong(n);

        // case 1 - largest palindrome smaller than n
        long pal1 = (long)Math.pow(10,len-1) - 1;    // 999.....

        candidates[0] = pal1;

        // case 2 - smallest palindrome larger than n
        long pal2 = (long)Math.pow(10,len) + 1;        // 111.....

        candidates[1] = pal2;
        
        // case 3 - mirror the left half of n to make palindrome
        String leftHalf = n.substring(0,(len+1)/2);
        String mirrorPalindrome = leftHalf + new StringBuilder(leftHalf).reverse().substring(len%2);
        long pal3 = Long.parseLong(mirrorPalindrome);

        candidates[2] = pal3;

        // case 4 - consider n-1 -> can it be made palindrome -> for cases like 1000
        long leftHalf1 = Long.parseLong(leftHalf);
        leftHalf1 = leftHalf1 - 1;
        String leftMin1 = Long.toString(leftHalf1);
        String sb1 = leftMin1 + new StringBuilder(leftMin1).reverse().substring(len%2);
        long pal4 = Long.parseLong(sb1);

        candidates[3] = pal4;

        // case 5 - consider n+1 -> can it be made palindrome  -> for cases like 999
        long leftHalf2 = Long.parseLong(leftHalf);
        leftHalf2 = leftHalf2 + 1;
        String leftPls1 = Long.toString(leftHalf2);
        String sb2 = leftPls1 + new StringBuilder(leftPls1).reverse().substring(len%2);
        long pal5 = Long.parseLong(sb2);

        candidates[4] = pal5;

        long closestPal = -1;

        for(long p : candidates){
            if(p!=num){
                if(Math.abs(num-closestPal) > Math.abs(num-p) || (Math.abs(num-closestPal) == Math.abs(num-p) && closestPal > p )){
                    closestPal = p;
                }
            }
        }

        return String.valueOf(closestPal);
    }
    /*
     *   public String nearestPalindromic(String numberStr) {
        long number = Long.parseLong(numberStr);
        if (number <= 10) return String.valueOf(number - 1);
        if (number == 11) return "9";

        int length = numberStr.length();
        long leftHalf = Long.parseLong(numberStr.substring(0, (length + 1) / 2));
        
        long[] palindromeCandidates = new long[5];
        palindromeCandidates[0] = generatePalindromeFromLeft(leftHalf - 1, length % 2 == 0);
        palindromeCandidates[1] = generatePalindromeFromLeft(leftHalf, length % 2 == 0);
        palindromeCandidates[2] = generatePalindromeFromLeft(leftHalf + 1, length % 2 == 0);
        palindromeCandidates[3] = (long)Math.pow(10, length - 1) - 1;
        palindromeCandidates[4] = (long)Math.pow(10, length) + 1;

        long nearestPalindrome = 0;
        long minDifference = Long.MAX_VALUE;

        for (long candidate : palindromeCandidates) {
            if (candidate == number) continue;
            long difference = Math.abs(candidate - number);
            if (difference < minDifference || (difference == minDifference && candidate < nearestPalindrome)) {
                minDifference = difference;
                nearestPalindrome = candidate;
            }
        }

        return String.valueOf(nearestPalindrome);
    }

    private long generatePalindromeFromLeft(long leftHalf, boolean isEvenLength) {
        long palindrome = leftHalf;
        if (!isEvenLength) leftHalf /= 10;
        while (leftHalf > 0) {
            palindrome = palindrome * 10 + leftHalf % 10;
            leftHalf /= 10;
        }
        return palindrome;
    }
     */
}
