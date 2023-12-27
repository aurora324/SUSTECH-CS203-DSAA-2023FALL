import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p72 {
    static int count = 0;
    static node[] nodes;
    static node[] queue;
    static int head = 0;
    static int rear = 0;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int sum = in.nextInt();
        nodes = new node[n + 1];
        queue = new node[n + 1];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new node(i);
            queue[i] = new node();
        }
        for (int i = 0; i < n - 1; i++) {
            int p = in.nextInt();
            int q = in.nextInt();
            int w = in.nextInt();
            nodes[p].children.add(nodes[q]);
            nodes[p].weight.add(w);
            nodes[q].children.add(nodes[p]);
            nodes[q].weight.add(w);
        }
        levelOrderLeaves(nodes[1],sum);

        out.print(count);
        out.close();
    }

    public static void levelOrderLeaves(node root,int target) {
        node[] queue = new node[50_0000];
        int front = 0;
        int rear = 0;

        queue[rear++] = root;

        while (front < rear) {
            node node = nodes[queue[front].val];
            node.result = true;
            front++;
            boolean flag=true;
            for (int i = 0; i < node.children.size(); i++) {
                if(!node.children.get(i).result){
                    flag=false;
                    break;
                }
            }
            if (flag) {
//                System.out.print(node.val + " ");
//                System.out.print(node.total + " ");
                if(node.total==target)count++;
            }
            for (int i = 0; i < node.children.size(); i++) {
                if (!node.children.get(i).result) {
                    queue[rear] = node.children.get(i);
                    node.children.get(i).total=node.total+node.weight.get(i);
                    rear++;
                }
            }




        }
    }

//    public static void find(node root, int current, int target) {
//        node cur = root;
//        if (root.children.size() != 0) {
//            boolean flag = false;
//            for (int i = 0; i < root.children.size(); i++) {
//                if (!root.children.get(i).result) {
//                    flag = true;
//                    root = root.children.get(i);
//                    break;
//                }
//            }
//        }
//        if(cur==root)
//    }

    //    public static int pathSumCount(TreeNode root, int targetSum) {
//        count = 0;
//        dfs(root, null, 0, targetSum);
//        return count;
//    }
//    public static void find(node root, int current, int target) {
//        node cur = nodes[1];
//        node[] stack = new node[nodes.length];
//        int top = 0;
//        stack[top] = cur;
//        cur.result = true;
//        push(nodes[1], stack, top, nodes);
//        top++;
//        separate(stack,cur,current,target);
//    }
//
//    public static void separate(node []stack,node root, int current, int target){
//
//    }

    public static void push(node x, node[] stack, int top, node[] nodes) {
        stack[top] = x;
        stack[top].result = true;
    }

    public static void pop(int x, node[] stack, int top, node[] nodes) {
        stack[top].val = 0;
    }

    private static class node {
        ArrayList<node> children = new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();
        int total;
        int val;
        boolean result;

        public node() {
        }

        public node(int val) {
            this.val = val;
        }

        public ArrayList<node> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<node> children) {
            this.children = children;
        }

        public ArrayList<Integer> getWeight() {
            return weight;
        }

        public void setWeight(ArrayList<Integer> weight) {
            this.weight = weight;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
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
