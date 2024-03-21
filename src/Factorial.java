import java.util.Scanner;

public class Factorial {
    int factorial(int n){
        int temp = 1;
        for (int i = n; i > 0; i--)
        {
            temp *= i;
        }
        return temp;
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Factorial f = new Factorial();
        System.out.print("정수를 입력하세요 : ");
        int x = stdIn.nextInt();

        System.out.println(x + "의 팩토리얼은 " + f.factorial(x) + "입니다.");
    }
}
