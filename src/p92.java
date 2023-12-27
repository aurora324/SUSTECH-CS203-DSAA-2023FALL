import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p92 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int s = in.nextInt();
            node[] nodes = new node[n + 1];
            for (int j = 1; j < nodes.length; j++) {
                nodes[j] = new node(i);
            }
            for (int j = 0; j < m; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                nodes[x].children.add(nodes[y]);
                nodes[y].children.add(nodes[x]);
            }
            node root = nodes[s];
            root.flag = true;

            node[] queue = new node[10_0001];
            int front = 0;
            int rear = 0;
            queue[front] = root;
            rear++;
            buildTree(queue, front, rear);
            for (int j = 1; j < nodes.length; j++) {
                if (nodes[j].total == 0 && j != s) {
                    out.print(-1 + " ");
                } else {
                    out.print(nodes[j].total + " ");
                }
            }
            out.print("\n");
        }
        out.close();
    }

    public static void buildTree(node[] queue, int front, int rear) {
        while (rear - front != 0) {
            for (int i = 0; i < queue[front].children.size(); i++) {
                if (!queue[front].children.get(i).flag) {
                    queue[rear] = queue[front].children.get(i);
                    queue[rear].flag = true;
                    rear++;
                    queue[front].children.get(i).total = queue[front].total + 1;
                }
            }
            front++;
        }
    }

    public static class node {
        int val;
        ArrayList<node> children = new ArrayList<>();
        boolean flag;
        int total;

        public node(int val) {
            this.val = val;
        }
    }

    static class QReader {
        private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
