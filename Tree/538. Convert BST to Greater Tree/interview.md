# 538. Convert BST to Greater Tree — Interview Script

## Approach (How I Think About It)

For this problem, we're given a binary search tree (BST), and we need to change it so that every node's value becomes the sum of all values greater than or equal to it in the original tree. Basically, each node should hold its original value plus the values of all nodes that are bigger than it.

Because it's a BST, we know that for any node, all the bigger values are on the right. So, if we do a reverse in-order traversal (right, root, left), we'll visit nodes from biggest to smallest. We can keep a running sum as we go, and update each node's value.

For binary tree problems, I like to use the "recursion three-step method." Here's how it applies to this problem:

1. **Figure out the function's parameters and return value:**  
   Our recursive function needs the current node. We also need a way to keep track of the running sum, so we can update each node as we go. In this code, we use a class variable `pre` to remember the previous node we visited.

2. **Decide the base case (when to stop):**  
   If the current node is null, there's nothing to do, so we just return. This stops the recursion.

3. **Write the logic for one step of recursion:**  
   We first recurse on the right subtree (to get bigger values first), then update the current node's value by adding the previous sum, then update our running sum, and finally recurse on the left subtree.

## Code

```java
class Solution {
    TreeNode pre = new TreeNode();
    public TreeNode convertBST(TreeNode root) {
        recursion(root);
        return root;
    }

    private void recursion(TreeNode root) {
        if (root == null) return;

        recursion(root.right);
        root.val += pre.val;
        pre = root;
        recursion(root.left);
    }
}
```

## Walkthrough (Line by Line)

Let's go through the code together:

- `TreeNode pre = new TreeNode();`
    - This is a class variable to keep track of the previous node we visited. It helps us remember the running sum as we go through the tree.

- `public TreeNode convertBST(TreeNode root)`
    - This is the main function. It starts the recursion and returns the updated tree.

- `recursion(root);`
    - We call our helper function to start the reverse in-order traversal.

- `private void recursion(TreeNode root)`
    - This is our recursive function. It takes the current node as a parameter.

- `if (root == null) return;`
    - **Step 2: Base case.** If the node is null, there's nothing to do, so we return.

- `recursion(root.right);`
    - First, we recurse on the right subtree, because those nodes have bigger values.

- `root.val += pre.val;`
    - We update the current node's value by adding the running sum (which is stored in `pre.val`).

- `pre = root;`
    - We update our running sum by setting `pre` to the current node.

- `recursion(root.left);`
    - Finally, we recurse on the left subtree, which has smaller values.

## Summary

So, the main idea is: use reverse in-order traversal to visit nodes from biggest to smallest, keep a running sum, and update each node as we go. This way, every node ends up with the sum of all values greater than or equal to it!

