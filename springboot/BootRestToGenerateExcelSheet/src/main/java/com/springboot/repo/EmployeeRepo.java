package com.springboot.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Serializable>{

}
