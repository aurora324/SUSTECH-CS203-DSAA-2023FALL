package lec3;

import java.io.*;
import java.util.StringTokenizer;

public class p1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int counter = in.nextInt();
        for (int i = 0; i < counter; i++) {
            int num1 = in.nextInt();
            int num2 = in.nextInt();
            int[] arr1 = new int[num1];
            for (int j = 0; j < num1; j++) {
                arr1[j] = in.nextInt();
            }
            int[] arr2 = new int[num2];
            for (int j = 0; j < num2; j++) {
                arr2[j] = in.nextInt();
            }
            int[] arr3 = new int[num1 + num2];

            int m =0;
            int n=0;
            while (m<arr1.length&&n<arr2.length){
                if(arr1[m]<=arr2[n]){
                    arr3[m+n]=arr1[m];
                    m++;
                }else{
                    arr3[m+n]=arr2[n];
                    n++;
                }
            }
            if(m==arr1.length){
                System.arraycopy(arr2,n,arr3,m+n,num2-n);
            }else{
                System.arraycopy(arr1,m,arr3,m+n,num1-m);
            }



            for (int j = 0; j < arr3.length-1; j++) {
                out.print(arr3[j] + " ");
            }
            out.println(arr3[arr3.length-1]);
            int k=1;
            System.out.println(arr3[k++]);
        }

        out.close();
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