import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BonusB {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        long[] arr = new long[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 2;
        long mod=998244353;
        for (int i = 3; i < arr.length; i++) {
            String binary = Integer.toBinaryString(i);
//            System.out.println("last"+i+" "+last);
            long height=binary.length();
            long cur=i;
            long last=cur-(pow(2,binary.length()-1)-1);
            long lastButOne=pow(2,height-2);
            long left;
            long right;
            if(last>lastButOne){
                left=lastButOne;
                right=last-left;
            }else{
                left=last;
                right=0;
            }
            cur-=last;
            cur/=2;
            left+=cur;
            right+=cur;


            long factor=calculateCombination(i-1, (int) left);
            arr[i]=factor%mod;
            arr[i]=arr[i]*arr[(int) left]%mod;
            arr[i]=arr[i]*arr[(int) right]%mod;

//            System.out.println(left+" "+right);

        }
        System.out.println(Arrays.toString(arr));
//        out.print(arr[n]);
        out.close();
    }

    public static long calculateCombination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        } else {
            return calculateCombination(n - 1, k - 1) + calculateCombination(n - 1, k);
        }
    }

    public static long c(long a, long b) {
        return factorial(a) / factorial(b) / factorial(a - b);
    }

    public static long factorial(long a) {
        if (a == 0) return 1;
        long re = 1;
        for (long i = 1; i <= a; i++) {
            re *= i;
        }
        if (re == 0) {
            System.out.println(a);
        }
        return re;
    }

    public static long pow(long a, long b) {
        long re = 1;
        for (int i = 0; i < b; i++) {
            re *= a;
        }
        return re;
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
