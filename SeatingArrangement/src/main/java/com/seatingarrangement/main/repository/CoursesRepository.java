package com.seatingarrangement.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seatingarrangement.main.model.Courses;

public interface CoursesRepository extends JpaRepository<Courses,Integer>{
	
	@Query("select r from Courses r where r.course=?1")
	Courses getByCourse(String branch);
	


}
