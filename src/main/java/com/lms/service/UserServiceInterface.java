package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.UserDto;
import com.lms.entity.User;

public interface UserServiceInterface {

	public ResponseEntity<Void> saveUser(UserDto userDto);
	public ResponseEntity<Void> updateUser(Integer userId, UserDto userDto);
	public ResponseEntity<Void> deleteUser(Integer userId);
	public ResponseEntity<User> findUserById(Integer userId);
	public ResponseEntity<List<User>> findAllUser();
	public ResponseEntity<List<User>> findUserByName(String userName);
	public ResponseEntity<User> findUserByPhoneNumber(long phoneNumber);
	public ResponseEntity<User> findUserByEmail(String email);
	public ResponseEntity<Void> addAddressToUser(Integer addressId, Integer userId);
	
}
