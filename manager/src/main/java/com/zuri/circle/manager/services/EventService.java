package com.zuri.circle.manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoWriteException;
import com.zuri.circle.manager.exceptions.AddVolunteerToEvent_Exception;
import com.zuri.circle.manager.exceptions.AddVolunteer_Exception;
import com.zuri.circle.manager.exceptions.RegisterEvent_Exception;
import com.zuri.circle.manager.models.Events;
import com.zuri.circle.manager.models.User;
import com.zuri.circle.manager.models.Volunteer;
import com.zuri.circle.manager.repo.EventRepo;

@Service
public class EventService {

	@Autowired
	EventRepo eventrepo;
	
	public void registerEvent(Events event) throws RegisterEvent_Exception {
		if(event != null) {
			try {
				eventrepo.insert(event);
			}catch(MongoWriteException e) {
				throw new RegisterEvent_Exception("Error while writing the object to database." + e.getMessage());
			}
		}else {
			throw new RegisterEvent_Exception("The Volunteer object is null");
		}
		
	}
	
	public void AddVolunteerToEvent(Events event, Volunteer volunteer) throws AddVolunteerToEvent_Exception {
		try {
			List<Volunteer> listOfVolunteer = event.getVolunteers();
			listOfVolunteer.add(volunteer);
			eventrepo.save(event);
		}catch(MongoWriteException e) {
			throw new AddVolunteerToEvent_Exception("Error while writing the object to database." + e.getMessage());
		}
	}

}
