/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.circuitbreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airport.flight.feignclients.PilotFeignClient;
import com.airport.flight.response.PilotResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/* writing a circuit breaker for airport-pilot-service so if the airport-pilot-service is down we send a dummy response as its result */

@Component
public class PilotServiceCircuitBreaker {

Logger logger = LoggerFactory.getLogger(PilotServiceCircuitBreaker.class);
	
	long count = 1;
	
	@Autowired
	PilotFeignClient pilotFeignClient;

	@CircuitBreaker(name = "airportPilotService",
			fallbackMethod = "fallbackGetPilotByIdId")
	public PilotResponse getPilotById (long pilotId) {
		logger.info("count = " + count);
		count++;
		PilotResponse pilotResponse = 
				pilotFeignClient.getPilotById(pilotId).getBody();
		
		return pilotResponse;
	}
	
	/* sending a dummy response if airport-pilot-service is down */
	public PilotResponse fallbackGetPilotByIdId(long pilotId, Throwable th) {
		logger.error("Error = " + th);
		return new PilotResponse();
	}
}

