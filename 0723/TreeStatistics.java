
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}

public class TreeStatistics {
    
    // 計算樹中所有節點值的總和
    public int calculateSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + calculateSum(root.left) + calculateSum(root.right);
    }
    
    // 找出樹中的最大值
    public int findMax(TreeNode root) {
        if (root == null) throw new IllegalArgumentException("Empty tree");
        int max = root.val;
        if (root.left != null) {
            max = Math.max(max, findMax(root.left));
        }
        if (root.right != null) {
            max = Math.max(max, findMax(root.right));
        }
        return max;
    }
    
    // 找出樹中的最小值
    public int findMin(TreeNode root) {
        if (root == null) throw new IllegalArgumentException("Empty tree");
        int min = root.val;
        if (root.left != null) {
            min = Math.min(min, findMin(root.left));
        }
        if (root.right != null) {
            min = Math.min(min, findMin(root.right));
        }
        return min;
    }
    
    // 計算所有葉節點的數量
    public int countLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return countLeaves(root.left) + countLeaves(root.right);
    }
    
    // 計算樹的深度（高度）
    public int calculateDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(calculateDepth(root.left), calculateDepth(root.right));
    }
    
    // 測試主函數
    public static void main(String[] args) {
        TreeStatistics ts = new TreeStatistics();
        
        // 建立測試樹:       10
        //                 /  \
        //                5    15
        //               /    /  \
        //              3    12   20
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(20);
        
        System.out.println("樹的統計資訊：");
        System.out.println("總和: " + ts.calculateSum(root));
        System.out.println("最大值: " + ts.findMax(root));
        System.out.println("最小值: " + ts.findMin(root));
        System.out.println("葉節點數量: " + ts.countLeaves(root));
        System.out.println("樹的深度: " + ts.calculateDepth(root));
    }
}