package lec1;

import java.util.Arrays;
import java.util.Scanner;

public class p51 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            char[][] arr = new char[2 * b + 2 * c + 1][2 * a + 2 * b + 1];
            for (int j = 0; j < arr.length; j++) {
                Arrays.fill(arr[j], '.');
            }

            for (int j = 0; j < arr.length; j++) {
                for (int k = 0; k < arr[j].length; k++) {
                    if(j<=2*b&&k>=2*b-j&k<=2*b+2*a-j){
                        if(j%2==0){
                            if(k%2==0){
                                arr[j][k]='+';
                            }else{
                                arr[j][k]='-';
                            }
                        }else{
                            if(k%2==1){
                                arr[j][k]='/';
                            }
                        }
                    }

                    if(j>2*b&&k<=2*a){
                        if(j%2==0){
                            if(k%2==0){
                                arr[j][k]='+';
                            }else{
                                arr[j][k]='-';
                            }
                        }else{
                            if(k%2==0){
                                arr[j][k]='|';
                            }
                        }
                    }
                }
            }

            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr.length; k++) {
                    if(j>2*a&&k>=2*a+2*b-j&&k<=2*a+2*b+2*c-j){
                        if(j%2==0){
                            if(k%2==0){
                                arr[k][j]='+';
                            }else{
                                arr[k][j]='|';
                            }
                        }else{
                            if(k%2==1){
                                arr[k][j]='/';
                            }
                        }
                    }
                }
            }

            for (int j = 0; j < arr.length; j++) {
                for (int k = 0; k < arr[j].length; k++) {
                    System.out.print(arr[j][k]);
                }
                System.out.println();
            }
        }
    }
}
