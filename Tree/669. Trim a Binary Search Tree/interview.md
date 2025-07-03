# 669. Trim a Binary Search Tree — Interview Script

## Approach (How I Think About It)

So, for this problem, we're given a binary search tree and two values, `low` and `high`. We need to trim the tree so that every node's value is between `low` and `high` (inclusive). If a node's value is out of range, we remove it, but we still want to keep the rest of the tree valid.

Because it's a binary search tree, we know that for any node:
- All values in the left subtree are smaller.
- All values in the right subtree are bigger.

This makes it easier to decide which parts to keep or cut.

For binary tree problems, I like to use the "recursion three-step method":
1. Figure out the function's parameters and return value.
2. Decide the base case (when to stop).
3. Write the logic for one step of recursion.

## Code

```java
public TreeNode trimBST(TreeNode root, int low, int high) {
    if (root == null) return null;

    if (root.val < low) {
        return trimBST(root.right, low, high);
    }
    if (root.val > high) {
        return trimBST(root.left, low, high);
    }
    root.left = trimBST(root.left, low, high);
    root.right = trimBST(root.right, low, high);
    return root;
}
```

## Walkthrough (Line by Line)

Let's go through the code together:

- `public TreeNode trimBST(TreeNode root, int low, int high)`
    - This is our recursive function. It takes the current node (`root`), and the `low` and `high` bounds. It returns the trimmed tree (or subtree).

- `if (root == null) return null;`
    - **Step 2: Base case.** If the node is null, there's nothing to trim, so we just return null.

- `if (root.val < low) { return trimBST(root.right, low, high); }`
    - If the current node's value is less than `low`, then both the node and everything in its left subtree are too small (because of BST property). So, we skip the left side and just trim the right subtree.

- `if (root.val > high) { return trimBST(root.left, low, high); }`
    - If the current node's value is greater than `high`, then both the node and everything in its right subtree are too big. So, we skip the right side and just trim the left subtree.

- `root.left = trimBST(root.left, low, high);`
    - If the current node is in range, we recursively trim the left subtree and attach it back.

- `root.right = trimBST(root.right, low, high);`
    - Same for the right subtree.

- `return root;`
    - Finally, we return the current node, which now has its left and right children trimmed as needed.

## Summary

So, the main idea is: use recursion to walk through the tree, and at each node, decide if we keep it, or if we need to move to the left or right. The BST property helps us skip whole subtrees when we know they're out of range. This makes the solution clean and efficient!
