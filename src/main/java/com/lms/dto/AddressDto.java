package com.lms.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

	private Integer addressId;
	@Min(1)
	private Integer houseNumber;
	@NotNull
	@NotBlank
	private String area;
	@NotNull
	@NotBlank
	private String city;
	@NotNull
	@NotBlank
	private String state;
	@NotNull
	@NotBlank
	private String country;
	@Digits(integer = 6, fraction = 0)
	private Long pincode;
	
}
