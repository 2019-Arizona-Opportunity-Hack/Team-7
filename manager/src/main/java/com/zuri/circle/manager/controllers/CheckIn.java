package com.zuri.circle.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zuri.circle.manager.models.CheckInEvent;
import com.zuri.circle.manager.models.Response;
import com.zuri.circle.manager.services.CheckInService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CheckIn {
	
	@Autowired
	private CheckInService checkInService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/checkIn")
	public @ResponseBody ResponseEntity<Response> handleCheckIn(@RequestBody CheckInEvent checkIn){
		
		if(checkIn!=null) {
			if(checkInService.handleCheckIn(checkIn))
				return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, null),HttpStatus.ACCEPTED);
			else
				return new ResponseEntity<Response>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), true, null),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST.toString(), true, null),HttpStatus.BAD_REQUEST);
	}

}
