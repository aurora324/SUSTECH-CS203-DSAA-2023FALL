import java.io.*;
import java.util.StringTokenizer;

public class p52 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
//        int []answer=new int[n];
        node[][] arr = new node[2][n];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = new node(j + 1);
            }
        }

        node head1 = new node(-1);
        node tail1 = new node(-2);

        node head2 = new node(-1);
        node tail2 = new node(-2);

        node cur1 = head1;
        for (int i = 0; i < p; i++) {
            node node1 = arr[0][in.nextInt() - 1];
            cur1.next = node1;
            node1.pre = cur1;
            cur1 = cur1.next;
        }
        cur1.next = tail1;
        tail1.pre = cur1;

        node cur2 = head2;
        for (int i = 0; i < q; i++) {
            node node2 = arr[1][in.nextInt() - 1];
            node2.pre = cur2;
            cur2.next = node2;
            cur2 = cur2.next;
        }
        cur2.next = tail2;
        tail2.pre = cur2;

        cur1 = head1.next;
        cur2 = head2.next;

        int[] answer = new int[n];
        int i = 0;
        int time = 1;
        while (i < n) {
            while (cur1.status) {
                if (cur1 == tail1) {
                    break;
                }
                cur1 = cur1.next;
            }
            while (cur2.status) {
                if (cur2 == tail2) {
                    break;
                }
                cur2 = cur2.next;
            }
            if (cur1.value == cur2.value) {
                if (cur1 != tail1) {
                    answer[cur1.value-1] = time;
                    cur1.status = true;
                    arr[1][cur1.value - 1].status = true;
                    i++;
                } else if (cur2 != tail2) {
                    answer[cur2.value-1] = time;
                    cur2.status = true;
                    arr[0][cur1.value - 1].status = true;
                    i++;
                }
            } else {
                if (cur1 != tail1) {
                    answer[cur1.value-1] = time;
                    cur1.status = true;
                    arr[1][cur1.value - 1].status = true;
                    i++;
                }
                if (cur2 != tail2) {
                    answer[cur2.value-1] = time;
                    cur2.status = true;
                    arr[0][cur2.value - 1].status = true;
                    i++;
                }
            }
            time++;
        }
        for (int j = 0; j < answer.length - 1; j++) {
            out.print(answer[j] + " ");
        }
        out.println(answer[answer.length - 1]);
        out.close();
    }

    private static class node {
        int value;
        boolean status;
        node next;
        node pre;


        public node() {
        }

        public node(int value) {
            this.value = value;
            this.status = false;
        }


        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public node getNext() {
            return next;
        }

        public void setNext(node next) {
            this.next = next;
        }

        public node getPre() {
            return pre;
        }

        public void setPre(node pre) {
            this.pre = pre;
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
