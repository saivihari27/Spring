package com.address.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address.entity.Address;
import com.address.repo.AddressRepo;
import com.address.response.AddressResponse;

@Service
public class AddressService {

	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AddressResponse getAddressByEmpId(int id){
		Address responseId = addressRepo.findAddressByEmployeeId(id);
		AddressResponse addressResponse = modelMapper.map(responseId, AddressResponse.class);
		return addressResponse;
	}
	
	
	public List<AddressResponse> getAllAddress() {
		 List<Address> allAddress = addressRepo.findAll();
		 List<AddressResponse> allAddressResponses = allAddress.stream().
		 	map(address -> modelMapper.map(address, AddressResponse.class)).collect(Collectors.toList());
		 return allAddressResponses;
	}

//	public void createAddress(AddressResponse addressResponse) {
//		 Address employee = modelMapper.map(addressResponse, Address.class);
//		Address savedEmployee = addressRepo.save(employee);
//		
//	}

	public AddressResponse getAddressById(int id) {
		Optional<Address> addressId = addressRepo.findById(id);
		AddressResponse addressResponse = modelMapper.map(addressId, AddressResponse.class);
		return addressResponse;
	}

	public AddressResponse createAddress(AddressResponse addressResponse) {
		Address address = modelMapper.map(addressResponse, Address.class);
		Address saveaddress = addressRepo.save(address);
		AddressResponse savedAddressResponse = modelMapper.map(saveaddress, AddressResponse.class);
		return savedAddressResponse;
	}

//	public void saveEmployee(EmployeeResponse employeeResponse) {
//		employeeRepo.save(null);
//		
//	}
}

