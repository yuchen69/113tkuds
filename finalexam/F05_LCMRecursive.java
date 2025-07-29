import java.util.*;

public class F05_LCMRecursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int gcd = gcd(Math.max(a, b), Math.min(a, b));
        System.out.println("LCM: " + (a * b / gcd));
    }

    public static int gcd(int a, int b) {
        if (a == b) return a;
        return a > b ? gcd(a - b, b) : gcd(a, b - a);
    }
}
/*
 * Time Complexity: O(max(a,b))
 * 說明：每次減少一，最壞情況是 a=b-1
 */
