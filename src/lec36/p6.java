package lec36;

import java.io.*;
import java.util.StringTokenizer;

public class p6 {
    static int k;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        int[][] arr = new int[3][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                arr[j][i] = in.nextInt();
            }
        }

        long sum = 0L;

        if (p == 0) {
            int[] temp = new int[n];
            System.arraycopy(arr[1], 0, temp, 0, n);
            quickSort(temp, 0, n - 1);
            for (int i = n - 1; i >= n - q; i--) {
                sum += temp[i];
            }
            out.print(sum);
            out.close();
            System.exit(0);
        }


        int a = 0;
        if (q == 0) {
            for (int i = 0; i < arr[0].length; i++) {
                sum += arr[1][i];
            }
        } else {

            int[][] copy = new int[3][n];
            for (int i = 0; i < arr[0].length; i++) {
                arr[2][i] = arr[0][i] - arr[1][i];
            }
            sort(arr, copy, 0, n - 1);
            if (arr[2][n - 1] > 0) {
                a = n - 1;
            } else {
                for (int i = 0; i < arr[0].length; i++) {
                    if (arr[2][i] <= 0) {
                        if(i-1>0)
                            a = i - 1;
                    }
                }
            }


            long advance = 0;
            if (q >= n) {
                for (int i = 0; i < n; i++) {
                    sum += Math.max(arr[0][i], arr[1][i]);
                }
                for (int i = 0; i < arr[0].length; i++) {
                    long temp = (long) arr[0][i] * power(2, p) - Math.max(arr[0][i], arr[1][i]);
                    if (temp > advance) {
                        advance = temp;
                    }
                }
            } else {

                if (a <= q - 1) {
                    for (int i = 0; i < a; i++) {
                        sum += arr[0][i];
                        long temp = (long) arr[0][i] * power(2, p) - arr[0][i];
                        if (temp > advance) {
                            advance = temp;
                        }

                    }
                    for (int i = a; i < arr[0].length; i++) {
                        sum += arr[1][i];
                        long temp = (long) arr[0][i] * power(2, p) - arr[1][i];
                        if (temp > advance) {
                            advance = temp;
                        }
                    }
                } else {
                    for (int i = 0; i < q - 1; i++) {
                        sum += arr[0][i];
                        long temp = (long) arr[0][i] * power(2, p) - arr[0][i];
                        if (temp > advance) {
                            advance = temp;
                        }
                    }
                    sum += arr[1][q - 1];
                    if ((long) arr[0][q - 1] * power(2, p) - arr[1][q - 1] > advance) {
                        advance = (long) arr[0][q - 1] * power(2, p) - arr[1][q - 1];
                    }

                    for (int i = q; i < arr[0].length; i++) {
                        sum += arr[1][i];
                        long temp = (long) arr[0][i] * power(2, p) - arr[1][i];
                        if (temp > advance) {
                            advance = temp;
                        }
                    }
                }
            }
            sum += advance;
        }
        out.print(sum);
        out.close();
    }

    public static long power(int a, int b) {
        long result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
    }


    public static void sort(int[][] arr, int[][] copy, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(arr, copy, start, mid);
        sort(arr, copy, mid + 1, end);
        merge(arr, copy, start, mid, end);
    }

    public static void merge(int[][] arr, int[][] copy, int start, int mid, int end) {
        if (end + 1 - start >= 0) {
            System.arraycopy(arr[0], start, copy[0], start, end + 1 - start);
            System.arraycopy(arr[1], start, copy[1], start, end + 1 - start);
            System.arraycopy(arr[2], start, copy[2], start, end + 1 - start);
        }
        int i = start;
        int j = mid + 1;
        for (int k = start; k <= end; k++) {
            if (i > mid) {
                for (int t = 0; t < arr.length; t++) {
                    arr[t][k] = copy[t][j];
                }
                j++;
            } else if (j > end) {
                for (int t = 0; t < arr.length; t++) {
                    arr[t][k] = copy[t][i];
                }
                i++;
            } else if (copy[2][i] > copy[2][j]) {
                for (int t = 0; t < arr.length; t++) {
                    arr[t][k] = copy[t][i];
                }
                i++;
            } else {
                for (int t = 0; t < arr.length; t++) {
                    arr[t][k] = copy[t][j];
                }
                j++;
            }
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
        if (k >= start) {
            quickSort(arr, start + 1, j);
        }
    }
}


class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}


