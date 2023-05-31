package com.kirvel.phonevalidator.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kirvel.phonevalidator.exception.NotValidPhoneNumberFormatException;

@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler({NotValidPhoneNumberFormatException.class})
	public ResponseEntity<Object> handleNotValidPhoneNumberFormatException(NotValidPhoneNumberFormatException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
