package com.kirvel.phonevalidator.validator;

import org.springframework.stereotype.Component;

@Component
public class PhoneValidatorImpl implements PhoneValidator {
	private static final String PHONE_REGEX = "^\\d{12}";

	@Override
	public boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber.matches(PHONE_REGEX);
	}
}
