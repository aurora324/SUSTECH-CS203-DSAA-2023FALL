import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p81 {
    public static void main(String[] args) {
//        System.out.println(true==true);
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            node[] nodes = new node[n + 1];
            for (int j = 1; j < nodes.length; j++) {
                nodes[j] = new node(j, in.nextInt());
            }
            boolean flag = true;
            for (int j = 0; j < n - 1; j++) {
                int p = in.nextInt();
                int q = in.nextInt();
                nodes[p].children.add(nodes[q]);
                nodes[q].father = nodes[p];
            }
            for (int j = 1; j < nodes.length; j++) {
                if(nodes[j].children.size()>2){
                    flag=false;
                }
            }
            node root = null;
            for (int j = 1; j < nodes.length; j++) {
                if (nodes[j].father == null) {
                    root = nodes[j];
                    break;
                }
            }

            boolean b1=true;
            for (int j = 1; j < nodes.length; j++) {
                for (int k = 0; k < nodes[j].children.size(); k++) {
                    if(!(nodes[j].val >=nodes[j].children.get(k).val)){
                        b1=false;
                        break;
                    }
                }
            }

            boolean b2=true;
            for (int j = 1; j < nodes.length; j++) {
                for (int k = 0; k < nodes[j].children.size(); k++) {
                    if(!(nodes[j].val <=nodes[j].children.get(k).val)){
                        b2=false;
                        break;
                    }
                }
            }
            if(!(b1||b2)){
                flag=false;
            }

            ArrayList<Integer> depth = new ArrayList<>();
            node[] queue = new node[n];
            int head = 0;
            int rear = 0;
            queue[0] = root;
            root.flag = true;
            rear++;
            buildTree(root, depth);

            int counter = 0;
            for (int j = 1; j < depth.size(); j++) {
                if (depth.get(j).equals(depth.get(0))) {
                    counter++;
                }
            }
            for (int j = 0; j < depth.size(); j++) {
                if (j <= counter) {
                    if (!depth.get(j).equals(depth.get(0))) {
                        flag = false;
                    }
                } else {
                    if (!depth.get(j).equals(depth.get(0) - 1)) {
                        flag = false;
                    }
                }
            }
            if (flag) {
                out.println("Case #" + (i + 1) + ": YES");
            } else {
                out.println("Case #" + (i + 1) + ": NO");
            }
        }
        out.close();
    }

    public static void buildTree(node root, ArrayList<Integer> depth) {
        if (root.children.isEmpty()) {
            depth.add(root.depth);
            return;
        }
        for (int i = 0; i < root.children.size(); i++) {
            root.children.get(i).depth = root.depth + 1;
            buildTree(root.children.get(i), depth);
        }


    }

    public static void checkDepth(node root, ArrayList<Integer> depth) {

    }

    private static class node {
        int val;
        int index;
        boolean flag;
        int depth;
        ArrayList<node> children = new ArrayList<>();
        node father;

        public node() {
        }

        public node(int index, int val) {
            this.index = index;
            this.val = val;
        }

        public node(int val, int index, boolean flag, ArrayList<node> children, node father) {
            this.val = val;
            this.index = index;
            this.flag = flag;
            this.children = children;
            this.father = father;
        }


        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
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

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public node getFather() {
            return father;
        }

        public void setFather(node father) {
            this.father = father;
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