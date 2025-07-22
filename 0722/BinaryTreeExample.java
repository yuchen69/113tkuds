class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTreeExample {
    
    // 前序走訪：根 -> 左 -> 右
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");  // 處理根節點
        preOrder(root.left);                // 遞迴左子樹
        preOrder(root.right);               // 遞迴右子樹
    }
    
    // 計算樹的高度
    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    // 計算節點總數
    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    // 搜尋特定值
    public static boolean search(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.data == target) {
            return true;
        }
        return search(root.left, target) || search(root.right, target);
    }
    
    public static void main(String[] args) {
    // 建立一棵高度為 5、節點總數為 9 的二元樹:
    //         1
    //        /
    //       2
    //      /
    //     3
    //    /
    //   4
    //  / \
    // 5   6
    //      \
    //       7
    //      / \
    //     8   9

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.left.left = new TreeNode(4);
    root.left.left.left.left = new TreeNode(5);
    root.left.left.left.right = new TreeNode(6);
    root.left.left.left.right.right = new TreeNode(7);
    root.left.left.left.right.right.left = new TreeNode(8);
    root.left.left.left.right.right.right = new TreeNode(9);

    System.out.print("前序走訪: ");
    preOrder(root); // 輸出節點順序
    System.out.println();

    System.out.println("樹的高度: " + getHeight(root));   // 5
    System.out.println("節點總數: " + countNodes(root));  // 9
    System.out.println("搜尋 4: " + search(root, 4));     // true
    System.out.println("搜尋 6: " + search(root, 6));     // true
    System.out.println("搜尋 10: " + search(root, 10));   // false
    }

}
