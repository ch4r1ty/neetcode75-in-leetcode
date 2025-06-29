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
    // 遇到在二叉搜索树上求什么最值啊，差值之类的，就把它想成在一个有序数组上求最值，求差值，这样就简单多了
    // 那么二叉搜索树采用中序遍历，其实就是一个有序数组。
    private TreeNode pre;
    private int minVal = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        recursion(root);
        return minVal;
    }

    private void recursion(TreeNode root) {
        if (root == null)
            return;

        recursion(root.left);
        if (pre != null) {
            if (root.val - pre.val < minVal) {
                minVal = root.val - pre.val;
            }
        }
         // 這題的精髓在這裏，用pre保存上一層遍歷的節點。注意這個pre = root的位置，是在左中右（中）的的位置。
        //  当我们处理完当前节点后，需要将当前节点设为"前一个节点"，供下一个节点使用
        pre = root;
        recursion(root.right);

    }
}