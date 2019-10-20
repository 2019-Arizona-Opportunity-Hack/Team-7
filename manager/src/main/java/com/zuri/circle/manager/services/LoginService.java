package com.zuri.circle.manager.services;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoWriteException;
import com.zuri.circle.manager.exceptions.Login_Exception;
import com.zuri.circle.manager.models.Donor;
import com.zuri.circle.manager.repo.DonorRepo;

@Service
public class LoginService {
	@Autowired
	DonorRepo donorRepo;
	
	public Donor login(String email, String password) throws Login_Exception {
		if(email != null && StringUtils.isNotBlank(email)) {
			try {
				Donor donor = donorRepo.findByEmail(email);
				if(donor!=null) {
				if(donor.getPassword().equals(password))
					return donor;
				else
					return null;
				}else {
					throw new Login_Exception("User doesnot exist");
				}
				
				
			}catch(MongoWriteException e) {
				throw new Login_Exception("The User object is null");
			}
		}else {
			
			throw new Login_Exception("The User object is null");
		}
	}

}
