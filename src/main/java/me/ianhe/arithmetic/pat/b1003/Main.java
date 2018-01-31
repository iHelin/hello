package me.ianhe.arithmetic.pat.b1003;

import java.util.Scanner;

/**
 * 1003. 我要通过！(20)
 * <p>
 * 时间限制
 * 400 ms
 * 内存限制
 * 65536 kB
 * 代码长度限制
 * 8000 B
 * 判题程序
 * Standard
 * 作者
 * CHEN, Yue
 * “答案正确”是自动判题系统给出的最令人欢喜的回复。本题属于PAT的“答案正确”大派送 —— 只要读入的字符串满足下列条件，系统就输出“答案正确”，否则输出“答案错误”。
 * <p>
 * 得到“答案正确”的条件是：
 * <p>
 * 1. 字符串中必须仅有P, A, T这三种字符，不可以包含其它字符；
 * 2. 任意形如 xPATx 的字符串都可以获得“答案正确”，其中 x 或者是空字符串，或者是仅由字母 A 组成的字符串；
 * 3. 如果 aPbTc 是正确的，那么 aPbATca 也是正确的，其中 a, b, c 均或者是空字符串，或者是仅由字母 A 组成的字符串。
 * <p>
 * 现在就请你为PAT写一个自动裁判程序，判定哪些字符串是可以获得“答案正确”的。
 * 输入格式： 每个测试输入包含1个测试用例。第1行给出一个自然数n (<10)，是需要检测的字符串个数。接下来每个字符串占一行，字符串长度不超过100，且不包含空格。
 * <p>
 * 输出格式：每个字符串的检测结果占一行，如果该字符串可以获得“答案正确”，则输出YES，否则输出NO。
 * <p>
 * 输入样例：
 * 8
 * PAT
 * PAAT
 * AAPATAA
 * AAPAATAAAA
 * xPATx
 * PT
 * Whatever
 * APAAATAA
 * 输出样例：
 * YES
 * YES
 * YES
 * YES
 * NO
 * NO
 * NO
 * NO
 *
 * @author iHelin
 * @since 2018/1/30 16:20
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine().trim();
            String news = s;
            if (news.contains("P") && news.contains("A") && news.contains("T")) {
                news = news.replace("A", "");
                news = news.replace("P", "");
                news = news.replace("T", "");
                news = news.replace("\\s+", "");
                if (news.isEmpty()) {
                    int p = s.indexOf("P");
                    int t = s.indexOf("T");
                    int len = s.length();
                    int b = t - p - 1;
                    int c = len - t - 1;
                    if (p * b == c) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                } else {
                    System.out.println("NO");
                }
            } else {
                System.out.println("NO");
            }
        }
    }
}
