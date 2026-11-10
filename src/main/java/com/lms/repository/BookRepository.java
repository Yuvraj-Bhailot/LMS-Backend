package com.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	Optional<Book> findByTitle(String title);
	List<Book> findByAuthor(String author);
	List<Book> findByCategory(String category);
	List<Book> findByCopiesGreaterThan(int copies);
	
}
