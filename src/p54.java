import java.io.*;
import java.util.StringTokenizer;

public class p54 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            node[] arr = new node[n];
            node[] copy = new node[n];
            node[] temp = new node[n];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = new node(in.nextInt());
                copy[j] = arr[j];
            }

            sort(copy, temp, 0, n - 1);

            node head = new node(-1);
            node tail = new node(-1);
            node cur = head;
            for (int j = 0; j < copy.length; j++) {
                copy[j].pre = cur;
                cur.next = copy[j];
                cur = cur.next;
            }
            cur.next = tail;
            tail.pre = cur;

            int[] stack = new int[n];
            int top = 0;
            for (int j = 0; j < arr.length; j++) {
                stack[top] = arr[j].val;
                top++;
                if (stack[top - 1] == head.next.val) {
                    arr[j].pre.next = arr[j].next;
                    arr[j].next.pre = arr[j].pre;
                    out.print(stack[top - 1] + " ");
                    stack[top - 1] = 0;
                    top--;
                    while (top-1>=0&&stack[top - 1] <= head.next.val){
                        out.print(stack[top - 1] + " ");
                        stack[top - 1] = 0;
                        top--;
                    }
                } else {
                    arr[j].pre.next = arr[j].next;
                    arr[j].next.pre = arr[j].pre;
                }
            }
            for (int j = top - 1; j >= 0; j--) {
                out.print(stack[j] + " ");
            }
            out.print("\n");
        }
        out.close();
    }

    public static void sort(node[] arr, node[] copy, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(arr, copy, start, mid);
        sort(arr, copy, mid + 1, end);
        merge(arr, copy, start, mid, end);
    }

    public static void merge(node[] arr, node[] copy, int start, int mid, int end) {
        if (end + 1 - start >= 0) System.arraycopy(arr, start, copy, start, end + 1 - start);
        int i = start;
        int j = mid + 1;
        for (int k = start; k <= end; k++) {
            if (i > mid) {
                arr[k] = copy[j];
                j++;
            } else if (j > end) {
                arr[k] = copy[i];
                i++;
            } else if (copy[i].val <= copy[j].val) {
                arr[k] = copy[i];
                i++;
            } else {
                arr[k] = copy[j];
                j++;
            }
        }
    }

    private static class node {
        int val;
        node next;
        node pre;

        public node() {
        }

        public node(int val) {
            this.val = val;
        }

        public node(int val, node next, node pre) {
            this.val = val;
            this.next = next;
            this.pre = pre;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public node getNext() {
            return next;
        }

        public void setNext(node next) {
            this.next = next;
        }

        public node getPre() {
            return pre;
        }

        public void setPre(node pre) {
            this.pre = pre;
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
