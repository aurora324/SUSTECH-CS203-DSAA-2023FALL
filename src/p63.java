import java.io.*;
import java.util.StringTokenizer;

public class p63 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String s = in.nextLine();
        int[] next = computeNext(s);
        int[][] answer = new int[s.length()][26];
        answer[0][s.charAt(0) - 97] = 1;
        for (int i = 1; i < s.length(); i++) {
            int a = s.charAt(i) - 97;
            System.arraycopy(answer[next[i - 1]], 0, answer[i], 0, answer[0].length);
            answer[i][a] = i + 1;
        }

        for (int[] ints : answer) {
            for (int j = 0; j < answer[0].length; j++) {
                out.print(ints[j] + " ");
            }
            out.print("\n");
        }
        out.close();
    }
    public static int[] computeNext(String pattern) {
        int m = pattern.length();
        int[] next = new int[m];
        next[0] = 0;
        int i = 1;
        int j = 0;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                next[i] = j;
                i++;
            } else if (j > 0) {
                j = next[j - 1];
            } else {
                next[i] = 0;
                i++;
            }
        }
        return next;
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
