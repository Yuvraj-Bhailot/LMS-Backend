package com.lms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.service.UserServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserServiceInterface userServiceInterface;

	@Operation(operationId = "saveUser", summary = "Create User", description = "This api is use to save user")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "User Created") })
	@PostMapping("/saveUser")
	public ResponseEntity<Void> saveUser(@Valid @RequestBody UserDto userDto) {
		return userServiceInterface.saveUser(userDto);
	}

	@Operation(operationId = "updateUser", summary = "Update user", description = "This api is use to update user")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User Updated"),
			@ApiResponse(responseCode = "400", description = "Bad Request")})
	@PatchMapping("/updateUser")
	public ResponseEntity<Void> updateUser(@RequestParam("userId") Integer userId, @RequestBody UserDto userDto) {
		return userServiceInterface.updateUser(userId, userDto);
	}

	@Operation(operationId = "deleteUser", summary = "Remove User", description = "This api is use to remove user")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User removed"),
			@ApiResponse(responseCode = "400", description = "Bad Request")})
	@DeleteMapping("/deleteUser")
	public ResponseEntity<Void> deleteUser(@RequestParam("userId") Integer userId) {
		return userServiceInterface.deleteUser(userId);
	}

	@Operation(operationId = "findUserById", summary = "Fetch User", description = "This api is use to fetch user by id")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User fetched"),
			@ApiResponse(responseCode = "404", description = "User Not Found")})
	@GetMapping("/findUserById")
	public ResponseEntity<User> findUserById(@RequestParam("userId") Integer userId) {
		return userServiceInterface.findUserById(userId);
	}

	@Operation(operationId = "findAllUser", summary = "Fetch User", description = "This api is use to fetch all user")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User fetched")})
	@GetMapping("/findAllUser")
	public ResponseEntity<List<User>> findAllUser() {
		return userServiceInterface.findAllUser();
	}

	@Operation(operationId = "findUserByName", summary = "Fetch User", description = "This api is use to fetch user by name")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User fetched")})
	@GetMapping("/findUserByName")
	public ResponseEntity<List<User>> findUserByName(@RequestParam("userName") String userName) {
		return userServiceInterface.findUserByName(userName);
	}

	@Operation(operationId = "findUserByPhoneNumber", summary = "Fetch User", description = "This api is use to fetch user by phone number")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User fetched"),
			@ApiResponse(responseCode = "404", description = "User Not Found")})
	@GetMapping("/findUserByPhoneNumber")
	public ResponseEntity<User> findUserByPhoneNumber(@RequestParam("phoneNumber") long phoneNumber) {
		return userServiceInterface.findUserByPhoneNumber(phoneNumber);
	}

	@Operation(operationId = "findUserByEmail", summary = "Fetch User", description = "This api is use to fetch user by email")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User fetched"),
			@ApiResponse(responseCode = "404", description = "User Not Found")})
	@GetMapping("/findUserByEmail")
	public ResponseEntity<User> findUserByEmail(@RequestParam("email") String email) {
		return userServiceInterface.findUserByEmail(email);
	}

	@Operation(operationId = "addAddressToUser", summary = "Add address", description = "This api is use to add address to user")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Address added"),
			@ApiResponse(responseCode = "400", description = "Bad Request")})
	@PutMapping("/addAddressToUser")
	public ResponseEntity<Void> addAddressToUser(@RequestParam("addressId") Integer addressId,
			@RequestParam("userId") Integer userId) {
		return userServiceInterface.addAddressToUser(addressId, userId);
	}

}
