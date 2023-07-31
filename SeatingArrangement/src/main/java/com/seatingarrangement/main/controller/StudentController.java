package com.seatingarrangement.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seatingarrangement.main.model.Student;
import com.seatingarrangement.main.model.StudentDetails;
import com.seatingarrangement.main.model.User;
//import com.seatingarrangement.main.model.User;
import com.seatingarrangement.main.service.StudentService;
//import com.seatingarrangement.main.service.UserService;

@RestController
@RequestMapping("/student")
public class StudentController {


	@Autowired
	private StudentService studentService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/add")
	public Student postStudent(@RequestBody Student student) {
		
		student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
		return studentService.insert(student);
	}
	
	
	@GetMapping("/getAll")
	public List<Student> getAll(){
		List<Student> list=studentService.getAll();
		return list;
	}
	

	@GetMapping("/getOne/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id){
		Student student=studentService.getById(id);
		if(student==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("INVALID ID given");
		}
		return ResponseEntity.status(HttpStatus.OK).body(student);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable("id") int id,@RequestBody Student newStudent){
		Student oldStudent=studentService.getById(id);
		if(oldStudent==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("INVALID ID given");
		}
		newStudent.setId(oldStudent.getId());
		newStudent=studentService.insert(newStudent);
		return ResponseEntity.status(HttpStatus.OK).body(newStudent);
		
	}
	
	
	 @DeleteMapping("/delete/{id}")
		public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
		 Student Student =studentService.getById(id);
			if (Student == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id given");
			}
			studentService.deleteStudent(Student);
			return ResponseEntity.status(HttpStatus.OK).body("student deleted");
		}
	

	 @GetMapping(path="login/{username}/{password}")
	 public Boolean loginStudent(@PathVariable("username") String username, @PathVariable("password") String password){
		 Student student = studentService.findByusername(username);
		
		 if(student == null) {
			 return  false;
		 }
		 
		 if(bCryptPasswordEncoder.matches(password, student.getPassword())){
			 return true;
		 }
		 return false;
	 }
	 
}
