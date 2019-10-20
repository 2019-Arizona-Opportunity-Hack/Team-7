package com.zuri.circle.manager.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zuri.circle.manager.exceptions.RegisterEvent_Exception;
import com.zuri.circle.manager.models.Donor;
import com.zuri.circle.manager.models.Response;

@RestController
public class DonationController {
	public static Logger logger = LogManager.getLogger(Register.class);
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/donation")
	public @ResponseBody ResponseEntity<Response> donation(Donor donor, int amount) throws RegisterEvent_Exception {
		logger.info("Entering the Register Event Method", donor);
		try {
			donationService.registerEvent(donor);
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, null),
					HttpStatus.OK);
		}catch (RegisterEvent_Exception e) {
			return new ResponseEntity<Response>(
					new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), true, e.getErrorMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
