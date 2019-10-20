package com.zuri.circle.manager.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zuri.circle.manager.models.Donations;
import com.zuri.circle.manager.models.Donor;

public interface DonationRepo extends MongoRepository<Donations, String>{
	public Optional<Donor> findByDonationId(String id);

}
