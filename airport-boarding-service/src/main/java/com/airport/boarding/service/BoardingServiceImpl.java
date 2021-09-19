/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.boarding.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.boarding.feignclients.BookingFeignClient;
import com.airport.boarding.feignclients.CheckinFeignClient;
import com.airport.boarding.feignclients.SecurityFeignClient;
import com.airport.boarding.response.BoardingResponse;

@Service
public class BoardingServiceImpl implements BoardingService {
	
	Logger logger = LoggerFactory.getLogger(BoardingServiceImpl.class);
	
	@Autowired
	BookingFeignClient bookingFeignClient;
	
	@Autowired
	CheckinFeignClient checkinFeignClient;
	
	@Autowired
	SecurityFeignClient securityFeignClient;

	@Override
	public BoardingResponse findBoardingByBookingId(Long bookingId) {
		logger.info("fetching complete boarding details by booking id " + bookingId);
		BoardingResponse boardingResponse = new BoardingResponse();
		boardingResponse.setBookingResponse(bookingFeignClient.getBookingByBookingId(bookingId).getBody());
		boardingResponse.setCheckinResponse(checkinFeignClient.findCheckinByBookingId(bookingId).getBody());
		boardingResponse.setSecurityResponse(securityFeignClient.findSecurityByBookingId(bookingId).getBody());
		return boardingResponse;
	}
}

