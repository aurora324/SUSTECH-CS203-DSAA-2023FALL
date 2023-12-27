public class LinkedListQueue {
    node head=new node(-1);
    node tail=head;
    int size;

    public LinkedListQueue(){}


    public String toString() {
        return "LinkedListQueue{head = " + head + ", tail = " + tail + ", size = " + size + "}";
    }

    private static class node{
        int value;
        node next;
        node pre;

        public node() {
        }

        public node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public node getNext() {
            return next;
        }

        public void setNext(node next) {
            this.next = next;
        }

        public node getPre() {
            return pre;
        }

        public void setPre(node pre) {
            this.pre = pre;
        }

        public String toString() {
            return "node{value = " + value + ", next = " + next + ", pre = " + pre + "}";
        }
    }

    public void enqueue(node node){
        tail.next=node;
        tail=node;
        tail.next=head;
        size++;
    }

    public boolean dequeue(node node){
        if(head==tail){
            return false;
        }else{
            head.next.next.pre=head;
            head.next=head.next.next;
            return true;
        }
    }

    public boolean isEmpty(){
        return head==tail;
    }

    public node peek(){
        if(isEmpty()){
            return null;
        }else{
            return head.next;
        }
    }

}
