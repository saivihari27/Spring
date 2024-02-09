package com.springboot.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Serializable>{

}
