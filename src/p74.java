import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p74 {
    public static void main(String[] args) {
//        PriorityQueue<Integer>
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int[] arr = new int[n + 1];
            int[] copy = new int[n + 1];
            for (int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }
            sort(arr, copy, 0, n - 1);
            int head1 = 0;
            int rear1 = n;
            int[] queue = new int[n + 1];
            int head2 = 0;
            int rear2 = 0;
            node left = new node(arr[head1]);

            node right = new node(arr[head1]);
            node cur = new node(left.val + right.val);
            arr[n] = Integer.MAX_VALUE;
            queue[0] = Integer.MAX_VALUE;

            ArrayList<Integer>arrayList=new ArrayList<>();
            while (!(rear2 - head2 == 1 && rear1 - head1 == 0)) {
                if (rear2 - head2 == 0) {
                    left = new node(arr[head1]);
                    head1++;
                } else {
                    if (arr[head1] > queue[head2]) {
                        left = new node(queue[head2]);
                        head2++;
                    } else {
                        left = new node(arr[head1]);
                        head1++;
                    }
                }

                if (rear2 - head2 == 0) {
                    right = new node(arr[head1]);
                    head1++;
                } else {
                    if (arr[head1] > queue[head2]) {
                        right = new node(queue[head2]);
                        head2++;
                    } else {
                        right = new node(arr[head1]);
                        head1++;
                    }
                }
//                if(arr[head1]<cur.val||queue[head2]< cur.val){
//                    if (arr[head1] > queue[head2]) {
//                        right = new node(queue[head2]);
//                        head2++;
//                    } else {
//                        right = new node(arr[head1]);
//                        head1++;
//                    }
//                }else {
//                    right=new node(cur.val);
//                }
                cur = new node(left.val + right.val);
                arrayList.add(cur.val);
                queue[rear2] = cur.val;
                rear2++;
                queue[rear2] = Integer.MAX_VALUE;
                cur.left = left;
                cur.right = right;
            }
            int result=0;
            for (int j = 0; j < arrayList.size(); j++) {
                result+=arrayList.get(j);
            }
            out.println(result);
        }
        out.close();
    }

    public static class node {
        int val;
        node left;
        node right;


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

        public node getLeft() {
            return left;
        }

        public void setLeft(node left) {
            this.left = left;
        }

        public node getRight() {
            return right;
        }

        public void setRight(node right) {
            this.right = right;
        }
    }

    public static void sort(int[] arr, int[] copy, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(arr, copy, start, mid);
        sort(arr, copy, mid + 1, end);
        merge(arr, copy, start, mid, end);
    }

    public static void merge(int[] arr, int[] copy, int start, int mid, int end) {
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
            } else if (copy[i] <= copy[j]) {
                arr[k] = copy[i];
                i++;
            } else {
                arr[k] = copy[j];
                j++;
            }
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
