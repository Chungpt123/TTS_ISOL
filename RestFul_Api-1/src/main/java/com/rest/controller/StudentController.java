package com.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.Student;
import com.rest.repository.StudentRepo;
import com.rest.service.StudentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/students")

public class StudentController {
	@Autowired
	StudentService studentService;
	@Autowired
	StudentRepo studentRepo;

	@GetMapping("{id}")
	public Student getOne(@PathVariable("id") Integer id) {
		return studentService.findById(id);
	}
	@GetMapping
	public List<Student> findAll() {
		return studentService.findAll();
	}

	

	@PostMapping
	public Student create(@Valid @RequestBody Student student) {
		return studentService.create(student);
	}

	@PutMapping("{id}")
	public Student update(@Valid @PathVariable("id") Integer id, @RequestBody Student student) {
		return studentService.update(student);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		studentService.delete(id);
	}

	@GetMapping("birthday")
	public List<Student> getByDate() {
		return studentService.findByDate();
	}

	@GetMapping
	Page<Student> getStudents(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy) {
		return studentRepo.findAll(PageRequest.of(page.orElse(0), 5, Sort.Direction.ASC, sortBy.orElse("id")));

	}

}
