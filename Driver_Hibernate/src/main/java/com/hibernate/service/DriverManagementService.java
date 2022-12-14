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
		System.out.print("Xin m???i nh???p s??? t??i x??? mu???n ph??n c??ng l??i xe: ");
		int driverNumber = -1;
		do {
			try {
				driverNumber = new Scanner(System.in).nextInt();
			} catch (InputMismatchException ex) {
				System.out.print("S??? t??i x??? c???n nh???p l?? m???t s??? nguy??n, vui l??ng nh???p l???i: ");
				continue;
			}
			if (driverNumber > 0) {
				break;
			}
			System.out.print("S??? t??i x??? ph???i l?? s??? d????ng, vui l??ng nh???p l???i: ");
		} while (true);
		List<Management> driverBusManagementDtos = new ArrayList<Management>();
		for (int i = 0; i < driverNumber; i++) {
			System.out.println("Nh???p th??ng tin cho t??i x??? th??? " + (i + 1) + ": ");
			Driver driver = inputDriver();
			System.out.println("L???p b???ng danh s??ch tuy???n xe l??i trong ng??y c???a l??i xe n??y: ");
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
		System.out.println("Nh???p m?? t??i x???:");
		do {
			int driverId = -1;
			do {
				try {
					driverId = new Scanner(System.in).nextInt();
				} catch (InputMismatchException ex) {
					System.out.printf("M?? t??i x??? c???n nh???p l?? m???t s??? nguy??n , vui l??ng nh???p l???i: ");
					continue;
				}
				if (driverId > 0) {
					break;
				}
				System.out.printf("M?? t??i x??? ph???i l?? s??? d????ng, vui l??ng nh???p l???i: ");
			} while (true);

			driver = MainRun2.driverService.findById(driverId);

			if (!DataUtil.isEmptyObject(driver)) {
				break;
			}
			System.out.println("Kh??ng t??m th???y t??i x??? c?? m?? " + driverId + ", vui l??ng nh???p l???i: ");
		} while (true);
		System.out.println(driver);
		return driver;
	}

	private Map<BusLine, Integer> createBusLine() {
		System.out.printf("Nh???p s??? l?????ng tuy???n m?? l??i xe n??y mu???n l??i: ");
		int busLineNumber = -1;
		do {
			try {
				busLineNumber = new Scanner(System.in).nextInt();
			} catch (InputMismatchException ex) {
				System.out.printf("S??? l?????ng tuy???n c???n nh???p l?? m???t s??? nguy??n, vui l??ng nh???p l???i: ");
				continue;
			}
			if (busLineNumber > 0) {
				break;
			}
			System.out.printf("S??? l?????ng tuy???n ph???i l?? s??? d????ng, vui l??ng nh???p l???i: ");
		} while (true);
		int totalRound = 0;
		Map<BusLine, Integer> busLineMap = new HashMap<BusLine, Integer>();
		for (int j = 0; j < busLineNumber; j++) {
			System.out.println("Nh???p m?? tuy???n xe th??? " + (j + 1) + " m?? t??i x??? n??y mu???n l??i: ");
			BusLine busLine;
			do {
				int busLineId = -1;
				do {
					try {
						busLineId = new Scanner(System.in).nextInt();
					} catch (InputMismatchException ex) {
						System.out.print("M?? tuy???n c???n nh???p l?? m???t s??? nguy??n, vui l??ng nh???p l???i: ");
						continue;
					}
					if (busLineId > 0) {
						break;
					}
					System.out.print("M?? tuy???n ph???i l?? s??? d????ng, vui l??ng nh???p l???i: ");
				} while (true);
				busLine = MainRun2.busLineService.findById(busLineId);
				if (!DataUtil.isEmptyObject(busLine)) {
					break;
				}
				System.out.println("Kh??ng t??m th???y tuy???n xe c?? m?? " + busLineId + ", vui l??ng nh???p l???i: ");
			} while (true);
			System.out.print("Nh???p s??? l?????t m?? t??i x??? n??y mu???n l??i: ");
			int busRound = -1;
			do {
				try {
					busRound = new Scanner(System.in).nextInt();
				} catch (InputMismatchException ex) {
					System.out.print("S??? l?????t c???n nh???p l?? m???t s??? nguy??n, vui l??ng nh???p l???i: ");
					continue;
				}
				if (busRound > 0) {
					break;
				}
				System.out.print("S??? l?????t ph???i l?? s??? d????ng, vui l??ng nh???p l???i: ");
			} while (true);
			totalRound += busRound;
			if (totalRound > 15) {
				System.out.println("T??i x??? kh??ng ???????c l??i qu?? 15 l?????t 1 ng??y, d???ng ph??n c??ng t???i ????y.");
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
		System.out.println("S???p x???p danh s??ch ph??n c??ng l??i xe theo: ");
		System.out.println(" 1. H??? t??n l??i xe");
		System.out.println(" 2. S??? tuy???n ?????m nh???n trong ng??y (gi???m d???n)");
		System.out.print("Vui l??ng nh???p l???a ch???n: ");
		int choice = -1;
		do {
			try {
				choice = new Scanner(System.in).nextInt();
			} catch (InputMismatchException ex) {
				System.out.print("Gi?? tr??? c???n nh???p l?? m???t s??? nguy??n, vui l??ng nh???p l???i: ");
				continue;
			}
			if (choice == 1 || choice == 2) {
				break;
			}
			System.out.print("Gi?? tr??? l???a ch???n kh??ng t???n t???i, vui l??ng nh???p l???i: ");
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
