
class DoublyLinkedList {
    Node head;

    public DoublyLinkedList() {
    }

    public DoublyLinkedList(Node head) {
        this.head = head;
    }


    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
    }

    public void delete(int data) {
        if (head == null) {
            return;
        }

        Node current = head;
        while (current != null) {
            if (current.value == data) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                }

                break;
            }
            current = current.next;
        }
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 获取
     * @return head
     */
    public Node getHead() {
        return head;
    }

    /**
     * 设置
     * @param head
     */
    public void setHead(Node head) {
        this.head = head;
    }

    public String toString() {
        return "DoublyLinkedList{head = " + head + "}";
    }

    private class node {
        int data;
        Node prev;
        Node next;

        public node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

}

class Main {
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        // 在双向链表中插入数据
        dll.insert(1);
        dll.insert(2);
        dll.insert(3);
        dll.insert(4);

        // 显示双向链表中的数据
        dll.display();  // 输出: 1 2 3 4

        // 从双向链表中删除数据
        dll.delete(2);

        // 再次显示双向链表中的数据
        dll.display();  // 输出: 1 3 4
    }
}