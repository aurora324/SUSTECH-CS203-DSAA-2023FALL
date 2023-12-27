import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p96 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();

        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            node[] nodes = new node[n + 1];
            for (int j = 1; j <= n; j++) {
                nodes[j] = new node(j);
                nodes[j].a = in.nextInt();
                nodes[j].b = in.nextInt();
            }
            for (int j = 0; j < m; j++) {
                int p = in.nextInt();
                int q = in.nextInt();
                nodes[p].children.add(nodes[q]);
                nodes[q].degree++;
            }
            node[] queue = new node[n];
            int front = 0;
            int rear = 0;
            for (int j = 1; j < nodes.length; j++) {
                if (nodes[j].degree == 0) {

                    queue[rear] = nodes[j];
                    rear++;
                }
            }

            int mod = 1000000007;
            while ((rear - front) != 0) {
                for (int j = 0; j < queue[front].children.size(); j++) {
                    queue[front].children.get(j).degree--;
                    queue[front].children.get(j).cur += (queue[front].cur + queue[front].a) % mod;
                    queue[front].children.get(j).cur %= mod;
                    if (queue[front].children.get(j).degree == 0) {
                        queue[rear] = queue[front].children.get(j);
                        rear++;
                    }
                }
                front++;
            }

            long result = 0;
            for (int j = 1; j < nodes.length; j++) {
                result += (nodes[j].cur * nodes[j].b) % mod;
                result %= mod;
            }
            out.println(result);

        }

        out.close();
    }

    public static class node {
        int val;
        int degree;
        long cur;
        long a;
        long b;
        ArrayList<node> children = new ArrayList<>();

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
