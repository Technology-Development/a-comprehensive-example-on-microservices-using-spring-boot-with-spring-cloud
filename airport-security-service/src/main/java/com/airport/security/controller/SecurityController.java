/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airport.security.request.Security;
import com.airport.security.response.SecurityResponse;
import com.airport.security.service.SecurityService;

@RestController
public class SecurityController {

Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	@Autowired
	SecurityService securityService;
	
	/* API to add security check details of a passenger
	 * 
	 * @param Security (identityProof, covidReport, ctScan, status, bookingId) 
	 * 
	 * @return SecurityResponse (id, identityProof, covidReport, ctScan, status, bookingId)
	 */
	@PostMapping("/api/v1/security")
	public ResponseEntity<SecurityResponse> saveNewSecurityChecking(@RequestBody Security security) {
		SecurityResponse securityResponse = securityService.saveNewSecurityChecking(security);
		return new ResponseEntity<SecurityResponse>(securityResponse, HttpStatus.CREATED);
	}
	
	/*
	 * API to get a security details of a booking by security id
	 * 
	 * @param id is the security id
	 * 
	 * @return SecurityResponse (id, identityProof, covidReport, ctScan, status, bookingId)
	 */
	@GetMapping("/api/v1/security/{id}")
	public ResponseEntity<SecurityResponse> getSecurityById(@PathVariable Long id) {
		SecurityResponse securityResponse = securityService.getSecurityById(id);
		return new ResponseEntity<SecurityResponse>(securityResponse, HttpStatus.OK);
	}
	
	/*
	 * API to find a security details of a booking by booking id
	 * 
	 * @param id is the booking id
	 * 
	 * @return SecurityResponse (id, identityProof, covidReport, ctScan, status, bookingId)
	 */
	@GetMapping("/api/v1/security/find-by-booking-id/{bookingId}")
	public ResponseEntity<SecurityResponse> findSecurityByBookingId(@PathVariable Long bookingId) {
		SecurityResponse securityResponse = securityService.findSecurityByBookingId(bookingId);
		return new ResponseEntity<SecurityResponse>(securityResponse, HttpStatus.OK);
	}
}

