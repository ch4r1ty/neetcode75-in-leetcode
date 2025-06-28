# 700. Search in a Binary Search Tree

## Problem Analysis

This problem asks us to search for a specific value in a Binary Search Tree (BST) and return the subtree rooted at that node. If the value doesn't exist, we return null.

The key insights are:
1. BST has a special property: left subtree values < root value < right subtree values
2. We can use this property to eliminate half of the tree at each step
3. If the target value is less than current node, search left subtree
4. If the target value is greater than current node, search right subtree
5. If we find the value, return that node

## Recursion Three Steps

### 1. Determine the parameters and return value of the recursive function
- Parameters: current TreeNode root and target value (int val)
- Return value: TreeNode (either the found node or null)

### 2. Determine the termination condition
- If root is null, return null (value not found)
- If root.val equals target value, return root (value found)

### 3. Determine the logic for each recursive level
- Compare current node value with target value
- If target is smaller, search left subtree
- If target is larger, search right subtree

## Code Implementation

```java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        // When root == null, root's value is null
        if (root == null) return root;
        if (root.val == val) return root;

        if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }
}
```

## Line-by-Line Explanation

```java
public TreeNode searchBST(TreeNode root, int val) {
```
- Main function that takes a BST root and target value as parameters
- Returns the node containing the target value, or null if not found

```java
if (root == null) return root;
```
- First termination condition: if we reach a null node, the value doesn't exist
- Return null (which is what root is when it's null)
- This handles the case where we've searched to the end of a branch

```java
if (root.val == val) return root;
```
- Second termination condition: if we find the target value, return this node
- This is our success case - we found what we're looking for
- Return the entire subtree rooted at this node

```java
if (root.val > val) {
```
- If current node's value is greater than target, the target must be in the left subtree
- This uses the BST property: all values in left subtree are smaller than root

```java
return searchBST(root.left, val);
```
- Recursively search the left subtree
- This eliminates the entire right subtree from consideration
- This is the key to BST efficiency - we cut the search space in half

```java
} else {
```
- If current node's value is less than or equal to target, search right subtree
- Note: we could also write `else if (root.val < val)` for clarity

```java
return searchBST(root.right, val);
```
- Recursively search the right subtree
- This eliminates the entire left subtree from consideration
- Again, we're cutting the search space in half

## Time Complexity Analysis

### Time Complexity: O(h) where h is the height of the tree
- **Best Case**: O(1) - target is at the root
- **Average Case**: O(log n) - for a balanced BST
- **Worst Case**: O(n) - for a skewed tree (like a linked list)

**Why O(h)?**

1. **Balanced BST**: Height is log(n), so time complexity is O(log n)
2. **Unbalanced BST**: Height could be n, so time complexity is O(n)
3. **Key Insight**: We eliminate half the tree at each step in a balanced BST

### Space Complexity: O(h)
- **Recursion Stack**: Each recursive call uses stack space
- **Height**: Space complexity equals the height of the tree
- **Best Case**: O(log n) for balanced BST
- **Worst Case**: O(n) for skewed tree

### Examples:

**Example 1**: Balanced BST
- Tree with 7 nodes, height 2
- Time: O(log 7) ≈ O(2)
- Space: O(2)

**Example 2**: Skewed BST (linked list)
- Tree with 1000 nodes, height 1000
- Time: O(1000) = O(n)
- Space: O(1000) = O(n)

## Summary

The core idea of this algorithm is:
1. Use BST property to eliminate half the tree at each step
2. If target is smaller than current node, search left
3. If target is larger than current node, search right
4. If target equals current node, we found it

The key insight is leveraging the BST property - this makes the search much more efficient than searching in a regular binary tree.

**Time complexity**: O(h) where h is tree height - O(log n) for balanced BST, O(n) for skewed BST
**Space complexity**: O(h) - recursion stack depth equals tree height
