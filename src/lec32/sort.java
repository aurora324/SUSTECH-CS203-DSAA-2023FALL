package lec32;

import java.util.Arrays;
import java.util.Scanner;

public class sort {
    public static void main(String[] args) {
        int num=9999;
        Scanner sc=new Scanner(System.in);
        int[]arr=new int[num];
        System.out.println(num);
        for (int i = 0; i < num; i++) {
            arr[i]=sc.nextInt();
        }
Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        if(num%2==0){
            System.out.println(arr[num/2-1]*2+1);
        }else{
            System.out.println(arr[(num+1)/2-1]*2);
        }
    }
}
