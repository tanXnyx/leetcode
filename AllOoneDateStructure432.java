import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
/*
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

Implement the AllOne class:

AllOne() Initializes the object of the data structure.
inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
Note that each function must run in O(1) average time complexity.

 

Example 1:

Input
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
Output
[null, null, null, "hello", "hello", null, "hello", "leet"]

Explanation
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "hello"
allOne.inc("leet");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "leet"
 

Constraints:

1 <= key.length <= 10
key consists of lowercase English letters.
It is guaranteed that for each call to dec, key is existing in the data structure.
At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
public class Node {
    String key;
    int count;
    Node next;
    Node prev;

    public Node(String s, int n) {
        count = n;
        key = s;
        next = null;
        prev = null;
    }
}
class AllOne {
    private HashMap<String, Node> nodes = new HashMap();
    Node head;
    Node tail;

    public AllOne() {
        head = new Node("", -1);
        tail = new Node("", Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }

    //b is directly after a
    private void swap(Node a, Node b) {
        Node t = b.next;
        a.next = t;
        t.prev = a;
        b.next = a;
        t = a.prev;
        t.next = b;
        a.prev = b;
        b.prev = t;
    }

    private void delete(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    private void updateInc(Node n) {
        while (n.next != tail && n.count > n.next.count) {
            swap(n, n.next);
        }
    }

    private void updateDec(Node n) {
        while (n.prev != head && n.count < n.prev.count) {
            swap(n.prev, n);
        }
    }
    
    public void inc(String key) {
        Node n = nodes.getOrDefault(key, new Node(key, 0));
        int c = ++n.count;
        //if not new key
        if(c != 1){
            if(c > tail.prev.count){
                delete(n);
                n.next = tail;
                n.prev = tail.prev;
                tail.prev.next = n;
                tail.prev = n;
            }else{
                updateInc(n);
            }
        }else { //new key
            nodes.put(key,n);
            n.prev = head;
            n.next = head.next;
            head.next.prev = n;
            head.next = n;
        }
    }
    
    public void dec(String key) {
        Node n = nodes.get(key);
        if (--n.count == 0) {
            nodes.remove(key);
            delete(n);
        } else {
            updateDec(n);
        }
    }
    
    public String getMaxKey() {
        return head.next == tail ? "" : tail.prev.key;
    }
    
    public String getMinKey() {
        return head.next == tail ? "" : head.next.key;
    }
}

 */
public class AllOoneDateStructure432 {
     private Map<String, Integer> count;
    private TreeSet<Pair<Integer, String>> set;

    public AllOne() {
        count = new HashMap<>();
        set = new TreeSet<>((a, b) -> a.getKey().equals(b.getKey()) ? a.getValue().compareTo(b.getValue()) : a.getKey() - b.getKey());
    }

    public void inc(String key) {
        int n = count.getOrDefault(key, 0);
        count.put(key, n + 1);
        set.remove(new Pair<>(n, key));
        set.add(new Pair<>(n + 1, key));
    }

    public void dec(String key) {
        int n = count.get(key);
        set.remove(new Pair<>(n, key));
        if (n == 1) count.remove(key);
        else {
            count.put(key, n - 1);
            set.add(new Pair<>(n - 1, key));
        }
    }

    public String getMaxKey() {
        return set.isEmpty() ? "" : set.last().getValue();
    }

    public String getMinKey() {
        return set.isEmpty() ? "" : set.first().getValue();
    }
}
/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */