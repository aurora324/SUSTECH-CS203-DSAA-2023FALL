package lab10;

import java.io.*;
import java.util.*;

public class Question4 {
    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int c = in.nextInt();
        int[] color = new int[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        boolean[][] hasColors = new boolean[n + 1][k + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < n + 1; i++) {
            color[i] = in.nextInt();
            hasColors[i][color[i]] = true;
        }
        for (int i = 0; i < m; i++) {
            int p = in.nextInt();
            int q = in.nextInt();
            graph[p].add(q);
            graph[q].add(p);
        }
        int[] colorCount = new int[n + 1];
        Arrays.fill(colorCount,1);
        Queue<QueueContent> queue = new ArrayDeque<>(n + 1);
        for (int i = 1; i < n + 1; i++) {
            queue.add(new QueueContent(i,i,0));
        }
        int[] ans = new int[n + 1];
        while (!queue.isEmpty()) {
            QueueContent content = queue.poll();
            ans[content.now] += content.steps;
            for (int t:graph[content.now]) {
                if (colorCount[t] < c && !hasColors[t][color[content.from]]) {
                    colorCount[t]++;
                    hasColors[t][color[content.from]] = true;
                    queue.add(new QueueContent(content.from,t, content.steps + 1));
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            out.print(ans[i]);
            out.print(" ");
        }
        out.close();
    }
    static class QueueContent {
        int from;
        int now;
        int steps;
        public QueueContent(int from,int now,int steps) {
            this.from = from;
            this.now = now;
            this.steps = steps;
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
