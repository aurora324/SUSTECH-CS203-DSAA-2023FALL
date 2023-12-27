package lec2;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int []arr=new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=sc.nextInt();
        }
        System.out.println(find1(arr,300));
        System.out.println(find2(arr,300));
        System.out.println(Long.MAX_VALUE);
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
