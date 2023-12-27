import java.io.*;
import java.util.StringTokenizer;

class lNode {
    long value;
    long index;
    boolean being;
    lNode pre;
    lNode next;

    public lNode(long x, long y) {
        value = x;
        index = y;
        being = true;
    }
}

public class NONONO {
    public static void main(String[] args) throws IOException {
        QReader in = new QReader();
        QWriter out = new QWriter();
        long max = 100010;
        int case_num = in.nextInt();
        for (int i = 0; i < case_num; i++) {
            boolean goon = false;
            int xn = in.nextInt();

            long[] headNodes = new long[xn + 2];
            headNodes[0] = xn + 1;
            headNodes[xn + 1] = xn + 1;
            lNode[] lNodes = new lNode[xn + 2];
            lNodes[0] = new lNode(0, 0);
            lNodes[xn + 1] = new lNode(max, xn + 1);
            for (int j = 1; j <= xn; j++) {
                headNodes[j] = xn + 1;
                lNodes[j] = new lNode(in.nextLong(), j);
                lNodes[j].pre = lNodes[j - 1];
                lNodes[j - 1].next = lNodes[j];
            }
            lNodes[xn].next = lNodes[xn + 1];
            lNodes[xn + 1].pre = lNodes[xn];

            lNode cur = lNodes[1];
            lNode point1 = null;
            lNode point2 = null;
            int now_point = 0;

            while (cur.value != max) {
                if (cur.next.value < cur.value) {
                    goon = true;
                    point1 = cur;
                    cur.being = false;
                    cur = cur.next;
                    while (cur.next.value < cur.value) {
                        cur = cur.next;
                        cur.being = false;
                    }
                    point2 = cur;
                    point1.pre.next = point2.next;
                    point2.next.pre = point1.pre;
                    headNodes[now_point] = point1.pre.index;
                    now_point++;
                }
                cur = cur.next;
            }

            if (!goon) {
                for (int j = 1; j < xn; j++) {
                    System.out.print(lNodes[j].value + " ");
                }
                System.out.print(lNodes[xn].value);
                System.out.println();

            }


            if (goon && point1.index == 1 && point2.index == xn) {
                System.out.println();
                goon = false;
            }

            while (goon) {
                goon = false;
                now_point = 0;
                int new_point = 0;
                cur = lNodes[(int) headNodes[now_point]];

                while (cur.value != max) {

                    if (cur.next.value < cur.value) {
                        goon = true;
                        point1 = cur;
                        cur.being = false;
                        cur = cur.next;
                        cur.being = false;
                        while (cur.next.value < cur.value) {
                            cur = cur.next;
                            cur.being = false;
                        }
                        point2 = cur;
                        point1.pre.next = point2.next;
                        point2.next.pre = point1.pre;

                        now_point++;
                        while (headNodes[now_point] != xn + 1 && headNodes[now_point - 1] == headNodes[now_point]) {
                            headNodes[now_point - 1] = xn + 1;
                            now_point++;
                        }
                        headNodes[now_point - 1] = xn + 1;

                        while (!lNodes[(int) headNodes[now_point]].being) {
                            headNodes[now_point] = xn + 1;
                            now_point++;
                        }
                        headNodes[new_point] = point1.pre.index;
                        new_point++;


                    } else {
                        now_point++;
                    }
                    cur = lNodes[(int) headNodes[now_point]];
                }

                if (!goon) {
                    cur = lNodes[0];
                    while (cur.next.value != max) {
                        cur = cur.next;
                        System.out.print(cur.value + " ");
                    }
                    System.out.println();
                }

                if (goon && point1.index == 1 && point2.index == xn) {
                    System.out.println();
                    goon = false;
                }
            }
        }
        out.close();
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