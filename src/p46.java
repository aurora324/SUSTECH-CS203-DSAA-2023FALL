import java.io.*;
import java.util.StringTokenizer;

public class p46 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        node head = new node(100001);
        node tail = new node(100001);
        int T = in.nextInt();

        for (int i = 0; i < T; i++) {
            boolean go = false;
            node point1 = null;
            node point2 = null;
            node[] temp = new node[100000];
            int counter = 0;
            int n = in.nextInt();
            node[] arr = new node[n];
            node[] copy = new node[100000];
            node cur = head;
            for (int j = 0; j < arr.length; j++) {
                node node = new node(in.nextInt());
                arr[j] = node;
                copy[node.val] = node;
                cur.next = node;
                node.pre = cur;
                cur = cur.next;
            }
            cur.next = tail;
            tail.pre = cur;


            //start
            cur = head.next;
            while (cur.val != 100001) {
                if (cur.next.val < cur.val) {
                    go = true;
                    point1 = cur;
                    if (point1 != head.next) {
                        temp[counter] = point1.pre;
                        counter++;
                    }
                    cur = cur.next;
                    while (cur.next.val != 100001) {
                        if (cur.next.val < cur.val) {
                            cur = cur.next;
                        } else {
                            break;
                        }
                    }
                    point2 = cur;
                    point1.pre.next = point2.next;
                    point2.next.pre = point1.pre;
                }
                cur = cur.next;
            }

            if (!go) {
                cur = head.next;
                while (cur != tail) {
                    out.print(cur.val + " ");
                    cur = cur.next;
                }
                out.print("\n");
                continue;
            }
            if (go && point1.pre.val == 100001 && point2.next.val == 100001) {
                out.print("\n");
                continue;
            }


            while (go) {
                cur = temp[0];
                temp[0] = null;
                go = false;
                counter = 0;
                if(cur==null){
                    cur=head.next;
                }
                while (cur.val != 100001 || cur == head) {
                    if (cur.next.val < cur.val) {
                        go = true;
                        point1 = cur;
                        if (point1 != head.next) {
                            temp[counter] = cur.pre;
                            counter++;
                        }
                        cur = cur.next;
                        while (cur.next.val != 100001) {
                            if (cur.next.val < cur.val) {
                                cur = cur.next;
                            } else {
                                break;
                            }
                        }
                        point2 = cur;
                        point1.pre.next = point2.next;
                        point2.next.pre = point1.pre;
                    }
                    cur = cur.next;
                }

                if (!go) {
                    cur = head.next;
                    while (cur != tail) {
                        out.print(cur.val + " ");
                        cur = cur.next;
                    }
                    out.print("\n");
                    continue;
                }

                if (go && point1.pre.val == 100001 && point2.next.val == 100001) {
                    out.print("\n");
                    break;
                }

            }


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
