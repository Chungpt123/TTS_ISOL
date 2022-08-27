package entity;


import java.util.Scanner;

public class Customer {

    // dùng hằng số static final
    public static final String SINH_VIEN = "Sinh viên";
    public static final String HOC_VIEN = "Học viên";
    public static final String GIAO_VIEN = "Giáo viên";

    private static int AUTO_ID = -1;
    private int id;
    private String name;
    private String address;
    private String phone;
    private String customerCategory;


    public Customer() {
        if (AUTO_ID == -1) {
            AUTO_ID = 10000;
            this.id = AUTO_ID;
            return;
        }
        this.id = ++AUTO_ID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(String customerCategory) {
        this.customerCategory = customerCategory;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", customerCategory='" + customerCategory + '\'' +
                '}';
    }

    public void inputInfo() {
        System.out.print("Nhập tên cho khách hàng: ");
        this.name = new Scanner(System.in).nextLine();
        System.out.print("Nhập địa chỉ cho khách hàng: ");
        this.address = new Scanner(System.in).nextLine();
        System.out.print("Nhập SĐT cho khách hàng: ");
        this.phone = new Scanner(System.in).nextLine();
        System.out.println("Nhập nhóm khách hàng là 1 trong các lựa chọn dưới đây: ");
        System.out.println("1. Học sinh");
        System.out.println("2. Sinh Viên");
        System.out.println("3. Giáo viên");
        System.out.print("Xin mời nhập lựa chọn của bạn: ");
        int choice = -1;
        do {
            choice = new Scanner(System.in).nextInt();
            if (choice >= 1 && choice <= 3) {
                break;
            }
            System.out.print("Lựa chọn loại khách hàng không hợp lệ, vui lòng chọn lại: ");
        } while (choice > 3 || choice < 1);
        switch (choice) {
            case 1:
                this.customerCategory = Customer.HOC_VIEN;
                break;
            case 2:
                this.customerCategory = Customer.SINH_VIEN;
                break;
            case 3:
                this.customerCategory = Customer.GIAO_VIEN;
                break;
        }
    }
}
