/*
 * Intuition
The goal is to compress a given string word such that each sequence of consecutive, identical characters is replaced by the count of that character (up to 9) followed by the character itself.
So we will just follow the steps given in the question.

Approach
Initialize Variables:
Start with an empty string comp to hold the result.
Set cnt (count) to 1, which will keep track of the current sequence of identical characters.
ch is set to the first character in word to start tracking the first character sequence.
Loop Through the String:
Traverse each character in the string word, starting from the second character.

If the current character matches ch (indicating it is part of the current sequence of identical characters), increment cnt.

If cnt reaches 9, reset it back to 1 for the next sequence, as we are limiting the maximum count per character to 9.
3)Update comp on Character Change:

When the current character is different from ch, append the count cnt and the character ch to comp.

Reset ch to the current character and cnt to 1 to start tracking the new sequence of characters.

Finalize the Result:
After the loop, append the last sequence count and character to comp to capture the final sequence.
Complexity
Time complexity:
O(n) -> for traversing the string

Space complexity:
O(n) -> to store the "comp" string
class Solution {
    public String compressedString(String word) {
        StringBuilder sb=new StringBuilder();
        int count=0,j=0;
        for(int i=0;i<word.length();++i){
            j=i;
            char c=word.charAt(i);
            while(j<word.length() && word.charAt(j)==c){
                ++count;
                ++j;
            }
            while(count>9){
                sb.append('9');
                sb.append(c);
                count-=9;

            }
            sb.append((char)(count+'0'));
            sb.append(c);
            count=0;
            i=j-1;

        }
        return sb.toString();
    }
}
 * 
 */
public class StringCompressionIII {
    public String compressedString(String word) {
        StringBuilder comp = new StringBuilder();
        int cnt = 1, n = word.length();
        char ch = word.charAt(0);
        for (int i = 1; i < n; i++) {
            if (word.charAt(i) == ch && cnt < 9) {
                cnt++;
            } else {
                comp.append(cnt).append(ch);
                ch = word.charAt(i);
                cnt = 1;
            }
        }
        comp.append(cnt).append(ch);
        return comp.toString();
    }
}

