# 226. Invert Binary Tree

## Problem Understanding
Given a binary tree, we need to invert it. Inverting a binary tree means:
- The left subtree becomes the right subtree
- The right subtree becomes the left subtree
- This should be done for every node in the tree

For example:
```
     4                 4
   /   \             /   \
  2     7    →      7     2
 / \   / \         / \   / \
1   3 6   9       9   6 3   1
```

## My Approach
I'll use recursion to solve this problem because:
1. The same operation needs to be performed on each node
2. The structure of the tree naturally fits recursive thinking
3. It's easier to understand and implement

## Recursion Three Steps

### 1. Parameters and Return Value
- Parameter: TreeNode root (the current node we're processing)
- Return: TreeNode (the root of the inverted subtree)
- Why: We need to return the root because we're building the tree from bottom to top

### 2. Termination Condition
- When root is null, return null
- Why: This handles empty trees and leaf nodes
- It's our base case to stop the recursion

### 3. Single Level Logic
I'll use pre-order traversal (root -> left -> right) because:
1. It's more intuitive to swap the children first before recursing
2. We can verify the swap worked before moving deeper
3. The logic flows naturally from top to bottom

The specific steps are:
- First, swap the left and right children of the current node (root node processing)
- Then, recursively invert the left subtree (left child processing) 
- Finally, recursively invert the right subtree (right child processing)
- Return the current node

This pre-order traversal approach means:
1. Process the current node first by swapping its children
2. Then process its left subtree
3. Finally process its right subtree

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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;  // termination condition

        // pre-order traversal
        swapChildren(root);             // process current node
        root.left = invertTree(root.left);   // process left subtree
        root.right = invertTree(root.right); // process right subtree
        return root;
    }

    public void swapChildren(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
```

## Key Points to Remember
1. We can use any traversal order (pre-order, in-order, or post-order)
   - I chose pre-order because it's more intuitive
   - We swap children before processing subtrees

2. The swap operation is simple:
   - Store left child in temp
   - Set left child to right child
   - Set right child to temp

3. The recursion naturally handles:
   - All levels of the tree
   - All types of nodes (root, internal, leaf)
   - Edge cases (null nodes)

## Common Mistakes to Avoid
1. Forgetting to handle null nodes
2. Not returning the root node
3. Trying to swap children after processing subtrees
4. Not considering the space complexity of recursion

Would you like me to explain any part in more detail? 