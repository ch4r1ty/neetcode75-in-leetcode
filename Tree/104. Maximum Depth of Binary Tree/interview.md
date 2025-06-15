# 104. Maximum Depth of Binary Tree

## Problem Understanding
Let me explain what we need to do. We're given a binary tree, and we need to find its maximum depth. The maximum depth is the length of the longest path from the root node to the farthest leaf node.

Let me show you an example:
```
     3
   /   \
  9     20
       /  \
      15   7
```
In this tree:
- The path from root to leaf node 9 has length 1
- The path from root to leaf node 15 has length 2
- The path from root to leaf node 7 has length 2
So the maximum depth is 2.

## My Approach
I'll use recursion to solve this problem because:
1. The depth of a tree is the maximum of its left and right subtree depths, plus one
2. This pattern repeats for every node in the tree
3. The tree structure naturally fits recursive thinking

## Recursion Three Steps

### 1. Parameters and Return Value
- Parameter: TreeNode root (the current node we're processing)
- Return: int (the depth of the current subtree)
- Why: We need to return the depth because we're building up the answer from the bottom

### 2. Termination Condition
- When root is null, return 0
- Why: An empty tree has depth 0
- This is our base case to stop the recursion

### 3. Single Level Logic
For each node, we need to:
1. Get the depth of the left subtree
2. Get the depth of the right subtree
3. Take the maximum of these two depths
4. Add 1 to account for the current node
5. Return this value

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
    public int maxDepth(TreeNode root) {
        // Base case: empty tree has depth 0
        if (root == null) return 0;
        
        // Get depths of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        // Return the maximum depth plus one for current node
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
```

## Key Points to Remember
1. The depth of a tree is:
   - The maximum of its left and right subtree depths
   - Plus one for the current node

2. We use post-order traversal because:
   - We need to know the depths of both subtrees
   - Before we can calculate the current node's depth

3. The recursion naturally handles:
   - All levels of the tree
   - All types of nodes (root, internal, leaf)
   - Edge cases (null nodes)

## Common Mistakes to Avoid
1. Forgetting to add 1 for the current node
2. Not handling null nodes properly
3. Not taking the maximum of left and right depths
4. Not considering the space complexity of recursion

Would you like me to explain any part in more detail? 