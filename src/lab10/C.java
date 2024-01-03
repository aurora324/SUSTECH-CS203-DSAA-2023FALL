package lab10;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class C {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        node[][] arr = new node[n][m];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = new node(in.nextLong());
            }
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j].children.add(arr[i + 1][j]);
                arr[i + 1][j].children.add(arr[i][j]);
                arr[i][j].lengthList.add(arr[i][j].value * arr[i + 1][j].value);
                arr[i + 1][j].lengthList.add(arr[i][j].value * arr[i + 1][j].value);
            }
        }


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length - 1; j++) {
                arr[i][j].children.add(arr[i][j + 1]);
                arr[i][j + 1].children.add(arr[i][j]);
                arr[i][j].lengthList.add(arr[i][j].value * arr[i][j + 1].value);
                arr[i][j + 1].lengthList.add(arr[i][j].value * arr[i][j].value);
            }
        }



//        heap heap = new heap(n);
//        nodes[1].value = 0;
//        heap.insert(nodes[1]);
//        node min;
//        while (heap.size != 0) {
//            min = heap.delete();
//            if (min.isVisited) {
//                continue;
//            }
//            min.isVisited = true;
//            int child = min.children.size();
//            for (int i = 0; i < child; i++) {
//                Node temp = min.children.get(i);
//                if (!temp.isVisited) {
//                    if (temp.value > min.LengthList.get(i) + min.value) {
//                        temp.value = min.LengthList.get(i) + min.value;
//                    }
//                    heap.insert(temp);
//                }
//            }
//        }

        out.close();
    }

    static class heap {
        node[] heap;
        int size = 0;

        public heap(int n) {
            heap = new node[n + 1];
        }

        public void insert(node x) {
            size++;
            heap[size] = x;
            int top = size;
            while (top > 1) {
                if (heap[top].value > heap[top / 2].value) {
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

        public node delete() {
            node q = heap[1];
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
                    if (heap[t].value >= heap[t * 2].value && heap[t].value >= heap[t * 2 + 1].value) {
                        break;
                    } else if (heap[2 * t].value >= heap[t].value && heap[2 * t].value >= heap[2 * t + 1].value) {
                        swap(heap, t, 2 * t);
                        t *= 2;
                    } else if (heap[2 * t + 1].value >= heap[t].value && heap[2 * t + 1].value >= heap[2 * t].value) {
                        swap(heap, t, 2 * t + 1);
                        t = 2 * t + 1;
                    }
                }
            }
            return q;
        }

        public static void swap(node[] heap, int i, int j) {
            node temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    public static class node {
        long value;
        ArrayList<node> children = new ArrayList<>();
        ArrayList<Long> lengthList = new ArrayList<>();

        public node(long val) {
            this.value = val;
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
