# Binary Tree Paths - Interview Script

## Problem Understanding
"Let me first understand what we need to do. We need to find all root-to-leaf paths in a binary tree. For example, if we have a tree like [1,2,3], we should return ['1->2', '1->3']. This is a classic tree traversal problem, and I think we can solve it using recursion."

## Solution Approach
"Let me walk you through my thought process using the three steps of recursion:

### Step 1: Parameters and Return Type
"First, we need to think about what parameters we need in our recursive function:
- We need a list to store all paths (result)
- We need a current path to track our progress
- We need the current node we're processing
- We don't need to return anything, we'll just modify our result list"

### Step 2: Termination Condition
"Next, let's think about when we should stop:
- We should stop when we reach a leaf node
- A leaf node is a node with no left and no right children
- When we reach a leaf, we need to add our current path to the result list
- This is different from other tree problems where we might return null or 0
- Here, we're actually building a complete path string"

### Step 3: Single Level Recursion Logic
"Finally, let's think about what we do at each level:
- We need to do a preorder traversal (root -> left -> right)
- This makes sense because we want to build paths from top to bottom
- At each node, we need to:
  1. Add the current node's value to our path
  2. Check if it's a leaf node
  3. If it's a leaf, add the complete path to our result list
  4. If not, continue traversing left and right


## Code Implementation
"Let me implement this approach:

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        List<String> res = new ArrayList<>();
        traverse(path, res, root);
        return res;

    }

    public void traverse(List<Integer> path, List<String> res, TreeNode node) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            // traverse logic in level
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i));
                sb.append("->");
            }
            sb.append(path.get(path.size() - 1));

            res.add(sb.toString());
            return;
        }

        if (node.left != null) {
            traverse(path, res, node.left);
            path.remove(path.size() - 1);   // last digit in current path
        }
        if (node.right != null) {
            traverse(path, res, node.right);
            path.remove(path.size() - 1);
        }
    }
}
```


## Complexity Analysis
"Let me analyze the time and space complexity:

Time Complexity: O(n)
- We visit each node exactly once
- For each node, we do constant time operations
- String concatenation is O(1) in this case as we're just adding a few characters

Space Complexity: O(h)
- Where h is the height of the tree
- This is due to the recursion stack
- In worst case (skewed tree), it's O(n)
- In best case (balanced tree), it's O(log n)"

## Optimization Considerations
"We could optimize this solution by:
1. Using StringBuilder instead of String concatenation
2. Using a list to store path elements and joining them at the end
3. Using iteration instead of recursion for very deep trees"

## Test Cases
"Let's verify the code with these test cases:
1. Empty tree: should return empty list
2. Single node: should return ['1']
3. Simple tree: [1,2,3] should return ['1->2', '1->3']
4. Complex tree: [1,2,3,4,5] should return ['1->2->4', '1->2->5', '1->3']"

## Common Pitfalls
"Some common mistakes to avoid:
1. Not handling null nodes correctly
2. Forgetting to add '->' between nodes
3. Not properly building the path string
4. Not considering the order of traversal"

## Summary
"To summarize, this problem tests:
1. Understanding of tree traversal
2. Recursive thinking
3. String manipulation
4. Path building logic

The key insight is using preorder traversal to build paths from root to leaf, and properly handling the path string construction at each step." 