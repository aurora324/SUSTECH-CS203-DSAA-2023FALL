package lec2;

import java.util.Scanner;

public class p1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int num1=sc.nextInt();
        int [] arr = new int[num1];
        for (int i = 0; i < num1; i++) {
            arr[i]=sc.nextInt();
        }
        int num2 = sc.nextInt();
        for (int i = 0; i < num2; i++) {
            int a = sc.nextInt();
            int j = 0,k=arr.length;
            while (1<k-j){
                int mid = (j+k)/2;
                if(a<arr[mid]){
                    k=mid;
                }else{
                    j=mid;
                }
            }
            if(arr[j]==a){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    public static int find(int[]arr,int key){
        int i=0;
        int j=arr.length-1;
        while(i<j){
            int mid =(i+j)/2;
            if(key<arr[mid]){
                j=mid;
            }else if(key>arr[mid]){
                i=mid+1;
            }else{
                return mid;
            }
        }
        return i;
    }
}
