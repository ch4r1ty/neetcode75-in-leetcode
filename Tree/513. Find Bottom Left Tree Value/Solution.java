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
    // 这题怎样遍历都行，只要先左后右，这题没有中间节点处理逻辑
    // 底下这两个全局变量
    private int maxDepth = -1;
    private int value = 0;
    public int findBottomLeftValue(TreeNode root) {
        value = root.val;
        findLeftValue(root, 0);
        return value;
    }

    public void findLeftValue(TreeNode root, int depth) {
        if (root == null) return;
        // 这一步保证保存的是最左边的元素
        if (root.left == null && root.right == null) {
            if (maxDepth < depth) {
                maxDepth = depth;
                value = root.val;
            }
        }
        if (root.left != null) {
            findLeftValue(root.left, depth + 1);
        }
        // 底下这种写法，是回溯思想的体现；上面的话简短一点
        if (root.right != null) {
            depth++;
            findLeftValue(root.right, depth);
            depth--;
        }
    }
}