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

import com.zuri.circle.manager.Request.LoginRequest;
import com.zuri.circle.manager.constants.ZuriConstants;
import com.zuri.circle.manager.exceptions.AddNeedHelp_Exception;
import com.zuri.circle.manager.exceptions.AddVolunteer_Exception;
import com.zuri.circle.manager.exceptions.Login_Exception;
import com.zuri.circle.manager.exceptions.ZuriException;
import com.zuri.circle.manager.models.Response;
import com.zuri.circle.manager.models.User;
import com.zuri.circle.manager.services.DonorService;
import com.zuri.circle.manager.services.LoginService;
import com.zuri.circle.manager.services.NeedHelpService;
import com.zuri.circle.manager.services.VolunteerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Register {
	public static Logger logger = LogManager.getLogger(Register.class);

	@Autowired
	private DonorService donorService;
	@Autowired
	private VolunteerService volunteerService;
	@Autowired
	private NeedHelpService needHelp;
	@Autowired
	private LoginService loginService;
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public @ResponseBody ResponseEntity<Response> register(@RequestBody User user) {

		logger.info("Entering the Register User Method", user);
		return registerUser(user);
	}

	public ResponseEntity<Response> registerUser(User user) {

		String strUserType = user.getUserType();
		if (strUserType != null && StringUtils.isNotBlank(strUserType)) {

			if (strUserType.equalsIgnoreCase(ZuriConstants.USER_TYPE_NEED_HELP)) {
				try {
					needHelp.addNeedHelp(user);
					return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, null),
							HttpStatus.OK);
				} catch (AddNeedHelp_Exception e) {
					return new ResponseEntity<Response>(
							new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), true, e.getErrorMessage()),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			if (strUserType.equalsIgnoreCase(ZuriConstants.USER_TYPE_DONOR)) {
				try {
					if (donorService.addUser(user))
						return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, null),
								HttpStatus.OK);
				} catch (ZuriException e) {
					return new ResponseEntity<Response>(
							new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), true, e.getErrorMessage()),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			if (strUserType.equalsIgnoreCase(ZuriConstants.USER_TYPE_VOLUNTEER)) {

				try {
					volunteerService.addVolunteer(user);
					
					return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, null),
							HttpStatus.OK);
				} catch (AddVolunteer_Exception e) {
					return new ResponseEntity<Response>(
							new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), true, e.getErrorMessage()),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}

			}
		}

		return null;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public @ResponseBody ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) throws Login_Exception{
		return null;
		
//		logger.info("Entering the Donation Method", loginRequest);
//		try {
//			if(loginService.login(loginRequest.getEmail(), loginRequest.getPassword())) {
//				return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, null),
//						HttpStatus.OK);
//			}
//			else {
//				return new ResponseEntity<Response>(new Response("Login failed", true, null),
//						HttpStatus.BAD_REQUEST);
//
//			}
//		}catch (Login_Exception e) {
//			return new ResponseEntity<Response>(
//					new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), true, e.getErrorMessage()),
//					HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		
	}
}
