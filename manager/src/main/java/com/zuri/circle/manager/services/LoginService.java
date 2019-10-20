package com.zuri.circle.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoWriteException;
import com.zuri.circle.manager.exceptions.Login_Exception;
import com.zuri.circle.manager.models.Donor;
import com.zuri.circle.manager.repo.DonorRepo;

@Service
public class LoginService {
	@Autowired
	DonorRepo donarRepo;
	
	public boolean login(String email, String password) throws Login_Exception {
		if(email != null) {
			try {
				Donor donor = donarRepo.findByEmail(email);
				if(donor.getPassword() == password)
					return true;
				else
					return false;
				
				
			}catch(MongoWriteException e) {
				throw new Login_Exception("The User object is null");
			}
		}else {
			
			throw new Login_Exception("The User object is null");
		}
	}

}
