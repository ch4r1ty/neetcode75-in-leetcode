# 617. Merge Two Binary Trees

## Problem Analysis

This problem asks us to merge two binary trees by adding their corresponding node values. When both trees have nodes at the same position, we add their values together. If one tree has a node where the other doesn't, we keep the existing node.

The key insights are:
1. We need to traverse both trees simultaneously
2. When both nodes exist, add their values
3. When one node is null, return the other node
4. Recursively merge the left and right subtrees

## Recursion Three Steps

### 1. Determine the parameters and return value of the recursive function
- Parameters: two TreeNode roots (root1 and root2)
- Return value: the merged TreeNode

### 2. Determine the termination condition
- If root1 is null, return root2
- If root2 is null, return root1

### 3. Determine the logic for each recursive level
- Add the values of both root nodes
- Recursively merge the left subtrees
- Recursively merge the right subtrees
- Return the modified root1

## Code Implementation

```java
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // Termination condition! This is the most crucial part of this problem
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
}
```

## Line-by-Line Explanation

```java
public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
```
- Main function that takes two binary tree roots as parameters
- We'll modify root1 and return it as the merged result

```java
if (root1 == null) return root2;
```
- First termination condition: if root1 is null, we can't add anything to it
- So we just return root2 (which might be null or a valid tree)
- This handles the case where one tree is empty

```java
if (root2 == null) return root1;
```
- Second termination condition: if root2 is null, there's nothing to add
- So we return root1 as is
- This handles the case where the second tree is empty

```java
root1.val += root2.val;
```
- If both nodes exist, add their values together
- We're modifying root1's value by adding root2's value to it
- This is the core merging logic

```java
root1.left = mergeTrees(root1.left, root2.left);
```
- Recursively merge the left subtrees
- The result becomes the new left child of root1
- This handles all the left subtree merging

```java
root1.right = mergeTrees(root1.right, root2.right);
```
- Recursively merge the right subtrees
- The result becomes the new right child of root1
- This handles all the right subtree merging

```java
return root1;
```
- Return the modified root1, which now contains the merged tree
- All the merging has been done in-place on root1

## Summary

The core idea of this algorithm is:
1. Handle null cases first - if either tree is null, return the other
2. When both nodes exist, add their values together
3. Recursively merge the left and right subtrees
4. Return the modified first tree

The key insight is in the termination conditions - they handle all the edge cases where one tree might be shorter than the other.

Time complexity is O(min(n1, n2)) where n1 and n2 are the number of nodes in the two trees, because we only process nodes that exist in both trees. Space complexity is O(min(h1, h2)) where h1 and h2 are the heights of the two trees, due to the recursion stack.
