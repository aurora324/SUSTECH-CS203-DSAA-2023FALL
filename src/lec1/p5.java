package lec1;

import java.util.Scanner;

public class p5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            for (int j = 0, k = 2 * b; j < b * 2; j++, k--) {
                for (int m = 0; m < k; m++) {
                    System.out.print(".");
                }
                for (int m = 0; m < a; m++) {
                    if (j%2==0) {
                        System.out.print("+-");
                    }else{
                        System.out.print("/.");
                    }
                }
                if(j%2==0){
                    for (int m = 0; m < j+1; m++) {
                        if(m%2==0){
                            System.out.print("+");
                        }else{
                            System.out.print(".");
                        }
                    }
                }else{
                    for (int m = 0; m < j+1; m++) {
                        if(m%2==0){
                            System.out.print("/");
                        }else{
                            System.out.print("|");
                        }
                    }
                }
                System.out.println();
            }


            for (int j = 0; j < (c-b)*2+1; j++) {
                if(j%2==0){
                    for (int k = 0; k < a; k++) {
                        System.out.print("+-");
                    }
                    for (int k = 0; k < b; k++) {
                        System.out.print("+.");
                    }
                    System.out.println("+");
                }else{
                    for (int k = 0; k < a; k++) {
                        System.out.print("|.");
                    }
                    for (int k = 0; k < b; k++) {
                        System.out.print("|/");
                    }
                    System.out.println("|");
                }
            }



            for (int j = 0, k = 2 * b; j < b * 2; j++, k--) {

                if(j%2==0){
                    for (int m = 0; m < a; m++) {
                        System.out.print("|.");
                    }
                    for (int m = 0; m < k/2; m++) {
                        System.out.print("|/");
                    }
                    for (int m = 0; m < j+1; m++) {
                        System.out.print(".");
                    }
                }else{
                    for (int m = 0; m < a; m++) {
                        System.out.print("+-");
                    }
                    for (int m = 0; m < k/2+1; m++) {
                        System.out.print("+.");
                    }
                    for (int m = 0; m < j; m++) {
                        System.out.print(".");
                    }
                }
                System.out.println();
            }


        }
    }
}
