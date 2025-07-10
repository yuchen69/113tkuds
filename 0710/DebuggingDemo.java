class DebuggingDemo {
    /**
     * 示範如何在程式中加入除錯資訊
     */
    static void bubbleSortWithDebugInfo(int[] array) {
        System.out.println("=== 氣泡排序除錯版本 ===");
        System.out.println("初始陣列：" + java.util.Arrays.toString(array));
        
        int n = array.length;
        
        for (int i = 0; i < n - 1; i++) {
            System.out.printf("\n--- 第 %d 輪排序開始 ---\n", i + 1);
            boolean swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                System.out.printf("比較 array[%d]=%d 與 array[%d]=%d", 
                                j, array[j], j + 1, array[j + 1]);
                
                if (array[j] > array[j + 1]) {
                    // 設定中斷點在這裡可以觀察交換過程
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                    System.out.println(" → 交換");
                } else {
                    System.out.println(" → 不交換");
                }
                
                // 顯示每次比較後的陣列狀態
                System.out.println("   目前狀態：" + java.util.Arrays.toString(array));
            }
            
            System.out.printf("第 %d 輪結束，是否有交換：%s\n", i + 1, swapped);
            
            if (!swapped) {
                System.out.println("提早結束：陣列已排序完成");
                break;
            }
        }
        
        System.out.println("\n最終結果：" + java.util.Arrays.toString(array));
    }
    
    /**
     * 展示陣列狀態檢查的工具方法
     */
    static void analyzeArray(int[] array, String description) {
        System.out.printf("\n=== %s 分析 ===\n", description);
        
        if (array == null) {
            System.out.println("陣列：null");
            return;
        }
        
        System.out.printf("陣列長度：%d\n", array.length);
        
        if (array.length == 0) {
            System.out.println("陣列為空");
            return;
        }
        
        System.out.println("陣列內容：" + java.util.Arrays.toString(array));
        
        // 計算統計資訊
        int sum = 0;
        int min = array[0];
        int max = array[0];
        
        for (int value : array) {
            sum += value;
            if (value < min) min = value;
            if (value > max) max = value;
        }
        
        double average = (double) sum / array.length;
        
        System.out.printf("統計資訊：\n");
        System.out.printf("  最小值：%d\n", min);
        System.out.printf("  最大值：%d\n", max);
        System.out.printf("  總和：%d\n", sum);
        System.out.printf("  平均值：%.2f\n", average);
        
        // 檢查是否已排序
        boolean isSorted = true;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                isSorted = false;
                break;
            }
        }
        System.out.printf("  是否已排序：%s\n", isSorted ? "是" : "否");
    }
    
    /**
     * 模擬常見的除錯場景
     */
    static void debugScenarios() {
        System.out.println("=== 除錯場景模擬 ===");
        
        // 場景 1：空陣列
        analyzeArray(new int[0], "空陣列");
        
        // 場景 2：單元素陣列
        analyzeArray(new int[]{42}, "單元素陣列");
        
        // 場景 3：已排序陣列
        analyzeArray(new int[]{1, 2, 3, 4, 5}, "已排序陣列");
        
        // 場景 4：逆序陣列
        analyzeArray(new int[]{5, 4, 3, 2, 1}, "逆序陣列");
        
        // 場景 5：包含重複元素的陣列
        analyzeArray(new int[]{3, 1, 4, 1, 5, 9, 2, 6, 5}, "包含重複元素的陣列");
    }
    
    public static void main(String[] args) {
        System.out.println("VS Code 除錯技巧示範");
        System.out.println("建議在以下位置設定中斷點：");
        System.out.println("1. bubbleSortWithDebugInfo 方法的交換位置");
        System.out.println("2. analyzeArray 方法的統計計算部分");
        System.out.println("3. main 方法的各個呼叫點");
        System.out.println();
        
        // 執行除錯場景
        debugScenarios();
        
        // 執行排序除錯
        int[] testArray = {64, 34, 25, 12, 22, 11, 90};
        bubbleSortWithDebugInfo(testArray);
        
        analyzeArray(testArray, "排序後的陣列");
    }
}
