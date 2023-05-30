package com.kirvel.phonevalidator.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "PhoneCodeEntity")
@Table(name = "phone_codes", indexes = @Index(name = "cn_index", columnList = "codeNumber"))
public class PhoneCodeEntity {
	@Id
	@GeneratedValue
	private Integer id;

	private String codeNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private CountryEntity countryEntity;

	public PhoneCodeEntity(Integer id, String codeNumber, CountryEntity countryEntity) {
		this.id = id;
		this.codeNumber = codeNumber;
		this.countryEntity = countryEntity;
	}

	public PhoneCodeEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	public CountryEntity getCountryEntity() {
		return countryEntity;
	}

	public void setCountryEntity(CountryEntity countryEntity) {
		this.countryEntity = countryEntity;
	}
}
