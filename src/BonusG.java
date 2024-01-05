import java.io.*;
import java.util.StringTokenizer;

public class BonusG {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        node[][] arr = new node[n][m];
        for (int i = 0; i < arr.length; i++) {
            node head = new node(-1);
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = new node(i * m + j);
                head.next = arr[i][j];
                arr[i][j].pre = head;
                head = head.next;
            }
            node tail = new node(-1);
            tail.pre = head;
            head.next = tail;
        }
        for (int i = 0; i < k; i++) {
            int p = in.nextInt();
            int q = in.nextInt();
            int pi = p / m;
            int pj = p % m;
            int qi = q / m;
            int qj = q % m;
            arr[pi][pj].next.pre = arr[qi][qj].pre;
            arr[qi][qj].pre.next = arr[pi][pj].next;
            arr[pi][pj].next = arr[qi][qj];
            arr[qi][qj].pre = arr[pi][pj];
        }
        int xi = x / m;
        int xj = x % m;
        node cur = arr[xi][xj];
        while (cur.pre.val != -1) {
            cur = cur.pre;
        }
        while (cur.next.val != -1) {
            out.print(cur.val + " ");
            cur = cur.next;
        }
        out.print(cur.val);

        out.close();
    }

    public static class node {
        int val;
        node next;
        node pre;

        public node() {
        }

        public node(int val) {
            this.val = val;
        }
    }

    static class QReader {
        private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
        private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

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
