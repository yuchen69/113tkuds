public class ArraySearcher {
    public static int findElement(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
    public static int countOccurrences(int[] array, int target) {
        int count = 0;
        for (int num : array) {
            if (num == target) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] data = {12, 45, 23, 67, 34, 89, 56, 78, 91, 25};
        int[] testNumbers = {67, 100};

        for (int number : testNumbers) {
            int index = findElement(data, number);
            int count = countOccurrences(data, number);

            if (index != -1) {
                System.out.println("數字 " + number + " 出現在索引位置：" + index);
            } else {
                System.out.println("數字 " + number + " 未找到！");
            }

            System.out.println("數字 " + number + " 出現次數：" + count);
            System.out.println("------");
        }
    }
}
