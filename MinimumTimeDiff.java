import java.util.List;
/*
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
 

Example 1:

Input: timePoints = ["23:59","00:00"]
Output: 1
Example 2:

Input: timePoints = ["00:00","23:59","00:00"]
Output: 0
 

Constraints:

2 <= timePoints.length <= 2 * 104
timePoints[i] is in the format "HH:MM".
 */
public class MinimumTimeDiff {
     public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440) return 0; 

        boolean[] seen = new boolean[1440]; 
        
        for (String time : timePoints) {
            int minutes = convertToMinutes(time);
            if (seen[minutes]) return 0; 
            seen[minutes] = true;
        }
        
        int first = Integer.MAX_VALUE, prev = Integer.MAX_VALUE;
        int minDiff = Integer.MAX_VALUE;
        
        for (int i = 0; i < 1440; i++) {
            if (seen[i]) {
                if (first == Integer.MAX_VALUE) {
                    first = i;
                } else {
                    minDiff = Math.min(minDiff, i - prev);
                }
                prev = i;
            }
        }
        

        minDiff = Math.min(minDiff, 1440 - prev + first);
        
        return minDiff;
    }
    
    private int convertToMinutes(String time) {
        return ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 
             + (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
    }
    //Kartikdevsharmaa
    /*
     * class Solution {
     public int findMinDifference(List<String> timePoints) {
        // convert input to minutes
        int[] minutes = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            String time = timePoints.get(i);
            int h = Integer.parseInt(time.substring(0, 2));
            int m = Integer.parseInt(time.substring(3));
            minutes[i] = h * 60 + m;
        }

        // sort times in ascending order
        Arrays.sort(minutes);

        // find minimum difference across adjacent elements
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < minutes.length - 1; i++) {
            ans = Math.min(ans, minutes[i + 1] - minutes[i]);
        }

        // consider difference between last and first element
        return Math.min(
            ans,
            24 * 60 - minutes[minutes.length - 1] + minutes[0]
        );
    }
}
     */
}
