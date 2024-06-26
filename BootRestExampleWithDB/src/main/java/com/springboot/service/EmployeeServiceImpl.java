package com.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Employee;
import com.springboot.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public String upsert(Employee emp) {
		employeeRepo.save(emp);
		return "Saved success";
	}

	@Override
	public Employee getEmployeeById(int empId) {
	  Optional<Employee> id = employeeRepo.findById(empId);
	  if(id.isPresent()) {
		  id.get();
	  }
	  return null;
				
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public String deleteEmployeeById(int empId) {
		if(employeeRepo.existsById(empId)) {
			employeeRepo.deleteById(empId);
		}
		return "Delete success";
	}
	
	public void validateNumber(String empMobileNo) {
		
	}

}
