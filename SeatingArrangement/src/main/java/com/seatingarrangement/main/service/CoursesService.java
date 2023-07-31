package com.seatingarrangement.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatingarrangement.main.model.Courses;
import com.seatingarrangement.main.repository.CoursesRepository;

@Service
public class CoursesService {
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	private Courses course;
	
	public Courses insert(Courses courses) {
		
		return coursesRepository.save(courses);
	}
	
	public List<Courses> getAll(){
		return coursesRepository.findAll();
	}
	
	public Courses getById(int id) {
		Optional<Courses> optional=coursesRepository.findById(id);
		if(!optional.isPresent()) {
			return null;
		}
		return optional.get();
	}
	
	public void deleteCourses(Courses courses) {
		coursesRepository.delete(courses);
	}


	public Courses getByCourse(String branch) {
		
		return coursesRepository.getByCourse(branch);
	}

	public Courses saveCourse(Courses courses) {
		return coursesRepository.save(courses);
	}
	
	 public List<Courses> getByCourseName(String courseName) {
		 List<Courses> courses=new ArrayList<>();
	       courses.add(coursesRepository.getByCourse(courseName));
	       return courses;
	    }
}
