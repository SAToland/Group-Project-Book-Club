package com.seth.BookClub.Services;


import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.seth.BookClub.Models.LoginUser;
import com.seth.BookClub.Models.User;
import com.seth.BookClub.Repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
		
		Optional<User> exists = userRepo.findByEmail(newUser.getEmail());
		// Reject if email is taken (present in database)
		if(exists.isPresent()) {
			result.rejectValue("email", "Matches", "Email already in use");
		}
        // Reject if password doesn't match confirmation
		if(!newUser.getConfirmPass().equals(newUser.getPassword())) {
			result.rejectValue("confirmPass", "Matches", "Passwords do no match");
		}
		// Return null if result has errors
		if(result.hasErrors()) {
			return null;
		}
		// Hash and set password, save user to database
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		
		return userRepo.save(newUser);
	}
	
	public User login(LoginUser login, BindingResult result) {
		// Find user in the DB by email
		Optional<User> exists = userRepo.findByEmail(login.getEmail());
		User user = null;
		if(!exists.isEmpty()) {
			user = exists.get();
		}
		// Reject if NOT present
		if(user == null) {
			result.rejectValue("email", "Matches", "Email does not exist");
		}
		// Reject if BCrypt password match fails
		if(user != null) {
			if(!BCrypt.checkpw(login.getPassword(), user.getPassword())) {
			    result.rejectValue("password", "Matches", "Invalid Password!");
			}
		}
		// Return null if result has errors
		if(result.hasErrors()) {
			return null;
		}
		// Otherwise, return the user object
		return user;
	}
	
	public List<User> allUsers(){
		return userRepo.findAll();
	}
	
	public User findUserByEmail(String email) {
		Optional<User> user = userRepo.findByEmail(email);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			return null;
		}
	}
	public User findById(Long id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			return null;
		}
	}
}
