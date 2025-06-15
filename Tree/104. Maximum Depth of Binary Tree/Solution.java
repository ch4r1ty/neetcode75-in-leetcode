// class Solution {
//     int res = 0;
//     public int maxDepth(TreeNode root) {
//         if (root == null) return 0;
//         traverse(root, 1);  // 這裏初始深度是 1，而不是0
//         return res;
//     }

//     private void traverse(TreeNode root, int depth) {
//         if (root == null) return;

//         res = Math.max(res, depth);
//         // root.left = traverse(root.left, depth + 1); // node 類型 = int 類型，能跑通就見鬼了
//         traverse(root.left, depth + 1);
//         traverse(root.right, depth + 1);
//     }
// }




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
    public int maxDepth(TreeNode root) {
        // 后序遍历求高度，这题要求最大深度，其实就是求的高度
        if (root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        int mid = Math.max(leftDepth, rightDepth) + 1;
        return mid;
    }
}