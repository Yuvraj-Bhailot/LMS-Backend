package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	List<Address> findByArea(String area);
	List<Address> findByCity(String city);
	List<Address> findByState(String state);
	List<Address> findByCountry(String country);
	List<Address> findByPincode(long pincode);
	
}
