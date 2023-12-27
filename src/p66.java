import java.io.*;
import java.util.StringTokenizer;

public class p66 {
    public static void main(String[] args) {
//        System.out.println((int)'a');
        QReader in = new QReader();
        QWriter out = new QWriter();
        String s = in.nextLine();
        String[] trans = new String[26];
        for (int i = 0; i < trans.length; i++) {
            trans[i] = s.charAt(2 * i) + "";
        }
        String a = in.nextLine();
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            b.append(trans[(int) a.charAt(i) - 97]);
        }
        String text = a +"*"+ b;
        int[] next = computeNext(text);
        int answer=next[next.length-1];
        out.print(a.length() - answer);
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
