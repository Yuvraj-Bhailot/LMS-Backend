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

import com.lms.dto.LibraryDto;
import com.lms.entity.Library;
import com.lms.service.LibraryServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LibraryController {

	private final LibraryServiceInterface libraryServiceInterface;

	@Operation(operationId = "saveLibrary", summary = "Create Library", description = "This api is use to save library")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Library saved") })
	@PostMapping("/saveLibrary")
	public ResponseEntity<Void> saveLibrary(@Valid @RequestBody LibraryDto libraryDto) {
		return libraryServiceInterface.saveLibrary(libraryDto);
	}

	@Operation(operationId = "updateLibrary", summary = "Update Library", description = "This api is use to update existing library")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Library updated"),
			@ApiResponse(responseCode = "400", description = "Bad Request")})
	@PatchMapping("/updateLibrary")
	public ResponseEntity<Void> updateLibrary(@RequestParam("libraryId") Integer libraryId,
			@RequestBody LibraryDto libraryDto) {
		return libraryServiceInterface.updateLibrary(libraryId, libraryDto);
	}

	@Operation(operationId = "deleteLibrary", summary = "Remove Library", description = "This api is use to remove library")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Library removed"),
			@ApiResponse(responseCode = "400", description = "Bad Request")})
	@DeleteMapping("/deleteLibrary")
	public ResponseEntity<Void> deleteLibrary(@RequestParam("libraryId") Integer libraryId) {
		return libraryServiceInterface.deleteLibrary(libraryId);
	}

	@Operation(operationId = "findLibraryById", summary = "Fetch Library", description = "This api is use to fetch library by id")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "library fetched"),
			@ApiResponse(responseCode = "404", description = "Library Not Found")})
	@GetMapping("/findLibraryById")
	public ResponseEntity<Library> findLibraryById(@RequestParam("libraryId") Integer libraryId) {
		return libraryServiceInterface.findLibraryById(libraryId);
	}

	@Operation(operationId = "findAllLibrary", summary = "Fetch Library", description = "This api is use to fetch all library")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "library fetched")})
	@GetMapping("/findAllLibrary")
	public ResponseEntity<List<Library>> findAllLibrary() {
		return libraryServiceInterface.findAllLibrary();
	}

	@Operation(operationId = "findLibraryByName", summary = "Fetch Library", description = "This api is use to fetch library by name")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "library fetched")})
	@GetMapping("/findLibraryByName")
	public ResponseEntity<List<Library>> findLibraryByName(@RequestParam("libraryName") String libraryName) {
		return libraryServiceInterface.findLibraryByName(libraryName);
	}

	@Operation(operationId = "findLibraryByPhoneNumber", summary = "Fetch Library", description = "This api is use to fetch library by phone number")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "library fetched"),
			@ApiResponse(responseCode = "404", description = "Library Not Found")})
	@GetMapping("/findLibraryByPhoneNumber")
	public ResponseEntity<Library> findLibraryByPhoneNumber(@RequestParam("phoneNumber") long phoneNumber) {
		return libraryServiceInterface.findLibraryByPhoneNumber(phoneNumber);
	}

	@Operation(operationId = "addAddressToLibrary", summary = "Add address to library", description = "This api is use to add address to library")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Address Added"),
			@ApiResponse(responseCode = "400", description = "Bad request")})
	@PutMapping("/addAddressToLibrary")
	public ResponseEntity<Void> addAddressToLibrary(@RequestParam("addressId") Integer addressId,
			@RequestParam("libraryId") Integer libraryId) {
		return libraryServiceInterface.addAddressToLibrary(addressId, libraryId);
	}

	@Operation(operationId = "addBookToLibrary", summary = "Add book to library", description = "This api is use to add book to library")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book added"),
			@ApiResponse(responseCode = "400", description = "Bad request")})
	@PutMapping("/addBookToLibrary")
	public ResponseEntity<Void> addBookToLibrary(@RequestParam("bookId") Integer bookId,
			@RequestParam("libraryId") Integer libraryId) {
		return libraryServiceInterface.addBookToLibrary(bookId, libraryId);
	}

	@Operation(operationId = "removeBookFromLibrary", summary = "Remove book from library", description = "This a[i is use remove book from library")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book removed"),
			@ApiResponse(responseCode = "400", description = "Bad Request")})
	@PutMapping("/removeBookFromLibrary")
	public ResponseEntity<Void> removeBookFromLibrary(@RequestParam("bookId") Integer bookId,
			@RequestParam("libraryId") Integer libraryId) {
		return libraryServiceInterface.removeBookFromLibrary(bookId, libraryId);
	}

}
