package com.seatingarrangement.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seatingarrangement.main.model.User;
import com.seatingarrangement.main.repository.UserRepository;


@Service
public class UserService<ApiResponse> implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User insert(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public User getById(int id) {
		Optional <User> optional = userRepository.findById(id);
		if(!optional.isPresent()) {
			return null;
		}
		return optional.get();
	}
	
	public void deleteUserDetails(User user) {
		userRepository.delete(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		return user;
	}

	public User findByusername(String username) {
		return userRepository.getUserByUsername(username);
	}

}
