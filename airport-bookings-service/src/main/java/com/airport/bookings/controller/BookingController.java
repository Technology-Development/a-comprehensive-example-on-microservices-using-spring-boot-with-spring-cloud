/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.bookings.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airport.bookings.request.Booking;
import com.airport.bookings.response.BookingResponse;
import com.airport.bookings.service.BookingService;

@RestController
public class BookingController {

	Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	BookingService bookingService;
	
	/* API to book a flight ticket
	 * 
	 * @param Booking (fullname, mobile, email, address, flightNumber(FK)) 
	 * It is input booking details which will be saved into the DB.
	 * 
	 * @return BookingResponse (id, mobile, email, address, FlightResponse object)
	 */
	@PostMapping("/api/v1/booking")
	public ResponseEntity<BookingResponse> bookingTicket (@RequestBody Booking booking) {
		BookingResponse bookingResponse = bookingService.bookingTicket(booking);
		return new ResponseEntity<BookingResponse>(bookingResponse, HttpStatus.CREATED);
	}
	
	/* API to fetch a booking details by bookingId 
	 * 
	 * @param bookingId is the booking id
	 * 
	 * @return BookingResponse (id, mobile, email, address, FlightResponse object)
	 */
	@GetMapping("/api/v1/booking/{bookingId}")
	public  ResponseEntity<BookingResponse> getBookingByBookingId(@PathVariable long bookingId) {
		BookingResponse bookingResponse = bookingService.getBookingByBookingId(bookingId);
		return new ResponseEntity<BookingResponse>(bookingResponse, HttpStatus.OK);
	}
	
	/* API to fetch a booking details by pnrNumber 
	 * 
	 * @param pnrNumber is the input PNR Number
	 * 
	 * @return BookingResponse (id, mobile, email, address, FlightResponse object)
	 */
	@GetMapping("/api/v1/booking/find-by-pnr")
	public  ResponseEntity<BookingResponse> findBookingByPNRNumber (@RequestParam String pnrNumber) {
		BookingResponse bookingResponse = bookingService.findBookingByPNRNumber(pnrNumber);
		return new ResponseEntity<BookingResponse>(bookingResponse, HttpStatus.OK);
	}
}

