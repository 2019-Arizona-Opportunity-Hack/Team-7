package com.zuri.circle.manager.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zuri.circle.manager.models.Donor;

public interface DonorRepo extends MongoRepository<Donor, String> {
	
	public Optional<Donor> findById(String id);
	public Optional<Donor> findByEmail(String email);
	
	

}
