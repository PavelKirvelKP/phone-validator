package com.kirvel.phonevalidator.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.kirvel.phonevalidator.exception.CountryNotFoundException;
import com.kirvel.phonevalidator.exception.NotValidPhoneNumberFormatException;
import com.kirvel.phonevalidator.service.CountryService;

@WebMvcTest
class CountryCodeExtractorImplControllerTest {
	private static final String PHONE_NUMBER = "+375293456543";
	private static final List<String> COUNTRIES = List.of("Some country", "Second country");
	private static final String ERROR_MESSAGE = "Not valid phone number";
	@MockBean
	private CountryService countryService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		when(countryService.getCountyByPhoneNumber(PHONE_NUMBER)).thenReturn(COUNTRIES);
	}

	@Test
	@DisplayName("should return correct country name and code status")
	void validateTest() throws Exception {
		mockMvc.perform(get(String.format("/countries?phone_number=%s", PHONE_NUMBER)))
				.andExpect(status().isOk())
				.andExpect(content().string("[\"Some country\",\"Second country\"]"));
	}

	@Test
	@DisplayName("should return correct error message for not valid phone number and code status")
	void errorNotValidPhoneNumberTest() throws Exception {
		when(countryService.getCountyByPhoneNumber(PHONE_NUMBER)).thenThrow(new NotValidPhoneNumberFormatException(ERROR_MESSAGE));

		mockMvc.perform(get(String.format("/countries?phone_number=%s", PHONE_NUMBER)))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(ERROR_MESSAGE));
	}

}
