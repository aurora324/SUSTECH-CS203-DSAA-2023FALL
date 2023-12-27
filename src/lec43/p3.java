package lec43;

import java.io.*;
import java.util.StringTokenizer;

public class p3 {
    public static void main(String[] args) {
        node head = new node(-1);
        node tail = new node(-1);


        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();

        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            int M = in.nextInt();
            node[] arr = new node[N];
            node[] copy = new node[100000];
            node cur = head;
            for (int j = 0; j < N; j++) {
                node node = new node(in.nextInt());
                arr[j] = node;
                cur.next = node;
                node.pre = cur;
                cur = cur.next;
                copy[arr[j].val] = arr[j];
            }
            cur.next = tail;
            tail.pre = cur;

            for (int j = 0; j < M; j++) {
                node node1 = copy[in.nextInt()];
                node node2 = copy[in.nextInt()];
                node node3 = copy[in.nextInt()];
                node node4 = copy[in.nextInt()];
                node node5;
                node node6;
                if (node2.next == node3) {
                    node1.pre.next = node3;
                    node3.pre = node1.pre;

                    node2.next = node4.next;
                    node4.next.pre = node2;

                    node4.next = node1;
                    node1.pre = node4;
                } else {
                    node5 = node2.next;
                    node6 = node3.pre;
                    node1.pre.next = node3;
                    node3.pre = node1.pre;

                    node2.next = node4.next;
                    node4.next.pre = node2;

                    node4.next = node5;
                    node5.pre = node4;

                    node6.next = node1;
                    node1.pre = node6;
                }
            }
            cur = head;
            while (cur.next.val != tail.val) {
                out.print(cur.next.val + " ");
                cur = cur.next;
            }
            out.print("\n");
        }
        out.close();
    }


    private static class node {
        int val;
        node next;
        node pre;

        public node() {
        }

        public node(int val) {
            this.val = val;
        }


        public int getVal() {
            return val;
        }


        public void setVal(int val) {
            this.val = val;
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
}

class QReader {
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

class QWriter implements Closeable {
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
        } catch (IOException ignored) {
        }
    }
}