package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.BookDto;
import com.lms.entity.Book;

public interface BookServiceInterface {

	public ResponseEntity<Void> saveBook(BookDto bookDto);
	public ResponseEntity<Void> updateBook(Integer bookId, BookDto bookDto);
	public ResponseEntity<Void> deleteBook(Integer bookId);
	public ResponseEntity<Book> findBookById(Integer bookId);
	public ResponseEntity<List<Book>> findAllBook();
	public ResponseEntity<Book> findBookByTitle(String title);
	public ResponseEntity<List<Book>> findBookByAuthor(String author);
	public ResponseEntity<List<Book>> findBookByCategory(String category);
	public ResponseEntity<List<Book>> findBookByGreaterThanCopies(int copies);
	public ResponseEntity<Void> borrowBook(Integer bookId, Integer userId);
	public ResponseEntity<Void> returnBook(Integer bookId, Integer userId);
	
}
