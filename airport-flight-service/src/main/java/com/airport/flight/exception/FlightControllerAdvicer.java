/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* Global Exception Handling */

@ControllerAdvice
public class FlightControllerAdvicer {

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Map<String, Object>> handleNullPointerException(NullPointerException nullPointerException) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "No flight is available on this route on the given date!");

		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Map<String, Object>> handleNoSuchElementException(NoSuchElementException noSuchElementException) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "No flight with given id exists!");

		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.NOT_FOUND);
	}
}

