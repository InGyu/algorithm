import java.util.Arrays;
import java.util.Scanner;

public class StringSearch {
    static int bfMatchLast(String txt, String pat) {
        int pt = 0;
        int pp = 0;
        int temp = -1;
        while(pt != txt.length() && pp != pat.length()) {
            if(txt.charAt(pt) == pat.charAt(pp)) {
                pt++;
                pp++;
            }
            else {
                pt = pt - pp + 1;
                pp = 0;
            }
            if (pp == pat.length()) {
                temp = pt - pp;
                pp = 0;
            }
        }

        return temp;
    }

    static int kmpMatch(String txt, String pat) {
        int pt = 1;
        int pp = 0;
        int[] skip = new int[pat.length() + 1];

        skip[pt] = 0;
        while (pt != pat.length()) {
            if (pat.charAt(pt) == pat.charAt(pp))
                skip[++pt] = ++pp;
            else if (pp == 0)
                skip[++pt] = pp;
            else
                pp = skip[pp];
        }

        pt = pp = 0;
        while (pt != txt.length() && pp != pat.length()) {
            if (txt.charAt(pt) == pat.charAt(pp)) {
                pt++;
                pp++;
            } else if (pp == 0)
                pt++;
            else
                pp = skip[pp];
        }

        if(pp == pat.length())
            return pt - pp;
        return -1;
    }

    static int bmMatch(String txt, String pat) {
        int pt;
        int pp;
        int txtLen = txt.length();
        int patLen = pat.length();
        int[] skip = new int[Character.MAX_VALUE + 1];

        for (pt = 0; pt <= Character.MAX_VALUE; pt++)
            skip[pt] = patLen;
        for (pt = 0; pt < patLen - 1; pt++)
            skip[pat.charAt(pt)] = patLen - pt - 1;
        System.out.println(Arrays.toString(skip));
        while (pt < txtLen) {
            pp = patLen -1;

            while (txt.charAt(pt) == pat.charAt(pp)) {
                if (pp == 0)
                    return pt;
                pp--;
                pt--;
            }

            pt += (skip[txt.charAt(pt)] > patLen - pp) ? skip[txt.charAt(pt)] : patLen - pp;
        }

        return  -1;
    }

    public static void main(String[] args) {

        Scanner stdIn = new Scanner(System.in);

        System.out.print("텍스트 : ");
        String s1 = stdIn.next();

        System.out.print("패턴 : ");
        String s2 = stdIn.next();

        int idx = bmMatch(s1, s2);


        if(idx == -1)
            System.out.println("텍스트에 패턴이 없습니다.");
        else {
            int len = 0;
            for (int i = 0; i < idx; i++)
                len += s1.substring(i, i+1).getBytes().length;
            len += s2.length();

            System.out.println((idx + 1) + "번째 문자부터 일치합니다.");
            System.out.println("텍스트 : " + s1);
            System.out.printf(String.format("패턴 : %%%ds\n", len), s2);
        }
    }
}
