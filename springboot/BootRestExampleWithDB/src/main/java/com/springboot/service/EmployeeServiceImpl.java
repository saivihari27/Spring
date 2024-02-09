package com.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Employee;
import com.springboot.repo.EmployeeRepo;
import com.springboot.response.EmployeeResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public String upsert(Employee emp) {
		employeeRepo.save(emp);
		return "Saved success";
	}

	@Override
	public EmployeeResponse getEmployeeById(int empId) {
	 Optional<Employee> emp = employeeRepo.findById(empId);
		 EmployeeResponse employeeResponse = modelMapper.map(emp,EmployeeResponse.class);
	  return employeeResponse;
				
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
