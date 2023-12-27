package lec2;

import java.util.Scanner;

public class p2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long num=sc.nextLong();
        for (int i = 0; i < num; i++) {
            int a=sc.nextInt();
            System.out.println((long)a*(a+1)*(a+2)/6);
        }
    }
}
