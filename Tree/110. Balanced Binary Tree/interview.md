# Balanced Binary Tree - Interview Script

## Problem Understanding
"Let me first understand the problem. We need to determine if a binary tree is height-balanced. A height-balanced binary tree is defined as a binary tree where the left and right subtrees of every node differ in height by at most one. This is an important property that we'll use in our solution."

## Solution Approach
"Here's my thought process:
First, we could use a brute force approach by checking the height difference at every node. But this would be inefficient as we'd be calculating heights multiple times.

Instead, we can use a bottom-up approach where we:
1. Calculate the height of each subtree
2. Check if the current node is balanced
3. Return both the height and balance status
4. If any subtree is unbalanced, the whole tree is unbalanced"

## Code Implementation
"Let me implement this approach:

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }
    
    private int checkBalance(TreeNode node) {
        // Base case: empty tree is balanced
        if (node == null) return 0;
        
        // Check left subtree
        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) return -1;
        
        // Check right subtree
        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) return -1;
        
        // Check if current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        // Return height of current node
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```

## Complexity Analysis
"Let me analyze the time and space complexity:

Time Complexity: O(n)
- We visit each node exactly once
- For each node, we perform constant time operations

Space Complexity: O(h)
- Where h is the height of the tree
- This is due to the recursion stack
- In worst case (skewed tree), it's O(n)
- In best case (balanced tree), it's O(log n)"

## Optimization Considerations
"This solution is optimal because:
1. We visit each node only once
2. We combine height calculation and balance checking
3. We use -1 as a sentinel value to indicate imbalance
4. We avoid recalculating heights multiple times"

## Test Cases
"Let's verify the code with these test cases:
1. Empty tree: should return true
2. Single node: should return true
3. Balanced tree: [1,2,2,3,3,3,3] should return true
4. Unbalanced tree: [1,2,2,3,3,null,3] should return false
5. Skewed tree: [1,2,null,3] should return false"

## Real-world Applications
"This problem has practical applications in:
1. AVL trees implementation
2. Database indexing
3. Memory management in operating systems
4. Game development for collision detection"

## Possible Improvements
"If asked about improvements, we could consider:
1. Using iteration instead of recursion to avoid stack overflow
2. Implementing a custom stack for very deep trees
3. Adding early termination if we find any imbalance
4. Using a more memory-efficient approach for very large trees"

## Common Pitfalls
"Some common mistakes to avoid:
1. Not handling null nodes correctly
2. Calculating heights separately from balance checking
3. Not considering the definition of height-balanced properly
4. Forgetting to add 1 when calculating node height"

## Summary
"To summarize, this problem tests:
1. Understanding of tree properties
2. Recursive thinking
3. Space-time complexity analysis
4. Code implementation skills
5. Optimization mindset

My solution uses a bottom-up approach to check balance while calculating heights, achieving O(n) time complexity and O(h) space complexity. The key insight is combining height calculation with balance checking to avoid redundant computations."
