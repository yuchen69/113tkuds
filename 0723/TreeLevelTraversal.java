import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}

public class TreeLevelTraversal {
    
    // 1. 將每一層的節點分別儲存在不同的List中
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    // 2. 之字形層序遍歷（鋸齒狀遍歷）
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                if (leftToRight) {
                    currentLevel.add(node.val);
                } else {
                    currentLevel.add(0, node.val); // 插入到開頭，實現反向
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            result.add(currentLevel);
            leftToRight = !leftToRight; // 切換方向
        }
        
        return result;
    }
    
    // 3. 只印出每一層的最後一個節點
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                // 如果是該層最後一個節點，加入結果
                if (i == levelSize - 1) {
                    result.add(node.val);
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return result;
    }
    
    // 額外功能：印出每一層的第一個節點
    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                // 如果是該層第一個節點，加入結果
                if (i == 0) {
                    result.add(node.val);
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return result;
    }
    
    // 輔助方法：使用遞歸實現層序遍歷
    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrderRecursiveHelper(root, 0, result);
        return result;
    }
    
    private void levelOrderRecursiveHelper(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) return;
        
        // 如果這是新的一層，創建新的列表
        if (level >= result.size()) {
            result.add(new ArrayList<>());
        }
        
        // 將當前節點加入對應層級
        result.get(level).add(node.val);
        
        // 遞歸處理左右子樹
        levelOrderRecursiveHelper(node.left, level + 1, result);
        levelOrderRecursiveHelper(node.right, level + 1, result);
    }
    
    // 計算樹的最大寬度（某一層的最大節點數）
    public int maxWidth(TreeNode root) {
        if (root == null) return 0;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return maxWidth;
    }
    
    public static void main(String[] args) {
        TreeLevelTraversal tlt = new TreeLevelTraversal();
        
        // 建立測試樹:          3
        //                   /   \
        //                  9     20
        //                       /  \
        //                      15   7
        //                     /
        //                    12
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.right.left.left = new TreeNode(12);
        
        // 測試普通層序遍歷
        System.out.println("普通層序遍歷（按層分組）:");
        List<List<Integer>> levelOrder = tlt.levelOrder(root);
        for (int i = 0; i < levelOrder.size(); i++) {
            System.out.println("第" + (i + 1) + "層: " + levelOrder.get(i));
        }
        
        // 測試之字形層序遍歷
        System.out.println("\n之字形層序遍歷:");
        List<List<Integer>> zigzag = tlt.zigzagLevelOrder(root);
        for (int i = 0; i < zigzag.size(); i++) {
            System.out.println("第" + (i + 1) + "層: " + zigzag.get(i));
        }
        
        // 測試右側視圖（每層最後一個節點）
        System.out.println("\n右側視圖（每層最後一個節點）:");
        List<Integer> rightView = tlt.rightSideView(root);
        System.out.println(rightView);
        
        // 測試左側視圖（每層第一個節點）
        System.out.println("\n左側視圖（每層第一個節點）:");
        List<Integer> leftView = tlt.leftSideView(root);
        System.out.println(leftView);
        
        // 測試遞歸版本
        System.out.println("\n遞歸版本層序遍歷:");
        List<List<Integer>> recursiveResult = tlt.levelOrderRecursive(root);
        for (int i = 0; i < recursiveResult.size(); i++) {
            System.out.println("第" + (i + 1) + "層: " + recursiveResult.get(i));
        }
        
        // 測試最大寬度
        System.out.println("\n樹的最大寬度: " + tlt.maxWidth(root));
    }
}