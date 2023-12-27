package lec34;

import java.io.*;
import java.util.StringTokenizer;

public class p4 {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int n=in.nextInt();
        int k=in.nextInt();
        int[]arr=new int[n];
        int[]copy=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=in.nextInt();
        }
        sort(arr,copy,0,n-1);
        System.out.println(arr[k-1]);
        out.close();
    }

    public static void sort(int[] arr, int[] copy, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(arr, copy, start, mid);
        sort(arr, copy, mid + 1, end);
        merge(arr, copy, start, mid, end);
    }

    public static void merge(int[] arr, int[] copy, int start, int mid, int end) {
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
            } else if (copy[i] >= copy[j]) {
                arr[k] = copy[i];
                i++;
            } else {
                arr[k] = copy[j];
                j++;
            }
        }
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
