import java.io.*;
import java.util.StringTokenizer;

public class BonusA {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            long k = in.nextLong();
            long[] arr = new long[n + 1];
            for (int j = 0; j < arr.length - 1; j++) {
                arr[j] = in.nextLong();
//                sort[i]=arr[i];
            }
//            sort(sort,temp,0,n-1);
            //how many in a row: mid
            //k row
            long p = 1;
            long q = 2000000000001L;
            while (q - p > 1) {
                long mid = (p + q) >> 1;
                boolean result = check(arr, mid, k);
                if (result) {
                    p = mid;
                } else {
                    q = mid;
                }
            }
//            System.out.println(p + "" + k);
            out.println(p * k);
        }

        out.close();
    }

    public static boolean check(long[] arr, long mid, long k) {
        //compare to k
        long curCounter = 0;
        long curRe = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            curRe += arr[i];
            if (curRe < mid) {
                while (arr[i] + arr[i + 1] < mid && i < arr.length - 2) {
                    i++;
                }
                curRe = arr[i];
            }
            if (curRe >= mid) {
                long sumCount = curRe / mid;
                curRe %= mid;
                curCounter += sumCount;
            }
        }
        return curCounter >= k;
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
