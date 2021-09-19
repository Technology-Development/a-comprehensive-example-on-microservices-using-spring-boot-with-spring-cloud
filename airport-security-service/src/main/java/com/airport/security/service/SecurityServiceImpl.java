/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.security.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.security.entity.SecurityEntity;
import com.airport.security.exception.SecurityCustomException;
import com.airport.security.repository.SecurityRepository;
import com.airport.security.request.Security;
import com.airport.security.response.SecurityResponse;

@Service
public class SecurityServiceImpl implements SecurityService {
	
	Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	SecurityRepository securityRepository;

	@Override
	public SecurityResponse saveNewSecurityChecking(Security security) {
		
		/* throw a custom exception on a condition */
		/* we choose 6xx series for custom exception as 1xx, 2xx, 3xx, 4xx are reserved */
		if (security.getCovidReport().equals("positive") || security.getCovidReport().equals("Not Available"))
			throw new SecurityCustomException("600", "You are not allowed! Either your covid report is +ve or you did not do covid testing.");

		logger.info("saving security check id " + security.getBookingId());

		/* map security input object to security entity object automatically */
		SecurityEntity securityEntity = modelMapper.map(security, SecurityEntity.class);

		securityRepository.save(securityEntity);

		logger.info("saved security check id " + security.getBookingId());

		return new SecurityResponse(securityEntity);
	}

	@Override
	public SecurityResponse getSecurityById(Long id) {
		logger.info("fetching security by security id " + id);
		SecurityEntity securityEntity = securityRepository.findById(id).get();
		return new SecurityResponse(securityEntity);
	}

	@Override
	public SecurityResponse findSecurityByBookingId(Long bookingId) {
		logger.info("fetching security by booking id " + bookingId);
		SecurityEntity securityEntity = securityRepository.findByBookingId(bookingId).get();
		return new SecurityResponse(securityEntity);
	}
}

