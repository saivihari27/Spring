package com.employee.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.response.EmployeeResponse;
import com.employee.service.EmployeeService;


@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") int id){
		EmployeeResponse employeeResponse = employeeService.getAddressByEmpId(id);
		return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
	}
	
	@PostMapping("/saveemployees")
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeResponse employeeResponse) {
		EmployeeResponse saveEmployee = employeeService.createEmployee(employeeResponse);
		return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);	
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
		List<EmployeeResponse> allEmployees = employeeService.getAllEmployees();
			return new ResponseEntity<>(allEmployees, HttpStatus.OK);
	}
	
	@GetMapping("/allemployees")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployeeWithAddress() {
		List<EmployeeResponse> allEmployeesWithAddress = employeeService.getAllEmployeesWithAddress();
		return new ResponseEntity<>(allEmployeesWithAddress, HttpStatus.OK);	
	}
	
	@PutMapping("/updateemployee")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable int id, @RequestBody EmployeeResponse employeeResponse) 
			throws Exception {
		EmployeeResponse updatedEmployee = employeeService.updateEmployee(id, employeeResponse);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}
}
