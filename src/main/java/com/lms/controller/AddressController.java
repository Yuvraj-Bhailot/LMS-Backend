package com.lms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.service.AddressServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AddressController {

	private final AddressServiceInterface addressServiceInterface;
	
	@Operation(operationId = "CreateAddress", summary = "Use to save new address", description = "This api is use for saving address")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Address Saved")})
	@PostMapping("/saveAddress")
	public ResponseEntity<Void> saveAddress(@Valid @RequestBody AddressDto addressDto) {
		return addressServiceInterface.saveAddress(addressDto);
	}
	
	@Operation(operationId = "UpdateAddress", summary = "Update existing address", description = "This api is use to update existing address")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Address Updated"),
							@ApiResponse(responseCode = "400", description = "Bad Request")})
	@PatchMapping("/updateAddress")
	public ResponseEntity<Void> updateAddress(@RequestParam("addressId") Integer addressId, @RequestBody AddressDto addressDto) {
		return addressServiceInterface.updateAddress(addressId, addressDto);
	}
	
	@Operation(operationId = "RemoveAddress", summary = "Remove existing address", description = "This api is use to remove address")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Address deleted"),
			@ApiResponse(responseCode = "400", description = "Bad Request")})
	@DeleteMapping("/deleteAddress")
	public ResponseEntity<Void> deleteAddress(@RequestParam("addressId") Integer addressId) {
		return addressServiceInterface.deleteAddress(addressId);
	}
	
	@Operation(operationId = "FetchAddressById", summary = "Fetch address", description = "This api is use to fetch address by id")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Address Found"),
			@ApiResponse(responseCode = "404", description = "Address Not Found For Given ID")})
	@GetMapping("/findAddressById")
	public ResponseEntity<Address> findAddressById(@RequestParam("addressId") Integer addressId) {
		return addressServiceInterface.findAddressById(addressId);
	}
	
	@Operation(operationId = "findAllAddress", summary = "Fetch address", description = "This api is use to fetch all address")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Address Found")})
	@GetMapping("/findAllAddress")
	public ResponseEntity<List<Address>> findAllAddress() {
		return addressServiceInterface.findAllAddress();
	}
	
	@Operation(operationId = "findAddressByArea", summary = "Fetch address", description = "This api is use to fetch address by area")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Address Found")})
	@GetMapping("/findAddressByArea")
	public ResponseEntity<List<Address>> findAddressByArea(@RequestParam("area") String area){
		return addressServiceInterface.findAddressByArea(area);
	}
	
	@Operation(operationId = "findAddressByCity", summary = "Fetch address", description = "This api is use to fetch address by city")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Address Found")})
	@GetMapping("/findAddressByCity")
	public ResponseEntity<List<Address>> findAddressByCity(@RequestParam("city") String city){
		return addressServiceInterface.findAddressByCity(city);
	}	
	
	@Operation(operationId = "findAddressByState", summary = "Fetch address", description = "This api is use to fetch address by state")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Address Found")})
	@GetMapping("/findAddressByState")
	public ResponseEntity<List<Address>> findAddressByState(@RequestParam("state") String state){
		return addressServiceInterface.findAddressByState(state);
	}	
	
	@Operation(operationId = "findAddressByCountry", summary = "Fetch address", description = "This api is use to fetch address by country")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Address Found")})
	@GetMapping("/findAddressByCountry")
	public ResponseEntity<List<Address>> findAddressByCountry(@RequestParam("country") String country){
		return addressServiceInterface.findAddressByCountry(country);
	}	
	
	@Operation(operationId = "findAddressByPincode", summary = "Fetch address", description = "This api is use to fetch address by pincode")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Address Found")})
	@GetMapping("/findAddressByPincode")
	public ResponseEntity<List<Address>> findAddressByPincode(@RequestParam("pincode") long pincode){
		return addressServiceInterface.findAddressByPincode(pincode);
	}
	
}
