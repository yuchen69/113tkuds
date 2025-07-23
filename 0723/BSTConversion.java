import java.util.*;

public class BSTConversion {
    
    // 二元樹節點類別
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // 雙向鏈表節點類別（重用TreeNode，left當作prev，right當作next）
    static class DoublyLinkedListNode {
        int val;
        DoublyLinkedListNode prev;
        DoublyLinkedListNode next;
        
        DoublyLinkedListNode(int val) {
            this.val = val;
        }
    }
    
    /**
     * 1. 將BST轉換為排序的雙向鏈表
     * @param root BST的根節點
     * @return 雙向鏈表的頭節點
     */
    public static DoublyLinkedListNode bstToDoublyLinkedList(TreeNode root) {
        if (root == null) return null;
        
        // 使用中序遍歷來獲得有序序列
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);
        
        // 建立雙向鏈表
        DoublyLinkedListNode head = new DoublyLinkedListNode(inorderList.get(0));
        DoublyLinkedListNode current = head;
        
        for (int i = 1; i < inorderList.size(); i++) {
            DoublyLinkedListNode newNode = new DoublyLinkedListNode(inorderList.get(i));
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        
        return head;
    }
    
    /**
     * 原地將BST轉換為雙向鏈表（更高效的版本）
     */
    private static TreeNode prev = null;
    
    public static TreeNode bstToDoublyLinkedListInPlace(TreeNode root) {
        if (root == null) return null;
        
        prev = null;
        TreeNode head = null;
        
        // 找到最左邊的節點作為頭節點
        TreeNode curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        head = curr;
        
        // 重置prev並進行轉換
        prev = null;
        convertToDoublyLinkedList(root);
        
        return head;
    }
    
    private static void convertToDoublyLinkedList(TreeNode root) {
        if (root == null) return;
        
        convertToDoublyLinkedList(root.left);
        
        // 處理當前節點
        if (prev != null) {
            prev.right = root;
            root.left = prev;
        }
        prev = root;
        
        convertToDoublyLinkedList(root.right);
    }
    
    /**
     * 2. 將排序陣列轉換為平衡的BST
     * @param nums 排序好的陣列
     * @return 平衡BST的根節點
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return arrayToBSTHelper(nums, 0, nums.length - 1);
    }
    
    private static TreeNode arrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        
        // 選擇中間元素作為根節點，確保平衡
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        
        // 遞歸建立左右子樹
        root.left = arrayToBSTHelper(nums, left, mid - 1);
        root.right = arrayToBSTHelper(nums, mid + 1, right);
        
        return root;
    }
    
    /**
     * 3. 將BST的每個節點值改為所有大於等於該節點值的節點值總和
     * @param root BST的根節點
     * @return 修改後的樹的根節點
     */
    public static TreeNode bstToGreaterSumTree(TreeNode root) {
        if (root == null) return null;
        
        // 使用反向中序遍歷（右->根->左），維護累積和
        int[] sum = {0}; // 使用陣列來模擬引用傳遞
        reverseInorderTraversal(root, sum);
        
        return root;
    }
    
    private static void reverseInorderTraversal(TreeNode root, int[] sum) {
        if (root == null) return;
        
        // 先遍歷右子樹
        reverseInorderTraversal(root.right, sum);
        
        // 處理當前節點
        sum[0] += root.val;
        root.val = sum[0];
        
        // 再遍歷左子樹
        reverseInorderTraversal(root.left, sum);
    }
    
    // 輔助方法：中序遍歷
    private static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }
    
    // 輔助方法：創建BST
    public static TreeNode createBST(int[] values) {
        TreeNode root = null;
        for (int val : values) {
            root = insertIntoBST(root, val);
        }
        return root;
    }
    
    private static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        
        return root;
    }
    
    // 輔助方法：打印樹
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
    
    // 輔助方法：打印雙向鏈表
    public static void printDoublyLinkedList(DoublyLinkedListNode head) {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        
        System.out.print("正向：");
        DoublyLinkedListNode curr = head;
        DoublyLinkedListNode tail = null;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" <-> ");
            tail = curr;
            curr = curr.next;
        }
        System.out.println();
        
        System.out.print("反向：");
        curr = tail;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.prev != null) System.out.print(" <-> ");
            curr = curr.prev;
        }
        System.out.println();
    }
    
    // 測試方法
    public static void main(String[] args) {
        System.out.println("=== BST轉換測試 ===");
        
        // 創建測試BST: [4,2,6,1,3,5,7]
        int[] bstValues = {4, 2, 6, 1, 3, 5, 7};
        TreeNode bst = createBST(bstValues);
        
        System.out.println("原始BST：");
        printTree(bst);
        
        // 1. BST轉雙向鏈表
        System.out.println("\n1. BST轉換為排序雙向鏈表：");
        DoublyLinkedListNode dll = bstToDoublyLinkedList(bst);
        printDoublyLinkedList(dll);
        
        // 2. 排序陣列轉BST
        System.out.println("\n2. 排序陣列轉平衡BST：");
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("輸入陣列：" + Arrays.toString(sortedArray));
        TreeNode balancedBST = sortedArrayToBST(sortedArray);
        System.out.println("轉換後的平衡BST：");
        printTree(balancedBST);
        
        // 3. BST轉Greater Sum Tree
        System.out.println("\n3. BST轉Greater Sum Tree：");
        TreeNode originalBST = createBST(new int[]{4, 2, 6, 1, 3, 5, 7});
        System.out.println("轉換前：");
        printTree(originalBST);
        
        TreeNode greaterSumTree = bstToGreaterSumTree(originalBST);
        System.out.println("轉換後（每個節點值為原本所有>=該值的節點總和）：");
        printTree(greaterSumTree);
        
        // 測試邊界情況
        System.out.println("\n4. 測試邊界情況：");
        
        // 空樹
        System.out.println("空樹轉雙向鏈表：");
        DoublyLinkedListNode emptyDLL = bstToDoublyLinkedList(null);
        printDoublyLinkedList(emptyDLL);
        
        // 空陣列轉BST
        System.out.println("空陣列轉BST：");
        TreeNode emptyBST = sortedArrayToBST(new int[]{});
        printTree(emptyBST);
        
        // 單節點
        System.out.println("單節點BST測試：");
        TreeNode singleNode = new TreeNode(5);
        System.out.println("原始單節點BST：");
        printTree(singleNode);
        
        DoublyLinkedListNode singleDLL = bstToDoublyLinkedList(singleNode);
        System.out.println("轉換為雙向鏈表：");
        printDoublyLinkedList(singleDLL);
    }
}