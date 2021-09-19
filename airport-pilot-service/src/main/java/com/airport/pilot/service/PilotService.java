/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.pilot.service;

import java.util.List;

import com.airport.pilot.request.Pilot;
import com.airport.pilot.response.PilotResponse;

public interface PilotService {

	public PilotResponse savePilot(Pilot pilot);

	public PilotResponse getPilotById(Long id);

	public void deletePilotById(Long id);

	public PilotResponse updatePilotById(Pilot pilot, Long id);

	public List<PilotResponse> findPilotByDesignation(String designation);
}

