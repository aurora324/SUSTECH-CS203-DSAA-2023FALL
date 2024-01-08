package lab10;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 5 2 2
 * 3 2
 * 2 3
 * <p>
 * 5 2 5
 * 4 5
 * 5 4
 */
public class E {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int S = in.nextInt();
        node[] nodes = new node[n + 1];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new node();
            nodes[i].index = i;
        }
        for (int i = 0; i < m; i++) {
            int p = in.nextInt();
            int q = in.nextInt();
            nodes[p].children.add(nodes[q]);
            nodes[q].reChildren.add(nodes[p]);
        }
        ArrayList<node> reverse = new ArrayList<>();
        for (int i = 1; i < nodes.length; i++) {
            if (!nodes[i].isVisited) {
                reDFS(nodes[i], reverse);
            }
        }
//        System.out.println();
        int cnt = 0;
        for (int i = reverse.size() - 1; i >= 0; i--) {
            if (reverse.get(i).isVisited) {
                cnt++;
                DFS(reverse.get(i), cnt);
            }
        }
        //szai入读为0的点，入读为0 的点减一
        int[] result = new int[cnt + 1];
        for (int i = 1; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].children.size(); j++) {
                if (nodes[i].color != nodes[i].children.get(j).color) {
                    result[nodes[i].children.get(j).color]++;
                }
            }
        }
        int counter = 0;
        for (int i = 1; i < result.length; i++) {
            if (result[i] == 0) counter++;
        }
        if (result[nodes[S].color] == 0) counter--;
        out.print(counter);
        out.close();
    }


    public static void reDFS(node node, ArrayList<node> reverse) {
        node.isVisited = true;
        for (int i = 0; i < node.reChildren.size(); i++) {
            if (!node.reChildren.get(i).isVisited) {
                reDFS(node.reChildren.get(i), reverse);
            }
        }
        reverse.add(node);
    }

    public static void DFS(node node, int cnt) {
        node.isVisited = false;
        node.color = cnt;
        for (int i = 0; i < node.children.size(); i++) {
            if (node.children.get(i).isVisited) {
                DFS(node.children.get(i), cnt);
            }
        }
    }

    public static class node {
        long val;
        int index;
        boolean isVisited;
        int color;
        ArrayList<node> children = new ArrayList<>();
        ArrayList<node> reChildren = new ArrayList<>();
        ArrayList<Long> length = new ArrayList<>();

        public node() {
        }

        public node(long val, int index) {
            this.val = val;
            this.index = index;
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
