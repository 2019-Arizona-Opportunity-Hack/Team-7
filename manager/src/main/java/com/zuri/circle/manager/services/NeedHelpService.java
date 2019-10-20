package com.zuri.circle.manager.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoWriteException;
import com.zuri.circle.manager.exceptions.AddNeedHelp_Exception;
import com.zuri.circle.manager.models.NeedHelp;
import com.zuri.circle.manager.models.User;
import com.zuri.circle.manager.repo.NeedHelpRepo;

@Service
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
