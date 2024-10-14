/*
 * class Solution {
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int i : nums){
            pq.add(i);
        }
        long ans = 0;
        while(k-- > 0){
            int n = pq.poll();
            ans += n;
            pq.offer((n + 2)/ 3);
        }
        return ans;
    }
}
 */

import java.util.PriorityQueue;

/**
 * maxScoreAfterApplingKoperation
 */
public class maxScoreAfterApplingKoperation {

     public long maxKelements(int[] nums, int k) {
    PriorityQueue<Integer>pq = new PriorityQueue<>((a, b)->b-a);
    for(int x : nums)
    pq.offer(x);
    long score = 0; 
    while(!pq.isEmpty() && k>0)
    {
      score +=pq.peek();
      int x = (int)Math.ceil(pq.poll()/3.0);
      pq.add(x);
      k--;
    }
     return score;
    }
}