package com.zuri.circle.manager.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.zuri.circle.manager.models.Events;
import com.zuri.circle.manager.models.User;
import com.zuri.circle.manager.models.Volunteer;

public class EmailAsyncService {

	public static Logger logger = LogManager.getLogger(EmailAsyncService.class);
	@Autowired
	EmailService emailNotific;
	@Async("mailSmsExecutor")
	public  CompletableFuture<Void> async(List<Volunteer> volunteers, Events event) {
	    try {
	    	
	    	if(volunteers!=null && !volunteers.isEmpty()) {
	    		int length = volunteers.size();
	    		User user = null;
	    		StringBuffer url =  new StringBuffer();
	    		
	    		for(Volunteer vol: volunteers) {
	    			user =  vol.getUser();
	        //String intermediate = sendSMS(user.getUserContactNo(),"You have successfully registered to Movierecommeder App");
	    	//sendSMS(user.getUserContactNo(), "OTP-"+sendOtp(user.getUserName()).toString());
	    			url.append("http://localhost/");
	    			url.append(event.getEventId());
	    			url.append("/");
	    			url.append(vol.getId());
	       emailNotific.sendMail(user, url.toString());
	       url.delete(0, length);
	    		}
	    		
	    	}
	    } catch (Throwable t) {
	        logger.debug(t.getMessage());
	    }
		return CompletableFuture.completedFuture(null);
	   
	}

}
