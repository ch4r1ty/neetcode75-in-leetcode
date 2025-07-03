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
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;

        // 不在范围内
        // 如果当前节点的值小于 low，则当前节点和它的左子树所有节点都小于 low
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        // 如果当前节点的值大于 high，则当前节点和它的右子树所有节点都大于 high
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        // 在范围内
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}