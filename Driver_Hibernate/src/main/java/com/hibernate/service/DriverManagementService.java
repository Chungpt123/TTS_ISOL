package com.hibernate.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.hibernate.entities.BusLine;
import com.hibernate.entities.Driver;
import com.hibernate.entities.DriverManagement;
import com.hibernate.entities.Management;
import com.hibernate.entities.ManagementDetail;
import com.hibernate.mainRun.MainRun2;
import com.hibernate.repository.DriverBusManagementRepository;
import com.hibernate.utils.DataUtil;

public class DriverManagementService implements DataInitializing {
	private final DriverBusManagementRepository driverManagementRepository = new DriverBusManagementRepository();
	private List<Management> driverBusManagementDtos;

	public List<Management> getDriverBusManagementDtos() {
		return driverBusManagementDtos;
	}

	public void setDriverBusManagementDtos(List<Management> driverBusManagementDtos) {
		this.driverBusManagementDtos = driverBusManagementDtos;
	}

	@Override
	public void init() {
		List<DriverManagement> driverBusManagements = driverManagementRepository.getAll();
        this.setDriverBusManagementDtos(toDto(driverBusManagements));

	}

	private List<Management> toDto(List<DriverManagement> driverBusManagements) {
		if (DataUtil.isEmptyCollection(driverBusManagements)) {
			return Collections.emptyList();
		}

		List<ManagementDetail> driverBusManagementTempDtos = new ArrayList<>();

		driverBusManagements.forEach(driverBusManagement -> {
			Integer driverId = driverBusManagement.getDriverId();
			Driver driver = MainRun2.driverService.findById(driverId);
			Integer busLineId = driverBusManagement.getBusLinedId();
			BusLine busLine = MainRun2.busLineService.findById(busLineId);
			Integer roundNumber = driverBusManagement.getRound();
			driverBusManagementTempDtos.add(new ManagementDetail(driver, busLine, roundNumber));
		});

		Map<Driver, Map<BusLine, Integer>> tempMap = driverBusManagementTempDtos.stream().collect(Collectors.groupingBy(
				ManagementDetail::getDriver,
				Collectors.toMap(ManagementDetail::getBusLine, ManagementDetail::getRound)));

		final List<Management> result = new ArrayList<>();
		tempMap.forEach((key, value) -> {
			Management driverBusManagementDto = new Management(key, value);
			driverBusManagementDto.setTotalDistance();
			result.add(driverBusManagementDto);
		});
		return result;
	}

	public void createManagement() {
		System.out.print("Xin mời nhập số tài xế muốn phân công lái xe: ");
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
		List<Management> driverBusManagementDtos = new ArrayList<Management>();
		for (int i = 0; i < driverNumber; i++) {
			System.out.println("Nhập thông tin cho tài xế thứ " + (i + 1) + ": ");
			Driver driver = inputDriver();
			System.out.println("Lập bảng danh sách tuyến xe lái trong ngày của lái xe này: ");
			Map<BusLine, Integer> busLineMap = createBusLine();
			Management management = new Management(driver, busLineMap);
			management.setTotalDistance();
			driverBusManagementDtos.add(management);

		}
		this.driverBusManagementDtos.forEach(System.out::println);
		driverManagementRepository.saveAll(toEntity(driverBusManagementDtos));

	}

	public List<DriverManagement> toEntity(List<Management> driverBusManagementDtos) {
		final List<DriverManagement> driverBusManagements = new ArrayList<>();
		driverBusManagementDtos.forEach(management -> {
			Driver driver = management.getDriver();
			management.getAssignedBuses().forEach((key, value) -> {
				DriverManagement temp = new DriverManagement();
				temp.setDriverId(driver.getId());
				temp.setBusLinedId(key.getId());
				temp.setDate(new Date());
				temp.setRound(value);
				driverBusManagements.add(temp);
			});
		});
		return driverBusManagements;
	}

