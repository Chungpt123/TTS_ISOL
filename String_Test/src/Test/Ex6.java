package Test;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex6 {
    public static void main(String[] args) {
        System.out.println("Xin moi nhap chuoi can sap xep");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
//        boolean check;
//        do {
//            System.out.println("Xin mời nhập xâu:");
//            String s = sc.nextLine();
//             check = check(s);
//
//        }while(check == false);
        System.out.println("Xau sau khi sap xep la");
        tach(str);

    }
    private static boolean check(String s){
        StringTokenizer st = new StringTokenizer(s);
        if(st.countTokens() < 20){
            while (st.hasMoreTokens()){
                if(st.nextToken().length() > 10){
                    return false;
                }
            }
        }else {
            return false;
        }

        return  true;
    }
     static void tach(String str){
        String [] s;
        s = str.split(" ");
        for(int i = 0; i < (s.length - 1); i++){
            for(int j = i+1; j < s.length; j++){
                    if(s[i].compareToIgnoreCase(s[j]) > 0){
                    String temp = s[i];
                    s[i] = s[j];
                    s[j] = temp;
                }
            }
        }
        xuat(s);
    }
    static void xuat(String[] s){
        for (String s1:s) {
            System.out.println(s1+" ");
        }
    }

}
