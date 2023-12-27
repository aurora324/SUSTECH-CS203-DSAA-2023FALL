import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p84force {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int n = in.nextInt();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(in.nextInt());
        }
        for (int i = 0; i < n - 1; i++) {
            int cur = indexOfMin(arrayList);
            int delete = 0;
            if (cur == 0) {
                delete = 1;
            } else if (cur == arrayList.size() - 1) {
                delete = cur - 1;
            } else {
                if (arrayList.get(cur - 1) <= arrayList.get(cur + 1)) {
                    delete = cur - 1;
                } else {
                    delete = cur + 1;
                }
            }

            int min = Math.min(cur, delete);
            int max = min + 1;

            arrayList.set(min, (arrayList.get(min) ^ arrayList.get(max)) + 1);
            arrayList.remove(max);
        }
        out.println(arrayList.get(0));
        out.close();
    }

    public static int indexOfMin(ArrayList<Integer> arrayList) {
        int re = 0;
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i) < arrayList.get(re)) {
                re = i;
            }
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
        private final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

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
