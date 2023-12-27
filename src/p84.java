import java.io.*;
import java.util.StringTokenizer;

public class p84 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        node[] nodes = new node[n + 1];
        nodes[1] = new node(in.nextInt());
        nodes[1].index = 1;
        for (int i = 2; i < nodes.length; i++) {
            nodes[i] = new node(in.nextInt());
            nodes[i].index = i;
            nodes[i].pre = nodes[i - 1];
            nodes[i - 1].next = nodes[i];
        }
        node[] heap = new node[50_0000];
        int top = 1;
        for (int i = 1; i < nodes.length; i++) {
            insert(heap, top, nodes[i]);
            top++;
        }

        node left = null;
        node right = null;
        node node = null;
        for (int i = 0; i < n; i++) {
            while (!heap[1].flag) {
                delete(heap, top);
                top--;
            }

            node = delete(heap, top);
            node.flag=false;
            top--;


            right = node;
            while (right.next != null && !right.next.flag) {
                right = right.next;
            }
            right = right.next;

            left = node;
            while (left.pre != null && !left.pre.flag) {
                left = left.pre;
            }
            left = left.pre;

            node delete = null;
            if (left != null && right != null) {
                if ((left.val^ node.val) < (right.val^ node.val)) {
                    delete = left;
                } else if ((left.val^ node.val) > (right.val^ node.val)) {
                    delete = left;
                } else {
                    if (left.index < right.index) {
                        delete = left;
                    } else {
                        delete = right;
                    }
                }
            } else if (left == null && right != null) {
                delete = right;
            } else if (left != null) {
                delete = left;
            }else {
                break;
            }
            System.out.println();

            delete.flag = false;
            insert(heap, top, new node((delete.val ^ node.val) + 1));
            top++;
//            System.out.println();
        }
        out.print(heap[1].val);
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
        private final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

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
