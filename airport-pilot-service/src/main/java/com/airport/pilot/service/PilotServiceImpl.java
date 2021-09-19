/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.pilot.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.pilot.entity.PilotEntity;
import com.airport.pilot.exception.PilotCustomException;
import com.airport.pilot.repository.PilotRepository;
import com.airport.pilot.request.Pilot;
import com.airport.pilot.response.PilotResponse;

@Service
public class PilotServiceImpl implements PilotService {

	Logger logger = LoggerFactory.getLogger(PilotServiceImpl.class);

	@Autowired
	PilotRepository pilotRepository;
	
	private static final ModelMapper modelMapper = new ModelMapper();

	@Override
	public PilotResponse savePilot(Pilot pilot) {

		/* throw a custom exception on a condition */
		/* we choose 6xx series for custom exception as 1xx, 2xx, 3xx, 4xx are reserved */
		if (pilot.getName().isEmpty() || pilot.getName().length() == 0)
			throw new PilotCustomException("600", "Please provide pilot name. Its blank");

		logger.info("adding new pilot " + pilot.getName());

		/* map pilot input object to pilot entity object automatically */
		PilotEntity pilotEntity = modelMapper.map(pilot, PilotEntity.class);

		pilotRepository.save(pilotEntity);

		logger.info("added new pilot " + pilot.getName());

		return new PilotResponse(pilotEntity);
	}

	@Override
	public PilotResponse getPilotById(Long id) {

		logger.info("fetching pilot " + id);

		PilotEntity pilotEntity = pilotRepository.findById(id).get();

		return new PilotResponse(pilotEntity);
	}

	@Override
	public void deletePilotById(Long id) {

		logger.info("deleting pilot " + id);

		pilotRepository.deleteById(id);
	}

	@Override
	public PilotResponse updatePilotById(Pilot pilot, Long id) {

		logger.info("updating pilot " + id);

		PilotEntity pilotEntity = pilotRepository.findById(id).get();

		/* map pilot input object to pilot entity object automatically */
		pilotEntity = modelMapper.map(pilot, PilotEntity.class);
		
		pilotEntity.setId(id);

		pilotRepository.save(pilotEntity);

		logger.info("updated pilot " + id);

		return new PilotResponse(pilotEntity);
	}

	@Override
	public List<PilotResponse> findPilotByDesignation(String designation) {
		
		logger.info("finding pilot by designation " + designation);

		List<PilotEntity> listPilotEntity = pilotRepository.findByDesignation(designation);

		List<PilotResponse> listPilotResponse = new ArrayList<>();
		
		for(PilotEntity pilotEntity : listPilotEntity) {
			PilotResponse pilotResponse = modelMapper.map(pilotEntity, PilotResponse.class);
			listPilotResponse.add(pilotResponse);
		}
		
		return listPilotResponse;
	}

}