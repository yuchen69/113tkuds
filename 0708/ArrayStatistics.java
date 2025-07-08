public class ArrayStatistics {
    public static void main(String[] args) {
        int[] numbers = {5, 12, 8, 15, 7, 23, 18, 9, 14, 6};

        int sum = 0;
        int max = numbers[0];
        int min = numbers[0];
        int maxIndex = 0;
        int minIndex = 0;
        int evenCount = 0;
        int oddCount = 0;

        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];
            sum += n;
            if (n > max) {
                max = n;
                maxIndex = i;
            }
            if (n < min) {
                min = n;
                minIndex = i;
            }
            if (n % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        double average = (double) sum / numbers.length;
        int aboveAvgCount = 0;

        for (int n : numbers) {
            if (n > average) {
                aboveAvgCount++;
            }
        }

        System.out.println("+-----------------------+");
        System.out.println("| 統計項目        | 數值     |");
        System.out.println("+-----------------------+");
        System.out.printf("| 總和             | %-7d |\n", sum);
        System.out.printf("| 平均值           | %-7.2f |\n", average);
        System.out.printf("| 最大值           | %-7d |\n", max);
        System.out.printf("| 最大值索引位置   | %-7d |\n", maxIndex);
        System.out.printf("| 最小值           | %-7d |\n", min);
        System.out.printf("| 最小值索引位置   | %-7d |\n", minIndex);
        System.out.printf("| 大於平均值的數量 | %-7d |\n", aboveAvgCount);
        System.out.printf("| 偶數數量         | %-7d |\n", evenCount);
        System.out.printf("| 奇數數量         | %-7d |\n", oddCount);
        System.out.println("+-----------------------+");
    }
}
