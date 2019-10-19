package com.zuri.circle.manager.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import com.zuri.circle.manager.models.Volunteer;

public interface VolunteerRepo extends MongoRepository<Volunteer, String>{

	public Optional<Volunteer> findById(String id);
	public Optional<Volunteer> findByEmail(String email);
}
