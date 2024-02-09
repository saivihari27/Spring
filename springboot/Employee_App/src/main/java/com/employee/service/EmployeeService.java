package com.employee.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.employee.entity.Employee;
import com.employee.repo.EmployeeRepo;
import com.employee.response.AddressResponse;
import com.employee.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${address.service.domain.url}")
	private String addressUrl;
	
	public EmployeeResponse getAddressByEmpId(int id){
		Optional<Employee> responseId = employeeRepo.findById(id);
		EmployeeResponse employeeResponse = modelMapper.map(responseId, EmployeeResponse.class);
		AddressResponse addressResponse = restTemplate.getForObject(addressUrl+"/empAddress/{empId}", AddressResponse.class, id);
		employeeResponse.setAddressResponse(addressResponse);
		return employeeResponse;
	}

	public EmployeeResponse createEmployee(EmployeeResponse employeeResponse) {
		Employee employee = modelMapper.map(employeeResponse, Employee.class);
		Employee empSave = employeeRepo.save(employee);
		EmployeeResponse savedEmpResponse = modelMapper.map(empSave, EmployeeResponse.class);
		return savedEmpResponse;
	}
	
	public List<EmployeeResponse> getAllEmployees(){
		List<Employee> listEmployee = employeeRepo.findAll();
		List<EmployeeResponse> listEmployeeResponses = listEmployee.stream().map(employee -> modelMapper.map(employee, EmployeeResponse.class))
		.collect(Collectors.toList());
//		Type type = new TypeToken<List<EmployeeResponse>>(){}.getType();
//		List<EmployeeResponse> listEmployeeResponse = modelMapper.map(listEmployee, type);
		return listEmployeeResponses;
	}
	
	public EmployeeResponse updateEmployee(int id, EmployeeResponse employeeResponse) throws Exception{
		Employee existingEmployee = employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceAccessException("Id not found"));
		
		modelMapper.map(employeeResponse, existingEmployee);
		Employee updatedSaveEmployee = employeeRepo.save(existingEmployee);
		EmployeeResponse response = modelMapper.map(updatedSaveEmployee, EmployeeResponse.class);
		return response;
	}
	
	public List<EmployeeResponse> getAllEmployeesWithAddress(){
		List<Employee> listEmployee = employeeRepo.findAll();
		List<EmployeeResponse> listEmployeeResponses = listEmployee.stream().map(employee -> modelMapper.map(employee, EmployeeResponse.class))
		.collect(Collectors.toList());
		AddressResponse[] addressArray = restTemplate.getForObject(addressUrl+"/alladdress",AddressResponse[].class);
		List<AddressResponse> listAddressResponses = Arrays.asList(addressArray);
		for (int i = 0; i < Math.min(listEmployeeResponses.size(), listAddressResponses.size()); i++) {
	        EmployeeResponse employeeResponse = listEmployeeResponses.get(i);
	        AddressResponse addressResponse = listAddressResponses.get(i);
	        employeeResponse.setAddressResponse(addressResponse);
	    }
		return listEmployeeResponses;
	}
}

