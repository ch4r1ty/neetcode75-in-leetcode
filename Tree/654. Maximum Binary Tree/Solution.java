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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // 这里定义了左右开闭，这里是左闭右闭
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int leftIndex, int rightIndex) {
        if (rightIndex < leftIndex) return null;
        if (rightIndex == leftIndex) return new TreeNode(nums[leftIndex]);
        
        int maxIndex = leftIndex;
        int maxValue = nums[maxIndex];
        for (int i = maxIndex; i <= rightIndex; i++) {
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
                maxValue = nums[i];
            }
        }

        TreeNode root = new TreeNode(maxValue);
        root.left = construct(nums, leftIndex, maxIndex - 1);
        root.right = construct(nums, maxIndex + 1, rightIndex);
        return root;
    }
}