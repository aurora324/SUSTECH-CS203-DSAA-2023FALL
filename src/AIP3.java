import java.util.Scanner;

class Node {
    int value;
    Node prev;
    Node next;

    public Node(int value) {
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

public class AIP3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // 测试用例数量

        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt(); // 队伍成员数量
            int M = scanner.nextInt(); // 交换指令数量

            // 创建双向链表并初始化
            Node head = null;
            Node tail = null;
            for (int i = 0; i < N; i++) {
                int value = scanner.nextInt();
                Node newNode = new Node(value);
                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.next = newNode;
                    newNode.prev = tail;
                    tail = newNode;
                }
            }

            for (int i = 0; i < M; i++) {
                int x1 = scanner.nextInt();
                int y1 = scanner.nextInt();
                int x2 = scanner.nextInt();
                int y2 = scanner.nextInt();

                // 执行交换指令
                head = swap(head, x1, y1, x2, y2);
            }

            // 输出最终队伍形成
            Node current = head;
            while (current != null) {
                System.out.print(current.value + " ");
                current = current.next;
            }
            System.out.println();
        }
    }

    // 执行交换指令
    private static Node swap(Node head, int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) {
            // 两个部分相同，无需交换
            return head;
        }

        Node nodeX1 = getNodeAtPosition(head, x1);
        Node nodeY1 = getNodeAtPosition(head, y1);
        Node nodeX2 = getNodeAtPosition(head, x2);
        Node nodeY2 = getNodeAtPosition(head, y2);

        if (nodeX1 != null && nodeX1.prev != null) {
            nodeX1.prev.next = nodeY2;
        } else if (nodeX1 != null) {
            head = nodeY2;
        }

        if (nodeX2 != null && nodeX2.prev != null) {
            nodeX2.prev.next = nodeY1;
        } else if (nodeX2 != null) {
            head = nodeY1;
        }

        if (nodeY1 != null && nodeY1.next != null) {
            nodeY1.next.prev = nodeX2;
        }

        if (nodeY2 != null && nodeY2.next != null) {
            nodeY2.next.prev = nodeX1;
        }

        if (nodeY1 != null && nodeY2 != null) {
            Node temp = nodeY1.next;
            nodeY1.next = nodeY2.next;
            nodeY2.next = temp;
        }

        return head;
    }

    // 根据位置获取节点
    private static Node getNodeAtPosition(Node head, int position) {
        Node current = head;
        int index = 0;
        while (current != null && index < position) {
            current = current.next;
            index++;
        }
        return current;
    }
}