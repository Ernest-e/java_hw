

/**
 * Написать программу вычисления n-ого треугольного числа
 */

package java_hw.hw1;

import java.util.Scanner;

public class task1 {
    public static void main (String[] args){
        int n = input();
        int res = trianNum(n);
        System.out.println(res);

    }
    static int input(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input a number: ");
        int n = in.nextInt();
        in.close();
        return n;
    } 

    static int trianNum(int n){
        return n*(n+1)/2;
    }
}
