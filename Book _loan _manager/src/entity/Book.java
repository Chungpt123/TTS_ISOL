package entity;

import java.util.Scanner;

public class Book {
    private static int AUTO_ID = -1;

    private int id;
    private String name;
    private String author;
    private int year;
    private String category;
    public Book() {
        if (AUTO_ID == -1) {
            AUTO_ID = 10000;
            this.id = AUTO_ID;
            return;
        }
        this.id = ++AUTO_ID;
    }

    public static int getAutoId() {
        return AUTO_ID;
    }

    public static void setAutoId(int autoId) {
        AUTO_ID = autoId;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", category='" + category + '\'' +
                '}';
    }
    public void inputInfo() {
        System.out.print("Nhập tên sách: ");
        this.name = new Scanner(System.in).nextLine();// Ctrl + D ==> nhân đôi dòng
        System.out.print("Nhập tác giả: ");// Alt + Shift + mũi tên lên/xuống
        this.author = new Scanner(System.in).nextLine(); // Ctrl + Z (hoàn tác hành động vừa thực hiện)
        System.out.print("Nhập năm  xuất bản: ");
        this.year = new Scanner(System.in).nextInt();
        System.out.print("Nhập thể loại: ");
        this.category = new Scanner(System.in).nextLine();
    }
}
