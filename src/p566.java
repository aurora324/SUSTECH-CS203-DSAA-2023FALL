import java.io.*;
import java.util.StringTokenizer;

public class p566 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int k = in.nextInt();
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
        int length = 0;
        int left = 0;
        int right;
        int min = arr[0];
        int max = arr[0];
        int[] queue1 = new int[n];
        queue1[0] = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            right = i;
            queue1[right] = arr[i];
            max = Math.max(max, queue1[right]);
            min = Math.min(min, queue1[right]);
            if (max - min > k) {
                while (Math.abs(queue1[right] - min) > k || Math.abs(max - queue1[right]) > k) {
                    left++;
                    min = queue1[left];
                    max = queue1[left];
                    for (int j = left; j <= right; j++) {
                        max = Math.max(max, queue1[j]);
                        min = Math.min(min, queue1[j]);
                    }
                }
            }
            max = Math.max(max, queue1[right]);
            min = Math.min(min, queue1[right]);
            length = Math.max(right - left + 1, length);
        }
        out.print(length);
        out.close();
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
        public void println() {
            try {
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


