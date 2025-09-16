# LeetCode Solutions in Java

A comprehensive collection of LeetCode problem solutions implemented in Java. This repository contains 131+ carefully crafted solutions with detailed explanations and optimized approaches.

## 📊 Repository Statistics

- **Total Solutions**: 131+ problems solved
- **Language**: Java
- **Problem Range**: LeetCode problems from #145 to #3217
- **Solution Types**: Various algorithms and data structures

## 🎯 Problem Categories

### Array & String Manipulation
- [Max Width Ramp](MaxWidthRamp.java) - Finding maximum width ramps in arrays
- [Separate Black and White Balls 2938](SeparateBlackAndWhiteBalls2938.java) - Minimum swaps to group elements
- [Spiral Matrix III 885](SpiralMatrixlll885.java) - Matrix traversal in spiral pattern
- [Convert 1D Array Into 2D Array 2022](Convert1DArrayInto2DArray2022.java) - Array reshaping

### Binary Trees & Linked Lists
- [Linked List in Binary Tree](LinkedListInBinaryTree.java) - Finding paths in binary trees
- [Binary Tree Postorder Traversal 145](BinaryTreePostorderTravesal145.java) - Tree traversal algorithms
- [Cousins in Binary Tree II 2641](CousinsInBinaryTreeII2641.java) - Advanced tree operations
- [Height of Binary Tree After Subtree Removal Queries 2458](HeightOfBinaryTreeAfterSubtreeRemovalQueries2458.java)

### Dynamic Programming
- [Strange Printer 664](StrangePrinter664.java) - Minimum operations to print string
- [Filling Bookcase Shelves 1105](FillingBookcaseShelves1105.java) - Optimal arrangement problem
- [Stone Game II 1140](StoneGamell1140.java) - Game theory with DP

### Graph Algorithms
- [Most Stones Removed with Same Row or Column](mOSTsTONESrEMOVEDwITHsAMErOWoRcOLUMN.java) - Union-Find/DSU approach
- [Count Sub Islands 1905](CountSubIslands1905.java) - Grid traversal and island counting
- [Regions Cut By Slashes 959](RegionsCutBySlashes959.java) - Grid connectivity

### String Processing
- [Shortest Palindrome 214](ShortestPalindrome214.java) - KMP algorithm for palindrome construction
- [Find the Closest Palindrome 564](FindTheClosestPalindrome564.java) - Mathematical palindrome generation
- [Longest Substring with Even Vowel Counts](FindTheLongestSubstringContainingVowelsInEvenCount.java)

### Data Structure Design
- [My Calendar II 731](MyCalenderII731.java) - Interval scheduling with overlap detection
- [Design a Stack with Increment Operation 1381](DesignAStackWithIncrementOperation1381.java)
- [Design Circular Deque 641](DesignCircleDeque641.java)

### Bit Manipulation
- [Count Number of Maximum Bitwise-OR Subsets](CountNumOfMaxBitwiseORSubsets.java)
- [Number Complement 476](NumberComplement476.java)
- [Minimum Bit Flips to Convert Number 2220](MinimumBitFlipsToConvertNumber2220.java)

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)
- Basic understanding of algorithms and data structures

### Running Solutions

1. Clone the repository:
```bash
git clone https://github.com/tanXnyx/leetcode.git
cd leetcode
```

2. Compile and run any solution:
```bash
javac SolutionFile.java
java SolutionFile
```

3. Most solutions include:
   - Problem description in comments
   - Multiple solution approaches where applicable
   - Time and space complexity analysis
   - Example test cases

## 📝 Solution Format

Each solution file typically contains:

```java
public class ProblemName {
    /*
     * Problem Description:
     * [Detailed problem statement from LeetCode]
     * 
     * Example 1:
     * Input: [example input]
     * Output: [example output]
     * 
     * Constraints:
     * [Problem constraints]
     */
    
    public returnType solutionMethod(parameters) {
        // Optimized solution implementation
        // with comments explaining the approach
    }
    
    // Alternative approaches or helper methods
}
```

## 🔍 Key Problem Highlights

### Most Stones Removed (Union-Find/DSU)
- **Approach**: Treat rows and columns as graph nodes
- **Key Insight**: Offset column indices to avoid conflicts
- **Complexity**: O(N log N) time, O(N) space

### Max Width Ramp (Stack-based)
- **Approach**: Monotonic decreasing stack + reverse traversal
- **Optimization**: Binary search variant also included
- **Complexity**: O(N) time, O(N) space

### Shortest Palindrome (KMP Algorithm)
- **Approach**: KMP pattern matching with string reversal
- **Key Insight**: Find longest prefix that's also suffix
- **Complexity**: O(N) time, O(N) space

## 🎯 Problem Difficulty Distribution

- **Easy**: ~30% of solutions
- **Medium**: ~55% of solutions  
- **Hard**: ~15% of solutions

## 🔧 Code Quality Features

- **Clean Code**: Well-formatted and documented
- **Multiple Approaches**: Many problems include alternative solutions
- **Optimization**: Focus on time and space efficiency
- **Edge Cases**: Comprehensive handling of corner cases
- **Comments**: Detailed explanations of algorithms and logic

## 📈 Continuous Updates

This repository is actively maintained with:
- New solutions added regularly
- Code optimizations and improvements
- Better documentation and explanations
- Additional test cases and examples

## 🤝 Contributing

Feel free to:
- Suggest optimizations to existing solutions
- Report bugs or issues
- Add alternative approaches
- Improve documentation

## 📚 Learning Resources

For better understanding of the algorithms used:
- **Union-Find**: Disjoint Set Union data structure
- **Dynamic Programming**: Optimal substructure problems
- **Graph Algorithms**: DFS, BFS, shortest paths
- **String Algorithms**: KMP, rolling hash, palindromes
- **Tree Algorithms**: Traversals, LCA, tree DP

## 📞 Contact

For questions or suggestions, please open an issue in this repository.

---

**Happy Coding!** 🎉

*This repository represents a journey through algorithmic problem-solving and serves as a reference for efficient Java implementations of popular coding interview questions.*
