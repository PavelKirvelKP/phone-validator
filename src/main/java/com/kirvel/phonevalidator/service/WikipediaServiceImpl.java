package com.kirvel.phonevalidator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.kirvel.phonevalidator.entity.CountryEntity;
import com.kirvel.phonevalidator.entity.PhoneCodeEntity;
import com.kirvel.phonevalidator.exception.ElementsNotFoundException;
import com.kirvel.phonevalidator.properties.WikipediaProperties;
import com.kirvel.phonevalidator.repository.CountryRepository;

@Service
public class WikipediaServiceImpl implements WikipediaService {
	private static final Logger logger = LoggerFactory.getLogger(WikipediaServiceImpl.class);
	private static final String ELEMENT_NOT_FOUND_MESSAGE = "Elements not found";
	private static final String TAG_TABLE = "table";
	private static final String TAG_TD = "td";
	private static final String TAG_TR = "tr";

	private final WikipediaProperties wikipediaProperties;

	private final CountryRepository countryRepository;

	public WikipediaServiceImpl(WikipediaProperties wikipediaProperties, CountryRepository countryRepository) {
		this.wikipediaProperties = wikipediaProperties;
		this.countryRepository = countryRepository;
	}

	@EventListener
	public void eventListener(ApplicationStartedEvent event) {
		loadData();
	}

	@Override
	public void loadData() {
		try {
			Document document = Jsoup.connect(wikipediaProperties.getQueryUrl()).get();
			List<Element> tables = document.select(TAG_TABLE);
			if (tables.isEmpty() || tables.get(1) == null) {
				throw new ElementsNotFoundException(ELEMENT_NOT_FOUND_MESSAGE);
			}
			Element element = tables.get(1);
			List<Element> trElements = element.select(TAG_TR);
			List<CountryEntity> countryEntities = new ArrayList<>();

			for (int i = 1; i < trElements.size(); i++) {
				String countryName = trElements.get(i).select(TAG_TD).get(0).text();
				String countryCode = trElements.get(i).select(TAG_TD).get(1).text();

				CountryEntity countryEntity = build(countryName, countryCode);
				countryEntities.add(countryEntity);
			}

			countryRepository.saveAll(countryEntities);
		} catch (Exception ex) {
			logger.warn("Could not load data from wikipedia page error message: {}", ex.getMessage());
		}

	}

	private CountryEntity build(String country, String countryCode) {
		CountryEntity countryEntity = new CountryEntity();
		List<PhoneCodeEntity> phoneCodeEntities = getCountyCodes(countryCode)
				.stream()
				.map(code -> {
					PhoneCodeEntity phoneCode = new PhoneCodeEntity();
					phoneCode.setCodeNumber(code);
					phoneCode.setCountryEntity(countryEntity);
					return phoneCode;
				}).toList();

		countryEntity.setCountry(country);
		countryEntity.setPhoneList(phoneCodeEntities);

		return countryEntity;
	}

	private List<String> getCountyCodes(String countryCode) {
		List<String> result = new ArrayList<>();
		Matcher matcher = Pattern.compile("\\d+")
				.matcher(countryCode);

		while (matcher.find()) {
			result.add(matcher.group());
		}

		return result;
	}

}
