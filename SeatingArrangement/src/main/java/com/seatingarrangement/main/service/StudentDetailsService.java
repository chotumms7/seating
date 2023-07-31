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
	
	public StudentDetails insert(StudentDetails studentDetails) {
	    if (studentDetails != null) {
	        int marks = studentDetails.getMarks();
	        String branch = null;
	        
	        // Fetch all courses from the backend
	        List<Courses> coursesList = courseService.getAll();

	        // Find the course that matches the student's marks
	        for (Courses course : coursesList) {
	            int minMarks = course.getMin_marks();
	            int maxMarks = course.getMax_marks();

	            if (marks >= minMarks && marks <= maxMarks) {
	                branch = course.getCourse();
	                    courseService.saveCourse(course);
	                    studentDetails.setBranch(branch);
	                    return studentDetailsRepository.save(studentDetails);

	            }
	        }

	        // Student's marks do not fall in any valid range, handle accordingly (throw an exception, return null, etc.)
	        throw new IllegalArgumentException("Invalid marks. Cannot allocate seat.");
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


	public StudentDetails getByStudentName(String studentName) {
		return studentDetailsRepository.getByStudentName(studentName);
	}
	
}
