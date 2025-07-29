import java.util.*;

public class F08_FibonacciRecursive {
    public static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Fib: " + fib(n));
    }
}
/*
 * Time Complexity: O(2^n)
 * 說明：重複計算相同子問題的遞迴
 */