import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TheNumOfTheSmallestUnoccupiedChair {
    /* class Solution {

    public int smallestChair(int[][] times, int targetFriend) {
        PriorityQueue<Node2> busyChair = new PriorityQueue<>();
        PriorityQueue<Integer> emptyChair = new PriorityQueue<>();
        
        int n = times.length;
        
        Node[] arrDepNodeArray = new Node[n];
        for(int i = 0; i < n; i++){
            int[] time = times[i];
            int arr = time[0];
            int dep = time[1];
            
            Node arrDepNode = new Node(arr, dep);
            arrDepNodeArray[i] = arrDepNode;
            if(i == targetFriend){
                arrDepNode.target = true;
            }
        }
        
        Arrays.sort(arrDepNodeArray);
        int chairNo = 1;
        for(Node nodeIter : arrDepNodeArray){
            int arr = nodeIter.arrival;
            int dep = nodeIter.departure;
            int chair = -1;
            boolean target = nodeIter.target;
            if(busyChair.size() == 0){
                chair = 0;
                busyChair.add(new Node2(dep, chair));
            }else{
                while(busyChair.size() > 0 && busyChair.peek().departure <= arr){
                    Node2 removeNode = busyChair.poll();
                    emptyChair.add(removeNode.chair);
                }
                if(emptyChair.size() > 0){
                    chair = emptyChair.poll();   
                }else{
                    chair = chairNo;
                    chairNo++;
                }
                busyChair.add(new Node2(dep, chair));
            }
            
            if(target){
                return chair;
            }
        }
        return -1;
    }
}


class Node implements Comparable<Node>{

    int arrival;
    int departure;
    boolean target = false;
    
    public Node(int arrival, int departure){
        this.arrival = arrival;
        this.departure = departure;
    }
    
    public int compareTo(Node o){
        return this.arrival - o.arrival;
    }
}

class Node2 implements Comparable<Node2>{

    int departure;
    int chair;
    
    
    public Node2(int departure, int chair){
        this.chair = chair;
        this.departure = departure;
    }
    
    public int compareTo(Node2 o){
        return this.departure - o.departure;
    }
}*/
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        Integer[] order = new Integer[n];
        
        for (int i = 0; i < n; i++) order[i] = i;

        Arrays.sort(order, (a, b) -> Integer.compare(times[a][0], times[b][0]));

        PriorityQueue<Integer> emptySeats = new PriorityQueue<>();
        PriorityQueue<int[]> takenSeats = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < n; i++) emptySeats.offer(i);

        for (int i : order) {
            int arrival = times[i][0], leave = times[i][1];

            while (!takenSeats.isEmpty() && takenSeats.peek()[0] <= arrival) {
                emptySeats.offer(takenSeats.poll()[1]);
            }

            int seat = emptySeats.poll();

            if (i == targetFriend) return seat;

            takenSeats.offer(new int[]{leave, seat});
        }

        return -1;
    }
}