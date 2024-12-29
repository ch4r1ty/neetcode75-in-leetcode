class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;

        // 這裏return的，一個主函數，兩個用了下面的函數
        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // 這個就是 leetcode 第100題
    private boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root != null && subRoot == null || root == null && subRoot != null || root.val != subRoot.val) return false;
        boolean left = isSameTree(root.left, subRoot.left);
        boolean right = isSameTree(root.right, subRoot.right);
        return left && right;
    }
}