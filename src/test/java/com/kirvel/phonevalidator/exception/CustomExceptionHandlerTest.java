package com.kirvel.phonevalidator.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kirvel.phonevalidator.exception.handler.CustomExceptionHandler;

class CustomExceptionHandlerTest {
	private static final String NOT_VALID_PHONE_NUMBER_ERROR_MESSAGE = "some error message";

	CustomExceptionHandler exceptionHandler;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);

		exceptionHandler = new CustomExceptionHandler();
	}

	@Test
	@DisplayName("handleNotValidPhoneNumberFormatException should fill error code and message correct")
	void testNotValidPhoneNumberException() {
		NotValidPhoneNumberFormatException countryNotFoundException = new NotValidPhoneNumberFormatException(NOT_VALID_PHONE_NUMBER_ERROR_MESSAGE);
		ResponseEntity<Object> response = exceptionHandler.handleNotValidPhoneNumberFormatException(countryNotFoundException);

		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(NOT_VALID_PHONE_NUMBER_ERROR_MESSAGE, Objects.requireNonNull(response.getBody()).toString());
	}
}