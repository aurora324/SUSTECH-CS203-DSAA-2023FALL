import java.io.*;
import java.util.StringTokenizer;

public class p55 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        node[] stack = new node[2000000];
        int head = 0;
        int rear = 0;
        long result = 0;
        for (int i = 0; i < n - 1; i++) {
            node node = new node(in.nextInt(), i);

            for (int j = rear - 1; j >= head; j--) {
                if (stack[j].val <= node.val) {
                    stack[j] = null;
                    rear--;
                }
            }
            stack[rear] = node;
            rear++;
        }


        int a;
        int index = n - 1;
        while ((a = in.nextInt()) != -1) {
            node node = new node(a, index);
            for (int j = rear - 1; j >= head; j--) {
                if (stack[j].val <= node.val) {
                    stack[j] = null;
                    rear--;
                }else {
                    break;
                }
            }
            stack[rear] = node;
            rear++;
            if (node.index - stack[head].index >= n) {
                stack[head] = null;
                head++;
            }

            index++;

            out.print(stack[head].val+" ");
            result ^= stack[head].val;
        }
        out.print(result);
        out.close();
    }

    private static class node {
        int val;
        int index;
        node pre;
        node next;

        public node() {
        }

        public node(int val, int index) {
            this.val = val;
            this.index = index;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public node getPre() {
            return pre;
        }

        public void setPre(node pre) {
            this.pre = pre;
        }

        public node getNext() {
            return next;
        }

        public void setNext(node next) {
            this.next = next;
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
