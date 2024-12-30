// 這個方法很慢

class Solution {
    private List<Integer> list = new ArrayList<Integer>();

    public boolean isValidBST(TreeNode root) {
        // 中序遍历，转换成数组，然后判断
        traversal(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) return;

        traversal(root.left, list);
        list.add(root.val);
        traversal(root.right, list);
    }
}