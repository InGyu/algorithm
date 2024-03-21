import com.sun.org.apache.xml.internal.utils.IntStack;

import java.util.Arrays;
import java.util.Scanner;
public class BubbleSort {
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }

    static void selectionSort(int[] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if(a[j] < a[min])
                    min = j;
            swap(a, i ,min);

        }
    }

    static void insertionSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int key = a[i];
            int pl = 0;
            int pr = i - 1;
            int pc;
            int pd;

            do {
                pc = (pl+pr)/2;
                if (a[pc] == key)
                    break;
                else if(a[pc] < key)
                    pl = pc + 1;
                else
                    pr = pc - 1;
            }while (pl <= pr);

            pd = (pc <= pr) ? pc + 1 : pr + 1;

            for (int j = 1; j > pd; j--) {
                a[j] = a[j - 1];
            }
            a[pd] =key;
        }
    }

    static void shellSort(int[] a, int n) {
        int h;
        for (h = 1; h < n;  h = h * 3 + 1);

        for (; h > 0; h /= 3)
            for (int i = h; i < n; i++) {
                int j;
                int tmp = a[i];
                for (j = i - h; j >= 0 && a[j] > tmp; j -= h)
                    a[j + h] = a[j];
                a[j + h] = tmp;

            }
    }

    static void quickSort(int[] a, int left, int right) {
        int pl = left;
        int pr = right;
        int x = a[(pl + pr) / 2];

        System.out.printf("a[%d] ~ a[%d} : {", left, right);
        for (int i = left; i < right; i++) {
            System.out.printf("%d, ", a[i]);
        }
        System.out.printf("%d}\n", a[right]);

        do {
            while (a[pl] < x)
                pl++;
            while (a[pr] > x)
                pr--;
            if(pl <= pr)
                swap(a, pl++, pr--);
        }while(pl <= pr);

        if (left < pr)
            quickSort(a, left, pr);
        if (pl < right)
            quickSort(a, pl, right);
    }

    static void quickSort2(int[] a, int left, int right) {
        IntStack lstack = new IntStack(right - left + 1);
        IntStack rstack = new IntStack(right - left + 1);

        lstack.push(left);
        rstack.push(right);

        while (!lstack.empty()) {
            int pl = left = lstack.pop();
            int pr = right = rstack.pop();
            int x= a[(left + right) / 2];
            do {
                while (a[pl] < x)
                    pl++;
                while (a[pr] > x)
                    pr--;
                if(pl <= pr)
                    swap(a, pl++, pr--);
            }while(pl <= pr);

            if (left < pr) {
                lstack.push(left);
                rstack.push(pr);
            }
            if(pl < right) {
                lstack.push(pl);
                rstack.push(right);
            }

        }
    }

    static int sort3elem(int[] x, int a, int b, int c) {
        if(x[b] < x[a])
            swap(x, b, a);
        if(x[c] < x[b])
            swap(x, c, b);
        if(x[b] < x[a])
            swap(x, b, a);
        return b;
    }

    static void quickSort3(int[] a, int left, int right) {
        int pl = left;
        int pr = right;
        int m = sort3elem(a, pl, (pl + pr) / 2, pr);
        int x = a[m];

        swap(a, m, right - 1);
        pl++;
        pr -= 2;


        do {
            while(a[pl] < x)
                pl++;
            while (a[pr] > x)
                pr--;
            if(pl <= pr)
                swap(a, pl++, pr--);
        }while (pl <= pr);

        if(left < pr)
            quickSort3(a, left, pr);
        if(pl < right)
            quickSort(a, pl, right);
    }

    static int[] buff;
    static void __mergeSort(int[] a, int left, int right) {
        if(left < right) {
            int i;
            int center = (left + right) / 2;
            int p = 0;
            int j = 0;
            int k = left;

            __mergeSort(a, left, center);
            __mergeSort(a, center + 1, right);

            for (i = left; i <= center; i++) {
                buff[p++] = a[i];
            }
            while (i <= right && j < p)
                a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
            while (j < p)
                a[k++] = buff[j++];
        }
    }

    static void mergeSort(int[] a, int n) {
        buff = new int[n];

        __mergeSort(a, 0, n - 1);

        buff = null;
    }

    static void downHeap(int[] a, int left, int right) {
        int temp = a[left];
        int child;
        int parent;

        for (parent = left; parent < (right + 1) / 2; parent = child) {
            int cl = parent * 2 + 1;
            int cr = cl + 1;
            child = (cr <= right && a[cr] > a[cl]) ? cr : cl;
            if(temp >= a[child])
                break;
            a[parent] = a[child];
        }
        a[parent] =temp;
    }

    static void heapSort(int[] a, int n) {
        for (int i = (n - 1) / 2; i >= 0; i--) {
            downHeap(a, i , n- 1);
        }
        for (int i = (n - 1); i > 0; i--) {
            swap(a, 0, i);
            downHeap(a, 0, i - 1);
        }
    }

    static void countingSort(int[] a, int n, int max) {
        int[] f = new int[max + 1];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(f));
            f[a[i]]++;
        }
        for (int i = 1; i <= max; i++) {
            System.out.println(Arrays.toString(f));
            f[i] += f[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            System.out.println(Arrays.toString(b));
            b[--f[a[i]]] = a[i];
        }
        for (int i = 0; i < n; i++) {
            a[i] = b[i];
        }
    }


    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("선택정렬");
        System.out.print("요소수 : ");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];
        int[] y = new int[nx];
        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "] : ");
            x[i] = stdIn.nextInt();
            y[i] = x[i];
        }
        int max = x[0];
        for (int i = 1; i < nx; i++) {
            if(max < x[i])
                max = x[i];
        }
        countingSort(x, nx, max);
        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++) {
            System.out.println("x[" + i + "] : " + x[i]);
        }
    }
}
