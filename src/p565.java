import java.io.*;
import java.util.StringTokenizer;

public class p565 {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        System.out.println('a' - ' ');
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
//        out.println("max");
        node[] stack = new node[3000000];
        int head = 0;
        int rear = 1;
        int max = 0;
        stack[0] = new node(arr[0], 0);
        for (int i = 1; i < arr.length; i++) {
            int a = arr[i];
            node node = new node(a, i);

            if (node.val <= stack[head].val || node.val - stack[rear - 1].val > k) {
                for (int j = rear - 1; j >= head; j--) {
                    if (stack[j].val <= node.val) {
                        stack[j] = null;
                        rear--;
                    } else {
                        break;
                    }

                }
            }
            stack[rear] = node;
            rear++;

            int innerMax = stack[head].val;


            int max1 = stack[head].val;
            for (int j = head; j < rear; j++) {
                max1 = Math.max(max1, stack[j].val);
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

//            out.println(max);
            if (stack[head].val - stack[rear - 1].val <= k) {
                max = Math.max(max, stack[rear - 1].index - stack[head].index + 1);
//                out.println(stack[head].index + " " + stack[rear - 1].index);
//                out.println("val" + stack[head].val + " " + stack[rear - 1].val);
            }

            if (stack[rear - 1].index - stack[head].index + 1 == 6) {
                out.println("index" + stack[head].index + " " + stack[rear - 1].index);
                out.println("val" + stack[head].val + " " + stack[rear - 1].val);
            }
//            if(stack[head].val-stack[rear-1].val<=k){
//                max=Math.max(max,stack[rear-1].index-stack[head].index+1);
////                out.println(stack[head].index+" "+stack[rear-1].index);
////                out.println(stack[head].val+" "+stack[rear-1].val);
//            }
//            if (stack[head].val - stack[rear-1].val == k) {
//                max=Math.max(max,stack[rear-1].index-stack[head].index+1);
//                stack[head] = null;
//                head++;
//            }


//            System.out.println(stack[head].val+" "+stack[rear-1].val);
//            System.out.println(head+" "+rear);

        }
//        System.out.println();
        return max;
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

            if (stack[rear - 1].index - stack[head].index + 1 == 6) {
                out.println("index" + stack[head].index + " " + stack[rear - 1].index);
                out.println("val" + stack[head].val + " " + stack[rear - 1].val);
            }
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
        return max;
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
