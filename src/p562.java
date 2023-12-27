import java.io.*;
import java.util.StringTokenizer;

public class p562 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int k = in.nextInt();
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

       out.println(checkMax(k, arr));
        out.close();
    }

    public static int checkMax(int k, int[] arr) {
        node[] stack = new node[3000000];
        int head = 0;
        int rear = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            node node = new node(arr[i], i);
            for (int j = rear - 1; j >= head; j--) {
                if (stack[j].val < node.val) {
                    stack[j] = null;
                    rear--;
                } else {
                    break;
                }
            }
            stack[rear] = node;
            rear++;

            if (stack[head].val - stack[rear - 1].val == k) {
                max = Math.max(max, stack[rear - 1].index - stack[head].index + 1);
                stack[head] = null;
                head++;
            }


            if (stack[head].val - stack[rear - 1].val > k) {
                for (int j = head; j < rear - 1; j++) {
                    if (stack[head].val - stack[rear - 1].val > k) {
                        stack[head] = null;
                        head++;
                    } else {
                        break;
                    }
                }
            }

            if (stack[head].val - stack[rear - 1].val == k) {
                max = Math.max(max, stack[rear - 1].index - stack[head].index + 1);
                stack[head] = null;
                head++;
            }


        }
        return max;
    }

    public static int[] checkMin(int n, int[] arr) {
        int[] answer = new int[arr.length - (n - 1)];
        node[] stack = new node[3000000];
        int head = 0;
        int rear = 0;

        for (int i = 0; i < n - 1; i++) {
            node node = new node(arr[i], i);

            for (int j = rear - 1; j >= head; j--) {
                if (stack[j].val >= node.val) {
                    stack[j] = null;
                    rear--;
                }
            }
            stack[rear] = node;
            rear++;
        }


        int max = 0;
        int index = n - 1;
        for (int i = 0; i < answer.length; i++) {
            int a = arr[index];
            node node = new node(a, index);
            for (int j = rear - 1; j >= head; j--) {
                if (stack[j].val >= node.val) {
                    stack[j] = null;
                    rear--;
                }
            }
            stack[rear] = node;
            rear++;
            if (node.index - stack[head].index >= n) {
                stack[head] = null;
                head++;
            }

            index++;

            if (stack[head].val - stack[rear - 1].val > max) {
                max = stack[head].val - stack[rear - 1].val;
            }

            answer[i] = stack[head].val;
        }
        return answer;
    }

    private static class node {
        int val;
        int index;
        node pre;
        node next;

        public node() {
        }

        public node(int val, int index) {
            this.val = val;
            this.index = index;
        }

        public node(int val, int index, node pre, node next) {
            this.val = val;
            this.index = index;
            this.pre = pre;
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }


        public node getPre() {
            return pre;
        }


        public void setPre(node pre) {
            this.pre = pre;
        }

        public node getNext() {
            return next;
        }

        public void setNext(node next) {
            this.next = next;
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
