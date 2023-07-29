package com.seatingarrangement.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seatingarrangement.main.model.Courses;
import com.seatingarrangement.main.model.StudentDetails;
import com.seatingarrangement.main.service.CoursesService;
import com.seatingarrangement.main.service.StudentDetailsService;

@RestController
@RequestMapping("/studentdetails")
public class StudentDetailsController {
	
	@Autowired
	private StudentDetailsService studentDetailsService;
	
	@Autowired
	private CoursesService coursesService;

    @PostMapping("/add")
    public ResponseEntity<StudentDetails> postStudentDetails(@RequestBody StudentDetails studentDetails) {
            
//   	  Courses courses=studentDetails.getCourses();
// 	  
//   	  courses=coursesService.insert(courses);
//   	  
//   	  studentDetails.setCourses(courses);
//   	  
//   	  return studentDetailsService.insert(studentDetails);
    	
    	
        StudentDetails savedStudentDetails = studentDetailsService.insert(studentDetails);
       // System.out.println(savedStudentDetails);
        return ResponseEntity.status(HttpStatus.OK).body(savedStudentDetails);
    }
	
	@GetMapping("/getAll")
	public List<StudentDetails> getAll(){
		List<StudentDetails> list=studentDetailsService.getAll();
		return list;
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateStudentDetails(@PathVariable("id") int id,@RequestBody StudentDetails newStudentDetails){
		StudentDetails oldStudentDetails=studentDetailsService.getById(id);
		if(oldStudentDetails==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("INVALID ID given");
		}
		newStudentDetails.setId(oldStudentDetails.getId());
		newStudentDetails=studentDetailsService.insert(newStudentDetails);
		return ResponseEntity.status(HttpStatus.OK).body(newStudentDetails);
		
	}
	
	
	 @DeleteMapping("/delete/{id}")
		public ResponseEntity<?> deleteStudentDetails(@PathVariable("id") int id) {
		 StudentDetails StudentDetails =studentDetailsService.getById(id);
			if (StudentDetails == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id given");
			}
			studentDetailsService.deleteStudentDetails(StudentDetails);
			return ResponseEntity.status(HttpStatus.OK).body("studentDetails deleted");
		}
	 @GetMapping("/report")
	    public List<StudentDetails> getStudentReport() {
	        return studentDetailsService.getAllStudents();
	    }
	 
}
