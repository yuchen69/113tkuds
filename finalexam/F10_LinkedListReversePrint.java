import java.util.*;

class Node {
    int val;
    Node next;
    Node(int val) {
        this.val = val;
    }
}

public class F10_LinkedListReversePrint {
    public static void reversePrint(Node head) {
        if (head == null) return;
        reversePrint(head.next);
        System.out.println(head.val);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node dummy = new Node(0);
        Node curr = dummy;
        for (int i = 0; i < n; i++) {
            curr.next = new Node(sc.nextInt());
            curr = curr.next;
        }
        reversePrint(dummy.next);
    }
}
/*
 * Time Complexity: O(n)
 * 說明：每個節點處理一次，使用遞迴印出反向
 */