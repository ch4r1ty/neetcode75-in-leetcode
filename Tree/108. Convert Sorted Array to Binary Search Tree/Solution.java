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
    public TreeNode sortedArrayToBST(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        return construct(nums, left, right);
    }

    private TreeNode construct(int[] nums, int left, int right) {
        // 这个要放在最前面
        if (left > right) return null;

        int mid = (left + right) / 2;
        // 值是nums[mid], 而不是mid
        TreeNode root = new TreeNode(nums[mid]);

        root.left = construct(nums, left, mid - 1);
        root.right = construct(nums, mid + 1, right);

        return root;
    }
}