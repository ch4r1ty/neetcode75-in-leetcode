Let's use the "three steps of recursion" to analyze and solve the Maximum Binary Tree problem (654. Maximum Binary Tree):

**1. Determine the parameters and return value of the recursive function**

- **Parameters:**  
  The recursive function needs the array (`int[] nums`) and the current range indices (`int leftIndex, int rightIndex`). We use left and right indices to define the current subarray we're working with. The range is defined as left-closed and right-closed `[leftIndex, rightIndex]`.

- **Return value:**  
  Since we need to construct a binary tree, the recursive function should return a `TreeNode` representing the root of the subtree for the current range.

  Example function signature:  
  `TreeNode construct(int[] nums, int leftIndex, int rightIndex);`

**2. Set the base case (termination condition)**

- If `rightIndex < leftIndex`, it means we have an empty range, so we return `null`.
- If `rightIndex == leftIndex`, it means we have only one element, so we create and return a leaf node with that value.

  Example base case code:
  ```
  if (rightIndex < leftIndex) return null;
  if (rightIndex == leftIndex) return new TreeNode(nums[leftIndex]);
  ```

**3. Define the logic for a single recursive step**

- Find the maximum value and its index in the current range `[leftIndex, rightIndex]`.
- Create a root node with the maximum value.
- Recursively construct the left subtree using the range `[leftIndex, maxIndex - 1]`.
- Recursively construct the right subtree using the range `[maxIndex + 1, rightIndex]`.
- Return the root node.

  Example recursive logic:
  ```
  // Find maximum value and its index
  int maxIndex = leftIndex;
  int maxValue = nums[maxIndex];
  for (int i = leftIndex; i <= rightIndex; i++) {
      if (nums[i] > maxValue) {
          maxIndex = i;
          maxValue = nums[i];
      }
  }
  
  // Create root and recursively build subtrees
  TreeNode root = new TreeNode(maxValue);
  root.left = construct(nums, leftIndex, maxIndex - 1);
  root.right = construct(nums, maxIndex + 1, rightIndex);
  return root;
  ```

**Summary:**  
- The recursive function takes the array and range indices as parameters.
- The function returns a `TreeNode` representing the root of the constructed subtree.
- We use left-closed and right-closed range `[leftIndex, rightIndex]`.
- The maximum value in each range becomes the root, with left and right subtrees constructed from the elements before and after the maximum value respectively.

This is the recursive approach for the Maximum Binary Tree problem, following the three steps of recursion.
