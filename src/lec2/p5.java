package lec2;

import java.util.Arrays;
import java.util.Scanner;

public class p5 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int L = sc.nextInt();
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);
            int[] result = new int[arr.length + 1];
            result[0] = arr[0];
            for (int i = 1; i < result.length - 1; i++) {
                result[i] = arr[i] - arr[i - 1];
            }
            result[result.length - 1] = L - arr[arr.length - 1];
            int i = 0;
            int j = L;
            while (j - i > 1) {
                int mid = (i + j) / 2;
                boolean a = check(m, mid, result);
                if (a) {
                    j = mid;
                } else {
                    i = mid;
                }
            }
            System.out.println(j);
        }
    }

    public static boolean check(int m, int mid, int[] result) {
        int counter = 1;
        int max = mid;
        for (int i = 0; i < result.length; i++) {
            if (mid < result[i]) {
                return false;
            }
            if (max < result[i]) {
                max = mid;
                counter += 1;
            }
            max = max - result[i];
        }
        return counter <= m;
    }
}
