import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p82 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[] heap = new int[n + 1];

        for (int i = 1; i < heap.length; i++) {
            int num = in.nextInt();
            int counter = 0;
            heap[i] = num;

            int top = i;
            while (top > 1) {
                if (heap[top] > heap[top / 2]) {
                    int temp = heap[top];
                    heap[top] = heap[top / 2];
                    heap[top / 2] = temp;
                    counter++;
                    top=top/2;
                } else {
                    break;
                }
            }
            out.print(counter+" ");

        }
        out.close();
    }

    private static class node {
        int val;
        int total;
        boolean flag;
        ArrayList<node> children = new ArrayList<>();

        public node() {
        }

        public node(int val) {
            this.val = val;
        }


        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ArrayList<node> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<node> children) {
            this.children = children;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
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
