class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new LinkedList<>();
        traverse(root, list);
        return list.get(k - 1);
    }

    private void traverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        traverse(root.left, list);
        list.add(root.val);
        traverse(root.right, list);
    }
}
