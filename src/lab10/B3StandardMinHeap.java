package lab10;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B3StandardMinHeap {
    public static void main(String[] args) {
        long result = 0;
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        node[] nodes = new node[n + 1];
        for (int i = 1; i < n + 1; i++) {
            nodes[i] = new node();
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            long c = in.nextLong();
            if (c > 0) {
                result += c;
            }
            nodes[a].children.add(nodes[b]);
            nodes[b].children.add(nodes[a]);
            nodes[a].LengthList.add(c);
            nodes[b].LengthList.add(c);
        }
        node[] heap = new node[n + 1];
        int top = 1;
        nodes[1].val = 0;
        insert(heap, top, nodes[1]);
        top++;
        node min;
        while (top != 1) {
            min = delete(heap, top);
            top--;
            min.isVisited = true;
            for (int i = 0; i < min.children.size(); i++) {
                node temp = min.children.get(i);
                if (!temp.isVisited) {
                    if (temp.val > min.LengthList.get(i)) {
                        temp.val = min.LengthList.get(i);
                        int index = temp.heapIndex;
                        if (index == 0) {
                            insert(heap, top, temp);
                            top++;
                        } else {
                            up(heap, temp);
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (nodes[i].val > 0) {
                result -= nodes[i].val;
            }
        }
        out.print(result);
        out.close();
    }

    public static void up(node[] heap, node node) {
        int index = node.heapIndex;
        while (index > 1) {
            if (heap[index].val < heap[index / 2].val) {
                swap(heap, index, index / 2);
                index /= 2;
            } else {
                break;
            }
        }
    }

    public static void insert(node[] heap, int top, node num) {
        heap[top] = num;
        num.heapIndex = top;
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

    public static void swap(node[] heap, int i, int j) {
        int index = heap[i].heapIndex;
        heap[i].heapIndex = heap[j].heapIndex;
        heap[j].heapIndex = index;

        node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void down(node[] heap, node node, int top) {
        int t = node.heapIndex;
        while (t * 2 < top) {
            if (t * 2 + 1 == top) {
                if (heap[t * 2].val <= heap[t].val) {
                    swap(heap, t, t * 2);
                    t *= 2;
                } else {
                    return;
                }
            } else {
                if (heap[t].val <= heap[t * 2].val && heap[t].val <= heap[t * 2 + 1].val) {
                    return;
                } else if (heap[2 * t].val <= heap[t].val && heap[2 * t].val <= heap[2 * t + 1].val) {
                    swap(heap, t, 2 * t);
                    t *= 2;
                } else if (heap[2 * t + 1].val <= heap[t].val && heap[2 * t + 1].val <= heap[2 * t].val) {
                    swap(heap, t, 2 * t + 1);
                    t = 2 * t + 1;
                }
            }
        }
    }

    public static node delete(node[] heap, int top) {
        node re = heap[1];
        heap[1] = heap[top - 1];
        heap[1].heapIndex = 1;
        heap[top - 1] = null;
        int t = 1;
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

    static class node {
        long val;
        boolean isVisited = false;
        ArrayList<node> children;
        ArrayList<Long> LengthList;
        int heapIndex;


        public node() {
            this.val = Long.MAX_VALUE;
            this.children = new ArrayList<>();
            this.LengthList = new ArrayList<>();
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