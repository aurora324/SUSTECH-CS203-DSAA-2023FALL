import java.io.*;
import java.util.StringTokenizer;

public class BonusD {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int T=in.nextInt();
        for (int i = 0; i < T; i++) {
            int n=in.nextInt();

            int [] arr=new int[n+1];
            for (int j = 1; j < arr.length; j++) {
                arr[j]=in.nextInt();
            }
            
            int q=in.nextInt();
            for (int j = 0; j < q; j++) {
                int start=in.nextInt();
                int answer=0;
                for (int k = start; k < arr.length; k++) {
                    if(arr[k]>arr[start]){
                        answer=k;
                        break;
                    }
                }
                if(answer>start){
                    out.println(answer-start);
                }else {
                    out.println(-1);
                }
            }
            
        }
        out.close();
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
