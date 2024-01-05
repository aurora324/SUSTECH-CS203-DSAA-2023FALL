import java.io.*;
import java.util.StringTokenizer;

public class BonusE {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String s = in.nextLine();

        String[] stack = new String[s.length()];
        stack[0] = " ";
        int top = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char cur = s.charAt(i);
            if (cur == ',') continue;
            if (cur == ')') {
                if (stack[top - 1].charAt(0) == '(') {
                    stack[top] = cur + "";
                    top++;
                } else {
                    stack[top - 1] = cur + stack[top - 1];
                }
            } else if (cur == '(') {
                stack[top - 1] = cur + stack[top - 1];
            } else if (cur == '+' || cur == '-' || cur == '*' || cur == '/') {
                stack[top - 2] = stack[top - 1] + cur + stack[top - 2];
                top--;
                stack[top] = null;
            } else {
                if (Character.isLetter(stack[top - 1].charAt(0)) || stack[top - 1].charAt(0) == '(') {
                    stack[top] = cur + "";
                    top++;
                    continue;
                } else {
                    stack[top - 1] = cur + stack[top - 1];
                }
            }
        }
        out.println(stack[0]);
        StringBuilder news = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == ',') continue;
            else news.append(s.charAt(i));
        }
        out.println(news);

        node node = buildTree(String.valueOf(news),0);
        out.close();
    }

    private static node buildTree(String expression,int index) {
        if (index >= expression.length()) {
            return null;
        }

        char c = expression.charAt(index++);
        node newNode = new node(c);

        if (isOperator(c)) {
            newNode.left = buildTree(expression,index);
            newNode.right = buildTree(expression,index);
        }

        return newNode;
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static class node {
        char c;
        node left;
        node right;

        public node() {
        }

        public node(char c) {
            this.c = c;
        }


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
