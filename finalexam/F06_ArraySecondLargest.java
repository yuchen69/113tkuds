import java.util.*;

public class F06_ArraySecondLargest {
    public static int[] helper(int[] arr, int index) {
        if (index == 0) return new int[]{arr[0], Integer.MIN_VALUE};
        int[] prev = helper(arr, index - 1);
        if (arr[index] > prev[0]) return new int[]{arr[index], prev[0]};
        else if (arr[index] > prev[1]) return new int[]{prev[0], arr[index]};
        else return prev;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int[] result = helper(arr, n - 1);
        System.out.println("SecondMax: " + result[1]);
    }
}
/*
 * Time Complexity: O(n)
 * 說明：繁用的是繁繞性同步觸發的適得模式
 */