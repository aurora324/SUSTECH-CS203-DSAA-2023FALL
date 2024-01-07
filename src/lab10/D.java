package lab10;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();

        int k = in.nextInt();
        int c = in.nextInt();


        node[] nodes = new node[n + 1];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new node();
            nodes[i].color = in.nextInt();
        }

        for (int i = 0; i < m; i++) {
            int p = in.nextInt();
            int q = in.nextInt();
            nodes[p].children.add(nodes[q]);
            nodes[q].children.add(nodes[p]);
        }
        int[][] result = new int[n + 1][k + 1];
        int[] copy = new int[k + 1];

        for (int p = 0; p < result.length; p++) {
            for (int q = 0; q < result[0].length; q++) {
                result[p][q] = Integer.MAX_VALUE;
            }
        }

        node[] queue = new node[50_0000];
        int front = 0;
        int rear = 0;

        for (int i = 1; i <= k; i++) {
            rear=0;
            for (int j = 1; j < nodes.length; j++) {
                nodes[j].isVisited = false;
                nodes[j].total = 0;
            }

            for (int j = 1; j < nodes.length; j++) {
                if (nodes[j].color == i) {
                    node root = nodes[j];
                    root.isVisited = true;
                    queue[rear]=root;
                    rear++;
                }
            }

            BFS(queue,front,rear);
            for (int j = 1; j < nodes.length; j++) {
                result[j][i] = nodes[j].total;
            }
        }
        for (int i = 1; i < result.length; i++) {
            long answer = 0;
            sort(result[i], copy, 1, k);
            for (int j = 1; j <= c; j++) {
                answer += result[i][j];
            }
            out.print(answer + " ");
        }


        out.close();
    }

    public static void BFS(node[] queue, int front, int rear) {
        while (rear - front != 0) {
            for (int i = 0; i < queue[front].children.size(); i++) {
                if (!queue[front].children.get(i).isVisited) {
                    queue[rear] = queue[front].children.get(i);
                    queue[rear].isVisited = true;
                    queue[rear].total=queue[front].total+1;
                    rear++;
                }
            }
            front++;
        }
    }

    public static void sort(int[] arr, int[] copy, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(arr, copy, start, mid);
        sort(arr, copy, mid + 1, end);
        merge(arr, copy, start, mid, end);
    }

    public static void merge(int[] arr, int[] copy, int start, int mid, int end) {
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
            } else if (copy[i] <= copy[j]) {
                arr[k] = copy[i];
                i++;
            } else {
                arr[k] = copy[j];
                j++;
            }
        }
    }

    public static class node {
        boolean isVisited;
        int color;
        int total;
        ArrayList<node> children = new ArrayList<>();

        public node() {
        }
    }

    public static void quickSort(int[] arr, int i, int j) {
        int start = i;
        int end = j;
        if (start > end) {
            return;
        }
        int baseNumber = arr[i];
        while (start != end) {
            while (end > start && arr[end] >= baseNumber) {
                end--;
            }
            while (end > start && arr[start] <= baseNumber) {
                start++;
            }

            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }

        int temp = arr[i];
        arr[i] = arr[start];
        arr[start] = temp;
        quickSort(arr, i, start - 1);
        quickSort(arr, start + 1, j);

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
