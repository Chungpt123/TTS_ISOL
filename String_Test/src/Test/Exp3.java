package Test;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Exp3 {
    public static String chuyenInHoa(String str){
        String s,strOutput;
        s= str.substring(0, 1);
        strOutput= str.replaceFirst(s,s.toUpperCase());
        return (strOutput);
    }
    public static String chuanHoa(String strInput){
        String strOutput="";
        StringTokenizer strToken= new StringTokenizer(strInput," ,\t,\r");
        strOutput+=""+chuyenInHoa(strToken.nextToken());
        while(strToken.hasMoreTokens()){
            strOutput+=" "+chuyenInHoa(strToken.nextToken());
        }
        return(strOutput);
    }

    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.println("Nhap vao 1 xau: ");
        String strInput= input.nextLine();
        System.out.println("Xau duoc chuan hoa la: "+chuanHoa(strInput));

    }
}
