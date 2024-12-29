class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;  // 終止條件

        // 后續遍歷
        // root.left = invertTree(root.left);  // 左葉子節點的處理邏輯
        // root.right = invertTree(root.right);    // 右葉子節點的處理邏輯
        // 前序遍歷
        swapChildren(root); // root 節點的處理邏輯
        root.left = invertTree(root.left);  // 左葉子節點的處理邏輯
        root.right = invertTree(root.right);    // 右葉子節點的處理邏輯
        return root;
    }

    public void swapChildren(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
