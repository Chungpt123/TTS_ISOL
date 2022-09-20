package com.rest.service.impl;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rest.entity.Student;
import com.rest.repository.StudentRepo;
import com.rest.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired StudentRepo studentRepo;

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
	}

	@Override
	public Student findById(Integer id) {
		// TODO Auto-generated method stub
		return studentRepo.findById(id).get();
	}
	@Override
	public Student create(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student);
	}

	@Override
	public Student update(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student);
	}

	@Override
	public void delete(Integer id) {
		studentRepo.deleteById(id);
		
	}
	@Override
	public List<Student> findByDate() {
		// TODO Auto-generated method stub
		return studentRepo.findByDate();
	}

	@Override
	public Page<Student> findByStudent(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
