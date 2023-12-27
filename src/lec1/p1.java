package lec1;

import java.util.Scanner;

public class p1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int[]arr1=new int[a];

        for (int i = 0; i < a; i++) {
            int c = sc.nextInt();
            arr1[i]=c;
        }

        int b = sc.nextInt();
        int[]arr2=new int[b];

        for (int i = 0; i < b; i++) {
            int c = sc.nextInt();
            arr2[i]=c;
        }

        for (int i = 0; i < arr2.length; i++) {
            boolean result = false;
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] == arr2[i]) {
                    result = true;
                    break;
                }
            }
            if (result) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }
}
