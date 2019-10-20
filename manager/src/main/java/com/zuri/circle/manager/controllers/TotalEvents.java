package com.zuri.circle.manager.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zuri.circle.manager.models.DashBoard;
import com.zuri.circle.manager.models.Donations;
import com.zuri.circle.manager.models.Donor;
import com.zuri.circle.manager.models.Events;
import com.zuri.circle.manager.models.TopResults;
import com.zuri.circle.manager.models.TotalVolandRev;
import com.zuri.circle.manager.models.Volunteer;
import com.zuri.circle.manager.repo.DonationRepo;
import com.zuri.circle.manager.repo.DonorRepo;
import com.zuri.circle.manager.repo.EventRepo;
import com.zuri.circle.manager.repo.VolunteerRepo;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TotalEvents {

	public static Logger logger = LogManager.getLogger(Register.class);
	@Autowired
	EventRepo eventRepo;

	@Autowired
	VolunteerRepo volunteerRepo;

	@Autowired
	DonorRepo donorRepo;
	@Autowired
	DonationRepo donationRepo;

	@RequestMapping(method = RequestMethod.GET, value = "/events")
	public @ResponseBody ResponseEntity<DashBoard> dashBoard() {
		List<Events> list = eventRepo.findAll();
		Date today = new Date();
		List<Events> res = new ArrayList<Events>();
		if (list != null && !list.isEmpty()) {
			for (Events e : list) {
				if (e!=null && e.getEventDate()!=null && (e.getEventDate().after(today) || e.getEventDate().equals(today)))
					res.add(e);

			}
			DashBoard dash = new DashBoard(String.valueOf(list.size()), res);
			return new ResponseEntity<DashBoard>(dash, HttpStatus.OK);
		}
		return new ResponseEntity<DashBoard>(HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/revenue")
	public @ResponseBody ResponseEntity<TotalVolandRev> totalRev() {
		List<Volunteer> list = volunteerRepo.findAll();
		int totalVol = 0;
		if (list != null && !list.isEmpty())
			totalVol = list.size();
		String totalRev = null;
		List<Donor> donList = donorRepo.findAll();
		Long total = 0L;
		if (donList != null && !donList.isEmpty()) {

			for (Donor d : donList) {
				total += Long.parseLong(d.getTotalAmount());
			}
		}

		return new ResponseEntity<TotalVolandRev>(new TotalVolandRev(String.valueOf(totalVol), String.valueOf(total)),
				HttpStatus.OK);

	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/topResults")
	public @ResponseBody ResponseEntity<TopResults> topResults() {
		List<Donations> list = donationRepo.findAll();
	    Collections.sort(list, new Comparator<Donations>() {
	    	public int compare(Donations a, Donations b) 
	        { 
	    		if(a.getDonationDate().after(b.getDonationDate()))
	            return -1 ; 
	    		else
	    			return 1;
	        } 
		});
	    
	    List<Donor> res = donorRepo.findAll();
	    Collections.sort(res, new Comparator<Donor>() {
	    	public int compare(Donor a, Donor b) 
	        { 
	    		if(Integer.parseInt(a.getTotalAmount())<= Integer.parseInt(b.getTotalAmount()))
	            return 1 ; 
	    		else
	    			return -1;
	        } 
		});
		
	   
	    	return new ResponseEntity<TopResults>(new TopResults(list, res), HttpStatus.OK);
	    
	    
	   // return new ResponseEntity<TopResults>(new )
	    
	}

}
