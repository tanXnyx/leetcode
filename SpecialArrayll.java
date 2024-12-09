public class SpecialArrayll {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        //     int n = queries.length;
        //     int[][] q = new int[n][3];
        //     for(int i=0;i<n;i++){
        //         q[i][0] = queries[i][0];
        //         q[i][1] = queries[i][1];
        //         q[i][2] = i;
        //     }
        //     Arrays.sort(q, (a,b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
        //     List<int[]> disparity = new ArrayList<>();
        //     for(int i=1;i<nums.length;i++){
        //         if((nums[i]-nums[i-1])%2==0){
        //             disparity.add(new int[]{i-1,i});
        //         }
        //     }
        //     int j = 0;
        //     boolean[] res = new boolean[n];
        //     for(int i=0;i<n;i++){
        //         while(j<disparity.size() && q[i][0]>=disparity.get(j)[1]){
        //             j++;
        //         }
        //         if(j<disparity.size() && q[i][0]<=disparity.get(j)[0] && q[i][1]>=disparity.get(j)[1]){
        //             res[q[i][2]] = false;
        //         } else {
        //             res[q[i][2]] = true;
        //         }
        //     }
        //     return res; 
        // }
    
        public boolean[] isArraySpecial(int[] nums, int[][] queries) {
            int[] prefix = new int[nums.length];
            for(int i=1;i<nums.length;i++){
                if((nums[i]-nums[i-1])%2==0){
                    prefix[i] = prefix[i-1]+1;
                } else {
                    prefix[i] = prefix[i-1];
                }
            }
            boolean[] res = new boolean[queries.length];
            for(int i=0;i<queries.length;i++){
               int start = queries[i][0], end = queries[i][1];
               if(prefix[end]==prefix[start])
               res[i] = true;
            }
            return res;
        }
}
