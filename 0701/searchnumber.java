import java.util.Scanner;

public class searchnumber {
    public static void main(String[] args) {
        int[] s = {1, 3, 5, 7, 9, 11, 13}; // 已排序的數列
        Scanner scanner = new Scanner(System.in);

        System.out.print("請輸入要查找的數字：");
        int x = scanner.nextInt();
        boolean found = false;

        // 線性搜尋
        for (int num : s) {
            System.out.println("比對中：x = " + x + "，目前元素 = " + num);
            if (num == x) {
                found = true;
                break;
            }
        }

        // 輸出結果
        if (found) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        scanner.close();
    }
}