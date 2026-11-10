package com.lms.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	@Column(nullable = false, unique = true)
	private String title;
	@Column(nullable = false)
	private String author;
	@Column(nullable = false)
	private String category;
	private int copies;
	private LocalDateTime borrowTime;
	private LocalDateTime returnTime;
	
	@ManyToMany
	private List<User> user;
	
}
