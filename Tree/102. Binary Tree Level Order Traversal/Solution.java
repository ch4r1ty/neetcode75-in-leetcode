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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        Deque<TreeNode> que = new LinkedList<TreeNode>();

        // 这个返回resList, 而不是null，因爲函數返回List<List<Integer>>
        if (root == null) return resList;
        que.offerFirst(root);

        // while 用來控制隊列(棧)que
        while (!que.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int len = que.size();   // 用 len 來保存當前高度的節點數量，控制每一層出棧的次數

            while (len > 0) {
                // 1. 出口處節點出棧，len--，放到單層結果集裏
                TreeNode temp = que.pollLast();
                len--;
                level.add(temp.val);

                // 2. 左右葉子入棧
                if (temp.left != null) que.offerFirst(temp.left);
                if (temp.right != null) que.offerFirst(temp.right);
            }
            // len == 0，當前層操作完畢，把單層結果集level放到resList裏
            resList.add(level);
        }

        return resList;
    }
}
