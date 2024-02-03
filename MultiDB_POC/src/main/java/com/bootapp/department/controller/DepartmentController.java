package com.bootapp.department.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bootapp.department.entity.Department;
import com.bootapp.department.repo.DepartmentRepository;

import jakarta.annotation.PostConstruct;

@RestController
public class DepartmentController {

	@Autowired
	public DepartmentRepository departmentRepository;
	
//	@PostMapping("/savestudent")
//	public Department saveDepartment(@RequestBody Department department) {
//		Department savedDepartment= departmentRepository.save(department);
//		return savedDepartment;
//	}
	
	@PostConstruct
	public void addToDB() {
		departmentRepository.saveAll(Stream.of(new Department(111, "CSE"), new Department(222, "EEE"))
				.collect(Collectors.toList()));
	}
	
	
	@GetMapping("/getdepartment/{id}")
	public Optional<Department> getDepartmentId(@PathVariable int id) {
		return departmentRepository.findById(id);
		
	}
	
	@GetMapping("/departments")
	public List<Department> getDepartments(){
		return departmentRepository.findAll();
	}
}