	private Driver inputDriver() {
		Driver driver;
		System.out.println("Nhập mã tài xế:");
		do {
			int driverId = -1;
			do {
				try {
					driverId = new Scanner(System.in).nextInt();
				} catch (InputMismatchException ex) {
					System.out.printf("Mã tài xế cần nhập là một số nguyên , vui lòng nhập lại: ");
					continue;
				}
				if (driverId > 0) {
					break;
				}
				System.out.printf("Mã tài xế phải là số dương, vui lòng nhập lại: ");
			} while (true);

			driver = MainRun2.driverService.findById(driverId);

			if (!DataUtil.isEmptyObject(driver)) {
				break;
			}
			System.out.println("Không tìm thấy tài xế có mã " + driverId + ", vui lòng nhập lại: ");
		} while (true);
		System.out.println(driver);
		return driver;
	}

	private Map<BusLine, Integer> createBusLine() {
		System.out.printf("Nhập số lượng tuyến mà lái xe này muốn lái: ");
		int busLineNumber = -1;
		do {
			try {
				busLineNumber = new Scanner(System.in).nextInt();
			} catch (InputMismatchException ex) {
				System.out.printf("Số lượng tuyến cần nhập là một số nguyên, vui lòng nhập lại: ");
				continue;
			}
			if (busLineNumber > 0) {
				break;
			}
			System.out.printf("Số lượng tuyến phải là số dương, vui lòng nhập lại: ");
		} while (true);
		int totalRound = 0;
		Map<BusLine, Integer> busLineMap = new HashMap<BusLine, Integer>();
		for (int j = 0; j < busLineNumber; j++) {
			System.out.println("Nhập mã tuyến xe thứ " + (j + 1) + " mà tài xế này muốn lái: ");
			BusLine busLine;
			do {
				int busLineId = -1;
				do {
					try {
						busLineId = new Scanner(System.in).nextInt();
					} catch (InputMismatchException ex) {
						System.out.print("Mã tuyến cần nhập là một số nguyên, vui lòng nhập lại: ");
						continue;
					}
					if (busLineId > 0) {
						break;
					}
					System.out.print("Mã tuyến phải là số dương, vui lòng nhập lại: ");
				} while (true);
				busLine = MainRun2.busLineService.findById(busLineId);
				if (!DataUtil.isEmptyObject(busLine)) {
					break;
				}
				System.out.println("Không tìm thấy tuyến xe có mã " + busLineId + ", vui lòng nhập lại: ");
			} while (true);
			System.out.print("Nhập số lượt mà tài xế này muốn lái: ");
			int busRound = -1;
			do {
				try {
					busRound = new Scanner(System.in).nextInt();
				} catch (InputMismatchException ex) {
					System.out.print("Số lượt cần nhập là một số nguyên, vui lòng nhập lại: ");
					continue;
				}
				if (busRound > 0) {
					break;
				}
				System.out.print("Số lượt phải là số dương, vui lòng nhập lại: ");
			} while (true);
			totalRound += busRound;
			if (totalRound > 15) {
				System.out.println("Tài xế không được lái quá 15 lượt 1 ngày, dừng phân công tại đây.");
				break;
			}
			busLineMap.put(busLine, busRound);
		}
		return busLineMap;
	}

	// show all
	public void showAll() {
		this.driverBusManagementDtos.forEach(System.out::println);
	}

	// sort by driver name
	private void sortByDriverName() {
		this.driverBusManagementDtos.sort(Comparator.comparing(o -> o.getDriver().getName()));
		this.showAll();
	}

	private void sortByBusLineNumber() {
		this.driverBusManagementDtos.sort(Comparator.comparing(Management::getTotalDistance).reversed());
		this.showAll();
	}

	public void sort() {
		System.out.println("Sắp xếp danh sách phân công lái xe theo: ");
		System.out.println(" 1. Họ tên lái xe");
		System.out.println(" 2. Số tuyến đảm nhận trong ngày (giảm dần)");
		System.out.print("Vui lòng nhập lựa chọn: ");
		int choice = -1;
		do {
			try {
				choice = new Scanner(System.in).nextInt();
			} catch (InputMismatchException ex) {
				System.out.print("Giá trị cần nhập là một số nguyên, vui lòng nhập lại: ");
				continue;
			}
			if (choice == 1 || choice == 2) {
				break;
			}
			System.out.print("Giá trị lựa chọn không tồn tại, vui lòng nhập lại: ");
		} while (true);

		switch (choice) {
		case 1:
			sortByDriverName();
			break;
		case 2:
			sortByBusLineNumber();
			break;
		}
	}

}
