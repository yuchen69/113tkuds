import java.util.*;
public class Q1_THSRStopCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] stations = sc.nextLine().split(" ");
        String[] se = sc.nextLine().split(" ");
        String start = se[0], end = se[1];
        int startIdx = -1, endIdx = -1;
        for (int i = 0; i < n; i++) {
            if (stations[i].equals(start)) startIdx = i;
            if (stations[i].equals(end)) endIdx = i;
        }
        if (startIdx == -1 || endIdx == -1) {
            System.out.println("Invalid");
        } else {
            System.out.println(Math.abs(startIdx - endIdx) + 1);
        }
    }
}
/*
 * Time Complexity: O(n)
 * 說明：走訪一次陣列找起訖站，總共 O(n)
 */