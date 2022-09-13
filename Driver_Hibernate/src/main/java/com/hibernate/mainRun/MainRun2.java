package com.hibernate.mainRun;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.hibernate.service.BusLineSevice;
import com.hibernate.service.DriverManagementService;
import com.hibernate.service.DriverService;

public class MainRun2 {
	public static final DriverService driverService = new DriverService();
    public static final BusLineSevice busLineService = new BusLineSevice();
    public static final DriverManagementService driverManagementService = new DriverManagementService();

    public static void main(String[] args) {
        init();
        menu();
    }

    private static void init() {
        driverService.init();
        busLineService.init();
        driverManagementService.init();
    }

    private static void menu() {
        while (true) {
            showMenu();
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    driverService.createNew();
                    break;
                case 2:
                    driverService.showAll();
                    break;
                case 3:
                    busLineService.createNew();
                    break;
                case 4:
                    busLineService.showAll();
                    break;
                case 5:
                    driverManagementService.createManagement();
                    break;
                case 6:
                	driverManagementService.sort();
                	break;
                case 7:
                	driverManagementService.showAll();
                  break;	
                case 8:            	
                	return;
                
            }
        }
    }

    private static int functionChoice() {
        System.out.println("Xin mời nhập lựa chọn: ");
        int choice = -1;
        do {
            try {
                choice = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Giá trị cần nhập là một số nguyên, vui lòng nhập lại: ");
                continue;
            }
            if (choice >= 1 && choice <= 8) {
                return choice;
            }
            System.out.println("Giá trị lựa chọn không tồn tại, vui lòng nhập lại: ");
        } while (true);
    }

    private static void showMenu() {
        System.out.println("\n\n\n-------------PHẦN MỀM QUẢN LÝ PHÂN CÔNG LÁI XE BUÝT-------------");
        System.out.println("1. Nhập danh sách lái xe mới.");
        System.out.println("2. Hiển thị danh sách lái xe.");
        System.out.println("3. Nhập danh sách tuyến xe mới.");
        System.out.println("4. Hiển thị danh sách tuyến xe.");
        System.out.println("5. Lập bảng phân công lái xe");      
        System.out.println("6. Sắp xếp danh sách phân công");
        System.out.println("7. Lập bảng thống kê khoảng cách chạy xe trong ngày");
        System.out.println("8. Kết thúc chương trình");
    }
}
