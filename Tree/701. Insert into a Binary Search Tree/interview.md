# 701. Insert into a Binary Search Tree

## Problem Analysis

This problem asks us to insert a new value into a Binary Search Tree (BST). The BST property is that for any node, all values in the left subtree are less than the node's value, and all values in the right subtree are greater. We need to return the root of the tree after insertion.

Key insights:
1. We use the BST property to decide whether to go left or right
2. If we reach a null spot, that's where we insert the new value
3. We use recursion to find the correct place to insert

## Recursion Three Steps

### 1. Determine the parameters and return value of the recursive function
- Parameters: current TreeNode root, and the value to insert (int val)
- Return value: TreeNode (the root of the tree after insertion)

### 2. Determine the termination condition
- If root is null, create a new node with the value and return it

### 3. Determine the logic for each recursive level
- If val is greater than root's value, insert into the right subtree
- If val is less than root's value, insert into the left subtree
- Return the root node

## Code Implementation

```java
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            TreeNode res = new TreeNode(val);
            return res;
        }

        if (root.val < val) root.right = insertIntoBST(root.right, val);
        if (root.val > val) root.left = insertIntoBST(root.left, val);
        
        return root;
    }
}
```

## Line-by-Line Explanation

```java
public TreeNode insertIntoBST(TreeNode root, int val) {
```
- Main function that takes the BST root and the value to insert
- Returns the root of the tree after insertion

```java
if (root == null) {
    TreeNode res = new TreeNode(val);
    return res;
}
```
- Base case: if the current node is null, we've found the spot to insert
- Create a new node with the value and return it

```java
if (root.val < val) root.right = insertIntoBST(root.right, val);
```
- If the value to insert is greater than the current node's value, go right
- Recursively insert into the right subtree

```java
if (root.val > val) root.left = insertIntoBST(root.left, val);
```
- If the value to insert is less than the current node's value, go left
- Recursively insert into the left subtree

```java
return root;
```
- Return the root node (unchanged except for the new insertion)

## Time Complexity Analysis

### Time Complexity: O(h)
- **h** = height of the tree
- In the worst case (skewed tree), h = n, so time is O(n)
- In the best case (balanced tree), h = log n, so time is O(log n)

### Space Complexity: O(h)
- Recursion stack depends on the height of the tree
- Worst case: O(n), best case: O(log n)

## Summary

The core idea of this algorithm is:
1. Use the BST property to decide where to insert the new value
2. If we reach a null spot, insert the new node there
3. Use recursion to traverse the tree and return the updated root

The key insight is that BST property lets us quickly find the right spot for insertion.

**Time complexity**: O(h) - h is the height of the tree
**Space complexity**: O(h) - recursion stack depth equals tree height
