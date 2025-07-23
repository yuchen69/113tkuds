import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}

public class TreeMirror {
    
    // 判斷一個二元樹是否是對稱的
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }
    
    // 判斷兩個子樹是否互為鏡像
    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        
        return (left.val == right.val) &&
               isMirror(left.left, right.right) &&
               isMirror(left.right, right.left);
    }
    
    // 將一個二元樹轉換為其鏡像樹
    public TreeNode convertToMirror(TreeNode root) {
        if (root == null) return null;
        
        // 交換左右子樹
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        // 遞歸轉換左右子樹
        convertToMirror(root.left);
        convertToMirror(root.right);
        
        return root;
    }
    
    // 比較兩個二元樹是否互為鏡像
    public boolean areMirrors(TreeNode tree1, TreeNode tree2) {
        return isMirror(tree1, tree2);
    }
    
    // 創建樹的副本
    public TreeNode copyTree(TreeNode root) {
        if (root == null) return null;
        
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = copyTree(root.left);
        newNode.right = copyTree(root.right);
        return newNode;
    }
    
    // 前序遍歷印出樹
    public void preorderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }
    
    // 層序遍歷印出樹（更好地看出結構）
    public void levelOrderTraversal(TreeNode root) {
        if (root == null) return;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    System.out.print(node.val + " ");
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    System.out.print("null ");
                }
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        TreeMirror tm = new TreeMirror();
        
        // 測試對稱樹:      1
        //               / \
        //              2   2
        //             / \ / \
        //            3  4 4  3
        TreeNode symmetricTree = new TreeNode(1);
        symmetricTree.left = new TreeNode(2);
        symmetricTree.right = new TreeNode(2);
        symmetricTree.left.left = new TreeNode(3);
        symmetricTree.left.right = new TreeNode(4);
        symmetricTree.right.left = new TreeNode(4);
        symmetricTree.right.right = new TreeNode(3);
        
        System.out.println("對稱樹測試:");
        System.out.println("是否對稱: " + tm.isSymmetric(symmetricTree));
        
        // 測試非對稱樹:    1
        //               / \
        //              2   2
        //               \   \
        //                3   3
        TreeNode asymmetricTree = new TreeNode(1);
        asymmetricTree.left = new TreeNode(2);
        asymmetricTree.right = new TreeNode(2);
        asymmetricTree.left.right = new TreeNode(3);
        asymmetricTree.right.right = new TreeNode(3);
        
        System.out.println("非對稱樹測試:");
        System.out.println("是否對稱: " + tm.isSymmetric(asymmetricTree));
        
        // 測試鏡像轉換
        TreeNode originalTree = new TreeNode(1);
        originalTree.left = new TreeNode(2);
        originalTree.right = new TreeNode(3);
        originalTree.left.left = new TreeNode(4);
        originalTree.left.right = new TreeNode(5);
        
        System.out.println("\n原始樹（前序遍歷）:");
        tm.preorderTraversal(originalTree);
        System.out.println();
        
        TreeNode copiedTree = tm.copyTree(originalTree);
        TreeNode mirrorTree = tm.convertToMirror(copiedTree);
        
        System.out.println("鏡像樹（前序遍歷）:");
        tm.preorderTraversal(mirrorTree);
        System.out.println();
        
        System.out.println("原樹和鏡像樹是否互為鏡像: " + tm.areMirrors(originalTree, mirrorTree));
    }
}