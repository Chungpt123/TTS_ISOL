package Test;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Exp7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Xin mời nhập hai chuỗi kiểm tra: ");
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        if(s1.contains(s2)){
            System.out.println(s1.replace(s2,""));
        }else{
            System.out.println("Không tìm thấy chuỗi trùng lặp");
        }

    }
}
