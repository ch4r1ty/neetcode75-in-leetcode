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
    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        List<String> res = new ArrayList<>();
        traverse(path, res, root);
        return res;

    }

    public void traverse(List<Integer> path, List<String> res, TreeNode node) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            // traverse logic in level
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i));
                sb.append("->");
            }
            sb.append(path.get(path.size() - 1));

            res.add(sb.toString());
            return;
        }

        if (node.left != null) {
            traverse(path, res, node.left);
            path.remove(path.size() - 1);   // last digit in current path
        }
        if (node.right != null) {
            traverse(path, res, node.right);
            path.remove(path.size() - 1);
        }
    }
}