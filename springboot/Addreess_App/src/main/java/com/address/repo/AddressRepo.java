package com.address.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.address.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{
	
	@Query(nativeQuery = true,value="select ad.id,ad.lane1,ad.city,ad.state,ad.zip from springboots_schema.address as ad join springboots_schema.employees as em on em.id = ad.empId where em.id=:empId")
	Address findAddressByEmployeeId(@Param("empId") int empId);
	
	
//	@Query(nativeQuery = true,value="select * from springboots_schema.address as ad join springboots_schema.employees as em on em.id = ad.empId")
//	List<Address> findAllEmployeesWithAddress();
}
