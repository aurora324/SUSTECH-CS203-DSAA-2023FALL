package lab10;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        int k = in.nextInt();

        node[][] nodes = new node[n + 1][k + 1];
        for (int i = 1; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                nodes[i][j] = new node();
                nodes[i][j].total = j;
            }
        }




        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            long z = in.nextLong();
            for (int j = 0; j <= k; j++) {
                nodes[x][k].children.add(nodes[y][k]);
                nodes[x][k].LengthList.add(z);
            }
        }

        for (int i = 0; i < p; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            for (int j = 0; j < k; j++) {
                nodes[x][j].children.add(nodes[y][j + 1]);
                nodes[x][j].LengthList.add(0L);
            }
        }


        node start = nodes[in.nextInt()][0];
        int end = in.nextInt();

        node[] heap = new node[n * (k + 1) + 1];
        int top = 1;
        start.val = 0;
        insert(heap, top, start);
        top++;
        node min;
        while (top != 1) {
            min = delete(heap, top);
            top--;
            min.isVisited = true;

            for (int i = 0; i < min.children.size(); i++) {
                node temp = min.children.get(i);
                if (!temp.isVisited) {
                    if (temp.val > min.LengthList.get(i) + min.val) {
                        temp.val = min.LengthList.get(i) + min.val;
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

        Long[]arr=new Long[k+1];
        Long []temp=new Long[k+1];
        for (int i = 0; i < nodes[end].length; i++) {
            arr[i]=nodes[end][i].val;
        }
        sort(arr,temp,0,k);

        System.out.print(arr[k]);
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

    public static void insert(node[] heap, int top, node node) {
        heap[top] = node;
        node.heapIndex = top;
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

    public static void sort(Long[] arr, Long[] copy, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(arr, copy, start, mid);
        sort(arr, copy, mid + 1, end);
        merge(arr, copy, start, mid, end);
    }

    public static void merge(Long[] arr, Long[] copy, int start, int mid, int end) {
        if (end + 1 - start >= 0) System.arraycopy(arr, start, copy, start, end + 1 - start);
        int i = start;
        int j = mid + 1;
        for (int k = start; k <= end; k++) {
            if (i > mid) {
                arr[k] = copy[j];
                j++;
            } else if (j > end) {
                arr[k] = copy[i];
                i++;
            } else if (copy[i] >= copy[j]) {
                arr[k] = copy[i];
                i++;
            } else {
                arr[k] = copy[j];
                j++;
            }
        }
    }

    public static class node {
        int heapIndex;
        long val;
        boolean isVisited;
        int total;
        ArrayList<node> children = new ArrayList<>();
        ArrayList<Long> LengthList = new ArrayList<>();

        public node() {
            this.val = Long.MAX_VALUE;
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