package in.p83;

import java.util.Random;
import java.util.Scanner;

public class in {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Random r=new Random();
        int n=100;
        int m=100;
        int k=m*n;
        int[]a=new int[n];
        int[]b=new int[m];
        for (int i = 0; i < n; i++) {
            a[i]=r.nextInt(1000)+1;
        }
        for (int i = 0; i < b.length; i++) {
            b[i]=r.nextInt(1000)+1;
        }
        a[0]=7;
        b[0]=10;
        System.out.println(n);
        System.out.println(m);
        System.out.println(k);
        int []answer=new int[k];
        int counter=0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                answer[counter]=a[i]*b[j];
                counter++;
            }
        }
        quickSort(answer,0,k-1);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i]+" ");
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i]+" ");
        }
    }

    public static void quickSort(int[] arr, int i, int j) {
        int start = i;
        int end = j;
        if (start > end) {
            return;
        }
        int baseNumber = arr[i];
        while (start != end) {
            //先移动end再移动start
            while (end > start && arr[end] >= baseNumber) {
                end--;
            }
            while (end > start && arr[start] <= baseNumber) {
                start++;
            }

            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }

        int temp = arr[i];
        arr[i] = arr[start];
        arr[start] = temp;
        quickSort(arr, i, start - 1);
        quickSort(arr, start + 1, j);

    }

}
