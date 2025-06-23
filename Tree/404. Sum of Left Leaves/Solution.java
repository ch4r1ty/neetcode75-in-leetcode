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

 // 這個代碼更簡潔一點

 class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        // 這一步其實不需要，因爲下面左右遞歸會到這一步
        // if (root.left == null && root.right == null) return 0;

        int leftValue = sumOfLeftLeaves(root.left);
        if (root.left != null && root.left.left == null && root.left.right == null) {
            leftValue += root.left.val;
        }
        int rightValue = sumOfLeftLeaves(root.right);
        
        int sum = leftValue + rightValue;

        return sum;
    }
}