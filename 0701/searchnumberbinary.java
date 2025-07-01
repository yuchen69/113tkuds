public class searchnumberbinary {
    public static void main (String[] args){
        System.out.println("請輸入要查找的數字:");
        int X = scanner.nextInt();

        int left = 0 
        int right = s.length - 1;
        boolean found = false;

        while (left <= right){

            int mid = (left + right) / 2;
            System.out.println("比對中 : x =" + x + ",中間元素 = " + s[mid]);

            if (s[mid] == x){
                found = true;
                break;
            }else if (s[mid] < x){
                left = mid + 1;
            }else {
                right = mid - 2;
        }
    }
    }
}
