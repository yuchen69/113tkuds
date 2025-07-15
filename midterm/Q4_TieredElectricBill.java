import java.util.*;
public class Q4_TieredElectricBill {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int total = 0;
        for (int i = 0; i < n; i++) {
            int kWh = Integer.parseInt(sc.nextLine());
            int bill = calc(kWh);
            total += bill;
            System.out.println("Bill: $" + bill);
        }
        System.out.println("Total: $" + total);
        System.out.println("Average: $" + Math.round((double) total / n));
    }
    public static int calc(int k) {
        int[] limit = {120, 330, 500, 700, 1000};
        double[] rate = {1.68, 2.45, 3.70, 5.04, 6.24, 8.46};
        int[] usage = new int[6];
        int[] bound = {120, 210, 170, 200, 300};
        int remaining = k;
        for (int i = 0; i < 5; i++) {
            if (remaining > 0) {
                usage[i] = Math.min(bound[i], remaining);
                remaining -= usage[i];
            }
        }
        usage[5] = remaining;
        double cost = 0;
        for (int i = 0; i < 6; i++) {
            cost += usage[i] * rate[i];
        }
        return (int) Math.round(cost);
    }
}
/*
 * Time Complexity: O(n)
 * 說明：走訪 n 筆資料，每筆計算費率固定時間
 */