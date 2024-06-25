package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.model.Employee;
import com.springboot.repo.EmployeeRepo;

public interface EmployeeService {
	
	public String upsert(Employee emp);
	public Employee getEmployeeById(int empId);
	public List<Employee> getAllEmployees();
	public String deleteEmployeeById(int empId);
}
