package com.kirvel.phonevalidator.extractor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kirvel.phonevalidator.exception.NotValidPhoneNumberFormatException;
import com.kirvel.phonevalidator.repository.PhoneCodeRepository;
import com.kirvel.phonevalidator.validator.PhoneValidator;

class CountryCodeExtractorTest {
	private static final String ERROR_MESSAGE = "Not valid phone number format";
	private static final String COUNTRY_CODE = "375";
	private static final String VALID_PHONE_NUMBER = "375293456723";

	@Mock
	private PhoneValidator phoneValidator;
	@Mock
	private PhoneCodeRepository phoneCodeRepository;
	private CountryCodeExtractor countryCodeExtractor;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		when(phoneValidator.isValidPhoneNumber(VALID_PHONE_NUMBER)).thenReturn(true);

		countryCodeExtractor = new CountryCodeExtractorImpl(phoneValidator, phoneCodeRepository);
	}

	@Test
	@DisplayName("should return correct country code")
	void isValidPhoneNumberTest() {
		when(phoneCodeRepository.isExistCodeNumber(COUNTRY_CODE)).thenReturn(1);

		assertEquals(COUNTRY_CODE, countryCodeExtractor.extractCountryCode(VALID_PHONE_NUMBER));
	}

	@ParameterizedTest
	@MethodSource("provideRandomSybmols")
	@DisplayName("should throw exception for not valid phone format")
	void testError(String phoneNumber) {
		NotValidPhoneNumberFormatException exception = assertThrows(NotValidPhoneNumberFormatException.class, () -> countryCodeExtractor.extractCountryCode(phoneNumber));

		assertEquals(ERROR_MESSAGE, exception.getMessage());
	}

	@ParameterizedTest
	@MethodSource("providePhoneNumbersWithDifferentLenghts")
	@DisplayName("should throw exception for not valid phone format")
	void testLengthError(String phoneNumber) {
		NotValidPhoneNumberFormatException exception = assertThrows(NotValidPhoneNumberFormatException.class, () -> countryCodeExtractor.extractCountryCode(phoneNumber));

		assertEquals(ERROR_MESSAGE, exception.getMessage());
	}

	@ParameterizedTest
	@MethodSource("provideNotValidPhoneNumbersFormat")
	@DisplayName("should throw exception for not valid phone format")
	void testNotValidPhoneNumbersFormatError(String phoneNumber) {
		NotValidPhoneNumberFormatException exception = assertThrows(NotValidPhoneNumberFormatException.class, () -> countryCodeExtractor.extractCountryCode(phoneNumber));

		assertEquals(ERROR_MESSAGE, exception.getMessage());
	}

	private static Stream<String> provideRandomSybmols() {
		return Stream.of("ghf", "0", "123", "%", "");
	}

	private static Stream<String> providePhoneNumbersWithDifferentLenghts() {
		return Stream.of("+37529456", "+3752947676767657656", "", "+3");
	}

	private static Stream<String> provideNotValidPhoneNumbersFormat() {
		return Stream.of("3752934456723", "3752934567t3", "3+75293456723");
	}

}
