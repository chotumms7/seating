package com.seatingarrangement.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seatingarrangement.main.model.StudentDetails;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails,Integer>{

}
