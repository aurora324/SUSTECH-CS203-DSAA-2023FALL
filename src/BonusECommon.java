import java.io.*;
import java.util.StringTokenizer;

public class BonusECommon {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String s = in.nextLine();
        String[] val = new String[s.length()];
        int topOfVal = 0;
        node[] op = new node[s.length()];
        int topOfOp = 0;
        for (int i = 0; i < s.length(); i++) {
            String cur = s.charAt(i) + "";
            if (cur.equals(",")) continue;
            else if (s.equals("(")) {
                op[topOfOp] = new node("(");
                topOfOp++;
            } else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                node node;
                if (s.equals("+")) {
                    node = new node("+", 1);
                } else if (s.equals("-")) {
                    node = new node("-", 1);
                } else if (s.equals("*")) {
                    node = new node("*", 2);
                } else {
                    node = new node("/", 2);
                }
                while (topOfOp != 0 && op[topOfOp - 1].s != "(" && node.val <= op[topOfOp - 1].val) {
                    val[topOfVal - 2] = val[topOfVal - 2] + op[topOfOp - 1] + val[topOfVal - 1];
                    topOfOp--;
                    topOfVal--;
                }
                op[topOfOp] = node;
                topOfOp++;
            }
            else if(s.equals(")")){
                while (!op[topOfOp-1].s.equals("(")){
                    //suan
                }
                op[topOfOp-1]=null;
                topOfOp--;
            }else{
                val[topOfVal]=cur;
                topOfVal++;
            }
        }
        System.out.println(val[0]);
        out.close();
    }

    public static void buildTree(node root, node[] stack, int top, String s, int cur) {
        if (top == 0) {
            return;
        }
//        if(check(s.charAt(cur)))
//        while (stack[top-1].left)
    }

    private static class node {
        String s;
        int val;
        //(
        boolean have;
        //in
        boolean isVisit;
        node left;
        node right;

        public node() {
        }

        public node(String s) {
            this.s = s;
        }

        public node(String s, int val) {
            this.s = s;
            this.val = val;
        }


    }

    public static boolean check(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
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
