package com.kirvel.phonevalidator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.kirvel.phonevalidator.service.CountryService;

@RestController
public class CountryControllerImpl implements CountryController {
	private final CountryService countryService;

	public CountryControllerImpl(CountryService countryService) {
		this.countryService = countryService;
	}

	@Override
	public ResponseEntity<List<String>> getCountries(String phoneNumber) {
		return ResponseEntity.ok()
				.body(countryService.getCountyByPhoneNumber(phoneNumber));
	}

}
