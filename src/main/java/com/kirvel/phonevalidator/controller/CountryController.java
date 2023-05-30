package com.kirvel.phonevalidator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/countries")
public interface CountryController {
	@GetMapping
	ResponseEntity<List<String>> getCountries(@RequestParam("phone_number") String phoneNumber);
}
