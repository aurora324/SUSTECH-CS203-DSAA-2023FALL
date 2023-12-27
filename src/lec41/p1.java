package lec41;

import java.io.*;
import java.util.StringTokenizer;

public class p1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        node head = new node(2000000000, 2000000000);
        node tail = new node(-2000000000, -2000000000);
        node cur = head;
        for (int i = 0; i < n; i++) {
            cur.next = new node(in.nextInt(), in.nextInt());
            cur = cur.next;
        }
        cur.next = tail;

        cur = head;
        for (int i = 0; i < m; i++) {
            node node = new node(in.nextInt(), in.nextInt());
            while (cur != tail) {
                if (node.exp > cur.next.exp) {
                    break;
                }
                cur = cur.next;
            }

            if (node.exp == cur.exp) {
                cur.coe += node.coe;
            } else if (node.exp < cur.exp){
                node.next = cur.next;
                cur.next = node;
            }
        }
        cur = head;
        int counter = 0;
        while (cur.next != tail) {
            counter++;
            cur = cur.next;
        }
        out.println(counter);
        cur = head;
        while (cur.next != tail) {
            out.print(cur.next.coe + " ");
            out.println(cur.next.exp);
            cur = cur.next;
        }
        out.close();
    }
}

class node {
    int coe;
    int exp;
    node next;

    public node() {
    }

    public node(int coe, int exp) {
        this.coe = coe;
        this.exp = exp;
    }

    public int getCoe() {
        return coe;
    }

    public void setCoe(int coe) {
        this.coe = coe;
    }


    public int getExp() {
        return exp;
    }


    public void setExp(int exp) {
        this.exp = exp;
    }

    public node getNext() {
        return next;
    }


    public void setNext(node next) {
        this.next = next;
    }

    public String toString() {
        return "node{coe = " + coe + ", exp = " + exp + ", next = " + next + "}";
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