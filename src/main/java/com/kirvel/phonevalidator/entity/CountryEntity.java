package com.kirvel.phonevalidator.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "CountryEntity")
@Table(name = "countries")
public class CountryEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private String country;
	@OneToMany(mappedBy = "countryEntity", cascade = CascadeType.ALL)
	private List<PhoneCodeEntity> phoneList;

	public CountryEntity(Integer id, String country, List<PhoneCodeEntity> phoneList) {
		this.id = id;
		this.country = country;
		this.phoneList = phoneList;
	}

	public CountryEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<PhoneCodeEntity> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<PhoneCodeEntity> phoneList) {
		this.phoneList = phoneList;
	}
}
