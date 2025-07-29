import java.util.*;

class TreeNode2 {
    int val;
    TreeNode2 left, right;
    TreeNode2(int val) {
        this.val = val;
    }
}

public class F12_TreeInOrderTraversal {
    public static void inOrder(TreeNode2 root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static TreeNode2 buildTree(Scanner sc) {
        int val = sc.nextInt();
        if (val == -1) return null;
        TreeNode2 node = new TreeNode2(val);
        node.left = buildTree(sc);
        node.right = buildTree(sc);
        return node;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeNode2 root = buildTree(sc);
        inOrder(root);
    }
}
/*
 * Time Complexity: O(n)
 * 說明：中序走訪每個節點一次
 */
