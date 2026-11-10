package com.lms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.UserDto;
import com.lms.entity.Address;
import com.lms.entity.User;
import com.lms.exception.BadRequestException;
import com.lms.exception.UserNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.UserRepository;
import com.lms.service.UserServiceInterface;
import com.lms.util.LibraryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface{

	private final LibraryMapper libraryMapper;
	private final UserRepository userRepository;	
	private final AddressRepository addressRepository;	
	
	@Override
	public ResponseEntity<Void> saveUser(UserDto userDto) {
		User user = libraryMapper.convertUserDtoToEntity(userDto);
		userRepository.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "User Saved Successfully");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
	}
	
	@Override
	public ResponseEntity<Void> updateUser(Integer userId, UserDto userDto) {
		HttpHeaders headers = new HttpHeaders();
		if(userId != null) {
			Optional<User> optional = userRepository.findById(userId);
			if (optional.isPresent()) {
				User user = optional.get();
				if (userDto.getUserName()!=null) {
					user.setUserName(userDto.getUserName());
				}
				if (userDto.getPhoneNumber()!=null) {
					user.setPhoneNumber(userDto.getPhoneNumber());
				}
				if (userDto.getEmail()!=null) {
					user.setEmail(userDto.getEmail());
				}
				userRepository.save(user);
				headers.add("message", "User Updated Successfully");
				return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
			}
		}
		throw new BadRequestException();
	}

	@Override
	public ResponseEntity<Void> deleteUser(Integer userId) {
		HttpHeaders headers = new HttpHeaders();
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			userRepository.delete(user);
			headers.add("message", "User Deleted Successfully");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
		}
		throw new BadRequestException();
	}

	@Override
	public ResponseEntity<User> findUserById(Integer userId) {
		HttpHeaders headers = new HttpHeaders();
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			headers.add("message", "User Fetched Successfully");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(user);
		}
		throw new UserNotFoundException();
	}

	@Override
	public ResponseEntity<List<User>> findAllUser() {
		List<User> userList = userRepository.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "All User Fetched Successfully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(userList);
	}

	@Override
	public ResponseEntity<List<User>> findUserByName(String userName) {
		List<User> userList = userRepository.findByUserName(userName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "User Fetched Sucessfully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(userList);
	}

	@Override
	public ResponseEntity<User> findUserByPhoneNumber(long phoneNumber) {
		Optional<User> optional = userRepository.findByPhoneNumber(phoneNumber);
		HttpHeaders headers = new HttpHeaders();
		if (optional.isPresent()) {
			User user = optional.get();
			headers.add("message", "User Fetched Sucessfully");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(user);
		}
		throw new UserNotFoundException();
	}

	@Override
	public ResponseEntity<User> findUserByEmail(String email) {
		Optional<User> optional = userRepository.findByEmail(email);
		HttpHeaders headers = new HttpHeaders();
		if (optional.isPresent()) {
			User user = optional.get();
			headers.add("message", "User Fetched Sucessfully");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(user);
		}
		throw new UserNotFoundException();
	}

	@Override
	public ResponseEntity<Void> addAddressToUser(Integer addressId, Integer userId) {
		Optional<Address> optionalAddress = addressRepository.findById(addressId);
		Optional<User> optionalUser = userRepository.findById(userId);
		HttpHeaders headers = new HttpHeaders();
		if (optionalAddress.isPresent() && optionalUser.isPresent()) {
			Address address = optionalAddress.get();
			User user = optionalUser.get();
			user.setAddress(address);
			headers.add("message", "Address Set To User");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
		}
		throw new BadRequestException();
	}

}
