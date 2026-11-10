package com.lms.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.lms.dto.AddressDto;
import com.lms.dto.BookDto;
import com.lms.dto.LibraryDto;
import com.lms.dto.UserDto;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.entity.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LibraryMapper {

	private final ModelMapper modelMapper;
	
	public Library convertLibraryDtoToEntity(LibraryDto libraryDto) {
		return modelMapper.map(libraryDto, Library.class);
	}
	
	public Book convertBookDtoToEntity(BookDto bookDto) {
		return modelMapper.map(bookDto, Book.class);
	}
	
	public Address convertAddressDtoToEntity(AddressDto addressDto) {
		return modelMapper.map(addressDto, Address.class);
	}
	
	public User convertUserDtoToEntity(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}
	
	public LibraryDto convertLibraryToDto(Library library) {
		return modelMapper.map(library, LibraryDto.class);
	}
	
	public BookDto convertBookToDto(Book book) {
		return modelMapper.map(book, BookDto.class);
	}
	
	public AddressDto convertAddressToDto(Address address) {
		return modelMapper.map(address, AddressDto.class);
	}
	
	public UserDto convertUserToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}
	
	
	
}
