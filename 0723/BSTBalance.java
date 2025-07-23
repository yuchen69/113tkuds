public class BSTBalance {
    
    // 定義樹節點
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    /**
     * 檢查BST是否為平衡樹
     * 平衡樹：任意節點的左右子樹高度差不超過1
     */
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }
    
    /**
     * 檢查平衡並返回高度，如果不平衡返回-1
     */
    private int checkBalance(TreeNode node) {
        if (node == null) return 0;
        
        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) return -1; // 左子樹不平衡
        
        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) return -1; // 右子樹不平衡
        
        // 檢查當前節點是否平衡
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // 當前節點不平衡
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * 計算節點的平衡因子（左子樹高度 - 右子樹高度）
     */
    public int getBalanceFactor(TreeNode node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }
    
    /**
     * 計算樹的高度
     */
    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
    
    /**
     * 找出BST中最不平衡的節點
     * 返回平衡因子絕對值最大的節點
     */
    public TreeNode findMostUnbalancedNode(TreeNode root) {
        if (root == null) return null;
        
        TreeNode[] result = {null};
        int[] maxImbalance = {0};
        
        findMostUnbalanced(root, result, maxImbalance);
        return result[0];
    }
    
    /**
     * 遞迴尋找最不平衡的節點
     */
    private void findMostUnbalanced(TreeNode node, TreeNode[] result, int[] maxImbalance) {
        if (node == null) return;
        
        int balanceFactor = Math.abs(getBalanceFactor(node));
        if (balanceFactor > maxImbalance[0]) {
            maxImbalance[0] = balanceFactor;
            result[0] = node;
        }
        
        findMostUnbalanced(node.left, result, maxImbalance);
        findMostUnbalanced(node.right, result, maxImbalance);
    }
    
    /**
     * 獲取所有節點的平衡因子資訊
     */
    public void printBalanceInfo(TreeNode root) {
        System.out.println("節點平衡因子資訊：");
        printBalanceInfoHelper(root);
    }
    
    private void printBalanceInfoHelper(TreeNode node) {
        if (node == null) return;
        
        int balanceFactor = getBalanceFactor(node);
        System.out.printf("節點 %d: 平衡因子 = %d", node.val, balanceFactor);
        
        if (Math.abs(balanceFactor) > 1) {
            System.out.print(" (不平衡)");
        }
        System.out.println();
        
        printBalanceInfoHelper(node.left);
        printBalanceInfoHelper(node.right);
    }
    
    /**
     * 建立測試用的BST
     */
    public TreeNode buildTestTree() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(18);
        // 添加一個不平衡的分支
        root.left.left.left = new TreeNode(1);
        root.left.left.left.left = new TreeNode(0);
        
        return root;
    }
    
    /**
     * 建立平衡的BST
     */
    public TreeNode buildBalancedTree() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(18);
        
        return root;
    }
    
    /**
     * 中序遍歷打印樹
     */
    public void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }
    
    // 測試主函數
    public static void main(String[] args) {
        BSTBalance bst = new BSTBalance();
        
        System.out.println("=== BST平衡檢查測試 ===");
        
        // 測試不平衡的樹
        System.out.println("\n1. 測試不平衡樹：");
        TreeNode unbalancedTree = bst.buildTestTree();
        System.out.print("樹的中序遍歷: ");
        bst.inorderTraversal(unbalancedTree);
        System.out.println();
        
        System.out.println("是否平衡: " + bst.isBalanced(unbalancedTree));
        bst.printBalanceInfo(unbalancedTree);
        
        TreeNode mostUnbalanced = bst.findMostUnbalancedNode(unbalancedTree);
        if (mostUnbalanced != null) {
            System.out.println("最不平衡的節點: " + mostUnbalanced.val + 
                             " (平衡因子: " + Math.abs(bst.getBalanceFactor(mostUnbalanced)) + ")");
        }
        
        // 測試平衡的樹
        System.out.println("\n2. 測試平衡樹：");
        TreeNode balancedTree = bst.buildBalancedTree();
        System.out.print("樹的中序遍歷: ");
        bst.inorderTraversal(balancedTree);
        System.out.println();
        
        System.out.println("是否平衡: " + bst.isBalanced(balancedTree));
        bst.printBalanceInfo(balancedTree);
        
        mostUnbalanced = bst.findMostUnbalancedNode(balancedTree);
        if (mostUnbalanced != null) {
            System.out.println("最不平衡的節點: " + mostUnbalanced.val + 
                             " (平衡因子: " + Math.abs(bst.getBalanceFactor(mostUnbalanced)) + ")");
        } else {
            System.out.println("樹是完全平衡的");
        }
        
        // 測試單個節點的平衡因子
        System.out.println("\n3. 測試特定節點的平衡因子：");
        TreeNode testNode = unbalancedTree.left; // 值為5的節點
        System.out.println("節點 " + testNode.val + " 的平衡因子: " + 
                          bst.getBalanceFactor(testNode));
    }
}