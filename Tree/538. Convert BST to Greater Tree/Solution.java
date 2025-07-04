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