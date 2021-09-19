/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.flight.circuitbreaker.PilotServiceCircuitBreaker;
import com.airport.flight.entity.FlightEntity;
import com.airport.flight.feignclients.PilotFeignClient;
import com.airport.flight.repository.FlightRepository;
import com.airport.flight.request.Flight;
import com.airport.flight.response.FlightResponse;

@Service
public class FlightServiceImpl implements FlightService{
	
	Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PilotFeignClient pilotFeignClient;
	
	@Autowired
	PilotServiceCircuitBreaker pilotServiceCircuitBreaker;
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public FlightResponse newFlight(Flight flight) {
		
		logger.info("starting createFlight() service method");
		
		/* map flight input object to flight entity object automatically */
		FlightEntity flightEntity = modelMapper.map(flight, FlightEntity.class);
		
		flightEntity = flightRepository.save(flightEntity);
		
		FlightResponse flightResponse = new FlightResponse(flightEntity);
		
		/* calling the airport-pilot-service using feign client */
		// flightResponse.setPilotResponse(pilotFeignClient.getPilotById(flightEntity.getPilotId()).getBody());
		
		/* calling airport-pilot-service using our defined circuit breaker for it as we think the service is down */
		flightResponse.setPilotResponse(pilotServiceCircuitBreaker.getPilotById(flightEntity.getPilotId()));
		
		return flightResponse;
	}

	@Override
	public FlightResponse getFlightById(long id) {
		
		logger.info("starting getFlightById() service method");
		
		FlightEntity flightEntity = flightRepository.findById(id).get();
		
		FlightResponse flightResponse = new FlightResponse(flightEntity);
		
		/* calling the airport-pilot-service using feign client */
		// flightResponse.setPilotResponse(pilotFeignClient.getPilotById(flightEntity.getPilotId()).getBody());
		
		/* calling airport-pilot-service using our defined circuit breaker for it as we think the service is down */
		flightResponse.setPilotResponse(pilotServiceCircuitBreaker.getPilotById(flightEntity.getPilotId()));
		
		return flightResponse;
	}

	@Override
	public FlightResponse updateFlightById(long id, Flight flight) {
		
		logger.info("updating flight " + id);
		
		FlightEntity flightEntity = flightRepository.findById(id).get();
		
		/* map flight input object to flight entity object automatically */
		flightEntity = modelMapper.map(flight, FlightEntity.class);
		
		flightEntity.setNumber(id);
		
		flightEntity = flightRepository.save(flightEntity);
		
		FlightResponse flightResponse = new FlightResponse(flightEntity);
		
		/* calling the airport-pilot-service using spring cloud feign client */
		// flightResponse.setPilotResponse(pilotFeignClient.getPilotById(flightEntity.getPilotId()).getBody());
		
		/* calling airport-pilot-service using our defined circuit breaker for it as we think the service is down */
		flightResponse.setPilotResponse(pilotServiceCircuitBreaker.getPilotById(flightEntity.getPilotId()));
		
		logger.info("updated flight " + id);

		return flightResponse;
	}

	@Override
	public void deleteFlightById(long id) {
		logger.info("deleting flight " + id);
		flightRepository.deleteById(id);
	}

	@Override
	public Long searchFlightBySourceDestinationAndDate(String source, String destination,
			String departureDate) {
		logger.info("searching flight by source, destination and departureDate");
		FlightEntity flightEntity = flightRepository.searchFlightBySourceDestinationAndDate(source, destination, departureDate);
		return flightEntity.getNumber();
	}

	
}
