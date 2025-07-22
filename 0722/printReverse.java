class ListNode {
    int data;
    ListNode next;
    public ListNode(int data) {
        this.data = data;
    }
}

public class printReverse {
    public static void printReverse(ListNode head) {
        if (head == null) return;
        printReverse(head.next);
        System.out.print(head.data + " ");
    }
}
