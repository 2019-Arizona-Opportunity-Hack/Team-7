package com.zuri.circle.manager.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zuri.circle.manager.Request.DonationRequest;
import com.zuri.circle.manager.exceptions.RegisterDonation_Exception;
import com.zuri.circle.manager.models.Response;
import com.zuri.circle.manager.services.DonationService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DonationController {
public static Logger logger = LogManager.getLogger(Register.class);
	@Autowired
	DonationService donationService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/donation")
	public @ResponseBody ResponseEntity<Response> donation(@RequestBody DonationRequest donationRequest) throws RegisterDonation_Exception {
		logger.info("Entering the Donation Method", donationRequest);
		try {
			
			donationService.registerdonation(donationRequest.getDonor(),donationRequest.getAmount());
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, null),
					HttpStatus.OK);
		}catch (RegisterDonation_Exception e) {
			return new ResponseEntity<Response>(
					new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), true, e.getErrorMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
