package com.lms.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.BookDto;
import com.lms.entity.Book;
import com.lms.entity.User;
import com.lms.exception.BadRequestException;
import com.lms.exception.BookNotFoundException;
import com.lms.repository.BookRepository;
import com.lms.repository.UserRepository;
import com.lms.service.BookServiceInterface;
import com.lms.util.LibraryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService implements BookServiceInterface{

	private final LibraryMapper libraryMapper;
	private final BookRepository bookRepository;	
	private final UserRepository userRepository;	
	
	@Override
	public ResponseEntity<Void> saveBook(BookDto bookDto) {
		Book book = libraryMapper.convertBookDtoToEntity(bookDto);
		bookRepository.save(book);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Book Saved Successfully");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
	}
	
	@Override
	public ResponseEntity<Void> updateBook(Integer bookId, BookDto bookDto) {
		HttpHeaders headers = new HttpHeaders();
		if(bookId != null) {
			Optional<Book> optional = bookRepository.findById(bookId);
			if (optional.isPresent()) {
				Book book = optional.get();
				if (bookDto.getTitle()!=null) {
					book.setTitle(bookDto.getTitle());
				}
				if (bookDto.getAuthor()!=null) {
					book.setAuthor(bookDto.getAuthor());
				}
				if (bookDto.getCategory()!=null) {
					book.setCategory(bookDto.getCategory());
				}
				if (bookDto.getCopies()!=null) {
					book.setCopies(bookDto.getCopies());
				}
				if (bookDto.getBorrowTime()!=null) {
					book.setBorrowTime(bookDto.getBorrowTime());
				}
				if (bookDto.getReturnTime()!=null) {
					book.setReturnTime(bookDto.getReturnTime());
				}
				bookRepository.save(book);
				headers.add("message", "Book Updated Successfully");
				return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
			}
		}
		throw new BadRequestException();
	}

	@Override
	public ResponseEntity<Void> deleteBook(Integer bookId) {
		HttpHeaders headers = new HttpHeaders();
		Optional<Book> optional = bookRepository.findById(bookId);
		if (optional.isPresent()) {
			Book book = optional.get();
			bookRepository.delete(book);
			headers.add("message", "Book Deleted Successfully");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
		}
		throw new BadRequestException();
	}

	@Override
	public ResponseEntity<Book> findBookById(Integer bookId) {
		HttpHeaders headers = new HttpHeaders();
		Optional<Book> optional = bookRepository.findById(bookId);
		if (optional.isPresent()) {
			Book book = optional.get();
			headers.add("message", "Book Fetched Successfully");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(book);
		}
		throw new BookNotFoundException();
	}

	@Override
	public ResponseEntity<List<Book>> findAllBook() {
		List<Book> bookList = bookRepository.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "All Book Fetched Successfully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bookList);
	}

	@Override
	public ResponseEntity<Book> findBookByTitle(String title) {
		Optional<Book> optional = bookRepository.findByTitle(title);
		HttpHeaders headers = new HttpHeaders();
		if (optional.isPresent()) {
			Book book = optional.get();
			headers.add("message", "Book Fetched SuccessFully");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(book);
		}
		throw new BookNotFoundException();
	}

	@Override
	public ResponseEntity<List<Book>> findBookByAuthor(String author) {
		List<Book> bookList = bookRepository.findByAuthor(author);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Book Fetched SuccessFully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bookList);
	}

	@Override
	public ResponseEntity<List<Book>> findBookByCategory(String category) {
		List<Book> bookList = bookRepository.findByCategory(category);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Book Fetched SuccessFully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bookList);
	}

	@Override
	public ResponseEntity<List<Book>> findBookByGreaterThanCopies(int copies) {
		List<Book> bookList = bookRepository.findByCopiesGreaterThan(copies);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Book Fetched SuccessFully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bookList);
	}

	
	@Override
	public ResponseEntity<Void> borrowBook(Integer bookId, Integer userId) {
		Optional<Book> optionalBook = bookRepository.findById(userId);
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalBook.isPresent() && optionalUser.isPresent() && optionalBook.get().getCopies()>0) {
			Book book = optionalBook.get();
			User user = optionalUser.get();
			
			List<User> users = book.getUser();
			
			if (users==null) {
				users = new ArrayList<User>();
			}
			users.add(user);
			book.setBorrowTime(LocalDateTime.now());
			book.setCopies(book.getCopies()-1);
			book.setUser(users);
			bookRepository.save(book);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		throw new BadRequestException();
	}


	@Override
	public ResponseEntity<Void> returnBook(Integer bookId, Integer userId) {
		Optional<Book> optionalBook = bookRepository.findById(userId);
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalBook.isPresent() && optionalUser.isPresent()) {
			Book book = optionalBook.get();
			User user = optionalUser.get();
			
			List<User> users = book.getUser();
			
			if (users.contains(user)) {
			book.setReturnTime(LocalDateTime.now());
			book.setCopies(book.getCopies()+1);
			bookRepository.save(book);
			return ResponseEntity.status(HttpStatus.OK).build();
			}
		}
		throw new BadRequestException();
	}
	
}
