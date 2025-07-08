import java.util.Arrays;

public class ArrayUtility {
    public static void main(String[] args) {
        int[] data = {3, 7, 1, 9, 4, 6, 8, 2, 5};

        System.out.print("原始陣列：");
        printArray(data);

        reverseArray(data);
        System.out.print("反轉後陣列：");
        printArray(data);

        int[] copied = copyArray(data);
        System.out.print("複製陣列：");
        printArray(copied);

        int secondMax = findSecondLargest(data);
        System.out.println("第二大的數值：" + secondMax);
    }

    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void reverseArray(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    public static int[] copyArray(int[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static int findSecondLargest(int[] array) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (int n : array) {
            if (n > first) {
                second = first;
                first = n;
            } else if (n > second && n != first) {
                second = n;
            }
        }
        return second;
    }
}
