package com.lms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.LibraryDto;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.exception.BadRequestException;
import com.lms.exception.LibraryNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.BookRepository;
import com.lms.repository.LibraryRepository;
import com.lms.service.LibraryServiceInterface;
import com.lms.util.LibraryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibraryService implements LibraryServiceInterface{

	private final LibraryMapper libraryMapper;
	private final LibraryRepository libraryRepository;	
	private final AddressRepository addressRepository;	
	private final BookRepository bookRepository;	
	
	@Override
	public ResponseEntity<Void> saveLibrary(LibraryDto libraryDto) {
		Library library = libraryMapper.convertLibraryDtoToEntity(libraryDto);
		libraryRepository.save(library);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Library Saved Successfully");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
	}
	
	@Override
	public ResponseEntity<Void> updateLibrary(Integer libraryId, LibraryDto libraryDto) {
		HttpHeaders headers = new HttpHeaders();
		if(libraryId != null) {
			Optional<Library> optional = libraryRepository.findById(libraryId);
			if (optional.isPresent()) {
				Library library = optional.get();
				if (libraryDto.getLibraryName()!=null) {
					library.setLibraryName(libraryDto.getLibraryName());
				}
				if (libraryDto.getPhoneNumber()!=null) {
					library.setPhoneNumber(libraryDto.getPhoneNumber());
				}
				libraryRepository.save(library);
				headers.add("message", "Library Updated Successfully");
				return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
			}
		}
		throw new BadRequestException();
	}

	@Override
	public ResponseEntity<Void> deleteLibrary(Integer libraryId) {
		HttpHeaders headers = new HttpHeaders();
		Optional<Library> optional = libraryRepository.findById(libraryId);
		if (optional.isPresent()) {
			Library library = optional.get();
			libraryRepository.delete(library);
			headers.add("message", "Library Deleted Successfully");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
		}
		throw new BadRequestException();
	}

	@Override
	public ResponseEntity<Library> findLibraryById(Integer libraryId) {
		HttpHeaders headers = new HttpHeaders();
		Optional<Library> optional = libraryRepository.findById(libraryId);
		if (optional.isPresent()) {
			Library library = optional.get();
			headers.add("message", "Library Fetched Successfully");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(library);
		}
		throw new LibraryNotFoundException();
	}

	@Override
	public ResponseEntity<List<Library>> findAllLibrary() {
		List<Library> libraryList = libraryRepository.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "All Library Fetched Successfully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(libraryList);
	}

	@Override
	public ResponseEntity<List<Library>> findLibraryByName(String libraryName) {
		List<Library> libraryList = libraryRepository.findByLibraryName(libraryName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Library Fetched Succesfully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(libraryList);
	}

	@Override
	public ResponseEntity<Library> findLibraryByPhoneNumber(long phoneNumber) {
		Optional<Library> optional = libraryRepository.findByPhoneNumber(phoneNumber);
		HttpHeaders headers = new HttpHeaders();
		if (optional.isPresent()) {
			Library library = optional.get();
			headers.add("message", "Library Fetched Succesfully");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(library);
		}
		throw new LibraryNotFoundException();
	}

	@Override
	public ResponseEntity<Void> addAddressToLibrary(Integer addressId, Integer libraryId) {
		Optional<Address> optionalAddress = addressRepository.findById(addressId);
		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);
		HttpHeaders headers = new HttpHeaders();
		if (optionalAddress.isPresent() && optionalLibrary.isPresent()) {
			Address address = optionalAddress.get();
			Library library = optionalLibrary.get();
			library.setAddress(address);
			libraryRepository.save(library);
			headers.add("message", "Address Set To Library");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
		}
		throw new BadRequestException();
	}

	@Override
	public ResponseEntity<Void> addBookToLibrary(Integer bookId, Integer libraryId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);
		HttpHeaders headers = new HttpHeaders();
		if (optionalBook.isPresent() && optionalLibrary.isPresent()) {
			Book book = optionalBook.get();
			Library library = optionalLibrary.get();
			
			List<Book> books = library.getBooks();
			if (books==null) {
				books = new ArrayList<Book>();
			}
			
			books.add(book);
			library.setBooks(books);
			libraryRepository.save(library);
			headers.add("message", "Book Added to library");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
		}
		throw new BadRequestException();
	}

	@Override
	public ResponseEntity<Void> removeBookFromLibrary(Integer bookId, Integer libraryId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);
		HttpHeaders headers = new HttpHeaders();
		if (optionalBook.isPresent() && optionalLibrary.isPresent()) {
			Book book = optionalBook.get();
			Library library = optionalLibrary.get();
			
			List<Book> books = library.getBooks();
			if (books.contains(book)) {
				books.remove(book);
				library.setBooks(books);
				libraryRepository.save(library);
				headers.add("message", "Book Removed From library");
				return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
			}
		}
		throw new BadRequestException();
	}

}
