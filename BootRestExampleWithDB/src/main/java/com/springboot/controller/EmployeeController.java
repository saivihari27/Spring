package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Employee;
import com.springboot.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee")
	public ResponseEntity<String> create(@RequestBody Employee emp) {
		String status = employeeService.upsert(emp);
		return new ResponseEntity<String>(status, HttpStatus.CREATED);	
	}
	
	@GetMapping("/employee/{empId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int empId){
		Employee status = employeeService.getEmployeeById(empId);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> listEmployee = employeeService.getAllEmployees();
		return new ResponseEntity<>(listEmployee, HttpStatus.OK);
	}
	
	@PutMapping("/employee")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee emp){
		String status = employeeService.upsert(emp);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@DeleteMapping("/employee/{empId}")
	public ResponseEntity<String> deleteById(@PathVariable int empId){
		String status = employeeService.deleteEmployeeById(empId);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
}
