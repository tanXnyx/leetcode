import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*A sentence is a string of single-space separated words where each word consists only of lowercase letters.

A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Given two sentences s1 and s2, return a list of all the uncommon words. You may return the answer in any order.

 

Example 1:

Input: s1 = "this apple is sweet", s2 = "this apple is sour"

Output: ["sweet","sour"]

Explanation:

The word "sweet" appears only in s1, while the word "sour" appears only in s2.

Example 2:

Input: s1 = "apple apple", s2 = "banana"

Output: ["banana"]

 

Constraints:

1 <= s1.length, s2.length <= 200
s1 and s2 consist of lowercase English letters and spaces.
s1 and s2 do not have leading or trailing spaces.
All the words in s1 and s2 are separated by a single space. */
public class UncommonWordsFromTwoSentences {
     public String[] uncommonFromSentences(String s1, String s2) {
        List<String> output = new ArrayList<>(); 
        HashMap<String, Integer> map = new HashMap<>();
        String s1Str[] = s1.split(" ");
        // for(int i = 0; i < s1.length(); ++i) {
        //         map.put(s1Str[i], map.getOrDefault(s1Str[i], 0 ) + 1);
        // }
        for(String word: s1Str) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        String s2Str[] = s2.split(" ");
         // Count occurrences of words from the second sentence
        for (String word : s2Str) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // Add words that appear only once to the output list
        for (String word : map.keySet()) {
            if (map.get(word) == 1) {
                output.add(word);
            }
        }

        // covert list to a string array
        return output.toArray(new String[0]);
        //     for(int i = 0; i < s2.length(); ++i) {
        //         if(map.containsKey(s2Str[i]) && map.get(s2Str[i]) == 1) {
        //             output.add(s2Str[i]);
        //         }
        //     }
        //     String[] stringArr = output.toArray(new String[0]);
        //     return stringArr;
        // }
    }
}
