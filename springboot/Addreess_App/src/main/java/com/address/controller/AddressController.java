package com.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.address.response.AddressResponse;
import com.address.service.AddressService;


@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/empAddress/{empId}")
	public ResponseEntity<AddressResponse> getAddressByEmpId(@PathVariable("empId") int empId){
		AddressResponse addressResponse = addressService.getAddressByEmpId(empId);
		return new ResponseEntity<>(addressResponse, HttpStatus.OK);
	}
	
	@GetMapping("/alladdress")
	public ResponseEntity<List<AddressResponse>> getAllAddress() {
		List<AddressResponse> allEmployeesWithAddress = addressService.getAllAddress();
		return new ResponseEntity<>(allEmployeesWithAddress, HttpStatus.OK);	
	}
	
	@GetMapping("/address/{id}")
	public ResponseEntity<AddressResponse> getEmployeeById(@PathVariable("id") int id){
		AddressResponse employeeResponse = addressService.getAddressById(id);
		return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
	}
	
	@PostMapping("/saveaddress")
	public ResponseEntity<AddressResponse> createEmployee(@RequestBody AddressResponse addressResponse) {
		AddressResponse saveAddress = addressService.createAddress(addressResponse);
		return new ResponseEntity<>(saveAddress, HttpStatus.CREATED);
		
	}
}
