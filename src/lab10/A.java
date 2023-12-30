package lab10;

import java.util.ArrayList;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i < n + 1; i++) {
            nodes[i] = new Node();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            long c = in.nextLong();
            nodes[a].children.add(nodes[b]);
            nodes[a].LengthList.add(c);
        }
        heap heap = new heap(n);
        nodes[1].value = 0;
        heap.insert(nodes[1]);
        Node min;
        while (heap.size != 0) {
            min = heap.delete();
            if (min.isVisited) {
                continue;
            }
            min.isVisited = true;
            int child = min.children.size();
            for (int i = 0; i < child; i++) {
                Node temp = min.children.get(i);
                if (!temp.isVisited) {
                    if (temp.value > min.LengthList.get(i) + min.value) {
                        temp.value = min.LengthList.get(i) + min.value;
                    }
                    heap.insert(temp);
                }
            }
        }
        if (nodes[n].value == Long.MAX_VALUE) {
            System.out.println("-1");
        } else System.out.println(nodes[n].value);
    }

    static class Node {
        long value;
        boolean isVisited = false;
        ArrayList<Node> children;
        ArrayList<Long> LengthList;


        public Node() {
            this.value = Long.MAX_VALUE;
            this.children = new ArrayList<>();
            this.LengthList = new ArrayList<>();
        }
    }

    static class heap {
        Node[] heap;
        int size = 0;

        public heap(int n) {
            heap = new Node[n + 1];
        }

        public void insert(Node x) {
            size++;
            heap[size] = x;
            int top = size;
            while (top > 1) {
                if (heap[top].value < heap[top / 2].value) {
                    swap(heap, top, top / 2);
                    top = top / 2;
                } else if (heap[top].value == heap[top / 2].value) {
                    swap(heap, top, top / 2);
                    top = top / 2;
                } else {
                    break;
                }
            }
        }

        public Node delete() {
            Node q = heap[1];
            heap[1] = heap[size];
            heap[size]=null;
            size--;
            int top = size;
            int t = 1;
            while (t * 2 < top - 1) {
                if (t * 2 + 1 == top - 1) {
                    if (heap[t * 2].value <= heap[t].value) {
                        swap(heap, t, t * 2);
                        t *= 2;
                    } else {
                        break;
                    }
                } else {
                    if (heap[t].value <= heap[t * 2].value && heap[t].value <= heap[t * 2 + 1].value) {
                        break;
                    } else if (heap[2 * t].value <= heap[t].value && heap[2 * t].value <= heap[2 * t + 1].value) {
                        swap(heap, t, 2 * t);
                        t *= 2;
                    } else if (heap[2 * t + 1].value <= heap[t].value && heap[2 * t + 1].value <= heap[2 * t].value) {
                        swap(heap, t, 2 * t + 1);
                        t = 2 * t + 1;
                    }
                }
            }
            return q;
        }

        public static void swap(Node[] heap, int i, int j) {
            Node temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

    }
}
