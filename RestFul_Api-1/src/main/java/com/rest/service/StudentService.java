package com.rest.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.rest.entity.Student;


public interface StudentService {
	List<Student> findAll();
	
	Student findById(Integer id);

	Student create(Student student);

	Student update(Student student);

	void delete(Integer id);
	 
	List<Student> findByDate();
	
	Page<Student> findByStudent(Pageable pageable);
	
	
}
