package com.seatingarrangement.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seatingarrangement.main.model.StudentDetails;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails,Integer>{

	@Query("select n from StudentDetails n where n.studentName=?1")
	StudentDetails getByStudentName(String studentName);

}
