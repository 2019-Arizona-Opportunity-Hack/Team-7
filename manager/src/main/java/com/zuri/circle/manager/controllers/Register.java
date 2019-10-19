package com.zuri.circle.manager.controllers;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zuri.circle.manager.constants.ZuriConstants;
import com.zuri.circle.manager.models.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Register {
	public static Logger logger = LogManager.getLogger(Register.class);
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/register")
public @ResponseBody ResponseEntity<Response> register(@RequestBody User user) {
		
		logger.info("Entering the Register User Method", user);
	        return register(user);
	}	 

	public ResponseEntity<Response> registerUser(User user){
		
		String strUserType = user.getUserType();
		if(strUserType!=null && StringUtils.isNotBlank(strUserType) ) {
			
			if(strUserType.equalsIgnoreCase(ZuriConstants.USER_TYPE_NEED_HELP))
			{
				
			}
			if(strUserType.equalsIgnoreCase(ZuriConstants.USER_TYPE_DONOR))
			{
				
			}
			if(strUserType.equalsIgnoreCase(ZuriConstants.USER_TYPE_VOLUNTEER))
			{
				
			}
		}
		
		
		
		
		
	}
	
	
	
}
