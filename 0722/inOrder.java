class TreeNode {
    int data;
    TreeNode left, right;
    public TreeNode(int data) {
        this.data = data;
    }
}

public class inOrder {
    public static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
}
