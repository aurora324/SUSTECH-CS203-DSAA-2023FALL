package lec1;

import java.util.Arrays;
import java.util.Scanner;

public class p6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            String[] arr = new String[14];
            int[] rank = new int[14];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = sc.next();
                String temp1 = arr[j].charAt(1) + "";

                switch (temp1) {
                    case "b" -> rank[j] = 0;
                    case "s" -> rank[j] = 10;
                    case "w" -> rank[j] = 20;
                    case "z" -> rank[j] = 30;
                }


                    int temp2 = Integer.parseInt(arr[j].charAt(0) + "");
                    rank[j] += temp2;

            }

//            for (int j = 0; j < rank.length - 1; j++) {
//                for (int k = j + 1; k < rank.length; k++) {
//                    if (rank[j] > rank[k]) {
//                        int t1 = rank[k];
//                        rank[k] = rank[j];
//                        rank[j] = t1;
//
//                        String t2 = arr[k];
//                        arr[k] = arr[j];
//                        arr[j] = t2;
//                    }
//                }
//            }

            Arrays.sort(rank);

            int[]result=new int[rank.length-1];
            for (int j = 0; j < result.length; j++) {
                result[j]=rank[j+1]-rank[j];
            }

            int start=0;
            int end=0;

            int s3=0;
            int s2=0;
            for (int j = 0; j < result.length; j++) {
                if((result[j]!=0&&result[j]!=1)||j==result.length-1){
                    end=j;
                }else{
                    break;
                }

                for (int k = start; k <= end; k++) {

                }

                start=j;
            }

//            int counter0=0;
//            int counter1=0;
//
//            for (int k : result) {
//                if (k == 0) {
//                    counter0 += 1;
//                }
//                if (k == 1) {
//                    counter1 += 1;
//                }
//            }
//
//            int[]result0=new int[counter0];
//            int[]result1=new int[counter1];
//
//            for (int j = 0,m=0,n=0; j < result.length; j++) {
//                if (j == 0) {
//                    result0[m]=j;
//                    m++;
//                }
//                if (j == 1) {
//                    result1[n]=j;
//                    n++;
//                }
//            }
        }
    }
}
