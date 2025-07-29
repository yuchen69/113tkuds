import java.util.*;

public class F04_TieredIncomeTax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] income = new int[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            income[i] = sc.nextInt();
            int tax = calcTax(income[i]);
            System.out.println("Tax: $" + tax);
            total += tax;
        }
        System.out.println("Average: $" + (total / n));
    }

    public static int calcTax(int inc) {
        int tax = 0;
        int[] limits = {540000, 1210000, 2420000, 4530000};
        double[] rates = {0.05, 0.12, 0.2, 0.3, 0.4};
        int[] base = {0, 27000, 142800, 376600, 829600};
        if (inc <= limits[0]) return (int)(inc * rates[0]);
        else if (inc <= limits[1]) return (int)(inc * rates[1] - base[1]);
        else if (inc <= limits[2]) return (int)(inc * rates[2] - base[2]);
        else if (inc <= limits[3]) return (int)(inc * rates[3] - base[3]);
        else return (int)(inc * rates[4] - base[4]);
    }
}
/*
 * Time Complexity: O(n)
 * 說明：線性處理 n 個所得稅入
 */
