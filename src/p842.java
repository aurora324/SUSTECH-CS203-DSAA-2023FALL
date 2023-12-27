import java.io.*;
import java.util.StringTokenizer;

public class p842 {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int n=in.nextInt();
        node [] nodes=new node[n+1];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i]=new node(in.nextInt());
            nodes[i].index=i;
        }

        out.close();
    }

    public static void insert(node[] heap, int top, node num) {
        heap[top] = num;
        while (top > 1) {
            if (heap[top].val < heap[top / 2].val) {
                swap(heap, top, top / 2);
                top = top / 2;
            } else if (heap[top].val == heap[top / 2].val && heap[top].index < heap[top / 2].index) {
                swap(heap, top, top / 2);
                top = top / 2;
            } else {
                break;
            }
        }
    }

    public static node delete(node[] heap, int top) {
//        System.out.println(heap[2].val+heap[3].val);
        node re = heap[1];
        heap[1] = heap[top - 1];
        heap[top - 1] = null;
        int t = 1;
        while (t * 2 < top - 1) {
            if (t * 2 + 1 == top - 1) {
                if (heap[t * 2].val <= heap[t].val) {
                    swap(heap, t, t * 2);
                    t *= 2;
                } else {
                    return re;
                }
            } else {
                if (heap[t].val <= heap[t * 2].val && heap[t].val <= heap[t * 2 + 1].val) {
                    return re;
                } else if (heap[2 * t].val <= heap[t].val && heap[2 * t].val <= heap[2 * t + 1].val) {
                    swap(heap, t, 2 * t);
                    t *= 2;
                } else if (heap[2 * t + 1].val <= heap[t].val && heap[2 * t + 1].val <= heap[2 * t].val) {
                    swap(heap, t, 2 * t + 1);
                    t = 2 * t + 1;
                }
            }
        }
        return re;
    }

    public static void swap(node[] heap, int i, int j) {
        node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private static class node {
        int val;
        int index;
        boolean flag = true;
        node pre;
        node next;

        public node() {
        }

        public node(int val) {
            this.val = val;
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
