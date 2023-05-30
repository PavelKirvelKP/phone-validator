package com.kirvel.phonevalidator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("wikipedia")
public class WikipediaPropertiesImpl implements WikipediaProperties {
	private String queryUrl;

	@Override
	public String getQueryUrl() {
		return queryUrl;
	}

	@Override
	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
}
