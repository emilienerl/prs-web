package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prs.business.User;
import com.prs.db.UserRepo;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;

	//get all users
	
		@GetMapping("/")
		public List<User> getAll() {
			return userRepo.findAll();
		}
		
		
		//get user by id
		
		@GetMapping("/{id}")
		public Optional<User> getById(@PathVariable int id) {
			return userRepo.findById(id);
		}
		

		// add a user
		
		@PostMapping("/")
		public User addUser(@RequestBody User u) {
			u = userRepo.save(u);
			return u;
		}
		
		
		//update a user
		
		@PutMapping("/")
		public User updateUser(@RequestBody User u) {
			u = userRepo.save(u);
			return u;
		}
		
		//delete a user
		
		@DeleteMapping("/{id}")
		public User deleteUser(@PathVariable int id) {
			Optional<User> u = userRepo.findById(id);
			if (u.isPresent()) {
				userRepo.deleteById(id);
			} else {
				System.out.println("Error - user not found for id " + id);
			}
			return u.get();
		}
	
		// login request param
		@GetMapping("/login")
		public Optional<User> login(@RequestParam String userName, @RequestParam String password) {
			Optional <User> u = userRepo.findByUserNameAndPassword(userName, password);
			if (u.isPresent()) {
				return u;
			} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
		}
		
		// login via POST
		@GetMapping("/login")
		public Optional<User> login(@RequestParam User u) {
			Optional <User> user = userRepo.findByUserNameAndPassword(u.getUserName(), u.getPassword());
			if (user.isPresent()) {
				return user;
			} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
}
		}
}
