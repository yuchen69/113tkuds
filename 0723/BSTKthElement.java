import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}

public class BSTKthElement {
    private int count = 0;
    private int result = -1;
    
    // 找到BST中第k小的元素（利用中序遍歷的性質）
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        result = -1;
        inorderTraversal(root, k);
        return result;
    }
    
    private void inorderTraversal(TreeNode node, int k) {
        if (node == null || count >= k) return;
        
        // 遍歷左子樹
        inorderTraversal(node.left, k);
        
        // 處理當前節點
        count++;
        if (count == k) {
            result = node.val;
            return;
        }
        
        // 遍歷右子樹
        inorderTraversal(node.right, k);
    }
    
    // 迭代版本的第k小元素查找
    public int kthSmallestIterative(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int count = 0;
        
        while (current != null || !stack.isEmpty()) {
            // 走到最左邊
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            current = stack.pop();
            count++;
            
            if (count == k) {
                return current.val;
            }
            
            current = current.right;
        }
        
        return -1; // k超出範圍
    }
    
    // 找到第k大的元素（反向中序遍歷）
    public int kthLargest(TreeNode root, int k) {
        count = 0;
        result = -1;
        reverseInorderTraversal(root, k);
        return result;
    }
    
    private void reverseInorderTraversal(TreeNode node, int k) {
        if (node == null || count >= k) return;
        
        // 先遍歷右子樹
        reverseInorderTraversal(node.right, k);
        
        // 處理當前節點
        count++;
        if (count == k) {
            result = node.val;
            return;
        }
        
        // 遍歷左子樹
        reverseInorderTraversal(node.left, k);
    }
    
    // 插入節點到BST
    public TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }
        return root;
    }
    
    // 中序遍歷印出所有元素
    public void inorderPrint(TreeNode root) {
        if (root != null) {
            inorderPrint(root.left);
            System.out.print(root.val + " ");
            inorderPrint(root.right);
        }
    }
    
    // 計算BST中節點的總數
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    public static void main(String[] args) {
        BSTKthElement bst = new BSTKthElement();
        TreeNode root = null;
        
        // 建立BST: [20, 10, 30, 5, 15, 25, 35, 1, 8]
        int[] values = {20, 10, 30, 5, 15, 25, 35, 1, 8};
        for (int val : values) {
            root = bst.insert(root, val);
        }
        
        System.out.println("BST中序遍歷（從小到大）:");
        bst.inorderPrint(root);
        System.out.println();
        
        int totalNodes = bst.countNodes(root);
        System.out.println("BST總節點數: " + totalNodes);
        
        // 測試第k小元素
        System.out.println("\n第k小元素測試:");
        for (int k = 1; k <= Math.min(5, totalNodes); k++) {
            int kthSmall = bst.kthSmallest(root, k);
            System.out.println("第" + k + "小的元素: " + kthSmall);
            
            // 重置計數器為下次查詢準備
            bst.count = 0;
            bst.result = -1;
        }
        
        // 測試迭代版本
        System.out.println("\n迭代版本測試:");
        System.out.println("第3小的元素: " + bst.kthSmallestIterative(root, 3));
        System.out.println("第5小的元素: " + bst.kthSmallestIterative(root, 5));
        
        // 測試第k大元素
        System.out.println("\n第k大元素測試:");
        for (int k = 1; k <= Math.min(3, totalNodes); k++) {
            int kthLarge = bst.kthLargest(root, k);
            System.out.println("第" + k + "大的元素: " + kthLarge);
            
            // 重置計數器
            bst.count = 0;
            bst.result = -1;
        }
        
        // 邊界測試
        System.out.println("\n邊界測試:");
        bst.count = 0;
        bst.result = -1;
        int invalidK = bst.kthSmallest(root, totalNodes + 1);
        System.out.println("查詢第" + (totalNodes + 1) + "小元素: " + invalidK);
    }
}