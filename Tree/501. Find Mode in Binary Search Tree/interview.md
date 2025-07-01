# 501. Find Mode in Binary Search Tree

## Problem Analysis

This problem asks us to find the mode(s) in a Binary Search Tree (BST). The mode is the value that appears most frequently. Since BST's inorder traversal gives us values in sorted order, we can easily count duplicates as we traverse.

Key insights:
1. Inorder traversal of BST gives values in non-decreasing order
2. We can count consecutive duplicates as we traverse
3. We keep track of the current count, the maximum count, and the previous node
4. If we find a new max count, we clear the result list and add the new value
5. If the count equals the max, we add the value to the result list

## Recursion Three Steps

### 1. Determine the parameters and return value of the recursive function
- Parameters: current TreeNode root
- Return value: void (we use global variables to track results)

### 2. Determine the termination condition
- If root is null, return (base case)

### 3. Determine the logic for each recursive level
- Recursively traverse left subtree (inorder: left first)
- Compare current node with previous node, update count
- If count > maxCount, clear result list and add value
- If count == maxCount, add value to result list
- Update previous node
- Recursively traverse right subtree (inorder: right last)

## Code Implementation

```java
class Solution {
    ArrayList<Integer> resList;
    int maxCount;
    int count;
    TreeNode pre;

    public int[] findMode(TreeNode root) {
        resList = new ArrayList<>();
        maxCount = 0;
        count = 0;
        pre = null;
        traversal(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    private void traversal(TreeNode root) {
        if (root == null) return;

        traversal(root.left);

        int rootValue = root.val;
        // Counting logic
        if (pre == null || rootValue != pre.val) {
            count = 1;
        } else {
            count++;
        }

        if (count > maxCount) {
            resList.clear();
            resList.add(rootValue);
            maxCount = count;
        } else if (count == maxCount) {
            resList.add(rootValue);
        }

        pre = root;

        traversal(root.right);
    }
}
```

## Line-by-Line Explanation

```java
ArrayList<Integer> resList;
```
- Stores the mode(s) found so far

```java
int maxCount;
```
- The highest frequency seen so far

```java
int count;
```
- The current frequency for the current value

```java
TreeNode pre;
```
- The previous node visited in inorder traversal

```java
public int[] findMode(TreeNode root) {
```
- Main function that takes the BST root and returns an array of modes

```java
resList = new ArrayList<>();
maxCount = 0;
count = 0;
pre = null;
traversal(root);
```
- Initialize all variables and start the inorder traversal

```java
int[] res = new int[resList.size()];
for (int i = 0; i < resList.size(); i++) {
    res[i] = resList.get(i);
}
return res;
```
- Convert the result list to an array and return it

```java
private void traversal(TreeNode root) {
    if (root == null) return;
```
- Base case: if the node is null, just return

```java
traversal(root.left);
```
- Recursively traverse the left subtree first (inorder)

```java
int rootValue = root.val;
```
- Store the current node's value for easy reference

```java
if (pre == null || rootValue != pre.val) {
    count = 1;
} else {
    count++;
}
```
- If this is the first node or the value is different from the previous node, reset count to 1
- If the value is the same as the previous node, increment count

```java
if (count > maxCount) {
    resList.clear();
    resList.add(rootValue);
    maxCount = count;
} else if (count == maxCount) {
    resList.add(rootValue);
}
```
- If the current value's count is greater than maxCount, clear the result list and add this value
- If the count equals maxCount, add this value to the result list

```java
pre = root;
```
- Update the previous node to the current node

```java
traversal(root.right);
```
- Recursively traverse the right subtree (inorder)

## Time Complexity Analysis

### Time Complexity: O(n)
- **n** = number of nodes in the tree
- We visit each node exactly once during inorder traversal
- Each node requires constant time operations (comparison, update)

### Space Complexity: O(h + k)
- **h** = height of the tree (recursion stack)
- **k** = number of modes (for the result list)
- In the worst case, the tree is skewed (height n), so space is O(n)

## Summary

The core idea of this algorithm is:
1. Use inorder traversal to visit nodes in sorted order
2. Count consecutive duplicates as we go
3. Track the highest frequency and update the result list accordingly
4. Return all values that have the highest frequency

The key insight is leveraging BST's inorder traversal property - it gives us values in order, making it easy to count duplicates.

**Time complexity**: O(n) - we must visit every node
**Space complexity**: O(h + k) - recursion stack plus result list
