package lec2;

import java.util.Scanner;

public class p4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = new int[num];
        int sum = sc.nextInt();
        for (int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }
        long counter = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length-1; j++) {
                int re = sum - arr[i] - arr[j];
                int[] detect = new int[arr.length - j+1];
                detect[0]=-1000000001;
                detect[detect.length-1]=1000000001;
                System.arraycopy(arr, j + 1, detect, 1, arr.length - j - 1);
                int m=find2(detect,re);
                int n=find1(detect,re);
                int sunCounter=(n-m-1);
                counter+=sunCounter;
            }
        }

        System.out.println(counter);
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
