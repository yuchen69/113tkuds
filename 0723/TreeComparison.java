public class TreeComparison {
    static class TreeNode {
        int data;
        TreeNode left, right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.data != b.data) return false;
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    public static boolean isSubtree(TreeNode a, TreeNode b) {
        if (b == null) return true;
        if (a == null) return false;
        if (isSameTree(a, b)) return true;
        return isSubtree(a.left, b) || isSubtree(a.right, b);
    }

    public static TreeNode largestCommonSubtree(TreeNode a, TreeNode b) {
        if (a == null || b == null) return null;
        if (isSameTree(a, b)) return a;

        TreeNode left = largestCommonSubtree(a.left, b);
        TreeNode right = largestCommonSubtree(a.right, b);
        if (left != null) return left;
        return right;
    }

    public static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }

    public static void main(String[] args) {
        /*
         Tree A:
               1
              / \
             2   3
            /
           4

         Tree B:
             2
            /
           4
        */
        TreeNode A = new TreeNode(1);
        A.left = new TreeNode(2);
        A.right = new TreeNode(3);
        A.left.left = new TreeNode(4);

        TreeNode B = new TreeNode(2);
        B.left = new TreeNode(4);

        TreeNode C = new TreeNode(1);
        C.left = new TreeNode(2);
        C.right = new TreeNode(3);
        C.left.left = new TreeNode(4);

        System.out.println("A 與 B 是否完全相同: " + isSameTree(A, B));
        System.out.println("B 是否為 A 的子樹: " + isSubtree(A, B));
        System.out.println("A 與 C 是否完全相同: " + isSameTree(A, C));

        TreeNode common = largestCommonSubtree(A, C);
        System.out.print("最大公共子樹中序遍歷: ");
        printInOrder(common);
        System.out.println();
    }
}