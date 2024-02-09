package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.entity.Employee;
import com.springboot.repo.EmployeeRepo;
import com.springboot.response.EmployeeResponse;

public interface EmployeeService {
	
	public String upsert(Employee emp);
	public EmployeeResponse getEmployeeById(int empId);
	public List<Employee> getAllEmployees();
	public String deleteEmployeeById(int empId);
}
