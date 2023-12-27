import java.io.*;
import java.util.StringTokenizer;

public class p563 {

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int k = in.nextInt();
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
        int start = 0;
        int end = n;
        while (end - start > 1) {
            int mid = (start + end) / 2;
//            out.println(mid);
            if (mix(mid, arr, k)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        out.print(start);

//        int m = 1;
//
//
//
//        int [][]answer=new int[3][];
//        answer[0]=checkMax(m, arr);
//        for (int i = 0; i < answer[0].length; i++) {
//            out.print(answer[0][i]+" ");
//        }
//        out.print("\n");
//
//        answer[1]=checkMin(m, arr);
//        for (int i = 0; i < answer[1].length; i++) {
//            out.print(answer[1][i]+" ");
//        }
//        out.print("\n");
//
//
//        answer[2]=new int[answer[1].length];
//        for (int i = 0; i < answer[2].length; i++) {
//            answer[2][i]=answer[0][i]-answer[1][i];
//            out.print(answer[2][i]+" ");
//        }
//
//        boolean result=false;
//        for (int i = 0; i < answer[2].length; i++) {
//            if(answer[2][i]==k){
//                result=true;
//                break;
//            }
//        }
        out.close();
    }

    public static boolean mix(int m, int[] arr, int k) {
        int[][] answer = new int[3][];
        answer[0] = checkMax(m, arr);
        answer[1] = checkMin(m, arr);
        answer[2] = new int[answer[1].length];
//        for (int i = 0; i < answer[0].length; i++) {
//            out.print(answer[0][i] + " ");
//        }
//        out.print("\n");
//        for (int i = 0; i < answer[1].length; i++) {
//            out.print(answer[1][i] + " ");
//        }
//        out.print("\n");
        for (int i = 0; i < answer[2].length; i++) {
            answer[2][i] = answer[0][i] - answer[1][i];
//            out.print(answer[2][i] + " ");
        }
//        out.print("\n");

        for (int i = 0; i < answer[2].length; i++) {
            if (answer[2][i] == k) {
                return true;
            }
        }
        return false;
    }

    public static int[] checkMax(int n, int[] arr) {
        int[] answer = new int[arr.length - (n - 1)];
        node[] stack = new node[2000000];
        int head = 0;
        int rear = 0;

        for (int i = 0; i < n - 1; i++) {
            node node = new node(arr[i], i);

            for (int j = rear - 1; j >= head; j--) {
                if (stack[j].val <= node.val) {
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
                if (stack[j].val <= node.val) {
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

//        System.out.print(result);
//        System.out.print("\n");
//        System.out.print(max);
        return answer;
    }

    public static int[] checkMin(int n, int[] arr) {
        int[] answer = new int[arr.length - (n - 1)];
        node[] stack = new node[2000000];
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

//        System.out.print(result);
//        System.out.print("\n");
//        System.out.print(max);
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
