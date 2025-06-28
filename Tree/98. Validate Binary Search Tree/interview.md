# 98. Validate Binary Search Tree

## Problem Analysis

This problem asks us to check if a binary tree is a valid Binary Search Tree (BST). A BST must satisfy the property that for every node, all values in its left subtree are less than the node's value, and all values in its right subtree are greater than the node's value.

The key insights are:
1. We need to traverse the tree and check the BST property at each node
2. We can use inorder traversal (left -> root -> right) to visit nodes in ascending order
3. In a valid BST, inorder traversal should visit nodes in strictly increasing order
4. If we find any node that breaks this increasing order, the tree is not a valid BST

## Recursion Three Steps

### 1. Determine the parameters and return value of the recursive function
- Parameters: current TreeNode root
- Return value: boolean (true if valid BST, false otherwise)

### 2. Determine the termination condition
- If root is null, return true (empty tree is considered valid)

### 3. Determine the logic for each recursive level
- Recursively check left subtree
- Check if current node value is greater than the maximum value seen so far
- Update the maximum value if current node is valid
- Recursively check right subtree
- Return true only if all checks pass

## Code Implementation

```java
class Solution {
    private long maxVal = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        // left
        boolean left = isValidBST(root.left);
        if (left == false) return false;
        // mid
        if (root.val > maxVal) {
            maxVal = root.val;
        } else {
            return false;
        }
        // right
        boolean right = isValidBST(root.right);
        if (right == false) return false;

        // After searching the entire tree, if no node violates BST property, return true
        return true;
    }
}
```

## Line-by-Line Explanation

```java
private long maxVal = Long.MIN_VALUE;
```
- Global variable to keep track of the maximum value seen so far
- Using long instead of int to handle edge cases with Integer.MIN_VALUE
- Initialized to Long.MIN_VALUE to handle the first node

```java
public boolean isValidBST(TreeNode root) {
```
- Main function that takes a tree root as parameter
- Returns true if the tree is a valid BST, false otherwise

```java
if (root == null) return true;
```
- Termination condition: if we reach a null node, return true
- Empty tree is considered a valid BST
- This handles the base case of recursion

```java
boolean left = isValidBST(root.left);
```
- Recursively check the left subtree
- This follows inorder traversal: left subtree first
- Store the result to check if left subtree is valid

```java
if (left == false) return false;
```
- If left subtree is not valid, the entire tree is not valid
- Early termination: no need to check further
- This is an optimization to avoid unnecessary computation

```java
if (root.val > maxVal) {
```
- Check if current node's value is greater than the maximum value seen so far
- In a valid BST, inorder traversal should visit nodes in strictly increasing order
- This is the key validation step

```java
maxVal = root.val;
```
- If current node is valid, update the maximum value
- This ensures we maintain the increasing order requirement
- Next node must be greater than this value

```java
} else {
    return false;
}
```
- If current node's value is not greater than maxVal, BST property is violated
- Return false immediately
- This catches any violation of the increasing order requirement

```java
boolean right = isValidBST(root.right);
```
- Recursively check the right subtree
- This follows inorder traversal: right subtree last
- Store the result to check if right subtree is valid

```java
if (right == false) return false;
```
- If right subtree is not valid, the entire tree is not valid
- Early termination: no need to check further
- Another optimization to avoid unnecessary computation

```java
return true;
```
- If we reach here, all checks have passed
- Left subtree is valid, current node is valid, right subtree is valid
- The entire tree is a valid BST

## Time Complexity Analysis

### Time Complexity: O(n)
- **n** = number of nodes in the tree
- We visit each node exactly once
- Each node requires constant time operations (comparison, update)

**Why O(n)?**

1. **Complete Traversal**: We must visit every node to validate the entire tree
2. **Constant Work per Node**: Each node only requires a few comparisons and updates
3. **No Redundant Work**: Early termination prevents unnecessary computation

### Space Complexity: O(h) where h is the height of the tree
- **Best Case**: O(log n) for balanced tree
- **Worst Case**: O(n) for skewed tree (like a linked list)

**Why O(h)?**

1. **Recursion Stack**: Each recursive call uses stack space
2. **Height**: Space complexity equals the height of the tree
3. **Global Variable**: maxVal uses constant space

### Examples:

**Example 1**: Valid BST
- Tree with 5 nodes, height 2
- Time: O(5) = O(n)
- Space: O(2) = O(log n)

**Example 2**: Invalid BST
- Tree with 7 nodes, height 3
- Time: O(7) = O(n) (might terminate early)
- Space: O(3) = O(log n)

**Example 3**: Skewed Invalid BST
- Tree with 1000 nodes, height 1000
- Time: O(1000) = O(n)
- Space: O(1000) = O(n)

## Summary

The core idea of this algorithm is:
1. Use inorder traversal to visit nodes in the order they should appear in a BST
2. Keep track of the maximum value seen so far
3. Ensure each new node's value is greater than the previous maximum
4. If any node violates this rule, the tree is not a valid BST

The key insight is using inorder traversal - in a valid BST, inorder traversal visits nodes in strictly increasing order.

**Time complexity**: O(n) - we must visit every node
**Space complexity**: O(h) - recursion stack depth equals tree height
