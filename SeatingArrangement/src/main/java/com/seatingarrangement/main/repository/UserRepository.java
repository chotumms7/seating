package com.seatingarrangement.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seatingarrangement.main.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	@Query("select u from User u where u.username=?1")
	public User getUserByUsername(String username);

}
