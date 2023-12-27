package lec45;

import java.io.*;
import java.util.StringTokenizer;

public class p5 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        node[] arr = new node[n];
        node[] copy = new node[n];
        node[] temp = new node[n];

        node head = new node(-1000, -1);
        node tail = new node(-2000, -1);
        node cur = head;
        node move1 = null;
        node move2 = null;
        for (int i = 0; i < n; i++) {
            node node = new node(in.nextInt(), i);
            arr[i] = node;
            copy[i] = node;
            temp[i] = node;
        }


        sort(copy, temp, 0, n - 1);
        for (node node : copy) {
            cur.next = node;
            node.pre = cur;
            cur = cur.next;
        }
        cur.next = tail;
        tail.pre = cur;

//        cur=head.next;
//        while (cur!=tail){
//            out.print(cur.val+" ");
//            cur=cur.next;
//        }
//        out.print("\n");
//
//        cur=head.next;
//        while (cur!=tail){
//            out.print(cur.index+" ");
//            cur=cur.next;
//        }
//        out.print("\n");

        int[] answer = new int[n - 1];
        for (int i = 0; i < answer.length; i++) {
            cur = arr[i];
            move1 = cur.next;
//            out.println(move1.val);
            move2 = cur.pre;
//            out.println(move2.val);
            while (move1 != tail && move1.index < cur.index) {
                move1 = move1.next;
            }

            while (move2 != head && move2.index < cur.index) {
                move2 = move2.pre;
            }


            int a = 0;
            int p = 0;
            int q = 0;
            if (move1 == tail) {
                p = 1000000001;
            } else {
                p = Math.abs(move1.val - cur.val);
            }
            if (move2 == head) {
                q = 1000000002;
            } else {
                q = Math.abs(move2.val - cur.val);
            }
            a = Math.min(p, q);

            out.print(a + " ");
            cur.next.pre=cur.pre;
            cur.pre.next=cur.next;
//            out.print(p + " ");
//            out.print(q+" ");
//            out.print("\n");
        }

        out.close();
    }

    public static void sort(node[] arr, node[] copy, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(arr, copy, start, mid);
        sort(arr, copy, mid + 1, end);
        merge(arr, copy, start, mid, end);
    }

    public static void merge(node[] arr, node[] copy, int start, int mid, int end) {
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
            } else if (copy[i].val <= copy[j].val) {
                arr[k] = copy[i];
                i++;
            } else {
                arr[k] = copy[j];
                j++;
            }
        }
    }

    private static class node {
        int val;
        int index;
        node next;
        node pre;

        public node() {
        }

        public node(int val) {
            this.val = val;
        }

        public node(int val, int index) {
            this.val = val;
            this.index = index;
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

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
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