/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airport.flight.request.Flight;
import com.airport.flight.response.FlightResponse;
import com.airport.flight.service.FlightService;

@RestController
public class FlightController {

	Logger logger = LoggerFactory.getLogger(FlightController.class);
	
	@Autowired
	FlightService flightService;
	
	/* API to add new flight into the DB 
	 * 
	 * @param Flight (id, vendor, source, destination, departureDate, departureTime, pilotId(FK)) 
	 * It is input flight details which will be saved into the DB.
	 * 
	 * @return FlightResponse (number, vendor, source, destination, departureDate, departureTime, PilotResponse(id, name, designation, experience))
	 * This output object FlightResponse also includes respective pilot details (PilotResponse)
	 */
	@PostMapping("/api/v1/flight")
	public ResponseEntity<FlightResponse> newFlight (@RequestBody Flight flight) {
		FlightResponse flightResponse = flightService.newFlight(flight);
		return new ResponseEntity<FlightResponse>(flightResponse, HttpStatus.CREATED);
	}
	
	/* API to fetch a flight from the DB 
	 * 
	 * @param id is the flight id
	 * 
	 * @return FlightResponse (number, vendor, source, destination, departureDate, departureTime, PilotResponse(id, name, designation, experience))
	 */
	@GetMapping("/api/v1/flight/{id}")
	public  ResponseEntity<FlightResponse> getFlightById (@PathVariable long id) {
		FlightResponse flightResponse = flightService.getFlightById(id);
		return new ResponseEntity<FlightResponse>(flightResponse, HttpStatus.OK);
	}
	
	/* API to update a flight from the DB 
	 * 
	 * @param id is the flight id
	 * 
	 * @return FlightResponse (number, vendor, source, destination, departureDate, departureTime, PilotResponse(id, name, designation, experience))
	 */
	@PutMapping("/api/v1/flight/{id}")
	public ResponseEntity<FlightResponse> updateFlightById(@PathVariable long id, @RequestBody Flight flight) {
		FlightResponse flightResponse = flightService.updateFlightById(id, flight);
		return new ResponseEntity<FlightResponse>(flightResponse, HttpStatus.CREATED);
	}
	
	/* API to delete a flight from the DB 
	 * 
	 * @param id is the flight id
	 * 
	 * @return FlightResponse (number, vendor, source, destination, departureDate, departureTime, PilotResponse(id, name, designation, experience))
	 */
	@DeleteMapping("/api/v1/flight/{id}")
	public ResponseEntity<Void> deleteFlightById(@PathVariable long id) {
		flightService.deleteFlightById(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	/* API to search a flight from the DB by source, destination and departureDate
	 * 
	 * @param id is the flight id
	 * 
	 * @return flightId is the id of the flight
	 */
	@GetMapping("/api/v1/flight/search")
	public  ResponseEntity<Map<String, Object>> searchFlightBySourceAndDestination (@RequestParam String source, @RequestParam String destination, @RequestParam String departureDate) {
		Long flightId = flightService.searchFlightBySourceDestinationAndDate(source, destination, departureDate);
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("flight id ", flightId);
		body.put("message ", "This flight is available for booking");
		
		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.FOUND);
	}
}

