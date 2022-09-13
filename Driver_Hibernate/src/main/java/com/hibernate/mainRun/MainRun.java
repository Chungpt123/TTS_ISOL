package com.hibernate.mainRun;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.hibernate.dao.DriverDao;
import com.hibernate.dao.DriverDaoImpl;
import com.hibernate.entities.Driver;

public class MainRun {
	static DriverDao driverDao = new DriverDaoImpl();
	public static void main(String[] args) {
		menu();
		
	}
	 private static void menu() {
		    do {
		      System.out.println("PHẦN MỀM QUẢN LÝ LÁI XE\n");
		      System.out.println("1. Hiển thị danh sách lái xe.");
		      System.out.println("2. Hiển thị thông tin một sinh viên.");
		      System.out.println("3. Thêm mới một sinh viên.");
		      System.out.println("4. Sửa thông tin một sinh viên.");
		      System.out.println("5. Xóa tin một sinh viên.");
		      System.out.println("6. Thoát.");
		      System.out.println("---------------------------------");
		      System.out.print("Xin mời chọn chức năng: ");
		      int choice = 0;
		      do {
		        int temp = new Scanner(System.in).nextInt();
		        if (temp > 0 && temp < 7) {
		          choice = temp;
		          break;
		        }
		        System.out.print("Chức năng vừa chọn không tồn tại, yêu cầu nhập lại: ");
		      } while (true);

		      switch (choice) {
		        case 1:
		          getAll();
		          break;
		        case 2:
		          viewById();
		          break;
		        case 3:
		          addNew();
		          break;
		        case 4:
		          updateDriver();
		          break;
		        case 5:
		          deleteDriver();
		          break;
		        case 6:
		          System.exit(0);
		      }
		    } while (true);
		  }


		  private static void getAll() {
		    List<Driver> drivers = driverDao.getAll();
		    if (!(drivers == null || drivers.isEmpty())) {
		      System.out.println("Danh sách lái xe: ");
		      for (Driver driver : drivers) {
				System.out.println(driver.toString());
		      }
		    } else {
		      System.out.println("Không có bản ghi nào trong CSDL");
		    }
		  }

		  private static void viewById() {
		    System.out.print("Nhập mã lái xe muốn xem thông tin: ");
		    int id = new Scanner(System.in).nextInt();
		    Driver driver = driverDao.getOneById(id);
		    if (driver != null) {
		      System.out.println(driver);
		    } else {
		      System.out.println("Không tìm thấy lái xe mang mã " + id);
		    }
		  }

		  private static void addNew() {
		    System.out.println("Nhập thông tin cho lái xe mới: ");

		    System.out.print("Nhập họ và tên của sinh viên:");
		    String fullName = new Scanner(System.in).nextLine();
		    System.out.print("Nhập số điện thoại lái xe:");
		    String phone = new Scanner(System.in).nextLine();
		    System.out.print("Nhập email lái xe:");
		    String email = new Scanner(System.in).nextLine();
		    System.out.print("Nhập kinh nghiệm lái xe:");
		    int level = new Scanner(System.in).nextInt();
//		    Date birthday = null;
//		    try {
//		      birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birtdayString);
//		    } catch (ParseException e) {
//		      logger.error(e);
//		    }
		    boolean addingResult = driverDao.addNew(new Driver(fullName, phone, email, level));

		    if (addingResult) {
		      System.out.println("Thêm mới thành công!");
		    } else {
		      System.out.println("Thêm mới thất bại!");
		    }
		  }


		  private static void updateDriver() {
		    System.out.print("Nhập mã của lái xe muốn sửa thông tin: ");
		    int id = new Scanner(System.in).nextInt();

		    Driver driver = driverDao.getOneById(id);
		    if (driver != null) {
		    	System.out.print("Nhập họ và tên của sinh viên: ");
			    driver.setName(new Scanner(System.in).nextLine()); 
			    System.out.print("Nhập số điện thoại lái xe:");
			    driver.setPhone(new Scanner(System.in).nextLine());
			    System.out.print("Nhập email lái xe:");
			    driver.setEmail(new Scanner(System.in).nextLine());
			    System.out.print("Nhập kinh nghiệm lái xe:");
			    driver.setLevel(new Scanner(System.in).nextInt());
			    
		      
		      boolean updateResult = driverDao.update(driver);

		      if (updateResult) {
		        System.out.println("Cập nhật thành công!");
		      } else {
		        System.out.println("cập nhật thất bại!");
		      }
		    } else {
		      System.out.println("Không tìm thấy sinh viên mang mã " + id);
		    }
		  }

		  private static void deleteDriver() {
		    System.out.print("Nhập mã lái xe muốn xóa: ");
		    int id = new Scanner(System.in).nextInt();
		    Driver driver = driverDao.getOneById(id);
		    boolean deleteResult = driverDao.delete(driver);
		    if (deleteResult) {
		      System.out.println("Xóa thành công!");
		    } else {
		      System.out.println("Xóa thất bại!");
		    }
		  }

}
