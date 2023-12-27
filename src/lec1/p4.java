package lec1;

import java.util.Scanner;

public class p4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int num1=sc.nextInt();
        for (int i = 0; i < num1; i++) {
            int num2=sc.nextInt();
            int []arr=new int[num2];
            for (int j = 0; j < arr.length; j++) {
                arr[j]=sc.nextInt();
            }

            int []newArr=new int[arr.length-1];
            for (int j = 0; j < newArr.length; j++) {
                newArr[j]=arr[j]-arr[j+1];
            }

            int[]Arr=new int[num1*(num1-1)/2];
            for (int j = 0; j < Arr.length; j++) {
                for (int m = 0; m < newArr.length; m++) {
                    for (int n = m; n < newArr.length; n++) {
                        int num=0;
                        for (int k = m; k <=n; k++) {
                            num+=newArr[k];
                        }
                        Arr[j]=num;
                    }
                }
            }

            for (int j = 0; j < Arr.length-1; j++) {
                for (int k = i+1; k < Arr.length; k++) {
                    if(Arr[j]<Arr[k]){
                        int temp=Arr[j];
                        Arr[j]=Arr[k];
                        Arr[k]=temp;
                    }
                }
            }

            System.out.println(Arr[0]);

        }
    }
}
