import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p101 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int m = in.nextInt();

        node[] nodes = new node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new node(Long.MAX_VALUE, i);
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            long z = in.nextLong();
            nodes[x].children.add(nodes[y]);
            nodes[x].weight.add(z);
        }

        node[] heap = new node[n + 1];
        int top = 1;
        nodes[1].isVisited = true;
        nodes[1].val = 0;
        for (int i = 0; i < nodes[1].children.size(); i++) {
            nodes[1].children.get(i).val = nodes[1].weight.get(i);
            insert(heap, top, nodes[1].children.get(i));
            top++;
        }
        while (top != 1) {
            node temp=delete(heap,top,1);
            top--;
            if(temp.isVisited)continue;

            temp.isVisited=true;

            for (int i = 0; i < temp.children.size(); i++) {
                node index=temp.children.get(i);
                if(!index.isVisited&&temp.val+temp.weight.get(i)<index.val){
                    node node=delete(heap,top,find(heap,heap[1].children.get(i)));
                        top--;
                        node.val=temp.val+temp.weight.get(i);
                        insert(heap,top,heap[1].children.get(i));
                        top++;
                }
//                if (!heap[1].children.get(i).isVisited) {
//                    long temp = heap[1].val + heap[1].weight.get(i);
//                    if (temp < heap[1].children.get(i).val) {
//                        System.out.println(i);
//                        node node=delete(heap,top,find(heap,heap[1].children.get(i)));
//                        top--;
//                        node.val=temp;
//                        insert(heap,top,heap[1].children.get(i));
//                        top++;
//                    }
//                }
            }
        }
        if (nodes[n].val == Long.MAX_VALUE) {
            out.print(-1);
        } else {
            out.print(nodes[n].val);
        }
        out.close();
    }

    public static void insert(node[] heap, int top, node num) {
        heap[top] = num;
        while (top > 1) {
            if (heap[top].val < heap[top / 2].val) {
                swap(heap, top, top / 2);
                top = top / 2;
            } else if (heap[top].val == heap[top / 2].val) {
                swap(heap, top, top / 2);
                top = top / 2;
            } else {
                break;
            }
        }
    }
    public static int find(node[]heap,node node){
        for (int i = 1; i < heap.length; i++) {
            if(heap[i].val==node.val){
                return i;
            }
        }
        return -1;
    }

    public static node delete(node[] heap, int top,int t) {
//        System.out.println(heap[2].val+heap[3].val);
//        int t = 1;
        node re = heap[t];
        heap[t] = heap[top - 1];
        heap[top - 1] = null;

        while (t * 2 < top - 1) {
            if (t * 2 + 1 == top - 1) {
                if (heap[t * 2].val <= heap[t].val) {
                    swap(heap, t, t * 2);
                    t *= 2;
                } else {
                    return re;
                }
            } else {
                if (heap[t].val <= heap[t * 2].val && heap[t].val <= heap[t * 2 + 1].val) {
                    return re;
                } else if (heap[2 * t].val <= heap[t].val && heap[2 * t].val <= heap[2 * t + 1].val) {
                    swap(heap, t, 2 * t);
                    t *= 2;
                } else if (heap[2 * t + 1].val <= heap[t].val && heap[2 * t + 1].val <= heap[2 * t].val) {
                    swap(heap, t, 2 * t + 1);
                    t = 2 * t + 1;
                }
            }
        }
        return re;
    }

    public static void swap(node[] heap, int i, int j) {
        node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static class node {
        int index;
        long val;
        boolean isVisited;
        ArrayList<node> children = new ArrayList<>();
        ArrayList<Long> weight = new ArrayList<>();

        public node() {
        }

        public node(long val, int index) {
            this.val = val;
            this.index=index;
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
