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
import com.seatingarrangement.main.model.Student;
import com.seatingarrangement.main.service.CoursesService;

@RestController
@RequestMapping("/courses")
public class CoursesController {
	
	@Autowired
	private CoursesService coursesService;
	
	@PostMapping("/add")
	public Courses postCourses(@RequestBody Courses courses) {
		return coursesService.insert(courses);
	}
	
	@GetMapping("/getAll")
	public List<Courses> getAll(){
		List<Courses> list=coursesService.getAll();
		return list;
	}
	

	@GetMapping("/getOne/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id){
		Courses courses=coursesService.getById(id);
		if(courses==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("INVALID ID given");
		}
		return ResponseEntity.status(HttpStatus.OK).body(courses);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCourses(@PathVariable("id") int id,@RequestBody Courses newCourses){
		Courses oldCourses=coursesService.getById(id);
		if(oldCourses==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("INVALID ID given");
		}
		newCourses.setId(oldCourses.getId());
		newCourses=coursesService.insert(newCourses);
		return ResponseEntity.status(HttpStatus.OK).body(newCourses);
		
	}
	
	
	 @DeleteMapping("/delete/{id}")
		public ResponseEntity<?> deleteCourses(@PathVariable("id") int id) {
		 Courses courses =coursesService.getById(id);
			if (courses == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id given");
			}
			coursesService.deleteCourses(courses);
			return ResponseEntity.status(HttpStatus.OK).body("course deleted");
		}
	 
	 @GetMapping("/getByCourseName/{courseName}")
	    public ResponseEntity<?> getCoursesByCourseName(@PathVariable("courseName") String courseName) {
	        List<Courses> courses = coursesService.getByCourseName(courseName);
	        if (courses.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("No courses found with the given course name: " + courseName);
	        }
	        return ResponseEntity.status(HttpStatus.OK).body(courses);
	    }

}
