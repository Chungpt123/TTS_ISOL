package Array_Core;

import java.util.Scanner;

public class example11 {
    public static void main(String[] args) {
        int m, n;
        int[][] arr, brr;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap  n: ");
        n = scanner.nextInt();
        System.out.println("Nhap ma tran a[][]: ");
        arr = nhap(n, n, scanner);// goi phuong thuc nhap
        System.out.println("Tổng các phần tử đường chéo chính và đường chéo phụ:" +Tong(arr, n));


    }
    private static int[][] nhap(int m, int n, Scanner scanner) {
        int[][] x = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                x[i][j] = scanner.nextInt();
            }
        }
        return x;
    }
    private static void show(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static double Tong(int[][] arr, int n){
        int s = 0;
        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                if(i + j == n+ 1){
                    s = s + arr[i][j];
                }
            }
        }
        return s;
    }

}
