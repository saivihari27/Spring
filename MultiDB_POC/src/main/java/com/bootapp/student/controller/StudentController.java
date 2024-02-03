package com.bootapp.student.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bootapp.student.entity.Student;
import com.bootapp.student.repo.StudentRepository;

import jakarta.annotation.PostConstruct;

@RestController
public class StudentController {

	@Autowired
	public StudentRepository studentRepository;
	
//	@PostMapping("/savestudent")
//	public Student saveStudent(@RequestBody Student std) {
//		Student savedStudent = studentRepository.save(std);
//		return savedStudent;
//	}
	
	@PostConstruct
	public void addToDB() {
		studentRepository.saveAll(Stream.of(new Student(1, "Saivihari"), new Student(2, "Raju"))
				.collect(Collectors.toList()));
	}
	
	@GetMapping("/getStudent/{id}")
	public Optional<Student> getStudentId(@PathVariable int id) {
		return studentRepository.findById(id);
		
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
}
