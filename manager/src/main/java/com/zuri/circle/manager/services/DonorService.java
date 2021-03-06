package com.zuri.circle.manager.services;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.zuri.circle.manager.exceptions.ZuriException;
import com.zuri.circle.manager.models.Donor;
import com.zuri.circle.manager.models.User;
import com.zuri.circle.manager.repo.DonorRepo;

@Service
public class DonorService {
	
	@Autowired
	private DonorRepo donorRepo;
	@Autowired
	EmailAsyncService asyncService;
	public static Logger logger = LogManager.getLogger(DonorService.class);
	
	public boolean  addUser(User user) throws ZuriException {
		try {
		if(user!=null) {
			Donor donor =  new Donor(null,user, user.getEmail(), "0", new HashMap<String, String>(),user.getPassword());
			donor = donorRepo.insert(donor);
			asyncService.async2(user);
			if(donor.getId()!=null && StringUtils.isNotBlank(donor.getId()))
				return true;
		}
		}catch(Exception e) {
			throw new ZuriException(e.getMessage());
		}
		
		return false;
	}

	public Donor registerApp(User user) {
	
		Donor donor =  new Donor(null,user,user.getEmail(),"0",new HashMap<>(),user.getPassword());
		donor = donorRepo.insert(donor);
		asyncService.async2(user);
		
		return donor;
		
		
	}
	

}
