import java.io.*;
import java.util.*;

public class BonusH {
    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        int m = in.nextInt();
        node[][] matrix = new node[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = new node(in.nextInt(), i, j);
                matrix[i][j].weight = (double) 1 / Math.pow(2, matrix[0][0].height - matrix[i][j].height);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                matrix[i][j].arrayList.add(matrix[i][j + 1]);
                matrix[i][j + 1].arrayList.add(matrix[i][j]);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                matrix[j][i].arrayList.add(matrix[j + 1][i]);
                matrix[j + 1][i].arrayList.add(matrix[j][i]);
            }
        }

        matrix[0][0].val = 0.0;
//        matrix[m][n].isVisited=true;


        node []heap=new node[100000];
        int top=1;
        insert(heap,top,matrix[0][0]);
        top++;

        while (!(heap[1]==matrix[n - 1][m - 1])) {
            node temp = delete(heap,top);
            top--;
            temp.isVisited = true;
            for (int i = 0; i < temp.arrayList.size(); i++) {
               node index = temp.arrayList.get(i);
                if (!index.isVisited && temp.val + temp.weight < index.val) {
                    index.val = temp.val + temp.weight;
                    insert(heap,top,index);
                    top++;
                    delete(heap,top);
                    top--;
                }
            }
        }

        System.out.printf("%.2f", matrix[n - 1][m - 1].val);
    }

    public static class node {
        int height;
        int x;
        int y;
        ArrayList<node> arrayList = new ArrayList<>();
        double weight;
        boolean isVisited = false;
        double val = Double.MAX_VALUE;

        node(int a, int i, int j) {
            this.height = a;
            this.x = i;
            this.y = j;
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

    public static void swap(node[] heap, int i, int j) {
        node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static node delete(node[] heap, int top) {
//        System.out.println(heap[2].val+heap[3].val);
        node re = heap[1];
        heap[1] = heap[top - 1];
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


    public static void insert(node[] heap, int top, node num) {
        heap[top] = num;
        while (top > 1) {
            if (heap[top].val < heap[top / 2].val) {
                swap(heap, top, top / 2);
                top = top / 2;
            } else {
                break;
            }
        }
    }

}
