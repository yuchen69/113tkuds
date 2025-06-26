public class ds_01 {
    public static void main(String[] args){
        System.out.print("輸入一個整數：");
        int a = Integer.parseInt(System.console().readLine());

        System.out.print("再輸入一個整數：");
        int b = Integer.parseInt(System.console().readLine());

        int sum = a + b;
        System.out.println("兩個整數的和是：" + sum);
    }
}