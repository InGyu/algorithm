
import java.util.*;
public class LinkedListTester {
    static Scanner stdIn = new Scanner(System.in);

    static class Data {
        static final int NO = 1;
        static final int NAME = 2;
        private Integer no;
        private String name;

        public String toString() {
            return "("+ no +") " + name;
        }

        void scanData(String guide, int sw) {
            System.out.println(guide + "할 데이터를 입력하세요");

            if((sw & NO) == NO) {
                System.out.print("번호 : ");
                no = stdIn.nextInt();
            }
            if((sw & NAME) == NAME) {
                System.out.print("이름 : ");
                name = stdIn.next();
            }
        }

        public static final Comparator<Data> NO_ORDER = new NoOrderComparator();

        private static class NoOrderComparator implements Comparator<Data> {
            public int compare(Data d1, Data d2) {
                return d1.no.compareTo(d2.no);
            }
        }


        public static final Comparator<Data> NAME_ORDER = new NameOrderComparator();

        private static class NameOrderComparator implements Comparator<Data> {
            public int compare(Data d1, Data d2) {
                return d1.name.compareTo(d2.name);
            }
        }
    }

    enum Menu {
        ADD_FIRST("머리에 노드를 삽입"),
        ADD_LAST("꼬리에 노드를 삽입"),
        RMV_FIRST("머리 노드를 삭제"),
        RMV_LAST("꼬리 노드를 삭제"),
        RMV_CRNT("선택 노드를 삭제"),
        CLEAR("모든 노드를 삭제"),
        SEARCH_NO("번호로 검색"),
        SEARCH_NAME("이름으로 검색"),
        NEXT("선택 노드를 하나 뒤쪽으로 진행"),
        PRINT_CRNT("선택 노드를 출력"),
        DUMP("모든 노드를 출력"),
        PURGE_NO(   "동일 번호의 노드를 삭제"),
        PURGE_NAME( "동일 이름의 노드를 삭제"),
        RETRIEVE(   "특정 노드를 출력"),
        TREMINATE("종료");

        private final String message;

        static Menu MenuAt(int idx) {
            for (Menu m : Menu.values())
                if(m.ordinal() == idx)
                    return m;
            return null;
        }

        Menu(String string) {
            message = string;
        }

        String getMessage() {
            return message;
        }
    }

    static Menu SelectMenu() {
        int key;
        do {
            for (Menu m : Menu.values()) {
                System.out.printf("(%d) %s ", m.ordinal(), m.getMessage());
                if((m.ordinal() % 3) == 2 && m.ordinal() != Menu.TREMINATE.ordinal())
                    System.out.println();
            }
            System.out.print(" : ");
            key = stdIn.nextInt();
        }while (key < Menu.ADD_FIRST.ordinal() || key > Menu.TREMINATE.ordinal());
        return Menu.MenuAt(key);
    }

    public static void main(String[] args) {
        Menu menu;
        Data data;
        Data ptr;
        Data temp = new Data();

        LinkedList<Data> list = new LinkedList<Data>();

        do {
            switch (menu = SelectMenu()) {

                case ADD_FIRST:
                    data =new Data();
                    data.scanData("머리에 삽입", Data.NO | Data.NAME);
                    list.addFirst(data);
                    break;
                case ADD_LAST:
                    data = new Data();
                    data.scanData("꼬리에 삽입", Data.NO | Data.NAME);
                    list.addLast(data);
                    break;
                case RMV_FIRST:
                    list.removeFirst();
                    break;
                case RMV_LAST:
                    list.removeLast();
                    break;
                case RMV_CRNT:
                    list.removeCurrentNode();
                    break;
                case SEARCH_NO:
                    temp.scanData("검색", Data.NO);
                    ptr = list.serach(temp, Data.NO_ORDER);
                    if(ptr == null)
                        System.out.println("그 번호의 데이터가 없습니다.");
                    else
                        System.out.println("검색 성공 : " + ptr);
                    break;
                case SEARCH_NAME:
                    temp.scanData("검색", Data.NAME);
                    ptr = list.serach(temp, Data.NAME_ORDER);
                    if(ptr == null)
                        System.out.println("그 이름의 데이터가 없습니다.");
                    else
                        System.out.println("검색 성공 : " + ptr);
                    break;
                case NEXT:
                    list.next();
                    break;
                case PRINT_CRNT:
                    list.printCurrentNode();
                    break;
                case DUMP:
                    list.dump();
                    break;
                case CLEAR:
                    list.clear();
                    break;
                case PURGE_NO:
                    list.purge(Data.NO_ORDER);
                    break;
                case PURGE_NAME:
                    list.purge(Data.NAME_ORDER);
                    break;
                case RETRIEVE : {						//【연습8-2】특정 노드를 출력
                    System.out.print("머리부터 몇 번째 데이터를 출력할까요? : ");
                    int no = stdIn.nextInt() - 1;
                    ptr = list.retrieve(no);
                    if (ptr == null)
                        System.out.println("그 데이터는 존재하지 않습니다.");
                    else
                        System.out.println("그 데이터는 " + ptr.toString() + "입니다.");
                    break;
                }
            }
        } while (menu != Menu.TREMINATE);
    }
}
