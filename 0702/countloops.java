public class countloops {
    public static void main(String[] args){
        int n = 5;
        int sum = 0;
        int count = 0;
        for (int i = 1; i <= n; i++){
            System.out.println("第" + i + "次迴圈，sum =" + sum + "+" + i);
            sum += i;
            count++;
        }
        System.out.println("總合為 :" + sum);
        System.out.println("加法執行了 :" + count);
    }
}