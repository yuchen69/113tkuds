public class ArrayPrinter {
    static String arrayToString(int[] array) {
        if (array == null) {
            return "null";
        }
        
        if (array.length == 0) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        
        sb.append("]");
        return sb.toString();
    }
    
    static void printArrayWithIndex(int[] array) {
        System.out.println("索引\t值");
        System.out.println("----\t---");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d\t%d\n", i, array[i]);
        }
    }
    
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int[] emptyArray = {};
        int[] nullArray = null;
        
        System.out.println("一般陣列：" + arrayToString(numbers));
        System.out.println("空陣列：" + arrayToString(emptyArray));
        System.out.println("null 陣列：" + arrayToString(nullArray));
        
        System.out.println("\n詳細資訊：");
        printArrayWithIndex(numbers);
    }
}
