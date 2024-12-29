class Solution {
    int res = 0;
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        traverse(root, 1);  // 這裏初始深度是 1，而不是0
        return res;
    }

    private void traverse(TreeNode root, int depth) {
        if (root == null) return;

        res = Math.max(res, depth);
        // root.left = traverse(root.left, depth + 1); // node 類型 = int 類型，能跑通就見鬼了
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }
}
