package lab10;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        node[] nodes = new node[n + 1];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new node();
        }
        int m = in.nextInt();
        node a = new node();
        node b = new node();
        long result=0;
        long miniWeight = Long.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int p = in.nextInt();
            int q = in.nextInt();
            long w = in.nextInt();
//            if(w>0)result+=w;
            nodes[p].children.add(nodes[q]);
            nodes[q].children.add(nodes[p]);
            nodes[p].weight.add(w);
            nodes[q].weight.add(w);
            if (w < miniWeight) {
                miniWeight = w;
                a = nodes[p];
                b = nodes[q];
            }
        }

        heap heap = new heap(50_0000);
        for (int i = 0; i < a.children.size(); i++) {
            heapNode heapNode = new heapNode(a.children.get(i), a.weight.get(i));
            heap.insert(heapNode);
            a.children.get(i).state = 1;
        }
        for (int i = 0; i < b.children.size(); i++) {
            heapNode heapNode = new heapNode(b.children.get(i), b.weight.get(i));
            heap.insert(heapNode);
            b.children.get(i).state = 1;
        }
        while (heap.size != 0) {
            heapNode heapNode = heap.delete();
            if (heapNode.node.state == 2) continue;

            heapNode.node.state = 2;

            for (int i = 0; i < heapNode.node.children.size(); i++) {
                if (heapNode.node.children.get(i).state == 0) {
                    heapNode.node.children.get(i).state = 1;
                    heap.insert(new heapNode(heapNode.node.children.get(i), heapNode.node.weight.get(i)));
                } else if (heapNode.node.children.get(i).state == 1) {
                    if (heapNode.node.weight.get(i) < heapNode.value) {
                        heap.insert(new heapNode(heapNode.node.children.get(i), heapNode.node.weight.get(i)));
                    }
                }
            }
        }



        out.close();
    }


    static class heap {
        heapNode[] heap;
        int size = 0;

        public heap(int n) {
            heap = new heapNode[n + 1];
        }

        public void insert(heapNode x) {
            size++;
            heap[size] = x;
            int top = size;
            while (top > 1) {
                if (heap[top].value < heap[top / 2].value) {
                    swap(heap, top, top / 2);
                    top = top / 2;
                } else if (heap[top].value == heap[top / 2].value) {
                    swap(heap, top, top / 2);
                    top = top / 2;
                } else {
                    break;
                }
            }
        }

        public heapNode delete() {
            heapNode q = heap[1];
            heap[1] = heap[size];
            heap[size] = null;
            size--;
            int top = size;
            int t = 1;
            while (t * 2 < top - 1) {
                if (t * 2 + 1 == top - 1) {
                    if (heap[t * 2].value <= heap[t].value) {
                        swap(heap, t, t * 2);
                        t *= 2;
                    } else {
                        break;
                    }
                } else {
                    if (heap[t].value <= heap[t * 2].value && heap[t].value <= heap[t * 2 + 1].value) {
                        break;
                    } else if (heap[2 * t].value <= heap[t].value && heap[2 * t].value <= heap[2 * t + 1].value) {
                        swap(heap, t, 2 * t);
                        t *= 2;
                    } else if (heap[2 * t + 1].value <= heap[t].value && heap[2 * t + 1].value <= heap[2 * t].value) {
                        swap(heap, t, 2 * t + 1);
                        t = 2 * t + 1;
                    }
                }
            }
            return q;
        }

        public static void swap(heapNode[] heap, int i, int j) {
            heapNode temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    public static class node {
        ArrayList<node> children = new ArrayList<>();
        ArrayList<Long> weight = new ArrayList<>();
        int state;

        public node() {
        }
    }

    static class heapNode {
        node node;
        long value = Long.MAX_VALUE;

        public heapNode(node node, long value) {
            this.node = node;
            this.value = value;
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
