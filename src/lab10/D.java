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
        ArrayList<node> arrayList = new ArrayList<>();
        for (int p = 0; p < result.length; p++) {
            for (int q = 0; q < result[0].length; q++) {
                result[p][q] = Integer.MAX_VALUE;
            }
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < nodes.length; j++) {
                nodes[j].isVisited = false;
                nodes[j].total = 0;
            }

            for (int j = 1; j < nodes.length; j++) {
                if (nodes[j].color == i) {
                    node root = nodes[j];
                    root.isVisited = true;
                    arrayList.add(root);
                }
            }

            BFS(arrayList);

            for (int j = 1; j < nodes.length; j++) {
                result[j][i] = nodes[j].total;
            }
        }
        for (int i = 1; i < result.length; i++) {
            long answer=0;
            for (int j = 1; j <=c; j++) {
                answer+=result[i][j];
            }
            out.print(answer+" ");
        }


        out.close();
    }

    public static void BFS(ArrayList<node> arrayList) {
        while (!arrayList.isEmpty()) {
            for (int i = 0; i < arrayList.get(0).children.size(); i++) {
                if (!arrayList.get(0).children.get(i).isVisited) {
                    arrayList.add(arrayList.get(0).children.get(i));
                    arrayList.get(arrayList.size()-1).isVisited = true;
                    arrayList.get(arrayList.size()-1).total = arrayList.get(0).total + 1;
                }
            }
            arrayList.remove(0);
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
