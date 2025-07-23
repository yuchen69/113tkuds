public class TreeTraversalBasic {
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        
        public TreeNode(int data) {
            this.data = data;
        }
    }
    
    // 前序遍歷：根 -> 左 -> 右
    public static void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    
    // 中序遍歷：左 -> 根 -> 右
    public static void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }
    
    // 後序遍歷：左 -> 右 -> 根
    public static void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }
    
    public static void main(String[] args) {
        // 建立測試樹
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        System.out.println("前序遍歷結果：");
        preOrder(root);
        System.out.println();
        
        System.out.println("中序遍歷結果：");
        inOrder(root);
        System.out.println();
        
        System.out.println("後序遍歷結果：");
        postOrder(root);
        System.out.println();
    }
}