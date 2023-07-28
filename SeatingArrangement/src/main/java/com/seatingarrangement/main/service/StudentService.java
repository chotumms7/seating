package com.seatingarrangement.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatingarrangement.main.model.Student;
import com.seatingarrangement.main.model.User;
import com.seatingarrangement.main.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	
	
	public Student insert(Student student) {
		
		return studentRepository.save(student);
	}
	
	public List<Student> getAll(){
		return studentRepository.findAll();
	}
	
	public Student getById(int id) {
		Optional<Student> optional=studentRepository.findById(id);
		if(!optional.isPresent()) {
			return null;
		}
		return optional.get();
	}
	
	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}

	public Student findByusername(String username) {
		
		return studentRepository.getStudentByUsername(username);
	}

//	public Student findByusername(String username) {
//		return studentRepository.getStudentByUsername(username);
//	}
	

}
