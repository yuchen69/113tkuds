import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ds_03 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("姓名：");
        String name = reader.readLine();

        System.out.print("年齡：");
        int age = Integer.parseInt(reader.readLine());

        System.out.print("城市：");
        String city = reader.readLine();

        System.out.println("\n以下是你的個人資訊：");
        System.out.println("姓名：" + name);
        System.out.println("年齡：" + age);
        System.out.println("城市：" + city);
    }
}