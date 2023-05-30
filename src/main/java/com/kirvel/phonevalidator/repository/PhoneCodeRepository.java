package com.kirvel.phonevalidator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kirvel.phonevalidator.entity.PhoneCodeEntity;

@Repository
public interface PhoneCodeRepository extends JpaRepository<PhoneCodeEntity, Integer> {
	@Query("SELECT COUNT(*) FROM PhoneCodeEntity p WHERE p.codeNumber = :codeNumber")
	Integer isExistCodeNumber(@Param("codeNumber") String codeNumber);

}
