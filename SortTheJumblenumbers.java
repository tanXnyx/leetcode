public class SortTheJumblenumbers {
    
    class Solution {
        public int[] sortJumbled(int[] mapping, int[] nums) {
         
         // this problem can be solved using the concept of Radix Sort;
    
        int max = -1 ;
       
         for(int x : nums)
         {
            if(x > max) max = x ;
         }  
    
          int [] ans = nums;
    
         for(int place = 1 ; max/place > 0 ; place *= 10 )   
         {
           
         ans = CountSort( ans, place , mapping);
           
         }
        
        return ans;
        }
    
    
    static int[] CountSort(int [] arr , int place, int[] mapping)
    {
        int n = arr.length;
    
        if( n <= 1 )return arr;
    
        int[] ans = new int[n];
    
        int[] freqArr = new int[10];
    
        for(int x : arr)                   // making frequency array;
        {    
            if(x/place != 0 || x==0)
            { int val = (x/place) % 10 ;
            
              freqArr[ mapping[val] ]++;        }
            else
            {
              freqArr[ 0 ]++;        
            }
        }
    
    
        for(int i = 1 ; i<10 ; i++)       // converting frequency array into prefix sum array;
        {
            freqArr[i] += freqArr[i-1];
        }
    
        for(int i = n - 1 ; i >= 0 ; i--)
        {
            if( arr[i]/place != 0 || arr[i]== 0)
            {
            int val = ( arr[i]/place )%10;
    
             ans[ freqArr[mapping[val]] - 1 ] = arr[i] ;
             
              freqArr[mapping[val]]--;
            }
            else
            {
                ans[ freqArr[0] - 1] = arr[i];
                freqArr[0]--;
            }
        }
    
    return ans;
    }
    
    
    }
}
