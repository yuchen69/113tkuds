import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
    }
}

public class F09_TreeHeightRecursive {
    public static int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static TreeNode buildTree(Scanner sc) {
        int val = sc.nextInt();
        if (val == -1) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildTree(sc);
        node.right = buildTree(sc);
        return node;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeNode root = buildTree(sc);
        System.out.println("Height: " + height(root));
    }
}
/*
 * Time Complexity: O(n)
 * 說明：每個節點訪問一次
 */