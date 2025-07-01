# 236. Lowest Common Ancestor of a Binary Tree

## Problem Analysis

This problem asks us to find the lowest common ancestor (LCA) of two nodes in a binary tree. The LCA is the lowest node in the tree that has both nodes as descendants (a node can be a descendant of itself).

Key insights:
1. If the current node is either p or q, then the current node could be the LCA
2. If p and q are found in different subtrees, the current node is their LCA
3. If both are found in the same subtree, the LCA is deeper in that subtree
4. We can use postorder traversal (left, right, root) to solve this

## Recursion Three Steps

### 1. Determine the parameters and return value of the recursive function
- Parameters: current TreeNode root, and the two target nodes p and q
- Return value: TreeNode (the LCA if found, otherwise null)

### 2. Determine the termination condition
- If root is null, return null
- If root is p or q, return root

### 3. Determine the logic for each recursive level
- Recursively search left and right subtrees for p and q
- If both left and right return non-null, current node is the LCA
- If only one side returns non-null, return that side
- If both sides return null, return null

## Code Implementation

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        // Postorder traversal
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        } else if (left != null && right == null) {
            return left;
        } else if(left == null && right != null) {
            return right;
        } else {
            return root;
        }
    }
}
```

## Line-by-Line Explanation

```java
if (root == null || root == p || root == q) {
    return root;
}
```
- Base case: if the current node is null, or is p or q, return it
- If we find either p or q, we return it up the call stack

```java
TreeNode left = lowestCommonAncestor(root.left, p, q);
TreeNode right = lowestCommonAncestor(root.right, p, q);
```
- Recursively search for p and q in the left and right subtrees
- This is postorder: we process children before the current node

```java
if (left == null && right == null) {
    return null;
}
```
- If neither subtree contains p or q, return null

```java
else if (left != null && right == null) {
    return left;
}
```
- If only the left subtree contains p or q, return the result from the left

```java
else if(left == null && right != null) {
    return right;
}
```
- If only the right subtree contains p or q, return the result from the right

```java
else {
    return root;
}
```
- If both left and right are non-null, it means p and q are found in different subtrees
- So the current node is their lowest common ancestor

## Time Complexity Analysis

### Time Complexity: O(n)
- **n** = number of nodes in the tree
- We visit each node at most once

### Space Complexity: O(h)
- **h** = height of the tree (recursion stack)
- In the worst case, the tree is skewed (height n), so space is O(n)

## Summary

The core idea of this algorithm is:
1. Use postorder traversal to search for p and q in the tree
2. If both are found in different subtrees, the current node is their LCA
3. If only one is found, return that one up the call stack
4. If the current node is p or q, return it

The key insight is that the first node where p and q split into different subtrees is their lowest common ancestor.

**Time complexity**: O(n) - we must visit every node
**Space complexity**: O(h) - recursion stack depth equals tree height
