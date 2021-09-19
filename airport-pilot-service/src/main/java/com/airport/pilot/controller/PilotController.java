/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.pilot.controller;

import java.util.List;

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

import com.airport.pilot.request.Pilot;
import com.airport.pilot.response.PilotResponse;
import com.airport.pilot.service.PilotService;

@RestController
public class PilotController {

	@Autowired
	PilotService pilotService;

	/*
	 * API to save a new pilot into the DB
	 * 
	 * @param Pilot (name, designation, experience) 
	 * 
	 * @return PilotResponse (id, name, designation, experience) 
	 */
	@PostMapping("/api/v1/pilot")
	public ResponseEntity<PilotResponse> savePilot(@RequestBody Pilot pilot) {
		PilotResponse pilotResponse = pilotService.savePilot(pilot);
		return new ResponseEntity<PilotResponse>(pilotResponse, HttpStatus.CREATED);
	}

	/*
	 * API to get a pilot details by pilot id
	 * 
	 * @param id is the pilot id
	 * 
	 * @return PilotResponse (id, name, designation, experience) 
	 */
	@GetMapping("/api/v1/pilot/{id}")
	public ResponseEntity<PilotResponse> getPilotById(@PathVariable Long id) {
		PilotResponse pilotResponse = pilotService.getPilotById(id);
		return new ResponseEntity<PilotResponse>(pilotResponse, HttpStatus.OK);
	}
	
	/*
	 * API to update a pilot details by pilot id
	 * 
	 * @param Pilot is a pilot object
	 * @param id is the pilot id
	 * 
	 * @return PilotResponse (id, name, designation, experience)
	 */
	@PutMapping("/api/v1/pilot/{id}")
	public ResponseEntity<PilotResponse> updatePilotById(@RequestBody Pilot pilot, @PathVariable Long id) {
		PilotResponse pilotResponse = pilotService.updatePilotById(pilot, id);
		return new ResponseEntity<PilotResponse>(pilotResponse, HttpStatus.CREATED);
	}

	/*
	 * API to delete a pilot details by pilot id
	 * 
	 * @param Pilot is a pilot object
	 * @param id is the pilot id
	 * 
	 * @return PilotResponse (id, name, designation, experience)
	 */
	@DeleteMapping("/api/v1/pilot/{id}")
	public ResponseEntity<Void> deletePilotById(@PathVariable Long id) {
		pilotService.deletePilotById(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	/*
	 * API to find all pilots by designation
	 * 
	 * @param designation is the pilot designation
	 * 
	 * @return PilotResponse (id, name, designation, experience) 
	 */
	@GetMapping("/api/v1/pilot")
	public ResponseEntity<List<PilotResponse>> findPilotByDesignation(@RequestParam String designation) {
		List<PilotResponse> listPilotResponse = pilotService.findPilotByDesignation(designation);
		return new ResponseEntity<List<PilotResponse>>(listPilotResponse, HttpStatus.FOUND);
	}
}
