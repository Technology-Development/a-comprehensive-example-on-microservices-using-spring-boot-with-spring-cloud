/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.bookings.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.bookings.email.BookingEmailService;
import com.airport.bookings.entity.BookingEntity;
import com.airport.bookings.feignclients.FlightFeignClient;
import com.airport.bookings.repository.BookingRepository;
import com.airport.bookings.request.Booking;
import com.airport.bookings.response.BookingResponse;
import com.airport.bookings.util.GeneratePNRNumber;

@Service
public class BookingServiceImpl implements BookingService {

	Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	BookingEmailService bookingEmailService;

	@Autowired
	GeneratePNRNumber generatePNRNumber;
	
	@Autowired
	FlightFeignClient flightFeignClient;

	@Override
	public BookingResponse bookingTicket(Booking booking) {
		logger.info("starting bookingTicket() service method");

		/* map booking input object to booking entity object automatically */
		BookingEntity bookingEntity = modelMapper.map(booking, BookingEntity.class);

		/* generate and save a random PNR number */
		bookingEntity.setPnrNumber(generatePNRNumber.gePNRNumber());

		bookingEntity = bookingRepository.save(bookingEntity);

		BookingResponse bookingResponse = new BookingResponse(bookingEntity);

		/* calling the airport-flight-service using feign client */
		bookingResponse.setFlightResponse(flightFeignClient.getFlightById(bookingEntity.getFlightNumber()).getBody());

		/* email the booking information to the passenger */
		bookingEmailService.sendEmail(bookingResponse.getEmail(), "Flight Booking Confirmed", bookingResponse);

		return bookingResponse;
	}

	@Override
	public BookingResponse getBookingByBookingId(long bookingId) {
		logger.info("starting getBookingByBookingId() service method");
		BookingEntity bookingEntity = bookingRepository.findById(bookingId).get();
		BookingResponse bookingResponse = new BookingResponse(bookingEntity);
		
		/* calling the airport-flight-service using feign client */
		bookingResponse.setFlightResponse(flightFeignClient.getFlightById(bookingEntity.getFlightNumber()).getBody());
		
		return bookingResponse;
	}

	@Override
	public BookingResponse findBookingByPNRNumber(String pnrNumber) {
		logger.info("starting findBookingByPNRNumber() service method");
		BookingEntity bookingEntity = bookingRepository.findByPNRNumber(pnrNumber);
		BookingResponse bookingResponse = new BookingResponse(bookingEntity);
		
		/* calling the airport-flight-service using feign client */
		bookingResponse.setFlightResponse(flightFeignClient.getFlightById(bookingEntity.getFlightNumber()).getBody());
		
		return bookingResponse;
	}
}
