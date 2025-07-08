class ArrayReversal {
    /**
     * 原地反轉演算法：使用雙指標技術
     * 時間複雜度：O(n/2) = O(n)
     * 空間複雜度：O(1) - 只使用固定的額外空間
     */
    static void reverseInPlace(int[] array) {
        int left = 0;                    // 左指標，從陣列開頭開始
        int right = array.length - 1;   // 右指標，從陣列結尾開始
        int swapCount = 0;               // 計算交換次數
        
        System.out.println("反轉過程：");
        
        while (left < right) {
            // 顯示當前要交換的元素
            System.out.printf("交換索引 %d（值：%d）與索引 %d（值：%d）\n", 
                            left, array[left], right, array[right]);
            
            // 交換元素：使用暫存變數的三步驟交換法
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            
            swapCount++;
            
            // 指標向中間移動
            left++;
            right--;
        }
        
        System.out.printf("總共進行了 %d 次交換\n", swapCount);
    }
    
    /**
     * 建立新陣列的反轉方法：不修改原陣列
     * 空間複雜度：O(n) - 需要額外的陣列空間
     */
    static int[] reverseToNew(int[] array) {
        int[] reversed = new int[array.length];
        
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - 1 - i];
        }
        
        return reversed;
    }
    
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6};
        int[] originalCopy = numbers.clone();  // 保留原始資料的副本
        
        System.out.println("原始陣列：" + java.util.Arrays.toString(numbers));
        
        // 方法一：原地反轉（修改原陣列）
        reverseInPlace(numbers);
        System.out.println("原地反轉後：" + java.util.Arrays.toString(numbers));
        
        // 方法二：建立新陣列（不修改原陣列）
        int[] newReversed = reverseToNew(originalCopy);
        System.out.println("原始陣列（未修改）：" + java.util.Arrays.toString(originalCopy));
        System.out.println("新反轉陣列：" + java.util.Arrays.toString(newReversed));
    }
}