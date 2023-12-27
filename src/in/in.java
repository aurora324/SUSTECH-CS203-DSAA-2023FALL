package in;

import java.util.Random;

public class in {
    public static void main(String[] args) {
        Random r=new Random();
        int T=100;
        System.out.println(T);
        for (int i = 0; i < T; i++) {
            int a=10000;
            System.out.println(a);
            for (int j = 0; j < a; j++) {
                int m=r.nextInt(100000);
                System.out.print(m+" ");
            }
            System.out.println();
        }

        System.out.println();
    }
}
