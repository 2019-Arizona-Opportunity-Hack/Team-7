package com.zuri.circle.manager.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoWriteException;
import com.zuri.circle.manager.exceptions.*;
import com.zuri.circle.manager.models.NeedHelp;
import com.zuri.circle.manager.models.User;
import com.zuri.circle.manager.models.Volunteer;
import com.zuri.circle.manager.repo.NeedHelpRepo;

public class NeedHelpService {
	
	@Autowired
	NeedHelpRepo needHelpRepo;
	
	public void addNeedHelp(User user) throws AddNeedHelp_Exception{
		if(user != null) {
			try {
				NeedHelp needy = new NeedHelp(null, user);
				needHelpRepo.insert(needy);
			}catch(MongoWriteException e) {
				throw new AddNeedHelp_Exception("Error while writing the object to database." + e.getMessage());
			}
		}else {
			throw new AddNeedHelp_Exception("The NeedHelp object is null");
		}
	}
	
	

}
