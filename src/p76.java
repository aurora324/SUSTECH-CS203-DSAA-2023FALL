import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.*;

public class p76 {
    public static void main(String[] args) {
//        List<node> list = new ArrayList<>();
        Map<String ,Integer>map=new Map<>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public Integer get(Object key) {
                return null;
            }

            @Nullable
            @Override
            public Integer put(String key, Integer value) {
                return null;
            }

            @Override
            public Integer remove(Object key) {
                return null;
            }

            @Override
            public void putAll(@NotNull Map<? extends String, ? extends Integer> m) {

            }

            @Override
            public void clear() {

            }

            @NotNull
            @Override
            public Set<String> keySet() {
                return null;
            }

            @NotNull
            @Override
            public Collection<Integer> values() {
                return null;
            }

            @NotNull
            @Override
            public Set<Entry<String, Integer>> entrySet() {
                return null;
            }
        };
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        node[] nodes = new node[n + 1];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new node();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            nodes[u].children.add(nodes[v]);
            nodes[v].children.add(nodes[u]);
        }
        for (int i = 1; i < nodes.length; i++) {
            nodes[i].p = in.nextInt();
        }
        //que ding root
        node root = nodes[1];
        for (int i = 2; i < nodes.length; i++) {
            if (nodes[i].p > root.p) {
                root = nodes[i];
            }
        }
        //qiuzhi
        long sum = 0;

        root.flag = true;
        buildTree(root);

        ArrayList<node> arrayList = new ArrayList<>();
        for (int i = 1; i < nodes.length; i++) {
            nodes[i].e = nodes[i].p;
            if (nodes[i].children.size() == 1) {
                arrayList.add(nodes[i]);
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).father != null) {
                if (arrayList.get(i).father.e < arrayList.get(i).e) {
                    arrayList.get(i).father.e = arrayList.get(i).e;
                }
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            sum += arrayList.get(i).e;
        }

//        if (root.children.size() == 1) {
//            sum+= root.e;
//            ArrayList<Integer> arrayList1 = new ArrayList<>();
//            root.maxFlag = true;
//            buildTree(root.children.get(0), arrayList1);
//            int[] arr = new int[arrayList1.size()];
//            for (int i = 0; i < arr.length; i++) {
//                arr[i] = arrayList1.get(i);
//            }
//            quickSort(arr, 0, arr.length - 1);
//            int re = root.e - arr[arr.length - 1];
//            sum += re;
//        } else {
//            int[] answer = new int[root.children.size()];
//            int counter = 0;
//            for (int w = 0; w < root.children.size(); w++) {
//                ArrayList<Integer> arrayList1 = new ArrayList<>();
//                root.maxFlag = true;
//                buildTree(root.children.get(w), arrayList1);
//                int[] arr = new int[arrayList1.size()];
//                for (int i = 0; i < arr.length; i++) {
//                    arr[i] = arrayList1.get(i);
//                }
//                quickSort(arr, 0, arr.length - 1);
//                answer[counter] = arr[arr.length - 1];
//                counter++;
//            }
//            quickSort(answer, 0, answer.length - 1);
//            sum += root.e - answer[answer.length - 1];
//            sum += root.e - answer[answer.length - 2];
//        }

        if (root.children.size() == 1) {
            sum += root.e;
            root.maxFlag = true;
            root.children.get(0).maxFlag = true;
            node node = leaf(root.children.get(0));
            sum += root.e - node.e;
        } else {
            root.maxFlag = true;
            node node11 = findMaxChildren(root.children);
            node11.maxFlag = true;
            node node12 = leaf(node11);
            sum += root.e - node12.e;
            node11.e = 0;
            node node21 = findMaxChildren(root.children);
            node21.maxFlag = true;
            node node22 = leaf(node21);
            sum += root.e - node22.e;
        }
        out.print(sum);
        out.close();
    }

    public static node leaf(node root) {
        if (root.children.size() == 1) {
            return root;
        }
        for (int i = 0; i < root.children.size(); i++) {
            if (!root.children.get(i).maxFlag) {
                root.children.get(i).maxFlag = true;
                root = root.children.get(i);
                leaf(root);
            }
        }
        return root;
    }

    public static node findMaxChildren(ArrayList<node> arrayList) {
        node num = arrayList.get(0);
        int re = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).e > num.e) {
                num = arrayList.get(i);
            }
        }
        return num;
    }

    public static void quickSort(int[] arr, int i, int j) {
        int start = i;
        int end = j;
        if (start > end) {
            return;
        }
        int baseNumber = arr[i];
        while (start != end) {
            while (end > start && arr[end] >= baseNumber) {
                end--;
            }
            while (end > start && arr[start] <= baseNumber) {
                start++;
            }
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }

        int temp = arr[i];
        arr[i] = arr[start];
        arr[start] = temp;
        quickSort(arr, i, start - 1);
        quickSort(arr, start + 1, j);
    }

    public static void buildTree(node root) {
        boolean result = false;
        for (int i = 0; i < root.children.size(); i++) {
            if (!root.children.get(i).flag) {
                result = true;
                break;
            }
        }
        if (!result) {
            return;
        }
        for (int i = 0; i < root.children.size(); i++) {
            if (!root.children.get(i).flag) {
                root.children.get(i).father = root;
                root.children.get(i).flag = true;
                buildTree(root.children.get(i));
            }
        }
    }

    public static void buildTree(node root, ArrayList<Integer> arrayList) {
        boolean result = false;
        for (int i = 0; i < root.children.size(); i++) {
            if (!root.children.get(i).maxFlag) {
                result = true;
                break;
            }
        }
        if (root.children.size() == 1) {
            arrayList.add(root.e);
        }
        if (!result) {
            return;
        }
        for (int i = 0; i < root.children.size(); i++) {
            if (!root.children.get(i).maxFlag) {
                root.children.get(i).father = root;
                root.children.get(i).maxFlag = true;
                buildTree(root.children.get(i), arrayList);
            }
        }
    }

    private static class node {

        int p;
        int e;
        boolean flag;

        boolean maxFlag;
        ArrayList<node> children = new ArrayList<>();
        node father;

        public node() {
        }


        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }

        public int getE() {
            return e;
        }

        public void setE(int e) {
            this.e = e;
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
