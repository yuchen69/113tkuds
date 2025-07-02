import java.util.Scanner;

public class q6fibonaccirec {

    static long ops = 0;

    static long fib(int n) {
        ops++;
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ops = 0;
        long fn = fib(n);

        System.out.println(fn);
        System.out.println(ops);
    }
}