import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

public class NaryTreePostorderTraversal {
     public List<Integer> postorder(Node root) {
        ArrayList<Integer> a=new ArrayList<>();
        postorder(root,a);
        return a;
    }
    void postorder(Node root,ArrayList<Integer> a){
        if(root!=null){
            for(int i=0;i<root.children.size();i++){
                postorder(root.children.get(i),a);
            }
        a.add(root.val);
        }
    }
}
