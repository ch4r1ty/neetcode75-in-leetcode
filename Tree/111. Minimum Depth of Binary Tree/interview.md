# 111. Minimum Depth of Binary Tree

## Problem Understanding
Let me explain what we need to do. We're given a binary tree, and we need to find its minimum depth. The minimum depth is the length of the shortest path from the root node to the nearest leaf node.

Let me show you an example:
```
     3
   /   \
  9     20
       /  \
      15   7
```

Note: A leaf node is a node with no children (both left and right children are null). This is important because:
- A node with only one child is NOT considered a leaf node
- We must find the shortest path to a true leaf node
- This makes the problem different from just finding the minimum of left and right depths

For example, in this tree:

In this tree:
- The path from root to leaf node 9 has length 2
- The path from root to leaf node 15 has length 3
- The path from root to leaf node 7 has length 3
So the minimum depth is 2.


## My Approach
I'll use recursion to solve this problem because:
1. The depth of a tree depends on its subtrees
2. We need to handle special cases carefully
3. The tree structure naturally fits recursive thinking

## Recursion Three Steps

### 1. Parameters and Return Value
- Parameter: TreeNode root (the current node we're processing)
- Return: int (the minimum depth of the current subtree)
- Why: We need to return the depth because we're building up the answer from the bottom

### 2. Termination Condition
- When root is null, return 0
- Why: An empty tree has depth 0
- This is our base case to stop the recursion

### 3. Single Level Logic
For each node, we need to handle several cases:
1. If it's a leaf node (both children are null) → return 1
2. If left child is null → return right subtree depth + 1
3. If right child is null → return left subtree depth + 1
4. If both children exist → return minimum of left and right depths + 1

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
    public int minDepth(TreeNode root) {
        // Base case: empty tree has depth 0
        if (root == null) return 0;

        // Get depths of left and right subtrees
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);

        // Handle special cases
        if (root.left == null && root.right == null) {
            return 1;  // Leaf node
        }
        if (root.left == null) {
            return rightDepth + 1;  // Only right subtree exists
        }
        if (root.right == null) {
            return leftDepth + 1;   // Only left subtree exists
        }

        // Both subtrees exist, take minimum
        return Math.min(rightDepth, leftDepth) + 1;
    }
}
```

## Key Points to Remember
1. The minimum depth is different from maximum depth because:
   - We need to find the shortest path to a leaf
   - We must handle nodes with only one child carefully
   - A leaf node is a node with no children

2. We need to handle these special cases:
   - Empty tree (null root)
   - Leaf nodes (no children)
   - Nodes with only one child
   - Nodes with both children

3. The recursion naturally handles:
   - All levels of the tree
   - All types of nodes (root, internal, leaf)
   - Edge cases (null nodes)

## Common Mistakes to Avoid
1. Not handling nodes with only one child correctly
2. Treating nodes with one child as leaf nodes
3. Not considering the case where a subtree is null
4. Not adding 1 for the current node

Would you like me to explain any part in more detail?
