import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p73 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int q = 0; q < T; q++) {
            int n = in.nextInt();
            node[][] nodes = new node[2][n + 1];
            for (int i = 0; i < nodes.length; i++) {
                for (int j = 0; j < nodes[0].length; j++) {
                    nodes[i][j] = new node(j);
                }
            }
            node[] preOrder = new node[n];
            for (int i = 0; i < preOrder.length; i++) {
                int temp = in.nextInt();
                preOrder[i] = nodes[0][temp];
                nodes[1][temp].other = i;
            }
            node[] inOrder = new node[n];
            for (int i = 0; i < inOrder.length; i++) {
                int temp = in.nextInt();
                inOrder[i] = nodes[1][temp];
                nodes[0][temp].other = i;
            }

            buildTree(preOrder, inOrder, preOrder[0], 1, 1 + preOrder[0].other - 1, 1 + preOrder[0].other - 1 + 1, n - 1);


            node root = preOrder[0];
            ArrayList<Integer> postOrder = new ArrayList<>();
            post(postOrder, root);
            for (Integer integer : postOrder) {
                out.print(integer + " ");
            }
            out.print("\n");
        }
        out.close();
    }

    public static void buildTree(node[] preOrder, node[] inOrder, node root, int preLeft, int preRight, int postLeft, int postRight) {
        if (preLeft <= preRight) {
            root.left = preOrder[preLeft];
            int rightLength = root.other - preOrder[preLeft].other - 1;
            int leftLength = preRight - preLeft - rightLength;
            buildTree(preOrder, inOrder, root.left, preLeft + 1, preLeft + 1 + leftLength - 1, preLeft + 1 + leftLength - 1 + 1, preLeft + 1 + leftLength - 1 + 1 + rightLength - 1);
        }

        if (postLeft <= postRight) {
            root.right = preOrder[postLeft];
            int leftLength = preOrder[postLeft].other - root.other - 1;
            int rightLength = postRight - postLeft - leftLength;
            buildTree(preOrder, inOrder, root.right, postLeft + 1, postLeft + 1 + leftLength - 1, postLeft + 1 + leftLength - 1 + 1, postLeft + 1 + leftLength - 1 + 1 + rightLength - 1);
        }
    }

    public static void post(ArrayList<Integer> postOrder, node root) {
        if (root.left != null) {
            post(postOrder, root.left);
        }

        if (root.right != null) {
            post(postOrder, root.right);
        }

        postOrder.add(root.val);
    }

    public static class node {
        int val;
        node left;
        node right;
        int other;

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
