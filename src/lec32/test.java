package lec32;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        int num=9999;
        Random r=new Random();
        int[]arr=new int[num];
        System.out.println(num);
        for (int i = 0; i < num; i++) {
            int a=r.nextInt(1000000);
            System.out.print(a+" ");
            arr[i]=a;
        }
        System.out.println();
    }
}
