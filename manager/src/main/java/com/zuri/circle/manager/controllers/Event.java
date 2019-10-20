package com.zuri.circle.manager.controllers;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zuri.circle.manager.exceptions.AddVolunteerToEvent_Exception;
import com.zuri.circle.manager.exceptions.RegisterEvent_Exception;
import com.zuri.circle.manager.models.Events;
import com.zuri.circle.manager.models.Response;
import com.zuri.circle.manager.models.Volunteer;
import com.zuri.circle.manager.services.EventService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Event {
	
	@Autowired
	private EventService eventService;
	
	public static Logger logger = LogManager.getLogger(Register.class);
   
	@RequestMapping(method = RequestMethod.POST, value = "/event")
	public @ResponseBody ResponseEntity<Response> createEvent(@RequestBody Events event) throws RegisterEvent_Exception {

		logger.info("Entering the Register Event Method", event);
		try {
		eventService.registerEvent(event);
			
			
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, null),
					HttpStatus.OK);
		}catch (RegisterEvent_Exception e) {
			return new ResponseEntity<Response>(
					new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), true, e.getErrorMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/event/add/{operation}")
	public @ResponseBody ResponseEntity<Response> AddVolunteerToEvent(@PathVariable String operation ) throws AddVolunteerToEvent_Exception {
		try {
			String[] arr = operation.split("&");
			eventService.AddVolunteerToEvent(arr[0], arr[1]);
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, null),
					HttpStatus.OK);
		}catch (AddVolunteerToEvent_Exception e) {
			return new ResponseEntity<Response>(
					new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), true, e.getErrorMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
