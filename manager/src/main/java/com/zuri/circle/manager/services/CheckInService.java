package com.zuri.circle.manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.zuri.circle.manager.models.CheckInEvent;
import com.zuri.circle.manager.models.Events;
import com.zuri.circle.manager.models.Volunteer;
import com.zuri.circle.manager.repo.EventRepo;
import com.zuri.circle.manager.repo.VolunteerRepo;

@Service
public class CheckInService {

	
	@Autowired
	private VolunteerRepo volunteerRepo;
	@Autowired
	private EventRepo eventRepo;
	
	public boolean handleCheckIn(CheckInEvent checkIn) {
		
	
		String strVolunteerId = checkIn.getVolunteerId();
		String strEventId =  checkIn.getEventId();
		
		if(strVolunteerId!=null && StringUtils.isNotBlank(strVolunteerId)
				&& strEventId!=null && StringUtils.isNotBlank(strEventId)) {
		    Optional<Volunteer> volunteer =  volunteerRepo.findById(strVolunteerId);
		    if(volunteer.isPresent())
		    {
		       List<Events> list =  volunteer.get().getListOfEvents();
		       list.add(eventRepo.findByEventId(strEventId));
		       volunteer.get().setListOfEvents(list);
		       volunteerRepo.save(volunteer.get());
		       return true;
		    }
		}
		
		
		return false;
	}

}
