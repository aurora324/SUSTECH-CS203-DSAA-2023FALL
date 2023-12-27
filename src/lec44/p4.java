package lec44;

import java.io.*;
import java.util.StringTokenizer;

public class p4 {


    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            node head = new node(-1);
            node tail = new node(-1);
            int n = in.nextInt();
            int k = n - 1;
            node[] arr = new node[n];
            node[] copy = new node[n];
            node[] temp = new node[n];

            for (int j = 0; j < arr.length; j++) {
                node node = new node(in.nextInt());
                arr[j] = node;
                copy[j] = node;
                temp[j] = node;
            }

            node cur = head;
            sort(copy, temp, 0, n - 1);

            for (int j = 0; j < copy.length; j++) {
                copy[j].rank = j;
                copy[j].pre = cur;
                cur.next = copy[j];
                cur = cur.next;
            }
            cur.next = tail;
            tail.pre = cur;

//            cur = head;
//            while (cur.next.val != -1) {
//                System.out.print(cur.next.val + " ");
//                cur = cur.next;
//            }
//            System.out.println();

            int[] answer = new int[n / 2 + 1];
            answer[answer.length - 1] = copy[n/2].val;
            node avg = copy[n / 2];
            for (int j = answer.length - 2; j > 0; j--, k -= 2) {

                copy[arr[k].rank].pre.next = copy[arr[k].rank].next;
                copy[arr[k].rank].next.pre = copy[arr[k].rank].pre;

                copy[arr[k - 1].rank].pre.next = copy[arr[k - 1].rank].next;
                copy[arr[k - 1].rank].next.pre = copy[arr[k - 1].rank].pre;


                if (arr[k].val > avg.val && arr[k - 1].val < avg.val || arr[k].val < avg.val && arr[k - 1].val > avg.val) {
                    answer[j] = avg.val;
                } else {
                    if ((arr[k].rank + arr[k - 1].rank) / 2 > avg.rank) {
                        answer[j] = avg.pre.val;
                        avg = avg.pre;
                    } else {
                        answer[j] = avg.next.val;
                        avg = avg.next;
                    }
                }
         }

            answer[0]=arr[0].val;
            for (int value : answer) {
                out.print(value + " ");
            }
            out.print("\n");

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
}


class node {
    int rank;
    int val;
    node next;
    node pre;

    public node() {
    }

    public node(int val) {
        this.val = val;

    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public String toString() {
        return "node{" +
                "aChar=" + val +
                ", next=" + next +
                '}';
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
        } catch (IOException e) {
            return;
        }
    }
}