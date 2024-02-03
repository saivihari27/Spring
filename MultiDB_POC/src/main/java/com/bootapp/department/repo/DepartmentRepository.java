package com.bootapp.department.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootapp.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	
}
