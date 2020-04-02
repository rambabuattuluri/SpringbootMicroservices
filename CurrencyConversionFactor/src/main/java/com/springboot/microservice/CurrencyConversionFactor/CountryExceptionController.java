package com.springboot.microservice.CurrencyConversionFactor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CountryExceptionController {

	   @ExceptionHandler(value = CountryNotFoundException.class)
	   public ResponseEntity<Object> exception(CountryNotFoundException exception) {
	      return new ResponseEntity<>("CountryCode Not Found", HttpStatus.NOT_FOUND);
	   }
}
