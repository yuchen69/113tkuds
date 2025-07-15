import java.util.*;
public class Q5_CPBLPrefixWins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] tokens = sc.nextLine().split(" ");
        int[] games = new int[n];
        for (int i = 0; i < n; i++) games[i] = Integer.parseInt(tokens[i]);
        int k = Integer.parseInt(sc.nextLine());
        int[] ps = new int[n + 1];
        for (int i = 1; i <= n; i++) ps[i] = ps[i - 1] + games[i - 1];
        System.out.print("PrefixSum:");
        for (int i = 1; i <= k; i++) System.out.print(" " + ps[i]);
        System.out.println();
    }
}
/*
 * Time Complexity: O(n)
 * 說明：前綴和表建立一次走訪，查詢 O(1)
 */