package lab10;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PassedF {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        int k = in.nextInt();

        node[] nodes = new node[n + 1];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new node(k);
            nodes[i].index = i;
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            Long c = in.nextLong();
            nodes[a].children.add(nodes[b]);
            nodes[a].LengthList.add(c);
        }

        for (int i = 0; i < p; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            nodes[a].portal.add(nodes[b]);
            nodes[a].portalLengthList.add(0L);

        }

        node start = nodes[in.nextInt()];
        node end = nodes[in.nextInt()];

        heapNode[] heap = new heapNode[n * (k + 1) + 1];
        int top = 1;
        start.state[0].val = 0;
        insert(heap, top, start.state[0]);
        top++;
        heapNode min;
        while (top != 1) {
            min = delete(heap, top);
            top--;
            min.isVisited = true;

            for (int i = 0; i < min.father.children.size(); i++) {
                heapNode temp = min.father.children.get(i).state[min.total];
                if (!temp.isVisited) {
                    if (temp.val > min.father.LengthList.get(i) + min.val) {
                        temp.val = min.father.LengthList.get(i) + min.val;
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

            if (min.total < k) {
                for (int i = 0; i < min.father.portal.size(); i++) {
                    heapNode temp = min.father.portal.get(i).state[min.total + 1];
                    if (!temp.isVisited) {
                        if (temp.val > min.father.portalLengthList.get(i) + min.val) {
                            temp.val = min.father.portalLengthList.get(i) + min.val;
                            int index = temp.heapIndex;
                            if (index == 0) {
                                insert(heap, top, temp);
                                top++;
                            } else {
                                up(heap, temp);
                            }
                            temp.total = min.total + 1;
                        }
                    }
                }
            }
        }


        long Min = Long.MAX_VALUE;
        for (int i = k; i >= 0; i--) {
            if (end.state[i].val < Min) {
                Min = end.state[i].val;
            }
        }
        out.print(Min);
        out.close();
    }

    public static void up(heapNode[] heap, heapNode node) {
        int index = node.heapIndex;
        while (index > 1) {
            if (heap[index].val < heap[index / 2].val) {
                index /= 2;
            } else {
                break;
            }
        }
        swap(heap, index, node.heapIndex);
    }

    public static void insert(heapNode[] heap, int top, heapNode node) {
        heap[top] = node;
        node.heapIndex = top;
        while (top > 1) {
            if (heap[top].val < heap[top / 2].val) {

                top = top / 2;
            } else if (heap[top].val == heap[top / 2].val) {

                top = top / 2;
            } else {
                break;
            }
        }
        swap(heap, top, node.heapIndex);
    }

    public static void swap(heapNode[] heap, int i, int j) {
        int index = heap[i].heapIndex;
        heap[i].heapIndex = heap[j].heapIndex;
        heap[j].heapIndex = index;
        heapNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void down(heapNode[] heap, heapNode node, int top) {
        int t = node.heapIndex;
        while (t * 2 < top) {
            if (t * 2 + 1 == top) {
                if (heap[t * 2].val <= heap[t].val) {
                    t *= 2;
                } else {
                    break;
                }
            } else {
                if (heap[t].val <= heap[t * 2].val && heap[t].val <= heap[t * 2 + 1].val) {
                    break;
                } else if (heap[2 * t].val <= heap[t].val && heap[2 * t].val <= heap[2 * t + 1].val) {
                    t *= 2;
                } else if (heap[2 * t + 1].val <= heap[t].val && heap[2 * t + 1].val <= heap[2 * t].val) {
                    t = 2 * t + 1;
                }
            }
        }
        swap(heap, t, node.heapIndex);
    }

    public static heapNode delete(heapNode[] heap, int top) {
        heapNode re = heap[1];
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
                    break;
                }
            } else {
                if (heap[t].val <= heap[t * 2].val && heap[t].val <= heap[t * 2 + 1].val) {
                    break;
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

    public static class node {
        int index;
        ArrayList<node> children = new ArrayList<>();
        ArrayList<Long> LengthList = new ArrayList<>();
        ArrayList<Long> portalLengthList = new ArrayList<>();
        ArrayList<node> portal = new ArrayList<>();
        heapNode[] state;

        public node(int k) {
            state = new heapNode[k + 1];
            for (int i = 0; i < state.length; i++) {
                state[i] = new heapNode(this);
                state[i].total = i;
            }
        }
    }

    public static class heapNode {
        int index;
        long val = Long.MAX_VALUE;
        int heapIndex;
        boolean isVisited;
        node father;
        int total;

        public heapNode(node father) {
            this.father = father;
            this.index = father.index;
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