/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.pilot.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/* Global Exception Handling */

@ControllerAdvice
public class PilotControllerAdvicer extends ResponseEntityExceptionHandler {

	/* Custom Exception Handler */
	@ExceptionHandler(PilotCustomException.class)
	public ResponseEntity<Map<String, Object>> handlePilotCustomException(PilotCustomException pilotCustomException) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", pilotCustomException.getErrorMessage());
		body.put("status", pilotCustomException.getErrorCode());

		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.BAD_REQUEST);
	}

	/*
	 * Other Exception Handler
	 * 
	 * Whatever exception we get (e.g. NoSuchElementException) on console we can
	 * create a handler for it like this
	 */
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Map<String, Object>> handleNoSuchElementException(NoSuchElementException noSuchElementException) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "No pilot with given id exists!");

		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.NOT_FOUND);
	}
	
	/*
	 * Other Exception Handler
	 * 
	 * Whatever exception we get (e.g. EmptyResultDataAccessException) on console we can
	 * create a handler for it like this
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Map<String, Object>> handleEmptyResultDataAccessException(EmptyResultDataAccessException emptyResultDataAccessException) {
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "No pilot with given id exists!");

		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.NOT_FOUND);
	}
}
