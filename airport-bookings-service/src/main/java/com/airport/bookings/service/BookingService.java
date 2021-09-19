/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.bookings.service;

import com.airport.bookings.request.Booking;
import com.airport.bookings.response.BookingResponse;

public interface BookingService {

	BookingResponse bookingTicket(Booking booking);

	BookingResponse getBookingByBookingId(long bookingId);

	BookingResponse findBookingByPNRNumber(String pnrNumber);
}

