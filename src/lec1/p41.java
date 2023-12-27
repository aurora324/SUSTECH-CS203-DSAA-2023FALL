package lec1;

import java.util.Scanner;

public class p41 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int num1=sc.nextInt();
        for (int i = 0; i < num1; i++) {
            int num2= sc.nextInt();
            int [] arr=new int[num2];
            for (int j = 0; j < arr.length; j++) {
                arr[j]=sc.nextInt();
            }
            int max = arr[0];
            int dif = arr[0]-arr[1];
            for (int j = 1; j < arr.length; j++) {
                if(dif<max-arr[j]){
                    dif=max-arr[j];
                }
                if(arr[j]>max){
                    max=arr[j];
                }
            }
            System.out.println(dif);
        }
    }
}
