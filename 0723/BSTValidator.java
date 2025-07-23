public class BSTValidator {
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        
        public TreeNode(int data) {
            this.data = data;
        }
    }
    
    // 驗證是否為有效的BST
    public static boolean isValidBST(TreeNode root) {
        return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private static boolean validate(TreeNode node, int min, int max) {
        if (node == null) return true;
        
        if (node.data <= min || node.data >= max) {
            return false;
        }
        
        return validate(node.left, min, node.data) && 
               validate(node.right, node.data, max);
    }
    
    public static void main(String[] args) {
        // 建立有效的BST
        TreeNode validBST = new TreeNode(50);
        validBST.left = new TreeNode(30);
        validBST.right = new TreeNode(70);
        validBST.left.left = new TreeNode(20);
        validBST.left.right = new TreeNode(40);
        
        // 建立無效的BST
        TreeNode invalidBST = new TreeNode(50);
        invalidBST.left = new TreeNode(30);
        invalidBST.right = new TreeNode(70);
        invalidBST.left.left = new TreeNode(20);
        invalidBST.left.right = new TreeNode(60); // 錯誤：60應該在右子樹
        
        System.out.println("第一個樹是有效的BST: " + isValidBST(validBST));
        System.out.println("第二個樹是有效的BST: " + isValidBST(invalidBST));
    }
}