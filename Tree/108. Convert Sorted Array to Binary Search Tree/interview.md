# 108. Convert Sorted Array to Binary Search Tree — Interview Script

## Approach (How I Think About It)

For this problem, we're given a sorted array, and we need to build a height-balanced binary search tree (BST) from it. The key is to keep the tree as balanced as possible, so the depth of the two subtrees of every node never differs by more than one.

The best way to do this is to always pick the middle element of the current subarray as the root. That way, the left and right subtrees will have about the same number of nodes.

For binary tree problems, I like to use the "recursion three-step method":
1. Figure out the function's parameters and return value.
2. Decide the base case (when to stop).
3. Write the logic for one step of recursion.

## Code

```java
public TreeNode sortedArrayToBST(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    return construct(nums, left, right);
}

private TreeNode construct(int[] nums, int left, int right) {
    if (left > right) return null;

    int mid = (left + right) / 2;
    TreeNode root = new TreeNode(nums[mid]);

    root.left = construct(nums, left, mid - 1);
    root.right = construct(nums, mid + 1, right);

    return root;
}
```

## Walkthrough (Line by Line)

Let's go through the code together:

- `public TreeNode sortedArrayToBST(int[] nums)`
    - This is the main function. It takes the sorted array and sets up the left and right pointers for the whole array. Then it calls our helper function `construct`.

- `int left = 0; int right = nums.length - 1;`
    - We start with the full range of the array, from index 0 to the last index.

- `return construct(nums, left, right);`
    - We call the recursive helper to build the tree.

- `private TreeNode construct(int[] nums, int left, int right)`
    - This is our recursive function. It takes the array and the current left and right bounds. It returns the root of the subtree for this range.

- `if (left > right) return null;`
    - **Step 2: Base case.** If our range is empty (left is greater than right), there's nothing to build, so we return null.

- `int mid = (left + right) / 2;`
    - We pick the middle index as the root for balance.

- `TreeNode root = new TreeNode(nums[mid]);`
    - We create a new tree node with the value at the middle index.

- `root.left = construct(nums, left, mid - 1);`
    - Recursively build the left subtree using the left half of the array.

- `root.right = construct(nums, mid + 1, right);`
    - Recursively build the right subtree using the right half of the array.

- `return root;`
    - Return the root node, which now has its left and right children attached.

## Summary

So, the main idea is: always pick the middle element as the root to keep the tree balanced, and use recursion to build the left and right subtrees. This way, we get a height-balanced BST from a sorted array!
