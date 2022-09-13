package com.hibernate.dao;

import java.util.List;

import com.hibernate.entities.Driver;

public interface DriverDao {
	List<Driver> getAll();

	Driver getOneById(int id);

	boolean addNew(Driver student);

	boolean update(Driver driver);

	boolean delete(Driver driver);
}
