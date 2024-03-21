
public class Queue<E> {
    private E[] que;
    private int capacity;
    private int num;
    private  int front;
    private  int rear;


    public static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException() { }
    }

    //--- 실행 시 예외 : 스택이 가득 참 ---//
    public static class OverflowQueueException extends RuntimeException {
        public OverflowQueueException() { }
    }

    //--- 생성자 ---//
    public Queue(int maxlen) {
        num = front = rear = 0;
        capacity = maxlen;
        try {
            que = (E[])new Object[capacity];		// 스택 본체용 배열을 생성
        } catch (OutOfMemoryError e) {				// 생성할 수 없음
            capacity = 0;
        }
    }

    //--- 스택에 x를 푸시 ---//
    public E enqueue(E x) throws OverflowQueueException {
        if (num >= capacity)								// 스택이 가득 참
            throw new OverflowQueueException();
        que[rear++] = x;
        num++;
        if (rear == capacity)
            rear = 0;
        return x;
    }


    public E dequeue() throws EmptyQueueException {
        if (num <= 0)												// 스택이 비어 있음
            throw new EmptyQueueException();
        E temp = que[front++];
        num--;
        if(front == capacity)
            front = 0;
        return temp;
    }

    //--- 스택에서 데이터를 피크(꼭대기의 데이터를 들여다 봄) ---//
    public E peek() throws EmptyQueueException {
        if (num <= 0)												// 스택이 비어 있음
            throw new EmptyQueueException();
        return que[front];
    }

    //--- 스택을 비웁니다 ---//
    public void clear() {
        num = 0;
    }

    //--- 스택에서 x를 검색하여 인덱스(발견하지 못하면 -1)를 반환합니다 ---//
    public int indexOf(E x) {
        for (int i = 0; i < num; i++){
            int idx = (i + front) % capacity;
            if(que[idx] == x)// 검색 성공
                return idx;
        }
        return -1;							// 검색 실패
    }
    public int search(E x) {
        for (int i = 0; i < num; i++)
            if (que[(i + front) % capacity] == x)	// 검색 성공
                return i + 1;
        return 0;								// 검색 실패
    }

    //--- 스택 용량을 반환합니다 ---//
    public int getCapacity() {
        return capacity;
    }

    //--- 스택에 쌓여있는 데이터수를 반환합니다 ---//
    public int size() {
        return num;
    }

    //--- 스택이 비어 있는가? ---//
    public boolean isEmpty() {
        return num <= 0;
    }

    //--- 스택이 가득 찼는가? ---//
    public boolean isFull() {
        return num >= capacity;
    }

    //--- 스택 안의 모든 데이터를 바닥 → 꼭대기의 순서로 출력 ---//
    public void dump() {
        if (num <= 0)
            System.out.println("큐가 비어 있습니다.");
        else {
            for (int i = 0; i < num; i++)
                System.out.print(que[(i + front) % capacity].toString() + " ");
            System.out.println();
        }
    }
}
