package com.seatingarrangement.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seatingarrangement.main.model.Student;


public interface StudentRepository extends JpaRepository<Student,Integer>{

	@Query("select s from Student s where s.username=?1")
	Student getStudentByUsername(String username);
 

}
