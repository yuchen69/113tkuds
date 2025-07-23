import java.util.*;

public class TreeDistance {
    
    // 定義樹節點
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent; // 用於某些演算法
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    /**
     * 計算樹中任意兩個節點之間的距離
     * 使用LCA（最低公共祖先）方法
     */
    public int findDistance(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || node1 == null || node2 == null) return -1;
        
        TreeNode lca = findLCA(root, node1, node2);
        if (lca == null) return -1;
        
        int dist1 = getDistance(lca, node1);
        int dist2 = getDistance(lca, node2);
        
        return dist1 + dist2;
    }
    
    /**
     * 通過節點值計算距離
     */
    public int findDistance(TreeNode root, int val1, int val2) {
        TreeNode node1 = findNode(root, val1);
        TreeNode node2 = findNode(root, val2);
        return findDistance(root, node1, node2);
    }
    
    /**
     * 找到最低公共祖先（LCA）
     */
    private TreeNode findLCA(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null) return null;
        
        if (root == node1 || root == node2) return root;
        
        TreeNode leftLCA = findLCA(root.left, node1, node2);
        TreeNode rightLCA = findLCA(root.right, node1, node2);
        
        if (leftLCA != null && rightLCA != null) return root;
        
        return leftLCA != null ? leftLCA : rightLCA;
    }
    
    /**
     * 計算從起始節點到目標節點的距離
     */
    private int getDistance(TreeNode start, TreeNode target) {
        if (start == null) return -1;
        if (start == target) return 0;
        
        int leftDist = getDistance(start.left, target);
        if (leftDist != -1) return leftDist + 1;
        
        int rightDist = getDistance(start.right, target);
        if (rightDist != -1) return rightDist + 1;
        
        return -1;
    }
    
    /**
     * 根據值找到節點
     */
    private TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        
        TreeNode left = findNode(root.left, val);
        if (left != null) return left;
        
        return findNode(root.right, val);
    }
    
    /**
     * 找出樹的直徑（任意兩個節點間的最大距離）
     */
    public int findDiameter(TreeNode root) {
        int[] diameter = {0};
        calculateDiameter(root, diameter);
        return diameter[0];
    }
    
    /**
     * 計算直徑的輔助函數
     * 返回以當前節點為根的子樹的最大深度
     */
    private int calculateDiameter(TreeNode node, int[] diameter) {
        if (node == null) return 0;
        
        int leftDepth = calculateDiameter(node.left, diameter);
        int rightDepth = calculateDiameter(node.right, diameter);
        
        // 更新直徑（通過當前節點的最長路徑）
        diameter[0] = Math.max(diameter[0], leftDepth + rightDepth);
        
        // 返回以當前節點為根的子樹的最大深度
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    /**
     * 找出距離根節點指定距離的所有節點
     */
    public List<Integer> findNodesAtDistance(TreeNode root, int distance) {
        List<Integer> result = new ArrayList<>();
        findNodesAtDistanceHelper(root, distance, result);
        return result;
    }
    
    /**
     * 遞迴找出指定距離的節點
     */
    private void findNodesAtDistanceHelper(TreeNode node, int distance, List<Integer> result) {
        if (node == null) return;
        
        if (distance == 0) {
            result.add(node.val);
            return;
        }
        
        findNodesAtDistanceHelper(node.left, distance - 1, result);
        findNodesAtDistanceHelper(node.right, distance - 1, result);
    }
    
    /**
     * 找出距離指定節點特定距離的所有節點（更複雜的版本）
     */
    public List<Integer> findNodesAtDistanceFromNode(TreeNode root, int targetVal, int distance) {
        List<Integer> result = new ArrayList<>();
        TreeNode target = findNode(root, targetVal);
        if (target == null) return result;
        
        // 使用BFS來找到距離目標節點特定距離的所有節點
        Map<TreeNode, TreeNode> parentMap = buildParentMap(root);
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.offer(target);
        visited.add(target);
        
        for (int level = 0; level < distance; level++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                
                // 檢查左子節點
                if (current.left != null && !visited.contains(current.left)) {
                    queue.offer(current.left);
                    visited.add(current.left);
                }
                
                // 檢查右子節點
                if (current.right != null && !visited.contains(current.right)) {
                    queue.offer(current.right);
                    visited.add(current.right);
                }
                
                // 檢查父節點
                TreeNode parent = parentMap.get(current);
                if (parent != null && !visited.contains(parent)) {
                    queue.offer(parent);
                    visited.add(parent);
                }
            }
        }
        
        // 收集結果
        while (!queue.isEmpty()) {
            result.add(queue.poll().val);
        }
        
        return result;
    }
    
    /**
     * 建立父節點映射
     */
    private Map<TreeNode, TreeNode> buildParentMap(TreeNode root) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMapHelper(root, null, parentMap);
        return parentMap;
    }
    
    private void buildParentMapHelper(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (node == null) return;
        
        parentMap.put(node, parent);
        buildParentMapHelper(node.left, node, parentMap);
        buildParentMapHelper(node.right, node, parentMap);
    }
    
    /**
     * 獲取所有節點對之間的距離
     */
    public void printAllDistances(TreeNode root) {
        List<TreeNode> nodes = getAllNodes(root);
        System.out.println("所有節點對之間的距離：");
        
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                TreeNode node1 = nodes.get(i);
                TreeNode node2 = nodes.get(j);
                int distance = findDistance(root, node1, node2);
                System.out.printf("節點 %d 到節點 %d 的距離: %d\n", 
                                node1.val, node2.val, distance);
            }
        }
    }
    
    /**
     * 獲取樹中所有節點
     */
    private List<TreeNode> getAllNodes(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        getAllNodesHelper(root, nodes);
        return nodes;
    }
    
    private void getAllNodesHelper(TreeNode node, List<TreeNode> nodes) {
        if (node == null) return;
        
        nodes.add(node);
        getAllNodesHelper(node.left, nodes);
        getAllNodesHelper(node.right, nodes);
    }
    
    /**
     * 建立測試用的樹
     */
    public TreeNode buildTestTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        
        return root;
    }
    
    /**
     * 層次遍歷打印樹
     */
    public void levelOrderTraversal(TreeNode root) {
        if (root == null) return;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            System.out.println();
        }
    }
    
    // 測試主函數
    public static void main(String[] args) {
        TreeDistance td = new TreeDistance();
        
        System.out.println("=== 樹的距離計算測試 ===");
        
        TreeNode root = td.buildTestTree();
        System.out.println("測試樹的層次遍歷：");
        td.levelOrderTraversal(root);
        
        // 測試兩個節點之間的距離
        System.out.println("\n1. 測試節點距離計算：");
        System.out.println("節點 4 到節點 5 的距離: " + td.findDistance(root, 4, 5));
        System.out.println("節點 4 到節點 6 的距離: " + td.findDistance(root, 4, 6));
        System.out.println("節點 8 到節點 7 的距離: " + td.findDistance(root, 8, 7));
        
        // 測試樹的直徑
        System.out.println("\n2. 測試樹的直徑：");
        int diameter = td.findDiameter(root);
        System.out.println("樹的直徑: " + diameter);
        
        // 測試距離根節點指定距離的節點
        System.out.println("\n3. 測試距離根節點指定距離的節點：");
        for (int dist = 0; dist <= 3; dist++) {
            List<Integer> nodes = td.findNodesAtDistance(root, dist);
            System.out.println("距離根節點 " + dist + " 的節點: " + nodes);
        }
        
        // 測試距離指定節點特定距離的所有節點
        System.out.println("\n4. 測試距離指定節點特定距離的所有節點：");
        List<Integer> nodesAt2FromNode2 = td.findNodesAtDistanceFromNode(root, 2, 2);
        System.out.println("距離節點 2 距離為 2 的所有節點: " + nodesAt2FromNode2);
        
        List<Integer> nodesAt1FromNode4 = td.findNodesAtDistanceFromNode(root, 4, 1);
        System.out.println("距離節點 4 距離為 1 的所有節點: " + nodesAt1FromNode4);
        
        // 打印所有節點對的距離（小樹才適用）
        System.out.println("\n5. 所有節點對的距離：");
        td.printAllDistances(root);
    }
}