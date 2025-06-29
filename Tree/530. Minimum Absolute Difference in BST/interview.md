# 530. Minimum Absolute Difference in BST

## Problem Analysis

This problem asks us to find the minimum absolute difference between any two nodes in a Binary Search Tree (BST). Since BST has the property that inorder traversal visits nodes in ascending order, we can think of this as finding the minimum difference between adjacent elements in a sorted array.

The key insights are:
1. BST inorder traversal gives us nodes in sorted order
2. The minimum difference must be between adjacent nodes in this sorted sequence
3. We can use a global variable to track the previous node and current minimum difference
4. As we traverse, we compare each node with the previous one and update the minimum

## Recursion Three Steps

### 1. Determine the parameters and return value of the recursive function
- Parameters: current TreeNode root
- Return value: void (we use global variables to track results)

### 2. Determine the termination condition
- If root is null, return (base case)

### 3. Determine the logic for each recursive level
- Recursively traverse left subtree (inorder: left first)
- Compare current node with previous node and update minimum difference
- Update the previous node to current node
- Recursively traverse right subtree (inorder: right last)

## Code Implementation

```java
class Solution {
    // When dealing with BST problems involving finding minimum/maximum values or differences,
    // think of it as finding these in a sorted array. BST with inorder traversal is essentially a sorted array.
    private TreeNode pre;
    private int minVal = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        recursion(root);
        return minVal;
    }

    private void recursion(TreeNode root) {
        if (root == null)
            return;

        recursion(root.left);
        if (pre != null) {
            if (root.val - pre.val < minVal) {
                minVal = root.val - pre.val;
            }
        }
        // The key insight here is using pre to save the previous node in traversal.
        // Note the position of pre = root - it's in the middle of left-root-right (root) position.
        // After we process the current node, we need to set current node as "previous node" for the next node to use.
        pre = root;
        recursion(root.right);
    }
}
```

## Line-by-Line Explanation

```java
private TreeNode pre;
```
- Global variable to keep track of the previous node visited in inorder traversal
- This allows us to compare current node with the previous one

```java
private int minVal = Integer.MAX_VALUE;
```
- Global variable to store the minimum difference found so far
- Initialized to maximum integer value so any real difference will be smaller

```java
public int getMinimumDifference(TreeNode root) {
```
- Main function that takes a BST root as parameter
- Returns the minimum absolute difference between any two nodes

```java
recursion(root);
return minVal;
```
- Call the recursive function to traverse the tree
- Return the minimum difference found during traversal

```java
private void recursion(TreeNode root) {
```
- Recursive helper function that performs inorder traversal
- Uses global variables to track state instead of returning values

```java
if (root == null)
    return;
```
- Termination condition: if we reach a null node, return
- This handles the base case of recursion

```java
recursion(root.left);
```
- Recursively traverse the left subtree first
- This follows inorder traversal: left -> root -> right
- All nodes in left subtree will be visited before current node

```java
if (pre != null) {
```
- Check if we have a previous node to compare with
- pre is null for the first node visited

```java
if (root.val - pre.val < minVal) {
    minVal = root.val - pre.val;
}
```
- Compare current node value with previous node value
- Since inorder traversal visits nodes in ascending order, root.val > pre.val
- Update minimum difference if current difference is smaller
- This is the core logic for finding minimum difference

```java
pre = root;
```
- **Key line**: Update pre to current node
- This must be done after processing current node but before visiting right subtree
- Ensures that next node (from right subtree) can compare with current node
- Position is crucial - it's in the "root" part of left-root-right traversal

```java
recursion(root.right);
```
- Recursively traverse the right subtree last
- This completes the inorder traversal pattern
- All nodes in right subtree will be visited after current node

## Time Complexity Analysis

### Time Complexity: O(n)
- **n** = number of nodes in the tree
- We visit each node exactly once during inorder traversal
- Each node requires constant time operations (comparison, update)

**Why O(n)?**

1. **Complete Traversal**: We must visit every node to find the minimum difference
2. **Constant Work per Node**: Each node only requires a few comparisons and updates
3. **Inorder Traversal**: Naturally visits all nodes in sorted order

### Space Complexity: O(h) where h is the height of the tree
- **Best Case**: O(log n) for balanced tree
- **Worst Case**: O(n) for skewed tree (like a linked list)

**Why O(h)?**

1. **Recursion Stack**: Each recursive call uses stack space
2. **Height**: Space complexity equals the height of the tree
3. **Global Variables**: pre and minVal use constant space

### Examples:

**Example 1**: Balanced BST
- Tree with 7 nodes, height 2
- Time: O(7) = O(n)
- Space: O(2) = O(log n)

**Example 2**: Skewed BST
- Tree with 1000 nodes, height 1000
- Time: O(1000) = O(n)
- Space: O(1000) = O(n)

## Summary

The core idea of this algorithm is:
1. Use inorder traversal to visit nodes in sorted order
2. Keep track of the previous node visited
3. Compare each node with the previous one to find minimum difference
4. Update the minimum difference whenever we find a smaller one

The key insight is leveraging BST's inorder traversal property - it gives us nodes in ascending order, making it easy to find adjacent differences.

**Time complexity**: O(n) - we must visit every node
**Space complexity**: O(h) - recursion stack depth equals tree height

