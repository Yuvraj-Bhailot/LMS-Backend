package com.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findByUserName(String userName);
	Optional<User> findByPhoneNumber(long phoneNumber);
	Optional<User> findByEmail(String email);
	
}
