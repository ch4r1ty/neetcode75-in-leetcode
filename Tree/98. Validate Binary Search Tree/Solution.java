// // 這個方法很慢

// class Solution {
//     private List<Integer> list = new ArrayList<Integer>();

//     public boolean isValidBST(TreeNode root) {
//         // 中序遍历，转换成数组，然后判断
//         traversal(root, list);
//         for (int i = 1; i < list.size(); i++) {
//             if (list.get(i) <= list.get(i - 1)) {
//                 return false;
//             }
//         }
//         return true;
//     }

//     private void traversal(TreeNode root, List<Integer> list) {
//         if (root == null) return;

//         traversal(root.left, list);
//         list.add(root.val);
//         traversal(root.right, list);
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
    private long maxVal = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        // left
        boolean left = isValidBST(root.left);
        if (left == false) return false;
        // mid
        if (root.val > maxVal) {
            maxVal = root.val;
        } else {
            return false;
        }
        // right
        boolean right = isValidBST(root.right);
        if (right == false) return false;

        // 搜索完整棵樹，沒有不符合BST的node，那就返回true
        return true;
    }
}