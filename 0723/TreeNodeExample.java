public class TreeNodeExample {
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public void displayNode() {
            System.out.println("節點值: " + data);
            System.out.println("左子節點: " + (left != null ? left.data : "null"));
            System.out.println("右子節點: " + (right != null ? right.data : "null"));
        }
    }

    public static void printTree(TreeNode node, int level) {
        if (node == null) return;

        // 印右邊
        printTree(node.right, level + 1);

        // 印中間
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.data);

        // 印左邊
        printTree(node.left, level + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode leftChild = new TreeNode(5);
        TreeNode rightChild = new TreeNode(15);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);

        root.left = leftChild;
        root.right = rightChild;
        leftChild.left = node1;
        leftChild.right = node2;

        // 印出所有節點資訊
        root.displayNode();
        leftChild.displayNode();
        rightChild.displayNode();
        node1.displayNode();
        node2.displayNode();

        // 顯示整棵樹的結構
        System.out.println("\n樹狀結構如下：");
        printTree(root, 0);
    }
}