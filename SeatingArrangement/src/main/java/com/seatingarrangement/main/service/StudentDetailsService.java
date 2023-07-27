package com.seatingarrangement.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatingarrangement.main.model.Courses;
import com.seatingarrangement.main.model.StudentDetails;
import com.seatingarrangement.main.repository.StudentDetailsRepository;

@Service
public class StudentDetailsService {
	
	@Autowired
	private StudentDetailsRepository studentDetailsRepository;
	
	@Autowired
	private CoursesService courseService;
	
	private Courses course;
	
//	public StudentDetails insert(StudentDetails studentDetails) {
//		if(studentDetails!=null) {
//			
//			Courses c = courseService.getByCourse(studentDetails.getBranch());
//			c.setNo_of_seats_alloted(c.getNo_of_seats_alloted()+1);
//			c.setNo_of_seats_available(c.getNo_of_seats_available()-1);
//		
//			return studentDetailsRepository.save(studentDetails);
//        }
//        return null;
//    }
	
	 public StudentDetails insert(StudentDetails studentDetails) {
	        if (studentDetails != null) {
	            Courses c = courseService.getByCourse(studentDetails.getBranch());
	            
	            // Check if there are available seats in the branch
	            if (c.getNo_of_seats_available() > 0) {
	                c.setNo_of_seats_alloted(c.getNo_of_seats_alloted() + 1);
	                c.setNo_of_seats_available(c.getNo_of_seats_available() - 1);
	                
	                // Save the updated course details with the new seat allocation
	                courseService.saveCourse(c);
	                
	                return studentDetailsRepository.save(studentDetails);
	            } else {
	                // Branch is full, handle accordingly (throw an exception, return null, etc.)
	                throw new IllegalStateException("Branch is full. Cannot add more students.");
	            }
	        }
	        return null;
	    }
	
	public List<StudentDetails> getAll(){
		return studentDetailsRepository.findAll();
		
	}
	
	public StudentDetails getById(int id) {
		Optional<StudentDetails> optional=studentDetailsRepository.findById(id);
		if(!optional.isPresent()) {
			return null;
		}
		return optional.get();
		
	}
	
	public void deleteStudentDetails(StudentDetails studentDetails) {
		studentDetailsRepository.delete(studentDetails);
		
	}
	
	public List<StudentDetails> getAllStudents() {
        return studentDetailsRepository.findAll();
    }
	
}
