// 연습4-2
// 제네릭 스택의 사용 예(Gstack 클래스의 모든 메서드 를 사용)

import java.util.Scanner;

class IntQueueTester {

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Queue<String> que = new Queue<>(64);	// 최대 64개를 푸시할 수 있는 스택

        while (true) {
            System.out.printf("현재 데이터 개수 : %d / %d\n", que.size(), que.getCapacity());
            System.out.print("(1) 인큐 (2) 디큐 (3) 피크 " +
                    "(4) 덤프 (5) 검색 (6) 비움 " +
                    "(7) 출력 (0) 종료 : ");

            int menu = stdIn.nextInt();
            if (menu == 0) break;

            String x;

            switch (menu) {
                case 1: 								// 푸시
                    System.out.print("데이터 : ");
                    x = stdIn.next();
                    try {
                        que.enqueue(x);
                    } catch (Queue.OverflowQueueException e) {
                        System.out.println("큐가 가득 찼습니다.");
                    }
                    break;

                case 2: 								// 팝
                    try {
                        x = que.dequeue();
                        System.out.println("디큐한 데이터는 " + x + "입니다.");
                    } catch (Queue.EmptyQueueException e) {
                        System.out.println("큐가 비어 있습니다.");
                    }
                    break;

                case 3: 								// 피크
                    try {
                        x = que.peek();
                        System.out.println("피크한 데이터는 " + x + "입니다.");
                    } catch (Queue.EmptyQueueException e) {
                        System.out.println("큐가 비어 있습니다.");
                    }
                    break;

                case 4:								// 덤프
                    que.dump();
                    break;

                case 5:								// 검색
                    System.out.print("검색할 데이터 : ");
                    x = stdIn.next();
                    int n = que.search(x);
                    if (n >= 0)
                        System.out.println("앞에서 " + (que.size() - n) +"번째에 있습니다.");
                    else
                        System.out.println("그 데이터가 없습니다.");
                    break;

                case 6:								// 비웁니다
                    que.clear();
                    break;

                case 7:								// 데이터 출력
                    System.out.println("용량 : " + que.getCapacity());
                    System.out.println("데이터수 : " + que.size());
                    System.out.println("비어 " + (que.isEmpty()	? "있습니다."
                            : "있지 않습니다."));
                    System.out.println("가득 차 " + (que.isFull() ? "있습니다."
                            : "있지 않습니다."));
                    break;
            }
        }
    }
}

