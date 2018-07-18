
public class LongestPath {
    public static int len = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        len = 0;
        len = getPath(root, root.val);
        return len;
    }

    public int getPath(TreeNode node, int val) {
        if (node == null) return 0;
        int left = getPath(node.left, node.val);
        int right = getPath(node.right, node.val);
        len = Math.max(len, left + right);
        if (val == node.val) return Math.max(left, right) + 1;
        return 0;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}