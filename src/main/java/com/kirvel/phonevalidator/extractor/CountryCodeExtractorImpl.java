package com.kirvel.phonevalidator.extractor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.kirvel.phonevalidator.exception.NotValidPhoneNumberFormatException;
import com.kirvel.phonevalidator.repository.PhoneCodeRepository;
import com.kirvel.phonevalidator.validator.PhoneValidator;

@Component
public class CountryCodeExtractorImpl implements CountryCodeExtractor {
	private final PhoneValidator phoneValidator;
	private final PhoneCodeRepository phoneCodeRepository;


	public CountryCodeExtractorImpl(PhoneValidator phoneValidator, PhoneCodeRepository phoneCodeRepository) {
		this.phoneValidator = phoneValidator;
		this.phoneCodeRepository = phoneCodeRepository;
	}

	@Override
	public String extractCountryCode(String phoneNumber) {
		if (!phoneValidator.isValidPhoneNumber(phoneNumber)) {
			throw new NotValidPhoneNumberFormatException("Not valid phone number format");
		}

		String countryCode = phoneNumber.substring(0, 7);
		while (StringUtils.hasLength(countryCode)) {
			if (phoneCodeRepository.isExistCodeNumber(countryCode) != 0) {
				break;
			}
			countryCode = countryCode.substring(0, countryCode.length() - 1);
		}

		return countryCode;
	}

}