import java.util.ArrayList;
import java.util.List;
List<Event> overLapBookings;
List<Event> bookings;
class Event
{
  int start;
  int end;
  public Event(int start, int end)
  {
    this.start = start;
    this.end = end;
  }  
}

public class MyCalenderII731 {
   /*
   You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.

A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).

The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.

Implement the MyCalendarTwo class:

MyCalendarTwo() Initializes the calendar object.
boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 

Example 1:

Input
["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
Output
[null, true, true, true, false, true, true]

Explanation
MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
myCalendarTwo.book(10, 20); // return True, The event can be booked. 
myCalendarTwo.book(50, 60); // return True, The event can be booked. 
myCalendarTwo.book(10, 40); // return True, The event can be double booked. 
myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 

Constraints:

0 <= start < end <= 109
At most 1000 calls will be made to book.
class MyCalendarTwo {
    Tree root;

    private static class Tree {
        int beg;
        int end;
        int times;
        Tree left;
        Tree right;

        public Tree(int beg, int end) {
            this.beg = beg;
            this.end = end;
            this.times = 1;
        }

        public static boolean query(Tree node, int b, int e) {
            if (node == null) {
                return true;
            }
            if (node.beg >= e) {
                return query(node.left, b, e);
            }
            if (node.end <= b) {
                return query(node.right, b, e);
            }
            if (node.times == 2) {
                return false;
            }
            int l1 = Math.min(b, node.beg);
            int l2 = Math.max(b, node.beg);
            int r1 = Math.min(e, node.end);
            int r2 = Math.max(e, node.end);
            boolean re1 = true;
            if (l1 != l2) {
                re1 = query(node.left, l1, l2);
            }
            boolean re2 = true;
            if (r1 != r2) {
                re2 = query(node.right, r1, r2);
            }

            return re1 && re2;

        }

        public static Tree insert(Tree node, int b, int e) {
            if (node == null) {
                return new Tree(b, e);
            }
            if (node.beg >= e) {
                node.left = insert(node.left, b, e);
                return node;
            }
            if (node.end <= b) {
                node.right = insert(node.right, b, e);
                return node;
            }
            int l1 = Math.min(b, node.beg);
            int l2 = Math.max(b, node.beg);
            int r1 = Math.min(e, node.end);
            int r2 = Math.max(e, node.end);
            node.beg = l2;
            node.end = r1;
            if (l1 != l2) {
                node.left = insert(node.left, l1, l2);
            }
            if (r1 != r2) {
                node.right = insert(node.right, r1, r2);
            }
            node.times++;
            return node;
        }
    }

    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        if (root == null) {
            root = new Tree(start, end);
            return true;
        }
        if (!Tree.query(root, start, end)) {
            return false;
        }
        Tree.insert(root, start, end);
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */


    public MyCalendarTwo() {
        overLapBookings = new ArrayList<>();
        bookings = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        
    for(Event e : overLapBookings)
    {
      if(isOverlap(e, start, end))
      return false;  
    } 

    for(Event e : bookings)
    {
      if(isOverlap(e, start, end))
      {
        overLapBookings.add(getOverlapEvent(e, start, end));
      }    
    }  
       bookings.add(new Event(start, end));

       return true; 
    }

    public boolean isOverlap(Event e, int start, int end)
    {
      return Math.max(e.start, start)<Math.min(e.end, end); 
    }

    public Event getOverlapEvent(Event e, int start, int end)
    {
      return new Event(Math.max(e.start, start), Math.min(e.end, end));  
    }
}
