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

import com.lms.dto.BookDto;
import com.lms.entity.Book;
import com.lms.service.BookServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookServiceInterface bookServiceInterface;

	@Operation(operationId = "saveBook", summary = "Create Book", description = "This api is used to save book")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Book Saved") })
	@PostMapping("/saveBook")
	public ResponseEntity<Void> saveBook(@Valid @RequestBody BookDto bookDto) {
		return bookServiceInterface.saveBook(bookDto);
	}

	@Operation(operationId = "updateBook", summary = "Update Existing Book", description = "This api is use to update existing book")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book Updated"),
			@ApiResponse(responseCode = "400", description = "Bad Request")})
	@PatchMapping("/updateBook")
	public ResponseEntity<Void> updateBook(@RequestParam("bookId") Integer bookId, @RequestBody BookDto bookDto) {
		return bookServiceInterface.updateBook(bookId, bookDto);
	}

	@Operation(operationId = "deleteBook", summary = "Removed Book", description = "This api is use to remove book")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book Removed"),
			@ApiResponse(responseCode = "400", description = "Bad Request")})
	@DeleteMapping("/deleteBook")
	public ResponseEntity<Void> deleteBook(@RequestParam("bookId") Integer bookId) {
		return bookServiceInterface.deleteBook(bookId);
	}

	@Operation(operationId = "findBookById", summary = "Fetch Book", description = "This api is use to fetch book by id")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book Fetched"),
			@ApiResponse(responseCode = "404", description = "Book Not Found")})
	@GetMapping("/findBookById")
	public ResponseEntity<Book> findBookById(@RequestParam("bookId") Integer bookId) {
		return bookServiceInterface.findBookById(bookId);
	}

	@Operation(operationId = "findAllBook", summary = "Fetch book", description = "This api is use to fetch all book")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book Fetched")})
	@GetMapping("/findAllBook")
	public ResponseEntity<List<Book>> findAllBook() {
		return bookServiceInterface.findAllBook();
	}

	@Operation(operationId = "findBookByTitle", summary = "Fetch book", description = "This api is use to fetch book by title")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book Fetched"),
			@ApiResponse(responseCode = "404", description = "Book Not Found")})
	@GetMapping("/findBookByTitle")
	public ResponseEntity<Book> findBookByTitle(@RequestParam("title") String title) {
		return bookServiceInterface.findBookByTitle(title);
	}

	@Operation(operationId = "findBookByAuthor", summary = "Fetch book", description = "This api isuse to fetch book by author")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book Fetched")})
	@GetMapping("/findBookByAuthor")
	public ResponseEntity<List<Book>> findBookByAuthor(@RequestParam("author") String author) {
		return bookServiceInterface.findBookByAuthor(author);
	}

	@Operation(operationId = "findBookByCategory", summary = "Fetch book", description = "This api is use to fetch book by category")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book Fetched")})
	@GetMapping("/findBookByCategory")
	public ResponseEntity<List<Book>> findBookByCategory(@RequestParam("category") String category) {
		return bookServiceInterface.findBookByCategory(category);
	}

	@Operation(operationId = "findBookByGreaterThanCopies", summary = "Fetch book", description = "This api is use for fetch book which have copies more than given parameter")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book Fetched")})
	@GetMapping("/findBookByGreaterThanCopies")
	public ResponseEntity<List<Book>> findBookByGreaterThanCopies(@RequestParam("copies") int copies) {
		return bookServiceInterface.findBookByGreaterThanCopies(copies);
	}

	@Operation(operationId = "borrowBook", summary = "Borrow Book operation", description = "This api is use for borrow book operation")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Borrow Operation Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request")})
	@PutMapping("/borrowBook")
	public ResponseEntity<Void> borrowBook(@RequestParam("bookId") Integer bookId,
			@RequestParam("userId") Integer userId) {
		return bookServiceInterface.borrowBook(bookId, userId);
	}

	@Operation(operationId = "returnBook", summary = "Return Book Operation", description = "This api is use to perform return book operation")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Return Operation Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request")})
	@PutMapping("/returnBook")
	public ResponseEntity<Void> returnBook(@RequestParam("bookId") Integer bookId,
			@RequestParam("userId") Integer userId) {
		return bookServiceInterface.returnBook(bookId, userId);
	}

}
