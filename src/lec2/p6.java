package lec2;

import java.util.Scanner;

public class p6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x1 = sc.nextLong();
        long y1 = sc.nextLong();
        long x2 = sc.nextLong();
        long y2 = sc.nextLong();
        int num = sc.nextInt();
        String s = sc.next();
        int[][] pos = {
                {0, 0, -1, 1},
                {1, -1, 0, 0}
        };

        int[][] semiPos = new int[2][num+1];
        for (int i = 1; i < s.length()+1; i++) {
            switch (s.charAt(i-1)) {
                case 'U':
                    semiPos[0][i] = semiPos[0][i - 1] + pos[0][0];
                    semiPos[1][i] = semiPos[1][i - 1] + pos[1][0];
                    break;
                case 'D':
                    semiPos[0][i] = semiPos[0][i - 1] + pos[0][1];
                    semiPos[1][i] = semiPos[1][i - 1] + pos[1][1];
                    break;
                case 'L':
                    semiPos[0][i] = semiPos[0][i - 1] + pos[0][2];
                    semiPos[1][i] = semiPos[1][i - 1] + pos[1][2];
                    break;
                case 'R':
                    semiPos[0][i] = semiPos[0][i - 1] + pos[0][3];
                    semiPos[1][i] = semiPos[1][i - 1] + pos[1][3];
                    break;
            }
        }


        long m = 0;
        long n = 1000000000000000L;
        long result = 0;
        while (n > m) {
            long mid = (m + n) / 2;

            long zheng = mid / num;
            long re = mid % num;
            long x3 = x2 + zheng * semiPos[0][num];
            long y3 = y2 + zheng * semiPos[1][num];
            if (re != 0) {
                x3 += semiPos[0][(int) (re)];
                y3 += semiPos[1][(int) (re)];
            }
            result = mid - Math.abs(x3 - x1) - Math.abs(y3 - y1);
            if (result >= 0) {
                n = mid;
            } else {
                m = mid + 1;
            }
        }
        if (n == 1000000000000000L) {
            System.out.println(-1);
        } else {
            System.out.println(n);
        }
    }
}
