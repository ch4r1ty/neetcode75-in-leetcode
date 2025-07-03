/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        // 第一种情况
        if (root == null)
            return root;
        if (root.val == key) {
            // 第二种情况
            if (root.left == null && root.right == null) {
                return null;
                // 第三种情况，马上删除一下试试
            } else if (root.left == null && root.right != null) {
                return root.right;
                // 第四种情况
            } else if (root.left != null && root.right == null) {
                return root.left;
                // 第五种情况
            } else {
                TreeNode temp = root.right;
                // 一直往左叶子找
                while (temp.left != null) {
                    temp = temp.left;
                }
                temp.left = root.left;
                // 这里返回的是root.right，而不是temp
                // temp是root.right最左左左左的子节点，被删节点的左子树接到了这里
                return root.right;
            }
        }

        // 这里要有传递值，而不是单单进行迭代。这是根据上面返回值决定的
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }
}