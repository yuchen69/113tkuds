public class matrix_multiplication_trace {
    public static void main(String[] args) {
        int[][] a = {
            {1, 2},
            {3, 4}
        };
        int[][] b = {
            {5, 6},
            {7, 8}
        };
        int[][] c = new int[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print("計算位置 c[" + i + "][" + j + "]：");
                StringBuilder process = new StringBuilder();
                int sum = 0;

                for (int k = 0; k < 2; k++) {
                    int product = a[i][k] * b[k][j];
                    sum += product;
                    process.append(a[i][k] + "*" + b[k][j]);
                    if (k < 1) process.append(" + ");
                }

                process.append(" = ").append(calculateTerm(a[i][0], b[0][j], a[i][1], b[1][j]))
                       .append(" = ").append(sum);
                System.out.println(process.toString());

                c[i][j] = sum;
            }
        }

        System.out.println("結果矩陣：");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static String calculateTerm(int a1, int b1, int a2, int b2) {
        int term1 = a1 * b1;
        int term2 = a2 * b2;
        return term1 + " + " + term2;
    }
}
