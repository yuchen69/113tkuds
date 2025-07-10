class NullPointerDemo {
    /**
     * 展示空指標參考的常見情況
     */
    static void demonstrateNullPointerErrors() {
        int[] nullArray = null;
        
        System.out.println("=== 空指標錯誤情況 ===");
        
        // 錯誤情況 1：存取 null 陣列的長度
        System.out.println("情況 1：存取 null 陣列的長度");
        try {
            int length = nullArray.length;  // NullPointerException
            System.out.println("這行不會執行到");
        } catch (NullPointerException e) {
            System.out.printf("捕獲錯誤：%s\n", e.getClass().getSimpleName());
            System.out.println("原因：嘗試存取 null 物件的屬性");
        }
        
        // 錯誤情況 2：存取 null 陣列的元素
        System.out.println("\n情況 2：存取 null 陣列的元素");
        try {
            int value = nullArray[0];  // NullPointerException
            System.out.println("這行不會執行到");
        } catch (NullPointerException e) {
            System.out.printf("捕獲錯誤：%s\n", e.getClass().getSimpleName());
            System.out.println("原因：嘗試存取 null 陣列的元素");
        }
        
        // 錯誤情況 3：對 null 陣列使用增強型 for 迴圈
        System.out.println("\n情況 3：對 null 陣列使用增強型 for 迴圈");
        try {
            for (int value : nullArray) {  // NullPointerException
                System.out.println(value);
            }
        } catch (NullPointerException e) {
            System.out.printf("捕獲錯誤：%s\n", e.getClass().getSimpleName());
            System.out.println("原因：增強型 for 迴圈無法處理 null 陣列");
        }
    }
    
    /**
     * 安全的陣列操作方法
     */
    static void safeArrayOperations(int[] array) {
        // 防禦性程式設計：總是先檢查 null
        if (array == null) {
            System.out.println("陣列為 null，無法進行操作");
            return;
        }
        
        if (array.length == 0) {
            System.out.println("陣列為空（長度為 0）");
            return;
        }
        
        System.out.printf("陣列長度：%d\n", array.length);
        System.out.printf("第一個元素：%d\n", array[0]);
        System.out.printf("最後一個元素：%d\n", array[array.length - 1]);
        
        // 安全的走訪
        System.out.print("陣列內容：");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
    
    /**
     * 使用 Optional 模式處理可能為 null 的陣列
     */
    static java.util.OptionalInt findMax(int[] array) {
        if (array == null || array.length == 0) {
            return java.util.OptionalInt.empty();
        }
        
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        
        return java.util.OptionalInt.of(max);
    }
    
    /**
     * 建立防禦性的陣列副本
     */
    static int[] createSafeCopy(int[] source) {
        if (source == null) {
            System.out.println("來源陣列為 null，返回空陣列");
            return new int[0];
        }
        
        int[] copy = new int[source.length];
        System.arraycopy(source, 0, copy, 0, source.length);
        
        System.out.printf("成功建立長度為 %d 的陣列副本\n", copy.length);
        return copy;
    }
    
    public static void main(String[] args) {
        demonstrateNullPointerErrors();
        
        System.out.println("\n=== 安全操作示範 ===");
        
        // 測試各種情況
        System.out.println("測試 null 陣列：");
        safeArrayOperations(null);
        
        System.out.println("\n測試空陣列：");
        safeArrayOperations(new int[0]);
        
        System.out.println("\n測試正常陣列：");
        safeArrayOperations(new int[]{1, 2, 3, 4, 5});
        
        System.out.println("\n=== Optional 模式示範 ===");
        int[][] testArrays = {null, {}, {1, 2, 3, 4, 5}};
        String[] descriptions = {"null 陣列", "空陣列", "正常陣列"};
        
        for (int i = 0; i < testArrays.length; i++) {
            java.util.OptionalInt maxOpt = findMax(testArrays[i]);
            System.out.printf("%s的最大值：%s\n", descriptions[i], 
                            maxOpt.isPresent() ? maxOpt.getAsInt() : "無");
        }
        
        System.out.println("\n=== 防禦性複製示範 ===");
        int[] original = {1, 2, 3};
        int[] copy1 = createSafeCopy(original);
        int[] copy2 = createSafeCopy(null);
        
        System.out.println("原始陣列：" + java.util.Arrays.toString(original));
        System.out.println("副本1：" + java.util.Arrays.toString(copy1));
        System.out.println("副本2：" + java.util.Arrays.toString(copy2));
    }
}
