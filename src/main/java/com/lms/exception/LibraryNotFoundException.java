package com.lms.exception;

public class LibraryNotFoundException extends RuntimeException{

	public LibraryNotFoundException() {
		super();
	}
	
	public LibraryNotFoundException(String message) {
		super(message);
	}
	
}
