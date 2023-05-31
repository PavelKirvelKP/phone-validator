package com.kirvel.phonevalidator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kirvel.phonevalidator.extractor.CountryCodeExtractor;
import com.kirvel.phonevalidator.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {
	private final CountryRepository countryRepository;
	private final CountryCodeExtractor countryCodeExtractor;

	public CountryServiceImpl(CountryRepository countryRepository, CountryCodeExtractor countryCodeExtractor) {
		this.countryRepository = countryRepository;
		this.countryCodeExtractor = countryCodeExtractor;
	}

	@Override
	public List<String> getCountyByPhoneNumber(String phoneNumber) {
		String codeNumber = countryCodeExtractor.extractCountryCode(phoneNumber);

		return countryRepository.getCountyNamesByPhoneCode(codeNumber);
	}
}
