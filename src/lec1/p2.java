package lec1;

import java.util.Scanner;

public class p2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[]arr3=new int[1000000];
        int a = sc.nextInt();

        for (int i = 0; i < a; i++) {
            int c = sc.nextInt();
            arr3[c]=1;
        }

        int b = sc.nextInt();
        int[]arr2=new int[b];

        for (int i = 0; i < b; i++) {
            int c = sc.nextInt();
            arr2[i]=c;
        }

        for (int i = 0; i < arr2.length; i++) {
            if(arr3[arr2[i]]==1){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }
        }


    }
}
