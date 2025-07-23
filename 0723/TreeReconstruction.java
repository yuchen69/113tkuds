import java.util.*;

public class TreeReconstruction {
    
    // 二元樹節點類別
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
        
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    /**
     * 1. 根據前序和中序遍歷結果重建二元樹
     * @param preorder 前序遍歷結果
     * @param inorder 中序遍歷結果
     * @return 重建的二元樹根節點
     */
    public static TreeNode buildTreeFromPreAndIn(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        
        // 建立中序遍歷的值與索引映射，提高查找效率
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return buildPreIn(preorder, 0, preorder.length - 1, 0, inorderMap);
    }
    
    private static TreeNode buildPreIn(int[] preorder, int preStart, int preEnd, 
                                      int inStart, Map<Integer, Integer> inorderMap) {
        if (preStart > preEnd) {
            return null;
        }
        
        // 前序遍歷的第一個元素是根節點
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        
        // 在中序遍歷中找到根節點的位置
        int rootIndex = inorderMap.get(rootVal);
        int leftSize = rootIndex - inStart;
        
        // 遞歸建立左右子樹
        root.left = buildPreIn(preorder, preStart + 1, preStart + leftSize, 
                              inStart, inorderMap);
        root.right = buildPreIn(preorder, preStart + leftSize + 1, preEnd, 
                               rootIndex + 1, inorderMap);
        
        return root;
    }
    
    /**
     * 2. 根據後序和中序遍歷結果重建二元樹
     * @param postorder 後序遍歷結果
     * @param inorder 中序遍歷結果
     * @return 重建的二元樹根節點
     */
    public static TreeNode buildTreeFromPostAndIn(int[] postorder, int[] inorder) {
        if (postorder == null || inorder == null || postorder.length != inorder.length) {
            return null;
        }
        
        // 建立中序遍歷的值與索引映射
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return buildPostIn(postorder, 0, postorder.length - 1, 0, inorderMap);
    }
    
    private static TreeNode buildPostIn(int[] postorder, int postStart, int postEnd, 
                                       int inStart, Map<Integer, Integer> inorderMap) {
        if (postStart > postEnd) {
            return null;
        }
        
        // 後序遍歷的最後一個元素是根節點
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        
        // 在中序遍歷中找到根節點的位置
        int rootIndex = inorderMap.get(rootVal);
        int leftSize = rootIndex - inStart;
        
        // 遞歸建立左右子樹
        root.left = buildPostIn(postorder, postStart, postStart + leftSize - 1, 
                               inStart, inorderMap);
        root.right = buildPostIn(postorder, postStart + leftSize, postEnd - 1, 
                                rootIndex + 1, inorderMap);
        
        return root;
    }
    
    /**
     * 3. 驗證重建的樹是否正確
     * 透過比較原始遍歷結果與重建樹的遍歷結果
     */
    public static boolean validateReconstruction(TreeNode root, int[] preorder, 
                                               int[] inorder, int[] postorder) {
        if (root == null) {
            return preorder.length == 0 && inorder.length == 0 && postorder.length == 0;
        }
        
        List<Integer> reconstructedPre = new ArrayList<>();
        List<Integer> reconstructedIn = new ArrayList<>();
        List<Integer> reconstructedPost = new ArrayList<>();
        
        preorderTraversal(root, reconstructedPre);
        inorderTraversal(root, reconstructedIn);
        postorderTraversal(root, reconstructedPost);
        
        return Arrays.equals(preorder, reconstructedPre.stream().mapToInt(i -> i).toArray()) &&
               Arrays.equals(inorder, reconstructedIn.stream().mapToInt(i -> i).toArray()) &&
               Arrays.equals(postorder, reconstructedPost.stream().mapToInt(i -> i).toArray());
    }
    
    // 前序遍歷
    private static void preorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        preorderTraversal(root.left, result);
        preorderTraversal(root.right, result);
    }
    
    // 中序遍歷
    private static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }
    
    // 後序遍歷
    private static void postorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;
        postorderTraversal(root.left, result);
        postorderTraversal(root.right, result);
        result.add(root.val);
    }
    
    // 輔助方法：打印樹的結構
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        printTreeHelper(root, "", true);
    }
    
    private static void printTreeHelper(TreeNode node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.val);
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    printTreeHelper(node.left, prefix + (isLast ? "    " : "│   "), node.right == null);
                }
                if (node.right != null) {
                    printTreeHelper(node.right, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }
    
    // 測試方法
    public static void main(String[] args) {
        System.out.println("=== 二元樹重建測試 ===");
        
        // 測試資料
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        
        System.out.println("原始遍歷結果：");
        System.out.println("前序：" + Arrays.toString(preorder));
        System.out.println("中序：" + Arrays.toString(inorder));
        System.out.println("後序：" + Arrays.toString(postorder));
        
        // 測試前序+中序重建
        System.out.println("\n1. 使用前序+中序重建：");
        TreeNode root1 = buildTreeFromPreAndIn(preorder, inorder);
        printTree(root1);
        
        // 驗證重建結果
        boolean valid1 = validateReconstruction(root1, preorder, inorder, postorder);
        System.out.println("重建驗證結果：" + (valid1 ? "正確" : "錯誤"));
        
        // 測試後序+中序重建
        System.out.println("\n2. 使用後序+中序重建：");
        TreeNode root2 = buildTreeFromPostAndIn(postorder, inorder);
        printTree(root2);
        
        // 驗證重建結果
        boolean valid2 = validateReconstruction(root2, preorder, inorder, postorder);
        System.out.println("重建驗證結果：" + (valid2 ? "正確" : "錯誤"));
        
        // 測試邊界情況
        System.out.println("\n3. 測試空樹：");
        TreeNode emptyRoot = buildTreeFromPreAndIn(new int[]{}, new int[]{});
        System.out.println("空樹重建結果：" + (emptyRoot == null ? "null（正確）" : "非null（錯誤）"));
        
        // 測試單節點
        System.out.println("\n4. 測試單節點：");
        int[] singlePre = {1};
        int[] singleIn = {1};
        int[] singlePost = {1};
        TreeNode singleRoot = buildTreeFromPreAndIn(singlePre, singleIn);
        printTree(singleRoot);
        boolean singleValid = validateReconstruction(singleRoot, singlePre, singleIn, singlePost);
        System.out.println("單節點重建驗證：" + (singleValid ? "正確" : "錯誤"));
    }
}