import java.io.*;
import java.util.StringTokenizer;

public class p95 {
    static int SUM = 0;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int T_case = 0; T_case < T; T_case++) {
            int N = in.nextInt();
            int M = in.nextInt();
            node[][] arr = new node[N + 2][M + 2];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    arr[i][j] = new node();
                }
            }
            for (int row = 1; row < arr.length - 1; row++) {
                for (int column = 1; column < arr[0].length - 1; column++) {
                    arr[row][column] = new node(in.nextInt());
                }
            }
            int i = 1;
            int j = 1;
            int sum = 0;
            DFS(arr, i, j, N, M, sum);
            out.println(SUM);
            SUM = 0;
        }
        out.close();
    }

    public static boolean adj(node[][] arr, int i, int j) {
        int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
        for (int k = 0; k < dir.length; k++) {
            if (arr[i + dir[k][0]][j + dir[k][1]].isVisited) {
                return true;
            }
        }
        return false;
    }

    public static void DFS(node[][] arr, int i, int j, int N, int M, int sum) {
        if (j > M) {
            j -= M;
            i++;
        }
        if (i > N) {
            return;
        }
        if (!adj(arr, i, j)) {
            sum += arr[i][j].val;
            SUM = Math.max(sum, SUM);
            arr[i][j].isVisited = true;
            DFS(arr, i, j + 1, N, M, sum);
            sum -= arr[i][j].val;
            arr[i][j].isVisited = false;
        }
        DFS(arr, i, j + 1, N, M, sum);
    }

    public static class node {
        int val;
        boolean isVisited;

        public node() {}

        public node(int val) {
            this.val = val;
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
