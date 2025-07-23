public class BSTDelete {
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        
        public TreeNode(int data) {
            this.data = data;
        }
    }
    
    // 插入節點
    public static TreeNode insert(TreeNode root, int data) {
        if (root == null) return new TreeNode(data);
        
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }
        
        return root;
    }
    
    // 刪除節點
    public static TreeNode delete(TreeNode root, int data) {
        if (root == null) return null;
        
        if (data < root.data) {
            root.left = delete(root.left, data);
        } else if (data > root.data) {
            root.right = delete(root.right, data);
        } else {
            // 找到要刪除的節點
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            
            // 有兩個子節點：找右子樹最小值替換
            root.data = findMin(root.right);
            root.right = delete(root.right, root.data);
        }
        
        return root;
    }
    
    private static int findMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }
    
    // 中序遍歷
    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }
    
    public static void main(String[] args) {
        TreeNode root = null;
        
        // 建立BST
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int value : values) {
            root = insert(root, value);
        }
        
        System.out.print("原始BST: ");
        inOrder(root);
        System.out.println();
        
        // 刪除葉節點
        root = delete(root, 20);
        System.out.print("刪除20後: ");
        inOrder(root);
        System.out.println();
        
        // 刪除有一個子節點的節點
        root = delete(root, 30);
        System.out.print("刪除30後: ");
        inOrder(root);
        System.out.println();
        
        // 刪除有兩個子節點的節點
        root = delete(root, 50);
        System.out.print("刪除50後: ");
        inOrder(root);
        System.out.println();
    }
}