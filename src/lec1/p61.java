package lec1;

import java.util.Scanner;

public class p61 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            String s = sc.next();
            int[][] arr = new int[4][10];
            for (int j = 1; j <= s.length(); j += 2) {
                String temp1 = s.charAt(j) + "";
                int m = 0, n;
                switch (temp1) {
                    case "s":
                        m = 1;
                        break;
                    case "w":
                        m = 2;
                        break;
                    case "z":
                        m = 3;
                        break;
                }
                n = Integer.parseInt((s.charAt(j - 1) + ""));
                arr[m][n] += 1;
            }
            boolean result = check14(arr);

            if (result) {
                System.out.println("Blessing of Heaven");
            } else {
                System.out.println("Bad luck");
            }
        }
    }

    public static boolean check14(int[][] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                if (arr[j][k] >= 2) {
                    arr[j][k] -= 2;
                    if(check12(arr)){
                        return true;
                    }else{
                        arr[j][k]+=2;
                    }
                }
            }
        }
        return false;
    }

    public static boolean check12(int[][] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                if (arr[j][k] >= 3) {
                    arr[j][k] -= 3;
                    if (check9(arr)) {
                        return true;
                    } else {
                        arr[j][k] += 3;
                    }
                }
            }

            for (int k = 0; k < arr[j].length - 2; k++) {
                if (j < 3 && arr[j][k] > 0 && arr[j][k + 1] > 0 && arr[j][k + 2] > 0) {
                    arr[j][k] -= 1;
                    arr[j][k + 1] -= 1;
                    arr[j][k + 2] -= 1;
                    if (check9(arr)) {
                        return true;
                    } else {
                        arr[j][k] += 1;
                        arr[j][k + 1] += 1;
                        arr[j][k + 2] += 1;
                    }
                }
            }
        }
        return false;
    }

    public static boolean check9(int[][] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                if (arr[j][k] >= 3) {
                    arr[j][k] -= 3;
                    if (check6(arr)) {
                        return true;
                    } else {
                        arr[j][k] += 3;
                    }
                }
            }

            for (int k = 0; k < arr[j].length - 2; k++) {
                if (j < 3 && arr[j][k] > 0 && arr[j][k + 1] > 0 && arr[j][k + 2] > 0) {
                    arr[j][k] -= 1;
                    arr[j][k + 1] -= 1;
                    arr[j][k + 2] -= 1;
                    if (check6(arr)) {
                        return true;
                    } else {
                        arr[j][k] += 1;
                        arr[j][k + 1] += 1;
                        arr[j][k + 2] += 1;
                    }
                }
            }
        }
        return false;
    }

    public static boolean check6(int[][] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                if (arr[j][k] >= 3) {
                    arr[j][k] -= 3;
                    if (check3(arr)) {
                        return true;
                    } else {
                        arr[j][k] += 3;
                    }
                }
            }

            for (int k = 0; k < arr[j].length - 2; k++) {
                if (j < 3 && arr[j][k] > 0 && arr[j][k + 1] > 0 && arr[j][k + 2] > 0) {
                    arr[j][k] -= 1;
                    arr[j][k + 1] -= 1;
                    arr[j][k + 2] -= 1;
                    if (check3(arr)) {
                        return true;
                    } else {
                        arr[j][k] += 1;
                        arr[j][k + 1] += 1;
                        arr[j][k + 2] += 1;
                    }
                }
            }
        }
        return false;
    }

    public static boolean check3(int[][] arr) {
        for (int j = 0; j < arr.length; j++) {

            for (int k = 0; k < arr[j].length; k++) {
                if (arr[j][k] >= 3) {
                    arr[j][k] -= 3;
                    return true;
                }
            }

            for (int k = 0; k < arr[j].length - 2; k++) {
                if (j < 3 && arr[j][k] > 0 && arr[j][k + 1] > 0 && arr[j][k + 2] > 0) {
                    arr[j][k] -= 1;
                    arr[j][k + 1] -= 1;
                    arr[j][k + 2] -= 1;
                    return true;
                }
            }
        }
        return false;
    }
}
