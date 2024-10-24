package com.seth.BookClub.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seth.BookClub.Models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	List<User> findAll();
}
