package java_hw.hw2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class task {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:/GB projects/java_hw/hw2/input.txt"));
        String str;
        double a = 0;
        int b = 0;
        StringBuilder sbWithA  = new StringBuilder();
        StringBuilder sbWithB = new StringBuilder();

        while ((str = br.readLine()) != null) {
            if (str.charAt(0) == 'a'){
                sbWithA.append(str);
            } else if (str.charAt(0) == 'b') {
                sbWithB.append(str);
            }
        }
        br.close();

        a = Double.parseDouble(sbWithA.toString().split(" ")[1]);
        b =  Integer.parseInt(sbWithB.toString().split(" ")[1]);

        double c = power(a, b);
        String toWrite = Double.toString(c);

        FileWriter fw = new FileWriter("C:/GB projects/java_hw/hw2/output.txt", false);
        fw.write(toWrite);
        fw.close();

        





    }
    static double power (double a, int b){
        double res = 0;
        if (a == 0 && b != 0){
            res = 0;
        } else if (a == 0 && b ==0){
            throw new RuntimeException();
        } else if (a != 0 && b ==0){
            res = 1;
        } else if (a!=0 && b > 0 ){
            res = a;
            for (int i = 1; i < b; i ++ ){
                res *= a;
            }
            
        } else if (a!=0 && b < 0) {
            res = 1/a;
            for (int i = -1; i > b; i -- ){
                res /= a;
            }
            
        }
        return res;
    }
}
