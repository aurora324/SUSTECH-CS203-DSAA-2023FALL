package lec42;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            node head = new node('-');
            node tail = new node('-');
            int n = in.nextInt();
            String s = in.next();
            node cur = head;
            cur.next = tail;
            tail.pre = cur;
            cur = tail;
            for (int k = 0; k < n; k++) {
                switch (s.charAt(k)) {
                    case 'r':
                        if (k + 1 >= n) {
                            break;
                        } else {
                            cur.aChar = s.charAt(k + 1);
                        }
                        if (cur.next == null) {
                            tail = new node('-');
                            cur.next = tail;
                            tail.pre = cur;
                        }
                        k++;
                        break;
                    case 'I':
                        cur = head.next;
                        break;
                    case 'H':
                        if (cur.pre.aChar != '-')
                            cur = cur.pre;
                        break;
                    case 'L':
                        if (cur.next != null)
                            cur = cur.next;
                        break;
                    case 'x':
                        if (cur.aChar != '-') {
                            node temp=cur.next;
                            cur.pre.next = cur.next;
                            cur.next.pre = cur.pre;
                            cur=temp;
                        }
                        break;
                    default:
                        node node = new node(s.charAt(k));
                        node.pre = cur.pre;
                        node.next = cur;
                        cur.pre.next = node;
                        cur.pre = node;
                        break;
                }
            }
            cur = head;
            while (cur.next != tail) {
                out.print(cur.next.aChar);
                cur = cur.next;
            }
            out.print("\n");
        }
        out.close();
    }

    private static class node {
        char aChar;
        node next;
        node pre;

        @Override
        public String toString() {
            return "node{" +
                    "aChar=" + aChar +
                    ", next=" + next +
                    '}';
        }

        public node() {
        }

        public node(char aChar) {
            this.aChar = aChar;
        }


        public char getAChar() {
            return aChar;
        }

        public void setAChar(char aChar) {
            this.aChar = aChar;
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
            } catch (IOException ignored) {

            }
        }
    }
}




