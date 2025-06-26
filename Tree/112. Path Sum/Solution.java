/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return traversal(root, targetSum - root.val);
    }

    private boolean traversal(TreeNode root, int count) {
        if (root.left == null && root.right == null && count == 0) return true;
        if (root.left == null && root.right == null && count!= 0) return false;

        // 这么写可以体现回溯
        if (root.left != null) {
            count -= root.left.val;
            if(traversal(root.left, count)) return true;
            count += root.left.val;
        }

        if (root.right != null) {
            count -= root.right.val;
            if(traversal(root.right, count)) return true;
            count += root.right.val;
        }

        return false;
    }
}