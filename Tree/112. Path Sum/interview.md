递归函数什么时候需要返回值？什么时候不需要返回值？这里总结如下三点：

如果需要搜索整棵二叉树且不用处理递归返回值，递归函数就不要返回值。（这种情况就是本文下半部分介绍的113.路径总和ii）
如果需要搜索整棵二叉树且需要处理递归返回值，递归函数就需要返回值。 （这种情况我们在236. 二叉树的最近公共祖先中介绍）
如果要搜索其中一条符合条件的路径，那么递归一定需要返回值，因为遇到符合条件的路径了就要及时返回。（本题的情况）

Let's use the "three steps of recursion" to analyze and solve the Path Sum problem (112. Path Sum):

**1. Determine the parameters and return value of the recursive function**

- **Parameters:**  
  The recursive function needs the current node (`TreeNode* cur`) and a counter (`int count`). The counter is used to track whether the sum of the values along the current path equals the target sum. We can initialize `count` as the target sum, and subtract the value of each visited node as we go down the tree.

- **Return value:**  
  When do we need a return value for recursion?  
  - If we need to search the entire tree and don't need to process the return value, the recursive function doesn't need a return value (like in Path Sum II).
  - If we need to search the entire tree and need to process the return value, the recursive function should return a value (like in Lowest Common Ancestor).
  - If we only need to find one path that meets the condition, the recursive function must return a value, so we can return immediately when we find a valid path (this is the case for this problem).

  For this problem, since we only need to find one valid path, the recursive function should return a `bool` indicating whether such a path exists.

  Example function signature:  
  `bool traversal(TreeNode* cur, int count);`

**2. Set the base case (termination condition)**

- We use the counter in a decreasing way: start with the target sum, and subtract the value of each node as we traverse.
- If we reach a leaf node (`cur->left == null && cur->right == null`) and `count == 0`, it means we've found a path whose sum equals the target, so we return `true`.
- If we reach a leaf node and `count != 0`, it means this path doesn't work, so we return `false`.

  Example base case code:
  ```
  if (!cur->left && !cur->right && count == 0) return true; // reached leaf and sum matches
  if (!cur->left && !cur->right) return false; // reached leaf but sum doesn't match
  ```

**3. Define the logic for a single recursive step**

- Since the base case checks for leaf nodes, we don't need to recurse on null nodes.
- For each child (left and right), we recursively call the function with the updated counter (`count - child->val`).
- If any recursive call returns `true`, we return `true` immediately (early return).
- If neither subtree returns `true`, we return `false`.

  Example recursive logic:
  ```
  if (cur->left) {
      if (traversal(cur->left, count - cur->left->val)) return true;
  }
  if (cur->right) {
      if (traversal(cur->right, count - cur->right->val)) return true;
  }
  return false;
  ```

**Summary:**  
- The recursive function takes the current node and the remaining sum as parameters.
- The function returns `true` if a valid path is found, otherwise `false`.
- We use a decreasing counter to track the remaining sum.
- We return as soon as we find a valid path.

This is the recursive approach for the Path Sum problem, following the three steps of recursion.
