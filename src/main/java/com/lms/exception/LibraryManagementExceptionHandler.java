package com.lms.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lms.util.ApiError;

@ControllerAdvice
public class LibraryManagementExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiError> handleBadRequestException(BadRequestException exception) {
		ApiError error = new ApiError();
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());
		List<String> details = new ArrayList<String>();
		details.add("Something Went Wrong");
		details.add("Wrong parameters passed");
		details.add("Missing parameters");
		error.setDetails(details);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ApiError> handleAddressNotFoundException(AddressNotFoundException exception) {
		ApiError error = new ApiError();
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());
		List<String> details = new ArrayList<String>();
		details.add("Address Not Found");
		error.setDetails(details);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ApiError> handleBookNotFoundException(BookNotFoundException exception) {
		ApiError error = new ApiError();
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());
		List<String> details = new ArrayList<String>();
		details.add("Book Not Found");
		error.setDetails(details);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(LibraryNotFoundException.class)
	public ResponseEntity<ApiError> handleLibraryNotFoundException(LibraryNotFoundException exception) {
		ApiError error = new ApiError();
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());
		List<String> details = new ArrayList<String>();
		details.add("Library Not Found");
		error.setDetails(details);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException exception) {
		ApiError error = new ApiError();
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());
		List<String> details = new ArrayList<String>();
		details.add("User Not Found");
		error.setDetails(details);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
}
