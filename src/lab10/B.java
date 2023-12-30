package lab10;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int m = in.nextInt();
        Node[] Nodes = new Node[n];
        Side[] Sides = new Side[m + 1];
        for (int i = 0; i < Nodes.length; i++) {
            Nodes[i] = new Node();
        }
        Side minSide = new Side();
        long minLength = Long.MAX_VALUE;
        for (int i = 1; i < Sides.length; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            long w = in.nextLong();
            Side side = new Side(Nodes[a], Nodes[b], w);
            Sides[i] = side;
            Nodes[a].children.add(Nodes[b]);
            Nodes[b].children.add(Nodes[a]);
            Nodes[a].SideList.add(Sides[i]);
            Nodes[b].SideList.add(Sides[i]);
            if (Sides[i].value < minLength) {
                minLength = Sides[i].value;
                minSide = Sides[i];
            }
        }
        heap heap = new heap(m + 1);
        heap.insert(minSide);
        minSide.state = 1;

        while (heap.size != 0) {
            minSide = heap.delete();
            if (minSide.a.isVisited && minSide.b.isVisited) {
                continue;
            }
            minSide.state = 2;
            minSide.a.isVisited = true;
            minSide.b.isVisited = true;
            for (int i = 0; i < minSide.a.SideList.size(); i++) {
                if (minSide.a.SideList.get(i).state == 0) {
                    heap.insert(minSide.a.SideList.get(i));
                    minSide.a.SideList.get(i).state = 1;
                }
            }

            for (int i = 0; i < minSide.b.SideList.size(); i++) {
                if (minSide.b.SideList.get(i).state == 0) {
                    heap.insert(minSide.b.SideList.get(i));
                    minSide.b.SideList.get(i).state = 1;
                }
            }
        }

        long answer = 0;
        for (int i = 1; i < Sides.length; i++) {
            if (!(Sides[i].state == 2) && Sides[i].value > 0) answer += Sides[i].value;
        }
        out.print(answer);
        out.close();
    }
    static class heap {
        Side[] heap;
        int size = 0;

        public heap(int n) {
            heap = new Side[n + 1];
        }

        public void insert(Side x) {
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

        public Side delete() {
            Side re = heap[1];
            heap[1] = heap[size];
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
            return re;
        }

        public static void swap(Side[] heap, int i, int j) {
            Side temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    static class Node {
        long value;
        boolean isVisited;
        ArrayList<Node> children = new ArrayList<>();
        ArrayList<Side> SideList = new ArrayList<>();


        public Node() {
        }

        public Node(long value) {
            this.value = Long.MAX_VALUE;
        }
    }

    static class Side {
        long value;
        Node a;
        Node b;
        int state;
        public Side() {
        }

        public Side(Node a, Node b, long value) {
            this.value = value;
            this.a = a;
            this.b = b;
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
