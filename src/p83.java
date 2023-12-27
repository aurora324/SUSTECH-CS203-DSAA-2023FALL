import java.io.*;
import java.util.StringTokenizer;

public class p83 {
    static long[] a;
    static long[] b;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        a = new long[n];
        b = new long[m];
        for (int i = 0; i < a.length; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = in.nextInt();
        }
        quickSort(a, 0, n - 1);

        node[] heap = new node[Integer.MAX_VALUE / 100];
        heap[1] = new node(0, 0);
        int top = 2;
        for (int i = 1; i < b.length; i++) {
            node node = new node(0, i);
            insert(heap, top, node);
            top++;
        }
//        System.out.println(heap[1].val);
        for (int i = 0; i < k; i++) {
            node node = delete(heap, top);
            out.print(node.val + " ");
            top--;
            if (node.i < n - 1) {
                insert(heap, top, new node(node.i + 1, node.j));
                top++;
            }
        }
        out.close();
    }

    public static void swap(node[] heap, int i, int j) {
        node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
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


    public static void insert(node[] heap, int top, node num) {
        heap[top] = num;
        while (top > 1) {
            if (heap[top].val < heap[top / 2].val) {
                swap(heap, top, top / 2);
                top = top / 2;
            } else {
                break;
            }
        }
    }

    public static void quickSort(long[] arr, int i, int j) {
        int start = i;
        int end = j;
        if (start > end) {
            return;
        }
        long baseNumber = arr[i];
        while (start != end) {
            //先移动end再移动start
            while (end > start && arr[end] >= baseNumber) {
                end--;
            }
            while (end > start && arr[start] <= baseNumber) {
                start++;
            }

            long temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }

        long temp = arr[i];
        arr[i] = arr[start];
        arr[start] = temp;
        quickSort(arr, i, start - 1);
        quickSort(arr, start + 1, j);

    }

    public static class node {
        int i;
        int j;
        long val;

        public node() {
        }

        public node(int i, int j) {
            this.i = i;
            this.j = j;
            this.val = a[i] * b[j];
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
