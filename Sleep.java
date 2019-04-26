package net.sssempai;

public class Sleep { //Теперь можно не париться на счёт некрасивого кода

    public static void $sleep_s(int s){
        try {
            Thread.sleep(s * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void $sleep_s(double s){
        try {
            Thread.sleep((long) (1000 * s));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void $sleep_m(int m){

        try {
            Thread.sleep(m * 60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
