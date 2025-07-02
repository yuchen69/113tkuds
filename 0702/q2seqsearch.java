import java.util.Scanner;

public class q2seqsearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int key = sc.nextInt();
        int pos = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] == key) {
                pos = i + 1;
                break;
            }
        }

        if (pos > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        System.out.println(pos);
    }
}