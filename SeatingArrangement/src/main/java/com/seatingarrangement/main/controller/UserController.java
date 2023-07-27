package com.seatingarrangement.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seatingarrangement.main.model.User;

import com.seatingarrangement.main.service.UserService;


@RestController
@RequestMapping("api/userdetails")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/add")
	public User postUserDetails(@RequestBody User user) {
//		System.out.println("mypassword" +bCryptPasswordEncoder.encode("test@1234"));
		user.setUsername(user.getUsername());
		//user.setRole("ADMIN");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userService.insert(user);
	}
	
	

	@GetMapping("/getAll")
	public List<User> getAll(){
		List<User> list=userService.getAll();
		return list;
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUserDetails(@PathVariable("id") int id,@RequestBody User newUserDetails){
		User oldUserDetails=userService.getById(id);
		if(oldUserDetails==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("INVALID ID given");
		}
		newUserDetails.setId(oldUserDetails.getId());
		newUserDetails=userService.insert(newUserDetails);
		return ResponseEntity.status(HttpStatus.OK).body(newUserDetails);
		
	}
	
	
	 @DeleteMapping("/delete/{id}")
		public ResponseEntity<?> deleteUserDetails(@PathVariable("id") int id) {
		 User userDetails =userService.getById(id);
			if (userDetails == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id given");
			}
			userService.deleteUserDetails(userDetails);
			return ResponseEntity.status(HttpStatus.OK).body("userDetails deleted");
		}
	 
//	 @PostMapping(path="/login")
//		 public ResponseEntity<?> loginUser(@RequestBody User user){
//			 UserRes userRes=userService.loginUser(user);
//			 return ResponseEntity.ok(userRes);
//		 }
	 @GetMapping(path="login/{username}/{password}")
	 public Boolean loginUser(@PathVariable("username") String username, @PathVariable("password") String password){
		 User user = userService.findByusername(username);
		
		 if(user == null) {
			 return  false;
		 }
		 
		 if(bCryptPasswordEncoder.matches(password, user.getPassword())){
			 return true;
		 }
		 return false;
	 }
}
