import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * class Trie{
    class Node{
        boolean eow;
        Node[]children;
        Node(){
            children=new Node[27];
        }
    }
    Node root;
    Trie(){
        root=new Node();
    }
    public void insert(String s){
        Node curr=root;
        for(char ch:s.toCharArray()){
            int idx=ch-'a';
            if(ch=='/')idx=26;
            if(curr.children[idx]==null)curr.children[idx]=new Node();
            curr=curr.children[idx];
        }
        curr=curr.children[26]=new Node();
        curr.eow=true;
    }
    public boolean search(String s){
        Node curr=root;
        for(char ch:s.toCharArray()){
            int idx=ch-'a';
            if(ch=='/')idx=26;
            if(curr.children[idx]==null)return false;
            curr=curr.children[idx];
            if(curr.eow==true)return true;
        }
        return false;
    }
}
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Trie trie=new Trie();
        Arrays.sort(folder,(a,b)->a.length()-b.length());
        List<String>ans=new ArrayList<>();
        for(String s:folder){
            if(!trie.search(s)){
                ans.add(s);
                trie.insert(s);
            }
        }
        return ans;
    }
}
Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.

If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".

The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.

For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
 

Example 1:

Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
Example 2:

Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
Example 3:

Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 

Constraints:

1 <= folder.length <= 4 * 104
2 <= folder[i].length <= 100
folder[i] contains only lowercase letters and '/'.
folder[i] always starts with the character '/'.
Each folder name is unique.
 */
public class RemoveSubFoldersFeomTheFilesystem {
        public List<String> removeSubfolders(String[] folder) {
        // Sort the folders lexicographically so parent folders come before their subfolders
        Arrays.sort(folder);
        
        // Initialize result list with the first folder
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);
        
        // Iterate through remaining folders starting from index 1
        for (int i = 1; i < folder.length; i++) {
            // Get the last added folder path and add a trailing slash
            String lastFolder = ans.get(ans.size() - 1) + "/";
            
            // Check if current folder starts with lastFolder
            // If it doesn't start with lastFolder, then it's not a subfolder
            if (!folder[i].startsWith(lastFolder)) {
                ans.add(folder[i]);
            }
        }
        
        return ans;
    }
}
