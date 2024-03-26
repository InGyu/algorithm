import java.util.Comparator;


public class LinkedListX<E> {
    class Node<E> {
        private E data;
        private Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> crnt;

    private Node<E> tail;

    public LinkedListX() {
        head = crnt = tail = null;
    }

    public E serach(E obj, Comparator<? super E> c) {
        Node<E> ptr = head;

        while (ptr != null) {
            if(c.compare(obj, ptr.data) == 0) {
                crnt = ptr;
                return ptr.data;
            }
            ptr = ptr.next;
        }
        return null;
    }

    public void addFirst(E obj) {
        boolean empty = (tail == null);
        Node<E> ptr = head;
        head = crnt = new Node<E>(obj, ptr);
        if (empty) tail = crnt;
    }

    public void addLast(E obj) {
        if (head == null)
            addFirst(obj);
        else {
            tail.next = crnt = new Node<>(obj, null);
            tail = crnt;
        }
    }

    public void removeFirst() {
        if(head != null)
            head = crnt = head.next;
    }

    public void removeLast() {
        if (head != null) {
            if (head.next == null)		           // 노드가 하나뿐이면
                removeFirst();					// 머리 노드를 삭제
            else {
                Node<E> ptr = head;			// 스캔 중인 노드
                Node<E> pre = head;			// 스캔 중인 노드의 앞쪽 노드

                while (ptr.next != null) {
                    pre = ptr;
                    ptr = ptr.next;
                }
                pre.next = null;				// pre는 삭제 후의 꼬리 노드
                tail = crnt = pre;
            }
        }
    }

    public void remove(Node<E> p) {
        if (head != null) {
            if(p == head)
                removeFirst();
            else {
                Node<E> ptr = head;

                while (ptr.next != p) {
                    ptr = ptr.next;
                    if(ptr == null)
                        return;
                }
                ptr.next = p.next;
                crnt = ptr;
            }
        }
    }

    public void removeCurrentNode() {
        remove(crnt);
    }

    public void clear() {
        while (head != null)
            removeFirst();
        crnt = null;
    }

    public boolean next() {
        if (crnt == null || crnt.next == null)
            return false;

        crnt = crnt.next;
        return true;
    }

    public void printCurrentNode() {
        if (crnt == null)
            System.out.println("선택한 노드가 없습니다.");
        else
            System.out.println(crnt.data);
    }

    public void dump() {
        Node<E> ptr = head;

        while (ptr != null) {
            System.out.println(ptr.data);
            ptr = ptr.next;
        }
    }

    public void purge(Comparator<? super E> c){
        Node<E> ptr = head;

        while(ptr != null) {
            int count = 0;
            Node<E> ptr2 = ptr;
            Node<E> pre = ptr;

            while(pre.next != null) {
                ptr2 = pre.next;
                if(c.compare(ptr.data, ptr2.data) == 0) {
                    pre.next = ptr2.next;
                    count++;
                }
                else
                    pre = ptr2;
            }
            if(count == 0)
                ptr = ptr.next;
            else {
                Node<E> temp = ptr;
                remove(ptr);
                ptr = temp.next;
            }
        }
        crnt = head;
    }

    public E retrieve(int n) {
        Node<E> ptr = head;

        while (n >= 0 && ptr != null) {
            if (n == 0) {
                crnt = ptr;
                return ptr.data;
            }
            n--;
            ptr = ptr.next;
        }
        return null;
    }
}
