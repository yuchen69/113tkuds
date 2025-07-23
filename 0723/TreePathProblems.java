import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}

public class TreePathProblems {
    
    // 1. 找出從根節點到所有葉節點的路徑
    public List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        List<Integer> currentPath = new ArrayList<>();
        findAllPaths(root, currentPath, result);
        return result;
    }
    
    private void findAllPaths(TreeNode node, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) return;
        
        // 將當前節點加入路徑
        currentPath.add(node.val);
        
        // 如果是葉節點，將路徑加入結果
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(currentPath));
        } else {
            // 遞歸探索左右子樹
            findAllPaths(node.left, currentPath, result);
            findAllPaths(node.right, currentPath, result);
        }
        
        // 回溯：移除當前節點
        currentPath.remove(currentPath.size() - 1);
    }
    
    // 2. 判斷樹中是否存在和為目標值的根到葉路徑
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        
        // 如果是葉節點，檢查當前值是否等於目標和
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        
        // 遞歸檢查左右子樹
        int remainingSum = targetSum - root.val;
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }
    
    // 找出所有和為目標值的根到葉路徑
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPathSum(root, targetSum, currentPath, result);
        return result;
    }
    
    private void findPathSum(TreeNode node, int targetSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) return;
        
        currentPath.add(node.val);
        
        // 如果是葉節點且路徑和等於目標值
        if (node.left == null && node.right == null && targetSum == node.val) {
            result.add(new ArrayList<>(currentPath));
        } else {
            // 遞歸探索左右子樹
            findPathSum(node.left, targetSum - node.val, currentPath, result);
            findPathSum(node.right, targetSum - node.val, currentPath, result);
        }
        
        // 回溯
        currentPath.remove(currentPath.size() - 1);
    }
    
    // 3. 找出樹中和最大的根到葉路徑
    public List<Integer> maxPathSum(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        MaxPathResult result = new MaxPathResult();
        List<Integer> currentPath = new ArrayList<>();
        findMaxPathSum(root, 0, currentPath, result);
        return result.maxPath;
    }
    
    private void findMaxPathSum(TreeNode node, int currentSum, List<Integer> currentPath, MaxPathResult result) {
        if (node == null) return;
        
        currentPath.add(node.val);
        currentSum += node.val;
        
        // 如果是葉節點，檢查是否為最大和
        if (node.left == null && node.right == null) {
            if (currentSum > result.maxSum) {
                result.maxSum = currentSum;
                result.maxPath = new ArrayList<>(currentPath);
            }
        } else {
            // 遞歸探索左右子樹
            findMaxPathSum(node.left, currentSum, currentPath, result);
            findMaxPathSum(node.right, currentSum, currentPath, result);
        }
        
        // 回溯
        currentPath.remove(currentPath.size() - 1);
    }
    
    // 輔助類別儲存最大路徑結果
    class MaxPathResult {
        int maxSum = Integer.MIN_VALUE;
        List<Integer> maxPath = new ArrayList<>();
    }
    
    // 額外功能：計算所有根到葉路徑的和
    public List<Integer> allPathSums(TreeNode root) {
        List<Integer> sums = new ArrayList<>();
        calculateAllPathSums(root, 0, sums);
        return sums;
    }
    
    private void calculateAllPathSums(TreeNode node, int currentSum, List<Integer> sums) {
        if (node == null) return;
        
        currentSum += node.val;
        
        // 如果是葉節點，記錄路徑和
        if (node.left == null && node.right == null) {
            sums.add(currentSum);
        } else {
            calculateAllPathSums(node.left, currentSum, sums);
            calculateAllPathSums(node.right, currentSum, sums);
        }
    }
    
    // 找出從根節點到特定節點的路徑
    public List<Integer> pathToNode(TreeNode root, int target) {
        List<Integer> path = new ArrayList<>();
        if (findPathToNode(root, target, path)) {
            return path;
        }
        return new ArrayList<>(); // 未找到
    }
    
    private boolean findPathToNode(TreeNode node, int target, List<Integer> path) {
        if (node == null) return false;
        
        path.add(node.val);
        
        // 找到目標節點
        if (node.val == target) return true;
        
        // 在左右子樹中尋找
        if (findPathToNode(node.left, target, path) || findPathToNode(node.right, target, path)) {
            return true;
        }
        
        // 回溯
        path.remove(path.size() - 1);
        return false;
    }
    
    // 計算從根到葉的最大路徑長度
    public int maxPathLength(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        
        int leftLength = root.left != null ? maxPathLength(root.left) : 0;
        int rightLength = root.right != null ? maxPathLength(root.right) : 0;
        
        return 1 + Math.max(leftLength, rightLength);
    }
    
    public static void main(String[] args) {
        TreePathProblems tpp = new TreePathProblems();
        
        // 建立測試樹:      5
        //               /   \
        //              4     8
        //             /     / \
        //            11    13  4
        //           / \       / \
        //          7   2     5   1
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        
        // 測試1: 找出所有根到葉路徑
        System.out.println("所有根到葉路徑:");
        List<List<Integer>> allPaths = tpp.allRootToLeafPaths(root);
        for (int i = 0; i < allPaths.size(); i++) {
            System.out.println("路徑" + (i + 1) + ": " + allPaths.get(i));
        }
        
        // 測試2: 檢查路徑和
        int targetSum = 22;
        System.out.println("\n是否存在和為" + targetSum + "的路徑: " + tpp.hasPathSum(root, targetSum));
        
        System.out.println("所有和為" + targetSum + "的路徑:");
        List<List<Integer>> pathsWithSum = tpp.pathSum(root, targetSum);
        for (List<Integer> path : pathsWithSum) {
            System.out.println(path);
        }
        
        // 測試3: 找出最大和路徑
        List<Integer> maxPath = tpp.maxPathSum(root);
        System.out.println("\n最大和路徑: " + maxPath);
        
        // 測試所有路徑和
        List<Integer> allSums = tpp.allPathSums(root);
        System.out.println("所有路徑和: " + allSums);
        System.out.println("最大路徑和: " + Collections.max(allSums));
        
        // 測試找到特定節點的路徑
        int targetNode = 13;
        List<Integer> pathToTarget = tpp.pathToNode(root, targetNode);
        System.out.println("到節點" + targetNode + "的路徑: " + pathToTarget);
        
        // 測試最大路徑長度
        System.out.println("最大路徑長度: " + tpp.maxPathLength(root));
    }
}