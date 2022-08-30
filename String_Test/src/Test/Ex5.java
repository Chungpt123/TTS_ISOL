package Test;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Xin mời nhập họ và tên:");
        String str = sc.nextLine();
        System.out.println("Ho va ten duoc sap xep lai (ten- ho- dem) "+doiViTri(str));

    }
    public static String doiViTri(String str){
        StringTokenizer strToken= new StringTokenizer(str," ");
        String ho    = strToken.nextToken();
        String hoDem = strToken.nextToken();
        String ten   = strToken.nextToken();
        String strOutput= ten+" "+ho+" "+hoDem;
        return(strOutput);
    }
}
