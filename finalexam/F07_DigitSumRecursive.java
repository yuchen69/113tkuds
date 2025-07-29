import java.util.*;

public class F07_DigitSumRecursive {
    public static int digitSum(int n) {
        if (n < 10) return n;
        return n % 10 + digitSum(n / 10);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println("DigitSum: " + digitSum(num));
    }
}
/*
 * Time Complexity: O(log n)
 * 說明：每次遞迴除以 10，與數字位數成正比
 */