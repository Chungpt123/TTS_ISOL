package com.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	@Query("select s from Student s where DAY(s.birthDay) = DAY(CURDATE())and MONTH(s.birthDay) = MONTH(CURDATE())")
	List<Student> findByDate();
	

}
