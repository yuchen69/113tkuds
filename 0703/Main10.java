import java.util.Scanner;

public class Main10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        for (int i = 1; i <= N; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 1; j <= i; j++) {
                if (line.length() > 0) line.append(' ');
                line.append(j);
            }
            for (int j = i - 1; j >= 1; j--) {
                line.append(' ').append(j);
            }
            System.out.println(line);
        }
        sc.close();
    }
}