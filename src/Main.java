public class Main {
    public static void main(String[] args) {
        String name = "Ivan";
        System.out.println(helloname(name));
        check_year(80);
    }

    public static float first_method(int a,int b,int c,int d){
        float result = a * (b + (c / d));
        return result;
    }
    public static boolean check20(int a, int b){
        if((a + b > 10) && (a + b <= 20)){
            return true;
        }
        return false;
    }
    public static void check_zero(int a){
        if(a >= 0){
            System.out.println("Переданное число " + a + " положительное.");
        }else {
            System.out.println("Переданное число " + a + " отрицательное.");
        }
    }
    public static String helloname(String name){
        String box = "Привет, " + name + "!";
        return box;
    }
    public static void check_year(int year){
        if((year % 4 == 0)){
            if(year % 100 == 0 && year % 400 != 0){
                System.out.println(year + " год не високосный");
            }else {
                System.out.println(year + " год високосный");
            }
        }else {
            System.out.println(year + "этот год не високосный");
        }
    }
}
