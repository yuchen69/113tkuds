import java.util.*;

public class F02_YouBikeNextFull {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split(":");
            times[i] = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        }
        String[] q = sc.nextLine().split(":");
        int query = Integer.parseInt(q[0]) * 60 + Integer.parseInt(q[1]);

        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (times[mid] > query) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (ans == -1) System.out.println("No bike");
        else System.out.printf("%02d:%02d\n", times[ans] / 60, times[ans] % 60);
    }
}
/*
 * Time Complexity: O(log n)
 * 說明：使用了二分搜尋在排序陸降的數列
 */