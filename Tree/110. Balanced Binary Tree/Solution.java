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
    public boolean isBalanced(TreeNode root) {
        int res = getHeight(root);
        return res != -1;   // 看清楚這裏是 !=
    }

    public int getHeight (TreeNode root) {  // 不用传入 height，这个是由每一层递归的left, right高度来决定的
        //  后序遍历求高度
        int height = 0; // 这个height是用来记录当前节点的高度，這樣寫的話，更好理解一點（也可以直接return，看下面的版本）
        if (root == null) return 0;

        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) return -1;    // 这个要判断，遇到-1的话一层一层上去，相当于返回false了
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        return height;
    }
}



// class Solution {
//     public boolean isBalanced(TreeNode root) {
//         return validate(root) != -1;
//     }

//     public int validate(TreeNode node) {
//         if (node == null) return 0;

//         int leftDepth = validate(node.left);
//         int rightDepth = validate(node.right);
//         if (leftDepth == -1) return -1;
//         if (rightDepth == -1) return -1;
        
//         if (Math.abs(leftDepth - rightDepth) > 1) return -1;
//         return Math.max(leftDepth, rightDepth) + 1;

//     }
// }