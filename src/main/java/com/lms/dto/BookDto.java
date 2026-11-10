package com.lms.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

	private Integer bookId;
	@NotNull
	@NotBlank
	private String title;
	@NotNull
	@NotBlank
	@Size(min = 3, max = 20)
	private String author;
	@NotNull
	@NotBlank
	private String category;
	@Min(1)
	private Integer copies;
	private LocalDateTime borrowTime;
	private LocalDateTime returnTime;
	
}
