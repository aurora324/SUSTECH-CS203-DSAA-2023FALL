package lec2;

import java.util.Scanner;

public class p3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner((System.in));
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int[] arr = new int[num1 + 2];
        for (int i = 1; i <= num1; i++) {
            arr[i] = sc.nextInt();
        }
        arr[0] = -1000000000;
        arr[arr.length - 1] = 1000000000;
        for (int i = 0; i < num2; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int m = find1(arr, y);
            int n = find2(arr, x);
            if (n - m + 1 > 0) {
                System.out.println("YES " + (n - m + 1));
            } else {
                System.out.println("NO");
            }

        }
    }

    public static int find1(int[] arr, int num) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int m = (i + j) >>> 1;
            if (num < arr[m]) {
                j = m;
            } else {
                i = m + 1;
            }
        }
        return j;
    }

    public static int find2(int[] arr, int num) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int mid = (i + j + 1) >>> 1;
            if (num > arr[mid]) {
                i = mid;
            } else {
                j = mid - 1;
            }
        }

        return i;
    }


}
