//package lec422;
//
//import java.io.*;
//import java.util.StringTokenizer;
//
//class DoublyLinkedList {
//    node head;
//
//    public DoublyLinkedList() {
//    }
//
//    public DoublyLinkedList(node head) {
//        this.head = head;
//    }
//
//
//    public void insert(node pre,node node) {
//        if (head == null) {
//            head = node;
//        } else {
//            pre.next = node;
//            node.prev = pre;
//        }
//    }
//
//    public void delete(int data) {
//        if (head == null) {
//            return;
//        }
//
//        Node current = head;
//        while (current != null) {
//            if (current.data == data) {
//                if (current.prev != null) {
//                    current.prev.next = current.next;
//                } else {
//                    head = current.next;
//                }
//
//                if (current.next != null) {
//                    current.next.prev = current.prev;
//                }
//
//                break;
//            }
//            current = current.next;
//        }
//    }
//
//    public void display() {
//        Node current = head;
//        while (current != null) {
//            System.out.print(current.data + " ");
//            current = current.next;
//        }
//        System.out.println();
//    }
//
//    public Node getHead() {
//        return head;
//    }
//
//    public void setHead(Node head) {
//        this.head = head;
//    }
//
//    public String toString() {
//        return "DoublyLinkedList{head = " + head + "}";
//    }
//}
//
//public class p2 {
//    public static void main(String[] args) {
//        QReader in = new QReader();
//        QWriter out = new QWriter();
//        int T = in.nextInt();
//        for (int i = 0; i < T; i++) {
//            int n = in.nextInt();
//            String s = in.next();
//            DoublyLinkedList doublyLinkedList=new DoublyLinkedList();
//            int start=0;
//            for (int j = 0; j < s.length(); j++) {
//                if(Character.isDigit(s.charAt(j))){
//                    doublyLinkedList.insert();
//                }else{
//                    start=j;
//                    break;
//                }
//            }
//            for (int j = start; j < s.length(); j++) {
//                switch (s.charAt(j)) {
//                    case 'r':
//                        if (cur.aChar != '-') {
//                            cur.aChar=re.charAt(k+1);
//                        } else {
//                            if(cur.next!=null){
//                                delete(cur.next);
//                                add(head,head.next);
//                            }else if(cur.pre!=null){
//                                delete(cur.pre);
//                                add(tail.pre, tail);
//                            }
//                        }
//                        k++;
//                        cur = tail;
//                        break;
//                    case 'I':
//                        cur = I(cur, head);
//                        break;
//                    case 'H':
//                        cur = H(cur, head);
//                        break;
//                    case 'L':
//                        cur = L(cur, tail);
//                        break;
//                    case 'x':
//                        if (cur.aChar != '-')
//                            delete(cur);
//                        break;
//                }
//            }
//        }
//
//        out.close();
//    }
//}
//
//class node {
//    int data;
//    node prev;
//    node next;
//
//    public node(int data) {
//        this.data = data;
//        this.prev = null;
//        this.next = null;
//    }
//}
//
//
//
//class QReader {
//    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    private StringTokenizer tokenizer = new StringTokenizer("");
//
//    private String innerNextLine() {
//        try {
//            return reader.readLine();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    public boolean hasNext() {
//        while (!tokenizer.hasMoreTokens()) {
//            String nextLine = innerNextLine();
//            if (nextLine == null) {
//                return false;
//            }
//            tokenizer = new StringTokenizer(nextLine);
//        }
//        return true;
//    }
//
//    public String nextLine() {
//        tokenizer = new StringTokenizer("");
//        return innerNextLine();
//    }
//
//    public String next() {
//        hasNext();
//        return tokenizer.nextToken();
//    }
//
//    public int nextInt() {
//        return Integer.parseInt(next());
//    }
//
//    public long nextLong() {
//        return Long.parseLong(next());
//    }
//}
//
//class QWriter implements Closeable {
//    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//    public void print(Object object) {
//        try {
//            writer.write(object.toString());
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    public void println(Object object) {
//        try {
//            writer.write(object.toString());
//            writer.write("\n");
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    @Override
//    public void close() {
//        try {
//            writer.close();
//        } catch (IOException ignored) {
//
//        }
//    }
//}
//
//
