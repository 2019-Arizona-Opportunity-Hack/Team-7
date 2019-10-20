package com.zuri.circle.manager.services;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoWriteException;
import com.zuri.circle.manager.exceptions.AddVolunteer_Exception;
import com.zuri.circle.manager.models.User;
import com.zuri.circle.manager.models.Volunteer;
import com.zuri.circle.manager.repo.VolunteerRepo;

@Service
public class VolunteerService {
	@Autowired
	VolunteerRepo volunteerRepo;
	
	public void addVolunteer(User user) throws AddVolunteer_Exception{
		if(user != null) {
			try {
				Volunteer volunteer = new Volunteer(null, user, user.getEmail());
				volunteerRepo.insert(volunteer);
			}catch(MongoWriteException e) {
				throw new AddVolunteer_Exception("Error while writing the object to database." + e.getMessage());
			}
		}else {
			throw new AddVolunteer_Exception("The Volunteer object is null");
		}
	}

}
