import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}

public class BSTValidation {
    
    // 1. 驗證一個二元樹是否為有效的BST
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidBSTHelper(TreeNode node, long minVal, long maxVal) {
        if (node == null) return true;
        
        // 檢查當前節點是否在有效範圍內
        if (node.val <= minVal || node.val >= maxVal) return false;
        
        // 遞歸檢查左右子樹
        return isValidBSTHelper(node.left, minVal, node.val) &&
               isValidBSTHelper(node.right, node.val, maxVal);
    }
    
    // 使用中序遍歷驗證BST（另一種方法）
    public boolean isValidBSTInorder(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);
        
        for (int i = 1; i < inorderList.size(); i++) {
            if (inorderList.get(i) <= inorderList.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
    
    private void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node != null) {
            inorderTraversal(node.left, list);
            list.add(node.val);
            inorderTraversal(node.right, list);
        }
    }
    
    // 2. 找出BST中不符合規則的節點
    public List<Integer> findInvalidNodes(TreeNode root) {
        List<Integer> invalidNodes = new ArrayList<>();
        findInvalidNodesHelper(root, Long.MIN_VALUE, Long.MAX_VALUE, invalidNodes);
        return invalidNodes;
    }
    
    private void findInvalidNodesHelper(TreeNode node, long minVal, long maxVal, List<Integer> invalidNodes) {
        if (node == null) return;
        
        // 檢查當前節點是否違反BST規則
        if (node.val <= minVal || node.val >= maxVal) {
            invalidNodes.add(node.val);
        }
        
        // 繼續檢查左右子樹
        findInvalidNodesHelper(node.left, minVal, node.val, invalidNodes);
        findInvalidNodesHelper(node.right, node.val, maxVal, invalidNodes);
    }
    
    // 3. 計算需要移除多少個節點才能讓樹變成有效的BST
    public int minNodesToRemove(TreeNode root) {
        return countNodes(root) - maxValidBSTSize(root);
    }
    
    // 計算最大有效BST子樹的節點數
    private int maxValidBSTSize(TreeNode root) {
        BSTInfo info = maxValidBSTHelper(root);
        return info.maxSize;
    }
    
    private BSTInfo maxValidBSTHelper(TreeNode node) {
        if (node == null) {
            return new BSTInfo(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        
        BSTInfo left = maxValidBSTHelper(node.left);
        BSTInfo right = maxValidBSTHelper(node.right);
        
        BSTInfo current = new BSTInfo();
        current.maxSize = Math.max(left.maxSize, right.maxSize);
        
        // 檢查當前子樹是否為有效BST
        if (left.isValid && right.isValid &&
            (node.left == null || left.maxVal < node.val) &&
            (node.right == null || right.minVal > node.val)) {
            
            current.isValid = true;
            current.size = left.size + right.size + 1;
            current.minVal = node.left == null ? node.val : left.minVal;
            current.maxVal = node.right == null ? node.val : right.maxVal;
            current.maxSize = Math.max(current.maxSize, current.size);
        } else {
            current.isValid = false;
        }
        
        return current;
    }
    
    // 輔助類別，儲存BST資訊
    class BSTInfo {
        boolean isValid;
        int size;
        int minVal;
        int maxVal;
        int maxSize;
        
        BSTInfo() {}
        
        BSTInfo(boolean isValid, int size, int minVal, int maxVal, int maxSize) {
            this.isValid = isValid;
            this.size = size;
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.maxSize = maxSize;
        }
    }
    
    // 計算樹中節點總數
    private int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    // 建立一個無效的BST用於測試
    public TreeNode createInvalidBST() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(6); // 這個節點違反BST規則
        return root;
    }
    
    // 建立一個有效的BST
    public TreeNode createValidBST() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(20);
        return root;
    }
    
    // 印出樹的結構（中序遍歷）
    public void printInorder(TreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }
    }
    
    public static void main(String[] args) {
        BSTValidation validator = new BSTValidation();
        
        // 測試有效的BST
        System.out.println("測試有效的BST:");
        TreeNode validBST = validator.createValidBST();
        System.out.print("中序遍歷: ");
        validator.printInorder(validBST);
        System.out.println();
        System.out.println("是否為有效BST: " + validator.isValidBST(validBST));
        System.out.println("使用中序遍歷驗證: " + validator.isValidBSTInorder(validBST));
        
        // 測試無效的BST
        System.out.println("\n測試無效的BST:");
        TreeNode invalidBST = validator.createInvalidBST();
        System.out.print("中序遍歷: ");
        validator.printInorder(invalidBST);
        System.out.println();
        System.out.println("是否為有效BST: " + validator.isValidBST(invalidBST));
        
        // 找出無效節點
        List<Integer> invalidNodes = validator.findInvalidNodes(invalidBST);
        System.out.println("無效節點: " + invalidNodes);
        
        // 計算需要移除的節點數
        int nodesToRemove = validator.minNodesToRemove(invalidBST);
        System.out.println("需要移除的節點數: " + nodesToRemove);
        System.out.println("總節點數: " + validator.countNodes(invalidBST));
        
        // 額外測試：邊界情況
        System.out.println("\n邊界測試:");
        System.out.println("空樹是否為有效BST: " + validator.isValidBST(null));
        
        TreeNode singleNode = new TreeNode(42);
        System.out.println("單節點樹是否為有效BST: " + validator.isValidBST(singleNode));
    }
}