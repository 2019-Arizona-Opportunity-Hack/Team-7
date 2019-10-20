package com.zuri.circle.manager.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zuri.circle.manager.models.NeedHelp;

public interface NeedHelpRepo extends MongoRepository<NeedHelp, String> {
	
	public Optional<NeedHelp> findById(String id);
	//public Optional<NeedHelp> findByEmail(String email);
	

}
