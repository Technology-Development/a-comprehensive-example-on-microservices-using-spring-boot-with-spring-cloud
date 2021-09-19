/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.boarding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.airport.boarding.response.BoardingResponse;
import com.airport.boarding.service.BoardingService;

@RestController
public class BoardingController {

Logger logger = LoggerFactory.getLogger(BoardingController.class);
	
	@Autowired
	BoardingService boardingService;
	
	/*
	 * API to find a boarding details of a booking by booking id
	 * 
	 * @param bookingId is the booking id
	 * 
	 * @return BoardingResponse (id, checkinId, securityId, bookingId)
	 */
	@GetMapping("/api/v1/boarding/find-by-booking-id/{bookingId}")
	public ResponseEntity<BoardingResponse> findBoardingByBookingId(@PathVariable Long bookingId) {
		BoardingResponse boardingResponse = boardingService.findBoardingByBookingId(bookingId);
		return new ResponseEntity<BoardingResponse>(boardingResponse, HttpStatus.OK);
	}
}

