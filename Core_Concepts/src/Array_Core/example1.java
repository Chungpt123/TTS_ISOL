package Array_Core;

import java.util.Scanner;

public class example1 {
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
        for (int i = 0; i < n / 2; i++) {
            if (Arr[i] != Arr[n - i - 1]) {
                kt = 0;
                break;
            }
        }

        if (kt == 0) {
            System.out.println("Mảng một chiều vừa nhập không là mảng đối xứng");
        } else {
            System.out.println("Mảng một chiều vừa nhập là mảng đối xứng");
        }
    }
}
