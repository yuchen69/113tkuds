import java.util.*;

public class F11_SortedArrayBinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int target = sc.nextInt();
        int result = binarySearch(arr, target);
        if (result == -1) System.out.println("Not found");
        else System.out.println("Found at index " + result);
    }
}
/*
 * Time Complexity: O(log n)
 * 說明：二分搜尋有對數時間複雜度
 */