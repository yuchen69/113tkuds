import java.util.*;

public class F03_ConvenienceHotItems {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] names = new String[n];
        int[] qty = new int[n];
        for (int i = 0; i < n; i++) {
            names[i] = sc.next();
            qty[i] = sc.nextInt();
        }
        for (int i = 1; i < n; i++) {
            int key = qty[i];
            String name = names[i];
            int j = i - 1;
            while (j >= 0 && qty[j] < key) {
                qty[j + 1] = qty[j];
                names[j + 1] = names[j];
                j--;
            }
            qty[j + 1] = key;
            names[j + 1] = name;
        }
        for (int i = 0; i < Math.min(n, 10); i++) {
            System.out.println(names[i] + " " + qty[i]);
        }
    }
}
/*
 * Time Complexity: O(n^2)
 * 說明：插入排序的最壞情況是全數量倒縱
 */