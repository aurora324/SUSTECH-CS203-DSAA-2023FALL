import java.io.*;
import java.util.StringTokenizer;

public class p651 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String a = in.nextLine();
        String b = in.nextLine();
        int i = 0;
        int j = Math.min(a.length(), b.length()) + 1;
        int mid = 0;
        long[][] temp = new long[2][Math.max(a.length(), b.length())];
        int answer = 0;
        while (j - i > 1) {
            mid = (i + j) / 2;
            long[][] arr1 = hashing(a, mid);
            long[][] arr2 = hashing(b, mid);
            sort(arr1, temp, 0, arr1[0].length - 1);
            sort(arr2, temp, 0, arr2[0].length - 1);
            boolean flag = false;
            for (int k = 0; k < arr1[0].length; k++) {
                int result = binarySearch(arr2[0], arr1[0][k]);
                if (result != -1) {
                    long indexOfa = arr1[1][k];
                    long indexOfb = arr2[1][result];
                    boolean part = true;
                    for (int p = 0; p < mid; p++) {
                        if (a.charAt((int) indexOfa) != b.charAt((int) indexOfb)) {
                            part = false;
                            break;
                        }
                    }
                    if (part) flag = true;
                    break;
                }
            }
            if (flag) {
                answer = mid;
                i = mid;
            } else {
                j = mid;
            }
        }
        out.print(answer);
        out.close();
    }

    public static int binarySearch(long[] arr, long a) {
        int j = 0, k = arr.length;
        while (1 < k - j) {
            int mid = (j + k) / 2;
            if (a < arr[mid]) {
                k = mid;
            } else {
                j = mid;
            }
        }
        if (a == arr[j]) {
            return j;
        }
        return -1;
    }

    public static void sort(long[][] arr, long[][] copy, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(arr, copy, start, mid);
        sort(arr, copy, mid + 1, end);
        merge(arr, copy, start, mid, end);
    }

    public static void merge(long[][] arr, long[][] copy, int start, int mid, int end) {
        if (end + 1 - start >= 0) {
            System.arraycopy(arr[0], start, copy[0], start, end + 1 - start);
            System.arraycopy(arr[1], start, copy[1], start, end + 1 - start);
        }
        int i = start;
        int j = mid + 1;
        for (int k = start; k <= end; k++) {
            if (i > mid) {
                arr[0][k] = copy[0][j];
                arr[1][k] = copy[1][j];
                j++;
            } else if (j > end) {
                arr[0][k] = copy[0][i];
                arr[1][k] = copy[1][i];
                i++;
            } else if (copy[0][i] <= copy[0][j]) {
                arr[0][k] = copy[0][i];
                arr[1][k] = copy[1][i];
                i++;
            } else {
                arr[0][k] = copy[0][j];
                arr[1][k] = copy[1][j];
                j++;
            }
        }
    }

    public static long[][] hashing(String s, int length) {
        long[][] arr = new long[2][s.length() - length + 1];
        long val = 0;
        for (int i = 0; i < length; i++) {
            val = val * 139 + s.charAt(i);
        }
        arr[0][0] = val;
        for (int i = 0; i < arr[0].length - 1; i++) {
            arr[0][i + 1] = (arr[0][i] - s.charAt(i) * power(139, length - 1)) * 139 + s.charAt(i + length);
            arr[1][i + 1] = i + 1;
        }
        return arr;
    }

    public static long power(int a, int b) {
        long val = 1;
        for (int i = 0; i < b; i++) {
            val *= a;
        }
        return val;
    }

    static class QReader {
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

    static class QWriter implements Closeable {
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
}
