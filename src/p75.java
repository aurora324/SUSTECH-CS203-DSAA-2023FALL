import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p75 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        node[] nodes = new node[n + 1];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new node(i);
        }
        for (int i = 0; i < n - 1; i++) {
            node p = nodes[in.nextInt()];
            node q = nodes[in.nextInt()];
            p.children.add(q);
            q.children.add(p);
        }


        node root = nodes[1];
        node[] queue = new node[400_000];
        int front = 0;
        int rear = 0;
        queue[rear] = root;
        queue[0].flag = true;
        rear++;
        for (int i = 0; i < root.children.size(); i++) {
            root.children.get(i).index = i + 1;
        }
        buildTree(queue, front, rear);
//        System.out.println();

        int max = 0;
        int[][] arr = new int[root.children.size()][400_000];
        int[] temp = new int[400_000];
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            arr[nodes[u].index - 1][i] = nodes[u].total;
        }

        for (int i = 0; i < arr.length; i++) {
            sort(arr[i], temp, 0, 400_000 - 1);
            for (int j = 1; j < arr[i].length; j++) {
                if (!(arr[i][j] == 0 && arr[i][j - 1] == 0)) arr[i][j] = Math.max(arr[i][j], arr[i][j - 1] + 1);
            }
            max = Math.max(max, arr[i][400_000 - 1]);
        }
//        System.out.println();
        out.print(max);

//        int m = in.nextInt();
//        int[] arr = new int[m];
//        int[] temp = new int[m];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = nodes[in.nextInt()].total;
//        }
//        sort(arr, temp, 0, m - 1);
//        for (int i = 1; i < arr.length; i++) {
//            arr[i] = Math.max(arr[i], arr[i - 1] + 1);
//        }
        out.close();
    }

    public static void buildTree(node[] queue, int front, int rear) {
        while (rear - front != 0) {
            for (int i = 0; i < queue[front].children.size(); i++) {
                if (!queue[front].children.get(i).flag) {
                    queue[rear] = queue[front].children.get(i);
                    queue[rear].flag = true;
                    if (queue[front].index != 0) {
                        queue[rear].index = queue[front].index;
                    }
                    rear++;
                    queue[front].children.get(i).total = queue[front].total + 1;
                }
            }
            front++;
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


    private static class node {
        int val;
        int total;
        boolean flag;
        int index;
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
