import java.io.*;
import java.util.StringTokenizer;

public class p56mix {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int k = in.nextInt();
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
        int a = checkMax(k, arr);
        int b = checkMin(k, arr);
//        out.println(a);
//        out.println(b);
        out.print(Math.max(a, b));
        out.close();
    }

    public static int checkMax(int k, int[] arr) {
        int length = 0;
        int left = 0;
        int right;
        int min = arr[0];
        int max = arr[0];
        int[] queue1 = new int[arr.length];
        queue1[0] = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            right = i;
            queue1[right] = arr[i];
            max = Math.max(max, queue1[right]);
            min = Math.min(min, queue1[right]);
            if (max - min > k) {
                while (Math.abs(queue1[right] - min) > k || Math.abs(max - queue1[right]) > k) {
                    left++;
                    min = queue1[left];
                    max = queue1[left];
                    for (int j = left; j <= right; j++) {
                        max = Math.max(max, queue1[j]);
                        min = Math.min(min, queue1[j]);
                    }
                }
            }
            max = Math.max(max, queue1[right]);
            min = Math.min(min, queue1[right]);
            length = Math.max(right - left + 1, length);
        }
        return length;
    }

    public static int checkMin(int k, int[] arr) {
//        out.println("min");
        node[] stack = new node[3000000];
        stack[0] = new node(arr[0], 0);
        int head = 0;
        int rear = 1;
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            int a = arr[i];
            node node = new node(a, i);
//            stack[rear] = node;
//            rear++;
            if (node.val >= stack[head].val || -node.val + stack[rear - 1].val > k) {
                for (int j = rear - 1; j >= head; j--) {
                    if (stack[j].val > node.val) {
                        stack[j] = null;
                        rear--;
                    } else {
                        break;
                    }
                }
            }



            stack[rear] = node;
            rear++;

//            if (stack[rear-1].val - stack[head].val == k) {
//                max=Math.max(max,stack[rear-1].index-stack[head].index+1);
//                stack[head] = null;
//                head++;
//            }


            if (stack[rear - 1].val - stack[head].val > k) {
                for (int j = head; j < rear - 1; j++) {
                    if (stack[rear - 1].val - stack[head].val > k) {
                        stack[head] = null;
                        head++;
                    } else {
                        break;
                    }
                }
            }
            if (stack[rear - 1].val - stack[head].val <= k) {
                max = Math.max(max, stack[rear - 1].index - stack[head].index + 1);
            }

//            if (stack[rear - 1].index - stack[head].index + 1 == 4) {
//                out.println("index" + stack[head].index + " " + stack[rear - 1].index);
//                out.println("val" + stack[head].val + " " + stack[rear - 1].val);
//            }
//            if (stack[rear - 1].val- stack[head].val  <= k&&stack[rear - 1].index- stack[head].index  == k) {
//                out.println(stack[head].index + " " + stack[rear - 1].index);
//                out.println("val" + stack[head].val + " " + stack[rear - 1].val);
//            }


//            out.println(max);
//            max=Math.max(max,stack[rear-1].index-stack[head].index+1);

//            if(stack[rear-1].val-stack[head].val<=k){
//                max=Math.max(max,stack[rear-1].index-stack[head].index+1);
//
////                out.println(stack[head].index+" "+stack[rear-1].index);
////                out.println(stack[head].val+" "+stack[rear-1].val);
//            }
//            if (stack[rear-1].val -  stack[head].val== k) {
//                max=Math.max(max,stack[rear-1].index-stack[head].index+1);
//                stack[head] = null;
//                head++;
//            }

//            System.out.println(stack[head].val+" "+stack[rear-1].val);
//            System.out.println(head+" "+rear);
        }
//        System.out.println();
        return 0;
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
