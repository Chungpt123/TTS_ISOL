package Array_Core;

import java.lang.reflect.Array;
import java.util.Scanner;

public class example2 {
    public static void main(String[] args) {
        int n, kt = 1;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Nhập số phần tử của mảng: ");
            n = sc.nextInt();
        }while(n <= 0);
        int [] Arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("A[" + i + "] = ");
            Arr[i] = sc.nextInt();
        }
        sortASC(Arr);
        System.out.println("Dãy số được sắp xếp tăng dần: ");
        show(Arr);
    }
    public static void sortASC(int [] arr) {
        int temp = arr[0];
        for (int i = 0 ; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
    public static void show(int [] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
