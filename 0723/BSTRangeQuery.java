import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}

public class BSTRangeQuery {
    
    // BST範圍查詢 - 返回所有在[min, max]範圍內的節點值
    public ArrayList<Integer> rangeQuery(TreeNode root, int min, int max) {
        ArrayList<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }
    
    private void rangeQueryHelper(TreeNode node, int min, int max, ArrayList<Integer> result) {
        if (node == null) return;
        
        // 如果當前節點值在範圍內，加入結果
        if (node.val >= min && node.val <= max) {
            result.add(node.val);
        }
        
        // 根據BST性質決定是否搜索左右子樹
        if (node.val > min) {
            rangeQueryHelper(node.left, min, max, result);
        }
        if (node.val < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
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
    
    // 中序遍歷（用於驗證BST結構）
    public void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }
    
    public static void main(String[] args) {
        BSTRangeQuery bst = new BSTRangeQuery();
        TreeNode root = null;
        
        // 建立BST: [20, 10, 30, 5, 15, 25, 35]
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        for (int val : values) {
            root = bst.insert(root, val);
        }
        
        System.out.println("BST中序遍歷:");
        bst.inorderTraversal(root);
        System.out.println();
        
        // 測試範圍查詢 [12, 27]
        ArrayList<Integer> result = bst.rangeQuery(root, 12, 27);
        Collections.sort(result); // 確保結果有序
        
        System.out.println("範圍查詢 [12, 27] 結果: " + result);
        
        // 額外測試
        result = bst.rangeQuery(root, 8, 18);
        Collections.sort(result);
        System.out.println("範圍查詢 [8, 18] 結果: " + result);
        
        result = bst.rangeQuery(root, 25, 40);
        Collections.sort(result);
        System.out.println("範圍查詢 [25, 40] 結果: " + result);
    }
}
