package com.kirvel.phonevalidator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kirvel.phonevalidator.extractor.CountryCodeExtractor;
import com.kirvel.phonevalidator.repository.CountryRepository;

class CountryCodeExtractorImplServiceTest {
	private static final String COUNTRY_CODE = "375";
	private static final String VALID_PHONE_NUMBER = "+375293456723";
	private static final List<String> COUNTRIES = List.of("Some county name");
	private static final String NOT_VALID_PHONE_NUMBER = "375293456723";

	@Mock
	private CountryRepository countryRepository;
	@Mock
	private CountryCodeExtractor countryCodeExtractor;

	private CountryService countryService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);

		when(countryCodeExtractor.extractCountryCode(VALID_PHONE_NUMBER)).thenReturn(COUNTRY_CODE);
		when(countryRepository.getCountyNamesByPhoneCode(COUNTRY_CODE)).thenReturn(COUNTRIES);

		countryService = new CountryServiceImpl(countryRepository, countryCodeExtractor);
	}

	@Test
	@DisplayName("should return valid county name")
	void validateTest() {
		when(countryRepository.getCountyNamesByPhoneCode(VALID_PHONE_NUMBER)).thenReturn(COUNTRIES);

		List<String> counties = countryService.getCountyByPhoneNumber(VALID_PHONE_NUMBER);

		assertEquals(COUNTRIES, counties);
	}

	@Test
	@DisplayName("should return empty list")
	void emptyResultTest() {
		when(countryRepository.getCountyNamesByPhoneCode(COUNTRY_CODE)).thenReturn(Collections.emptyList());

		List<String> result = countryService.getCountyByPhoneNumber(NOT_VALID_PHONE_NUMBER);

		assertTrue(result.isEmpty());
	}

}
