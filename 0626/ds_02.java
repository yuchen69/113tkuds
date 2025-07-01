public class ds_02 {
    public static void main(String[] args) {
        System.out.print("輸入半徑：");
        double radius = Double.parseDouble(System.console().readLine());

        double area = Math.PI * radius * radius;

        System.out.println("面積是：" + area);
    }
}