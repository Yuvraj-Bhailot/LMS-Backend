package com.lms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private Integer userId;
	@NotNull
	@NotBlank
	private String userName;
	@Min(6000000000L)
	@Max(9999999999L)
	private Long phoneNumber;
	@Email
	private String email;
	
}
