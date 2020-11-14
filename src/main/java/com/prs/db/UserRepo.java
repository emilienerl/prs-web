package com.prs.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.business.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	//single instance of a user has to return one and only one user; Optional wrapper to check for nulls
	
	Optional<User> findByUserNameAndPassword(String userName, String password);

}
