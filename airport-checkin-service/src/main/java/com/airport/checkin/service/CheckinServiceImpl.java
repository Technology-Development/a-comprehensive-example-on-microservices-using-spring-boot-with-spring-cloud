/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.checkin.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.checkin.entity.CheckinEntity;
import com.airport.checkin.repository.CheckinRepository;
import com.airport.checkin.request.Checkin;
import com.airport.checkin.response.CheckinResponse;

@Service
public class CheckinServiceImpl implements CheckinService {
	
	Logger logger = LoggerFactory.getLogger(CheckinServiceImpl.class);
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	CheckinRepository checkinRepository;

	@Override
	public CheckinResponse saveNewCheckin(Checkin checkin) {
		
		logger.info("saving check-in id " + checkin.getBookingId());

		/* map check-in input object to check-in entity object automatically */
		CheckinEntity checkinEntity = modelMapper.map(checkin, CheckinEntity.class);

		checkinRepository.save(checkinEntity);

		logger.info("saved check-in id " + checkin.getBookingId());

		return new CheckinResponse(checkinEntity);
	}

	@Override
	public CheckinResponse getCheckinById(Long id) {
		logger.info("fetching check-in by id " + id);
		CheckinEntity checkinEntity = checkinRepository.findById(id).get();
		return new CheckinResponse(checkinEntity);
	}

	@Override
	public CheckinResponse findCheckinByBookingId(Long bookingId) {
		logger.info("fetching check-in by booking id " + bookingId);
		CheckinEntity checkinEntity = checkinRepository.findByBookingId(bookingId).get();
		return new CheckinResponse(checkinEntity);
	}
}

