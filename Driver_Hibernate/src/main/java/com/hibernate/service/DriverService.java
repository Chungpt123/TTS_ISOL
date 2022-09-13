package com.hibernate.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.hibernate.entities.Driver;
import com.hibernate.repository.DriverRepository;

public class DriverService implements DataInitializing {
	private List<Driver> drivers;

    private final DriverRepository driverRepository = new DriverRepository();

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public void init() {
        this.setDrivers(driverRepository.getAll());
    }

    public void showAll() {
        this.drivers.forEach(System.out::println);
    }

    public void createNew() {
        System.out.print("Xin mời nhập số tài xế muốn thêm mới: ");
        int driverNumber = -1;
        do {
            try {
                driverNumber = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Số tài xế cần nhập là một số nguyên, vui lòng nhập lại: ");
                continue;
            }
            if (driverNumber > 0) {
                break;
            }
            System.out.print("Số tài xế phải là số dương, vui lòng nhập lại: ");
        } while (true);

        List<Driver> newDrivers = new ArrayList<Driver>();
        for (int i = 0; i < driverNumber; i++) {
            Driver driver = inputInfo();
//            this.drivers.add(driver);
            newDrivers.add(driver);
        }
        this.driverRepository.saveAll(newDrivers);
    }

    private Driver inputInfo() {
        Driver driver = new Driver();
        System.out.println("Nhập tên của tài xế: ");
        driver.setName(new Scanner(System.in).nextLine());
        System.out.println("Nhập địa chỉ email của tài xế: ");
        driver.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Nhập SDT của tài xế: ");
        driver.setPhone(new Scanner(System.in).nextLine());
        System.out.println("Nhập kinh nghiệm của tài xế: ");
        driver.setLevel(new Scanner(System.in).nextInt());
        return driver;
    }

    public Driver findById(int driverId) {
        return driverRepository.findById(driverId);
    }
}
