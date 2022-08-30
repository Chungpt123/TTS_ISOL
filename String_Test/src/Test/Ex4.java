package Test;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex4 {
    public static void timXauMax(String strInput){
        StringTokenizer strToken= new StringTokenizer(strInput," ,\t,\r");
        int Max,i=1,lengthStr;
        Max= strToken.nextToken().length();
        int viTriMax= i;
        while(strToken.hasMoreTokens()){
            lengthStr= strToken.nextToken().length();
            i++;
            if(Max < lengthStr){
                Max= lengthStr;
                viTriMax= i;
            }
        }
        System.out.println("Độ dìa xâu lớn nhất là: "+Max+" ở vị trí "+viTriMax);
    }

    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.println("Nhập vào 1 xâu: ");
        String strInput= input.nextLine();
        timXauMax(strInput);

    }
}
