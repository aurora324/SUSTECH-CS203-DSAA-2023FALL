import java.io.*;
import java.util.StringTokenizer;

public class p85 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int m = in.nextInt();
        int k = in.nextInt();
        node[] nodes = new node[m];
        for (int i = 0; i < m; i++) {
            nodes[i] = new node(in.nextInt());
        }
        node root = nodes[0];
        root.depth = 1;
        for (int i = 1; i < m; i++) {
            insert(root, nodes[i]);
        }
        System.out.println();
        out.close();

    }

    public static void insert(node root, node node) {
        node.depth = 1;
//        node children=node.left.val<node.right.val?node.left:node.right;
        node cur = root;
        while (true) {
            if (node.val > cur.val) {
                if (cur.right == null) {
                    cur.depth++;
                    cur.right = node;
                    node.father = cur;
                    break;
                } else {
                    cur.depth++;
                    cur = cur.right;
                }
            } else if (node.val < cur.val) {
                if (cur.left == null) {
                    cur.depth++;
                    cur.left = node;
                    node.father = cur;
                    break;
                } else {
                    cur.depth++;
                    cur = cur.left;
                }
            }
        }
        while (cur != root) {
            cur = cur.father;
            int leftDepth = 0;
            int rightDepth = 0;
            if (cur.left != null) {
                leftDepth = cur.left.depth;
            }
            if (cur.right != null) {
                leftDepth = cur.right.depth;
            }
            if (leftDepth - rightDepth == 2) {
                rightRotate(cur);
            } else {
                leftRotate(cur);
            }

        }

    }

    public static void delete(node root, node node) {
        if (node.left == null && node.right == null) {
            if (node.father.left == node) {
                node.father.left = null;
            } else {
                node.father.right = null;
            }
        } else if (node.right == null) {
            if (node.val > node.father.val) {
                node.father.right = node.left;
            } else {
                node.father.left = node.left;
            }
            node.left.father = node.father;
        } else if (node.left == null) {
            if (node.val > node.father.val) {
                node.father.right = node.right;
            } else {
                node.father.left = node.right;
            }
            node.right.father = node.father;
        } else {
            node ancestor = ancestor(node);
            if (ancestor.left == null && ancestor.right == null) {
                node.val = ancestor.val;
                ancestor.father = null;
            } else if (ancestor.right != null) {
                node.val = ancestor.val;
                ancestor.val = ancestor.right.val;
                ancestor.right.father = null;
            }
        }
    }

    public static node ancestor(node root) {
        node cur = root;
        if (root.right == null) {
            return null;
        } else {
            cur = cur.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
    }

    public static void leftRotate(node root) {

    }

    public static void rightRotate(node root) {

    }

    public static class node {
        int val;
        int depth;
        node left;
        node right;
        node father;

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
