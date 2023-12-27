import java.io.*;
import java.util.StringTokenizer;

public class p53 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        char[] stack = new char[100000];
        int[] score = new int[100000];
        String s = in.nextLine();
        if (s.length() == 0) {
            System.out.println(0);
            System.exit(0);
        }
        int top = 0;
        for (int i = 0; i < s.length(); i++) {
            stack[top] = s.charAt(i);
            if (stack[top] == '(') {
                top++;
            } else {
                if (stack[top] == ')') {
                    if (score[top] == 0) {
                            stack[top] = ' ';
                            top--;
                            stack[top] = ' ';
                            score[top] += 1;
                            score[top]%=514329;
                    } else {
                        stack[top] = ' ';
                        top--;
                        score[top] += score[top + 1] * 2;
                        score[top]%=514329;
                        score[top + 1] = 0;
                    }
                }
            }
        }
        out.print(score[0]);
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
