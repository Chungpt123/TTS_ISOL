import entity.Book;
import entity.Customer;
import entity.LoanSlip;
import entity.LoanslipDetail;

import java.util.Scanner;

public class MainRun {
    private static Customer[] customers = new Customer[100];
    private static Book[] books = new Book[100];
    private static LoanSlip[] loanSlips = new LoanSlip[100];

    public static void main(String[] args) {
        showMenu();
    }

    private static void showMenu() {
        while (true) {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    inputNewBook();
                    break;
                case 2:
                    showAllBook();
                    break;
                case 3:
                    inputNewCustomer();
                    break;
                case 4:
                    showAllCustomer();
                    break;
                case 5:
                    createLoanSlip();
                    showAllLoanSlip();
                    break;
                case 6:
                    sortLoanSlipByCustomerName();
                    break;
                case 7:
                    sortLoanSlipByQuatity();
                    break;
                case 8:
                    search99();
                    break;
                case 9:
                    System.out.println("Xin cảm ơn đã sử dụng phần mềm của chúng tôi!");
                    return;
            }
        }
    }

    private static LoanSlip searchLoanSlipByCustomer(String name) {
        for (int i = 0; i < loanSlips.length; i++) {
            LoanSlip loanSlip = loanSlips[i];
            if (loanSlips != null && loanSlip.getCustomer().getName().equalsIgnoreCase(name)) {
                return loanSlip;
            }
        }
        return null;
    }

    private static void search99() {
        System.out.print("Xin mời nhập tên khách hàng: ");
        String name;
        LoanSlip loanSlip;
        do {
            name = new Scanner(System.in).nextLine();
            // thực hiện tìm kiếm khách hàng theo name vừa nhập xem có khách hàng trong hệ thống hay không
            // nếu có thì oke, nếu KHÔNG thì bắt người dùng nhập lại
            loanSlip = searchLoanSlipByCustomer(name);
            if (loanSlip != null) {
//                LoanslipDetail [] lsD = loanSlip.getLoanslipDetails();
//                for (int i = 0; i < lsD.length; i++){
//                    System.out.println(lsD[i]);
//                }
                break;
            }
            System.out.print("Không tìm thấy khách hàng có name là" + name + ", vui lòng nhập lại: ");
        } while (true);

    }

    private static void sortLoanSlipByQuatity() {
        // kiểm tra xem trong ORDER đã có dữ liệu hay chưa
        boolean coDuLieuPhieuMuon = false;
        for (int i = 0; i < loanSlips.length; i++) {
            if (loanSlips[i] != null) {
                coDuLieuPhieuMuon = true;
            }
        }
        if (!coDuLieuPhieuMuon) {
            System.out.println("Chưa có phiếu nào trong hệ thống, vui lòng tạo phiếu mượn trước khi thực hiện tính năng này.");
            return;
        }
        sapXepPhieuMuon2();
        showAllLoanSlip();
    }
    private static void sapXepPhieuMuon2() {
        for (int i = 0; i < loanSlips.length - 1; i++) {
            LoanSlip ls1 = loanSlips[i];
            for (int j = i + 1; j < loanSlips.length; j++) {
                LoanSlip ls2 = loanSlips[j];
                if ( count(ls1.getLoanslipDetails()) > count(ls2.getLoanslipDetails())) {
                    LoanSlip temp = ls1;
                    ls1 = ls2;
                    ls2 = temp;
                }
            }
        }
    }
    private static int count(LoanslipDetail [] abs){
        int count = 0;
        for(int i =0 ; i < abs.length; i++){
            count = count + abs[i].getQuantity();
        }
        return count ;
    }

    private static void sortLoanSlipByCustomerName() {
        // kiểm tra xem trong ORDER đã có dữ liệu hay chưa
        boolean coDuLieuPhieuMuon = false;
        for (int i = 0; i < loanSlips.length; i++) {
            if (loanSlips[i] != null) {
                coDuLieuPhieuMuon = true;
            }
        }
        if (!coDuLieuPhieuMuon) {
            System.out.println("Chưa có phiếu nào trong hệ thống, vui lòng tạo phiếu mượn trước khi thực hiện tính năng này.");
            return;
        }
        sapXepPhieuMuon();
        showAllLoanSlip();
    }

    private static void sapXepPhieuMuon() {
        for (int i = 0; i < loanSlips.length - 1; i++) {
            LoanSlip ls1 = loanSlips[i];
            for (int j = i + 1; j < loanSlips.length; j++) {
                LoanSlip ls2 = loanSlips[j];
                if (ls1.getCustomer().getName().compareTo(ls2.getCustomer().getName()) > 0) {
                    LoanSlip temp = ls1;
                    ls1 = ls2;
                    ls2 = temp;
                }
            }
        }
    }



    private static void createLoanSlip() {
        // kiểm tra xem danh sách khách hàng và điện thoại có dữ liệu hay chưa
        if (!checkData()) {
            System.out.println("Chưa có dữ liệu về khách hàng hoặc sách để thao tác, vui lòng nhập khách hàng và sách trước đã!");
            return;
        }

        // 0. nhập số lượng khách hàng muốn mua hàng
        System.out.print("Nhập số lượng khách hàng muốn mượn: ");
        int customerNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < customerNumber; i++) {
            LoanSlip loanSlip = new LoanSlip();
            Customer customer = nhapKhachHang(i);
            // lưu khách hàng vào phiếu mượn
            loanSlip.setCustomer(customer);

            // 2. xác định xem khách hàng này mượn những sách nào
            LoanslipDetail[] loanslipDetails = createLoanslipDetail();
            loanSlip.setLoanslipDetails(loanslipDetails);

            // lưu luôn vào danh sách phiếu mượn của hệ thống
            saveOrder(loanSlip);
        }
    }

    private static void showAllLoanSlip() {
        for (int i = 0; i < loanSlips.length; i++) {
            if (loanSlips[i] == null) {
                continue;
            }
            System.out.println(customers[i]);
        }
    }

    private static void showAllBook() {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                continue;

            }
            System.out.println(books[i]);
        }
    }

    private static void showAllCustomer() {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] == null) {
                continue;
            }
            System.out.println(customers[i]);
        }
    }

    private static void saveBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                break;
            }
        }
    }

    private static void inputNewBook() {
        System.out.print("Nhập số lượng sách mới muốn thêm: ");
        int newPhoneNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < newPhoneNumber; i++) {
            System.out.println("Nhập thông tin cho cuốn sách thứ " + (i + 1));
            // thực hiện nhập thông tin cho book
            Book book = new Book();
            book.inputInfo();

            // lưu vào hệ thống ==> thêm vào mảng books đã khai báo bên trên
            saveBook(book);
        }
    }

    private static void inputNewCustomer() {
        // xử lý nhập khách hàng mới
        System.out.print("Nhập số lượng khách hàng mới muốn thêm: ");
        int newCustomerNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < newCustomerNumber; i++) {
            System.out.println("Nhập thông tin cho khách hàng thứ " + (i + 1));
            // thực hiện nhập thông tin cho customer
            Customer customer = new Customer();
            customer.inputInfo();

            // lưu vào hệ thống ==> thêm vào mảng CUSTOMERS đã khai báo bên trên
            saveCustomer(customer);
        }
    }

    private static void saveCustomer(Customer customer) {
        for (int j = 0; j < customers.length; j++) {
            if (customers[j] == null) {
                customers[j] = customer;
                break;
            }
        }
    }

    private static boolean checkData() {
        boolean coDuLieuCustomers = false;
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null) {
                coDuLieuCustomers = true;
                break;
            }
        }
        boolean coDuLieuBook = false;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                coDuLieuBook = true;
                break;
            }
        }
        return coDuLieuBook && coDuLieuCustomers;
    }

    private static void saveOrder(LoanSlip order) {
        for (int i = 0; i < loanSlips.length; i++) {
            if (loanSlips[i] == null) {
                loanSlips[i] = order;
                return;
            }
        }
    }

    private static Customer searchCustomerById(int idCustomer) {
        for (int i = 0; i < customers.length; i++) {
            Customer customer = customers[i];
            if (customer != null && customer.getId() == idCustomer) {
                return customer;
            }
        }
        return null;
    }

    private static Customer nhapKhachHang(int index) {
        System.out.print("Xin mời nhập mã khách hàng thứ " + (index + 1) + " muốn mua hàng: ");
        int idCustomer;
        Customer customer;
        do {
            idCustomer = new Scanner(System.in).nextInt();
            // thực hiện tìm kiếm khách hàng theo id vừa nhập xem có khách hàng trong hệ thống hay không
            // nếu có thì oke, nếu KHÔNG thì bắt người dùng nhập lại
            customer = searchCustomerById(idCustomer);
            if (customer != null) {
                break;
            }
            System.out.print("Không tồn tại người dùng có ID là " + idCustomer + ", vui lòng nhập lại: ");
        } while (true);
        return customer;
    }

    private static LoanslipDetail[] createLoanslipDetail() {
        System.out.print("Nhập số lượng sách muốn mua: ");
        int bookQuantity = new Scanner(System.in).nextInt();
        LoanslipDetail[] loanslipDetails = new LoanslipDetail[bookQuantity];
        for (int j = 0; j < bookQuantity; j++) {
            LoanslipDetail loanslipDetail = new LoanslipDetail();
            // 2.1. xác định được xem là khách hàng mượn book nào
            // ==> bắt người dùng nhập ID của book muốn mua

            Book book = nhapSach(j);
            // lưu book tìm được vào chi tiết phiếu mượn
            loanslipDetail.setBook(book);

            System.out.print("Nhập số lượng mượn: ");
            int quantity;
            do {
                quantity = new Scanner(System.in).nextInt();
                if (quantity > 0) {
                    break;
                }
                System.out.print("Số lượng muốn mượn phải là số dương, vui lòng nhập lại: ");
            } while (true);
            loanslipDetail.setQuantity(quantity);

            // lưu chi tiết đơn hàng vừa nhập vào đơn hàng
            for (int i = 0; i < loanslipDetails.length; i++) {
                if (loanslipDetails[i] == null) {
                    loanslipDetails[i] = loanslipDetail;
                    break;
                }
            }
        }
        return loanslipDetails;
    }

    private static Book nhapSach(int index) {
        System.out.print("Xin mời nhập ID của cuốn sách thứ " + (index + 1) + " mà người dùng muốn mượn: ");
        int idBook;
        Book book;
        do {
            idBook = new Scanner(System.in).nextInt();
            // thực hiện tìm kiếm khách hàng theo id vừa nhập xem có khách hàng trong hệ thống hay không
            // nếu có thì oke, nếu KHÔNG thì bắt người dùng nhập lại
            book = searchBookById(idBook);
            if (book != null) {
                break;
            }
            System.out.print("Không tồn tại điện thoại có ID là " + idBook + ", vui lòng nhập lại: ");
        } while (true);
        return book;
    }

    private static Book searchBookById(int idBook) {
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            if (book != null && book.getId() == idBook) {
                return book;
            }
        }
        return null;
    }

    private static int functionChoice() {
        System.out.println("\n\n===== PHẦN MỀM QUẢN LÝ MƯỢN SÁCH THƯ VIỆN =====\n\n");
        System.out.println("1. Nhập danh sách đầu sách mới.");
        System.out.println("2. In ra danh sách đầu sách trong hệ thống.");
        System.out.println("3. Nhập khách hàng mới vào hệ thống.");
        System.out.println("4. In ra danh sách khách hàng có trong hệ thống.");
        System.out.println("5. Lập phiếu mượn các khách hàng.");
        System.out.println("6. Sắp xếp danh sách quản lý mượn sách theo họ tên khách hàng.");
        System.out.println("7.Sắp xếp danh sách quản lý mượn sách theo số lượng cuốn sách.");
        System.out.println("8.Tìm kiếm và hiển thị danh sách mượn sách theo tên bạn đọc.");
        System.out.println("9. Thoát chương trình");
        System.out.println("--------------------------------------");
        System.out.print("Xin mời nhập lựa chọn của bạn: ");
        int functionChoice = -1;
        do {
            functionChoice = new Scanner(System.in).nextInt();
            if (functionChoice >= 1 && functionChoice <= 8) {
                break;
            }
            System.out.print("Chức năng được chọn không hợp lệ, vui lòng chọn lại: ");
        } while (functionChoice > 9 || functionChoice < 1);
        return functionChoice;
    }
}

