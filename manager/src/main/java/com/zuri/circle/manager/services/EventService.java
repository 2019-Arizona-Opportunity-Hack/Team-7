package com.zuri.circle.manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoWriteException;
import com.zuri.circle.manager.exceptions.AddVolunteerToEvent_Exception;
import com.zuri.circle.manager.exceptions.RegisterEvent_Exception;
import com.zuri.circle.manager.models.Events;
import com.zuri.circle.manager.models.Volunteer;
import com.zuri.circle.manager.repo.EventRepo;
import com.zuri.circle.manager.repo.VolunteerRepo;

@Service
public class EventService {

	@Autowired
	EventRepo eventrepo;
	@Autowired
	VolunteerRepo volrepo;
	
	@Autowired
	EmailAsyncService asyncService;
	
	public void registerEvent(Events event) throws RegisterEvent_Exception {
		if(event != null) {
			try {
				event =eventrepo.insert(event);
				asyncService.async(volrepo.findAll(), event);
			}catch(MongoWriteException e) {
				throw new RegisterEvent_Exception("Error while writing the object to database." + e.getMessage());
			}
		}else {
			throw new RegisterEvent_Exception("The event object is null");
		}		
	}
	
	public void AddVolunteerToEvent(String eventId, String volunteerId) throws AddVolunteerToEvent_Exception {
		try {
			Events event =  eventrepo.findByEventId(eventId);
			Optional<Volunteer> volunteer = volrepo.findById(volunteerId);
			if(volunteer.isPresent() && event!=null) {
				Volunteer  vol = volunteer.get();
				List<Volunteer> list =  event.getVolunteers();
				if(list!=null ) {
					list.add(vol);
					event.setVolunteers(list);
				}
				
			}
			
			
			
		
			eventrepo.save(event);
		}catch(MongoWriteException e) {
			throw new AddVolunteerToEvent_Exception("Error while writing the object to database." + e.getMessage());
		}
	}
}
