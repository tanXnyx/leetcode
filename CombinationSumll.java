import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]
 

Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]
 

Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
Approach
Sorting the Array: First, we sort the candidates array. Sorting helps in easily skipping duplicates and ensures that we can break out of the loop early when the remaining candidates cannot form the target sum.

Backtracking:

We use a backtracking approach to explore all possible combinations.
Start from the first element and try to include it in the current combination.
If the current element is the same as the previous element and it wasn't included in the previous combination, skip it to avoid duplicates.
Subtract the value of the current element from the target and recursively search for the remaining target in the subarray starting from the next index.
If the target becomes 0, it means we have found a valid combination, so we add it to the result list.
If the target becomes negative or we run out of elements, backtrack by removing the last element from the current combination.
Pruning the Search:

As the array is sorted, if at any point an element is greater than the target, we can break the loop since no further elements can be part of a valid combination (because they will all be greater).
 */
public class CombinationSumll {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List < List < Integer >> ans = new ArrayList < > ();
        Arrays.sort(candidates);
        findCombinations(0, candidates, target, ans, new ArrayList < > ());
        return ans;
    }
    static void findCombinations(int ind, int[] arr, int target, List < List < Integer >> ans, List < Integer > ds) {
        if (target == 0) {
            ans.add(new ArrayList < > (ds));
            return;
        }

        for (int i = ind; i < arr.length; i++) {
            if (i > ind && arr[i] == arr[i - 1]) continue;
            if (arr[i] > target) break;

            ds.add(arr[i]);
            findCombinations(i + 1, arr, target - arr[i], ans, ds);
            ds.remove(ds.size() - 1);
        }
    }
    /*
     * class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return new AbstractList<List<Integer>>() {
            List<List<Integer>> result = null;

            public List<Integer> get(int i) {
                init();
                return result.get(i);
            }

            public int size() {
                init();
                return result.size();
            }

            private void init() {
                if (result != null) return;

                Arrays.sort(candidates);

                // Create a frequency map to count occurrences of each element
                Map<Integer, Integer> freqMap = new HashMap<>();
                for (int i : candidates) {
                    freqMap.put(i, freqMap.getOrDefault(i, 0) + 1);
                }

                // Convert frequency map to a list of arrays [element, frequency]
                List<int[]> freq = new ArrayList<>();
                freqMap.forEach((k, v) -> freq.add(new int[] { k, v }));

                Set<List<Integer>> set = new HashSet<>();
                impl(freq, 0, target, new ArrayList<>(), 0, set);
                result = new ArrayList<>(set);
            }
        };
    }

    private void impl(List<int[]> candidates, int start, int target, List<Integer> list, int sum, Set<List<Integer>> result) {
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.size(); i++) {
            int[] c = candidates.get(i);
            if (c[1] == 0) continue; // Skip if no occurrences left
            if (sum + c[0] > target) continue; // Skip if sum exceeds target

            // Choose the current element
            list.add(c[0]);
            c[1]--; // Decrease the count

            // Recurse with updated parameters
            impl(candidates, i, target, list, sum + c[0], result);

            // Backtrack: remove the current element and restore count
            list.remove(list.size() - 1);
            c[1]++;
        }
    }
}
     */
}
