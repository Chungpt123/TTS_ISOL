package Array_Core;

import java.util.Scanner;

public class example3 {
    public static void main(String[] args) {
        int n, kt = 1, count = 1;
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
        for (int i = 0; i < n; i++) {
            for (int j = i+ 1; j <n; j++){
                if(Arr[i] == Arr[j]){
                    count ++;
                }
            }
            System.out.println("Số phần tử " + Arr[i] + " có trong mảng = " + count);
        }
    }

}
