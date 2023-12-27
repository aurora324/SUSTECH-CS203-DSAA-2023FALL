import java.io.*;
import java.util.StringTokenizer;

public class p51 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            String s = in.next();
            int top = 0;
            char[] stack = new char[n];
            int counter = 0;
            for (int j = 0; j < n; j++) {
                char cur = s.charAt(j);
                if (cur == '(' || cur == '[' || cur == '{') {
                    stack[top] = cur;
                    top++;
                    counter++;
                } else {
                    if (top == 0) {
                        break;
                    } else {
                        if (cur == '}' && stack[top - 1] == '{' || cur == ']' && stack[top - 1] == '[' || cur == ')' && stack[top - 1] == '(') {
                            counter++;
                            top--;
                            stack[top] = ' ';
                        } else {
                            break;
                        }
                    }
                }
            }
            if (top == 0 && counter == n) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
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

