package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;

public interface AddressServiceInterface {

	public ResponseEntity<Void> saveAddress(AddressDto addressDto);
	public ResponseEntity<Void> updateAddress(Integer addressId, AddressDto addressDto);
	public ResponseEntity<Void> deleteAddress(Integer addressId);
	public ResponseEntity<Address> findAddressById(Integer addressId);
	public ResponseEntity<List<Address>> findAllAddress();
	public ResponseEntity<List<Address>> findAddressByArea(String area);	
	public ResponseEntity<List<Address>> findAddressByCity(String city);	
	public ResponseEntity<List<Address>> findAddressByState(String state);	
	public ResponseEntity<List<Address>> findAddressByCountry(String country);	
	public ResponseEntity<List<Address>> findAddressByPincode(long pincode);	
	
}
