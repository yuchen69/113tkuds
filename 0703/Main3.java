import java.util.Scanner;

public class 撰寫一個Java程式來轉換攝氏溫度到華氏溫度 {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int celsius = scanner.nextInt();
        int fahrenheit = (celsius * 9 / 5) + 32;

        System.out.println(fahrenheit);
    }
}
