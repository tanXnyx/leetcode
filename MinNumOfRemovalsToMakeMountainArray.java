import java.util.Arrays;
/*
 * class Solution {
    public int minimumMountainRemovals(int[] nums) {
               int n=nums.length;
        int[] lis=new int[n];
        int[] lis1=new int[n];
        int[] dp=new int[n];
        int[] dp1=new int[n];
        int len=0;
        int j=0;
        for(int a:nums){
            int i= Arrays.binarySearch(lis,0,len,a);
            if(i<0){
                i=-(i+1);
            }
            lis[i]=a;
            if(len==i){
                len++;
            dp[j]=len;
            }else{
                dp[j]=i+1;
            }
            j++;
        }
        int len1=0;
        for(int i=n-1;i>=0;i--){
            int k=Arrays.binarySearch(lis1,0,len1,nums[i]);
            if(k<0){
                k=-(k+1);
            }
            lis1[k]=nums[i];
            if(len1==k){
                len1++;
            dp1[i]=len1;
            }else{
                dp1[i]=k+1;
            }
        }
        int max=0;
        for(int i=1;i<n-1;i++){
            if(dp[i]>1 && dp1[i]>1)
                max=Math.max(max,dp[i]+dp1[i]);
        }
        return n-max+1;
    }
}
You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array nums​​​, return the minimum number of elements to remove to make nums​​​ a mountain array.

 

Example 1:

Input: nums = [1,3,1]
Output: 0
Explanation: The array itself is a mountain array so we do not need to remove any elements.
Example 2:

Input: nums = [2,1,1,5,6,2,3,1]
Output: 3
Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
 

Constraints:

3 <= nums.length <= 1000
1 <= nums[i] <= 109
It is guaranteed that you can make a mountain array out of nums.
 */
public class MinNumOfRemovalsToMakeMountainArray {
     public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] LIS = new int[n], LDS = new int[n];
        Arrays.fill(LIS, 1);
        Arrays.fill(LDS, 1);

        // Compute LIS for each index
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }

        // Compute LDS from each index
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j > i; --j) {
                if (nums[i] > nums[j]) {
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
                }
            }
        }

        int maxMountainLength = 0;

        // Find the maximum mountain length
        for (int i = 1; i < n - 1; ++i) {
            if (LIS[i] > 1 && LDS[i] > 1) {  // Valid peak
                maxMountainLength = Math.max(maxMountainLength, LIS[i] + LDS[i] - 1);
            }
        }

        return n - maxMountainLength;
    }
}
