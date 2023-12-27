import java.io.*;
import java.util.StringTokenizer;

public class p564 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int k = in.nextInt();
        int n = in.nextInt();

        int max = 0;
        int head = 1;
        int rear = 1;
        node[] stack = new node[3000000];

        stack[0] = new node(Integer.MAX_VALUE, 0);
        for (int i = 0; i < n; i++) {
            node node = new node(in.nextInt(), i + 1);
            int cur = rear - 1;
            while (stack[cur].val < node.val) {
                stack[cur + 1] = stack[cur];
                stack[cur] = null;
                cur--;
            }
            stack[cur + 1] = node;
            rear++;

//            max = Math.max(stack[head].val - stack[rear - 1].val, max);

            if(stack[head].val-stack[rear-1].val==k){
                max=Math.max(max,Math.abs(stack[rear-1].index-stack[head].index)+1);
            }else if(stack[head].val-stack[rear-1].val>k){
                while (stack[head].val-stack[rear-1].val>k){
                    head++;
                }
                if(stack[head].val-stack[rear-1].val==k){
                    max=Math.max(max,Math.abs(stack[rear-1].index-stack[head].index)+1);
                }
            }
        }
//        for (int i = 1; i < n; i++) {
//            out.print(stack[i].val+" ");
//        }
        out.print(max);

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

        public node(int val, int index, node pre, node next) {
            this.val = val;
            this.index = index;
            this.pre = pre;
            this.next = next;
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
