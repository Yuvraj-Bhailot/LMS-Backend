package com.lms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.exception.AddressNotFoundException;
import com.lms.exception.BadRequestException;
import com.lms.repository.AddressRepository;
import com.lms.service.AddressServiceInterface;
import com.lms.util.LibraryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService implements AddressServiceInterface{

	private final LibraryMapper libraryMapper;
	private final AddressRepository addressRepository;	
	
	@Override
	public ResponseEntity<Void> saveAddress(AddressDto addressDto) {
		Address address = libraryMapper.convertAddressDtoToEntity(addressDto);
		addressRepository.save(address);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Address Saved Successfully");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
	}
	
	@Override
	public ResponseEntity<Void> updateAddress(Integer addressId, AddressDto addressDto) {
		HttpHeaders headers = new HttpHeaders();
		if(addressId != null) {
			Optional<Address> optional = addressRepository.findById(addressId);
			if (optional.isPresent()) {
				Address address = optional.get();
				if (addressDto.getHouseNumber()!=null) {
					address.setHouseNumber(addressDto.getHouseNumber());
				}
				if (addressDto.getArea()!=null) {
					address.setArea(addressDto.getArea());
				}
				if (addressDto.getCity()!=null) {
					address.setCity(addressDto.getCity());
				}
				if (addressDto.getState()!=null) {
					address.setState(addressDto.getState());
				}
				if (addressDto.getCountry()!=null) {
					address.setCountry(addressDto.getCountry());
				}
				if (addressDto.getPincode()!=null) {
					address.setPincode(addressDto.getPincode());
				}
				addressRepository.save(address);
				headers.add("message", "Address Updated Successfully");
				return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
			}
		}
		throw new BadRequestException();
	}

	@Override
	public ResponseEntity<Void> deleteAddress(Integer addressId) {
		HttpHeaders headers = new HttpHeaders();
		Optional<Address> optional = addressRepository.findById(addressId);
		if (optional.isPresent()) {
			Address address = optional.get();
			addressRepository.delete(address);
			headers.add("message", "Address Deleted Successfully");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
		}
		throw new BadRequestException();
	}

	@Override
	public ResponseEntity<Address> findAddressById(Integer addressId) {
		HttpHeaders headers = new HttpHeaders();
		Optional<Address> optional = addressRepository.findById(addressId);
		if (optional.isPresent()) {
			Address address = optional.get();
			headers.add("message", "Address Fetched Successfully");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(address);
		}
		throw new AddressNotFoundException();
	}

	@Override
	public ResponseEntity<List<Address>> findAllAddress() {
		List<Address> addressList = addressRepository.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "All Address Fetched Successfully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(addressList);
	}

	@Override
	public ResponseEntity<List<Address>> findAddressByArea(String area) {
		List<Address> areaList = addressRepository.findByArea(area);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Address Fetched Successfully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(areaList);
	}

	@Override
	public ResponseEntity<List<Address>> findAddressByCity(String city) {
		List<Address> areaList = addressRepository.findByCity(city);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Address Fetched Successfully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(areaList);
	}

	@Override
	public ResponseEntity<List<Address>> findAddressByState(String state) {
		List<Address> areaList = addressRepository.findByState(state);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Address Fetched Successfully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(areaList);
	}

	@Override
	public ResponseEntity<List<Address>> findAddressByCountry(String country) {
		List<Address> areaList = addressRepository.findByCountry(country);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Address Fetched Successfully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(areaList);
	}

	@Override
	public ResponseEntity<List<Address>> findAddressByPincode(long pincode) {
		List<Address> areaList = addressRepository.findByPincode(pincode);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Address Fetched Successfully");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(areaList);
	}

}
