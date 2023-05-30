package com.kirvel.phonevalidator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kirvel.phonevalidator.entity.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
	@Query("SELECT c.country FROM CountryEntity c JOIN FETCH PhoneCodeEntity p ON c.id = p.countryEntity.id WHERE p.codeNumber = :phoneCode")
	List<String> getCountyNamesByPhoneCode(@Param("phoneCode") String phoneCode);

}
