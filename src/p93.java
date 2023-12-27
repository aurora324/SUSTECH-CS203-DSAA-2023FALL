import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p93 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T_case = in.nextInt();
        for (int i = 0; i < T_case; i++) {
            int n_node = in.nextInt();
            int m_road = in.nextInt();
            node[] nodes = new node[n_node + 1];
            for (int j = 1; j < nodes.length; j++) {
                nodes[j] = new node(j);
            }
            for (int j = 0; j < m_road; j++) {
                int p = in.nextInt();
                int q = in.nextInt();
                nodes[p].children.add(nodes[q]);
                nodes[q].children.add(nodes[p]);
            }
            node root = nodes[1];

            node[] queue = new node[n_node];
            int front = 0;
            int rear = 0;
            queue[rear] = root;
            queue[rear].flag = true;
            rear++;

            buildTree(queue,front,rear);



            int counter = 0;
            int counter_true = 0;
            for (int j = 1; j < nodes.length; j++) {
                if (!nodes[j].tower) {
                    counter++;
                }
            }
            counter_true=n_node-counter;




            if (counter > n_node / 2) {
                out.println(counter_true);

                for (int j = 1; j < nodes.length; j++) {
                    if (nodes[j].tower) {
                        out.print(j + " ");
                    }
                }
                out.println("");
            } else {
                out.println(counter);
                for (int j = 1; j < nodes.length; j++) {
                    if (!nodes[j].tower) {
                        out.print(j + " ");
                    }
                }
                out.println("");
            }


        }
        out.close();
    }

    public static void buildTree(node[] queue, int front, int rear) {
        while (rear - front != 0) {
            for (int i = 0; i < queue[front].children.size(); i++) {
                if (!queue[front].children.get(i).flag) {
                    queue[rear] = queue[front].children.get(i);
                    queue[rear].flag = true;
                    if(!queue[front].tower){
                        queue[rear].tower=true;
                    }
                    rear++;
                }
            }
            front++;
        }
    }

    public static class node {
        boolean flag;
        int val;
        ArrayList<node> children = new ArrayList<>();
        node father;
        boolean tower;

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
