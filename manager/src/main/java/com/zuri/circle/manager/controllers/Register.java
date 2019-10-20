package com.zuri.circle.manager.controllers;

import org.apache.commons.lang.StringUtils;
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

import com.zuri.circle.manager.constants.ZuriConstants;
import com.zuri.circle.manager.exceptions.ZuriException;
import com.zuri.circle.manager.models.Response;
import com.zuri.circle.manager.models.User;
import com.zuri.circle.manager.services.DonorService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Register {
	public static Logger logger = LogManager.getLogger(Register.class);
	
	@Autowired
     private  DonorService donorService;	
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
				try {
					if(donorService.addUser(user))
						return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, null),HttpStatus.OK);
				} catch (ZuriException e) {
					return new ResponseEntity<Response>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), true, e.getErrorMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			if(strUserType.equalsIgnoreCase(ZuriConstants.USER_TYPE_VOLUNTEER))
			{
				
			}
		}
		
		return null;
		
		
		
	}
	
	
	
}
