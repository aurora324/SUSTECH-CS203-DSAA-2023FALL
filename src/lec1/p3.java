package lec1;

import java.util.Scanner;

public class p3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            String[] arr = new String[13];
            int[] rank = new int[13];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = sc.next();
                String temp1 = arr[j].charAt(0) + "";

                if (temp1.equals("W") && arr[j].length() == 2) {
                    rank[j] = 10;
                } else if (temp1.equals("T")) {
                    rank[j] = 20;
                } else if (temp1.equals("Y")) {
                    rank[j] = 30;
                } else if (temp1.equals("E")) {
                    rank[j] = 40;
                } else if (temp1.equals("S")) {
                    rank[j] = 50;
                } else if (temp1.equals("W")) {
                    rank[j] = 60;
                } else if (temp1.equals("N")) {
                    rank[j] = 70;
                } else if (temp1.equals("B")) {
                    rank[j] = 80;
                } else if (temp1.equals("F")) {
                    rank[j] = 90;
                } else if (temp1.equals("Z")) {
                    rank[j] = 100;
                }

                if (arr[j].length() == 2) {
                    int temp2 = Integer.parseInt(arr[j].charAt(1) + "");
                    rank[j] += temp2;
                }
            }

            for (int j = 0; j < rank.length - 1; j++) {
                for (int k = j + 1; k < rank.length; k++) {
                    if (rank[j] > rank[k]) {
                        int t1 = rank[k];
                        rank[k] = rank[j];
                        rank[j] = t1;

                        String t2 = arr[k];
                        arr[k] = arr[j];
                        arr[j] = t2;
                    }
                }
            }

            for (String s : arr) {
                System.out.print(s + " ");
            }
            System.out.println();

        }
    }
}
