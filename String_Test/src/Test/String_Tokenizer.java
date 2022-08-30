package Test;

import java.util.StringTokenizer;

public class String_Tokenizer {
    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer("Toi-ten-la-chung","-");
        System.out.println("Tổng số token: " + st.countTokens());
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
//    public static void main(String args[]) {
//        StringTokenizer st = new StringTokenizer("Toi-ten-,la-VietTut", "-,", false);
//        System.out.println("Tổng số token: " + st.countTokens());
//        // in chuỗi token dựa trên dấu phân cách
//        System.out.println("Chuỗi token: " + st.nextToken("-,"));
//        System.out.println("Chuỗi token kế tiếp: " + st.nextToken("e"));
//    }
}
