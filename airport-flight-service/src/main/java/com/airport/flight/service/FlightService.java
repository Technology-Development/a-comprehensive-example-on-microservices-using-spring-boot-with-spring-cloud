/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.service;

import com.airport.flight.request.Flight;
import com.airport.flight.response.FlightResponse;

public interface FlightService {

	public FlightResponse newFlight(Flight flight);

	public FlightResponse getFlightById(long id);

	public FlightResponse updateFlightById(long id, Flight flightRequest);

	public void deleteFlightById(long id);

	public Long searchFlightBySourceDestinationAndDate(String source, String destination, String departureDate);

}

