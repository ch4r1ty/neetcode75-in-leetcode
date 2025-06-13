# 101. Symmetric Tree

## Problem Understanding
Given a binary tree, we need to check if it is symmetric around its center. A tree is symmetric if:
- The left subtree is a mirror reflection of the right subtree
- This means for any two nodes that are symmetric:
  - Their values must be equal
  - The left child of one must mirror the right child of the other
  - The right child of one must mirror the left child of the other

For example:
```
     1
   /   \
  2     2    →  Symmetric
 / \   / \
3   4 4   3

     1
   /   \
  2     2    →  Not Symmetric
   \     \
    3     3
```

## My Approach
I'll use recursion to solve this problem because:
1. We need to compare pairs of nodes that are symmetric
2. The same comparison logic applies at each level
3. The tree structure naturally fits recursive thinking

## Recursion Three Steps

### 1. Parameters and Return Value
- Parameters: 
  - TreeNode left (left subtree node)
  - TreeNode right (right subtree node)
- Return: boolean (whether the subtrees are symmetric)
- Why: We need two parameters to compare symmetric nodes, and we need to return whether they match

### 2. Termination Conditions
We have four cases to handle:
1. If both nodes are null → return true (they match)
2. If one node is null but the other isn't → return false (they don't match)
3. If both nodes have different values → return false (they don't match)
4. If both nodes have the same value → continue checking their children

### 3. Single Level Logic
For each level, we need to:
1. Compare the outer pair (left.left with right.right)
2. Compare the inner pair (left.right with right.left)
3. Both comparisons must be true for the tree to be symmetric

## Time and Space Complexity
- Time Complexity: O(n) where n is the number of nodes
  - We visit each node exactly once
- Space Complexity: O(h) where h is the height of the tree
  - This is the space used by the recursion stack
  - In worst case (skewed tree), it's O(n)
  - In best case (balanced tree), it's O(log n)

## Code Implementation
```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        // Handle null cases
        if (left == null && right != null) return false;
        if (left != null && right == null) return false;
        if (left == null && right == null) return true;
        
        // Compare values
        if (left.val != right.val) return false;
        
        // Compare outer and inner pairs
        boolean outside = compare(left.left, right.right);
        boolean inside = compare(left.right, right.left);
        return outside && inside;
    }
}
```

## Key Points to Remember
1. The comparison is done in pairs:
   - Outer pair: left.left with right.right
   - Inner pair: left.right with right.left

2. Null checks are crucial:
   - We must check for null before accessing node values
   - Different null combinations have different meanings

3. The recursion naturally handles:
   - All levels of the tree
   - All types of nodes (root, internal, leaf)
   - Edge cases (null nodes)

## Common Mistakes to Avoid
1. Not handling all null cases properly
2. Forgetting to check node values
3. Not comparing the correct pairs of nodes
4. Not considering the space complexity of recursion

Would you like me to explain any part in more detail? 

 