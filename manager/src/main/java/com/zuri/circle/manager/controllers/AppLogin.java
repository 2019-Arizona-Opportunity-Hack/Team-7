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
import com.zuri.circle.manager.exceptions.Login_Exception;
import com.zuri.circle.manager.models.Donor;
import com.zuri.circle.manager.models.Login;
import com.zuri.circle.manager.models.Response;
import com.zuri.circle.manager.services.LoginService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppLogin {
	public static Logger logger = LogManager.getLogger(AppLogin.class);
	@Autowired
	private static LoginService loginService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public Donor donation(@RequestBody Login login){
		
		
		try {
			Donor donor =loginService.login(login.getEmail(), login.getPassword());
			if(donor!=null) {
				return donor;
			}else {
				return  null;
			}
		} catch (Login_Exception e) {
			return null;
		}
	}
}
