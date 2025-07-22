public class FactorialExample {

    public static int factorial(int n) {
        if (n == 0) {
            System.out.println("0! = 1");
            return 1;
        }

        int result = 1;
        StringBuilder expr = new StringBuilder(n + "! = ");

        for (int i = n; i >= 1; i--) {
            result *= i;
            expr.append(i);
            if (i > 1) {
                expr.append(" * ");
            }
        }

        expr.append(" = ").append(result);
        System.out.println(expr);
        return result;
    }

    public static void main(String[] args) {
        factorial(3); // 3! = 3 * 2 * 1 = 6
        factorial(5); // 5! = 5 * 4 * 3 * 2 * 1 = 120
        factorial(0); // 0! = 1
    }
}
