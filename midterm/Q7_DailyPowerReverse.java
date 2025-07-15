import java.util.*;
public class Q7_DailyPowerReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().split(" ");
        int[] arr = new int[7];
        for (int i = 0; i < 7; i++) arr[i] = Integer.parseInt(tokens[i]);
        for (int i = 0; i < 3; i++) {
            int tmp = arr[i];
            arr[i] = arr[6 - i];
            arr[6 - i] = tmp;
        }
        for (int i = 0; i < 7; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }
}
