package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.LibraryDto;
import com.lms.entity.Library;

public interface LibraryServiceInterface {

	public ResponseEntity<Void> saveLibrary(LibraryDto libraryDto);
	public ResponseEntity<Void> updateLibrary(Integer libraryId, LibraryDto libraryDto);
	public ResponseEntity<Void> deleteLibrary(Integer libraryId);
	public ResponseEntity<Library> findLibraryById(Integer libraryId);
	public ResponseEntity<List<Library>> findAllLibrary();
	public ResponseEntity<List<Library>> findLibraryByName(String libraryName);
	public ResponseEntity<Library> findLibraryByPhoneNumber(long phoneNumber);
	public ResponseEntity<Void> addAddressToLibrary(Integer addressId, Integer libraryId);
	public ResponseEntity<Void> addBookToLibrary(Integer bookId, Integer libraryId);
	public ResponseEntity<Void> removeBookFromLibrary(Integer bookId, Integer libraryId);

}
