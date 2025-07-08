import java.util.Scanner;

public class 一個Java程式來輸出多行文字展示你的個人信息 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        String age = scanner.nextLine();
        String city = scanner.nextLine();

        System.out.println(name);
        System.out.println(age);
        System.out.println(city);
    }
}