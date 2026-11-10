package com.lms.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer libraryId;
	private String libraryName;
	@Column(unique = true)
	private long phoneNumber;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany
	@JoinColumn(name = "library_id")
	private List<Book> books;
	
}
