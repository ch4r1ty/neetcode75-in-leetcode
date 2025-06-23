This problem wants us to find the leftmost value in the last row of a binary tree.

I use recursion for this. Since it's a binary tree, using recursion just feels natural.

Basically, there are two things we care about: how do we know we're at the last row, and how do we make sure we get the leftmost value?

Let's use the "three steps of recursion" to explain this problem:

1. **Determine the parameters and return value of the recursive function**  
   We need a recursive function that takes the current node and the current depth as parameters. Since we want to keep track of the deepest level and the leftmost value at that level, we also use two global variables: `maxDepth` (to record the maximum depth seen so far) and `value` (to record the leftmost node's value at that depth).

2. **Set the base case (termination condition)**  
   If the current node is null, we simply return, because there's nothing to process.

3. **Define the logic for a single recursive step**  
   At each node, we check if it's a leaf node (both left and right children are null). If it is, and its depth is greater than `maxDepth`, we update `maxDepth` and `value`.  
   Then, we recursively traverse the left subtree first, and then the right subtree. This ensures that when there are multiple nodes at the same depth, we encounter the leftmost one first.

By following these three steps of recursion, we can correctly find the leftmost value in the last row of the tree.

This approach guarantees that, after the traversal, our answer will be the value of the leftmost node in the last row of the tree.

Let me walk you through the code real quick:

We have two global variables: maxDepth for the deepest level we've seen, and value for the leftmost value.

In findBottomLeftValue, we start from the root and call our helper with depth 0.

In findLeftValue, if the node is null, we return. If it's a leaf and deeper than maxDepth, we update maxDepth and value.

We always go left first, then right. That way, when we reach the deepest level, the first value we find is the leftmost one.

So yeah, that's the idea, and the code just follows this logic.
