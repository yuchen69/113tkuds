public class SimpleBinaryTree {
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    // 印出樹狀結構（橫向表示，右 → 根 → 左）
    public static void printTree(TreeNode node, int level) {
        if (node == null) return;

        printTree(node.right, level + 1);

        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.data);

        printTree(node.left, level + 1);
    }

    public static void main(String[] args) {
        // 建立一個簡單的二元樹
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // 逐步印出節點資訊
        System.out.println("樹的結構:");
        System.out.println("根節點: " + root.data);
        System.out.println("左子樹: " + root.left.data);
        System.out.println("右子樹: " + root.right.data);
        System.out.println("左子樹的左子樹: " + root.left.left.data);
        System.out.println("左子樹的右子樹: " + root.left.right.data);

        // 顯示整棵樹的圖形結構
        System.out.println("\n橫向樹狀圖如下：");
        printTree(root, 0);
    }
}