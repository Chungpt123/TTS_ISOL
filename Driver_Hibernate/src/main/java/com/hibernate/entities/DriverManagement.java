package com.hibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "DRIVER_BUS_MANAGEMENT")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverManagement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "driver_id", nullable = false)
	int driverId;
	@Column(name = "bus_line_id", nullable = false)
	int busLinedId;
	@Column(name = "date", nullable = false)
	Date date = new Date();
	@Column(name = "round", nullable = false)
	int round;
}
