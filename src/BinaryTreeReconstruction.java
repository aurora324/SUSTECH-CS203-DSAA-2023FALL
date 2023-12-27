import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BinaryTreeReconstruction {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int rootIndexInorder = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                rootIndexInorder = i;
                break;
            }
        }

        int leftSubtreeSize = rootIndexInorder - inStart;

        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftSubtreeSize, inorder, inStart, rootIndexInorder - 1);
        root.right = buildTreeHelper(preorder, preStart + leftSubtreeSize + 1, preEnd, inorder, rootIndexInorder + 1, inEnd);

        return root;
    }

    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int T=in.nextInt();
        int n=in.nextInt();
        int[] preorder = new int[n];
        for (int i = 0; i < preorder.length; i++) {
            preorder[i]=in.nextInt();
        }
        int[] inorder = new int[n];
        for (int i = 0; i < inorder.length; i++) {
            inorder[i]=in.nextInt();
        }

        BinaryTreeReconstruction solution = new BinaryTreeReconstruction();
        TreeNode root = solution.buildTree(preorder, inorder);
        System.out.println(root);
        // 验证重建的二叉树
        System.out.println("重建的二叉树的中序遍历结果：");
        ArrayList<Integer>post=postOrderTraversal(root);
        for (int i = 0; i < post.size(); i++) {
            out.print(post.get(i)+" ");
        }
        out.close();
    }

    private static ArrayList<Integer> postOrderTraversal(TreeNode root) {
        ArrayList<Integer>pre =new ArrayList<>();
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            pre.add(root.val);
        }
        return pre;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
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
